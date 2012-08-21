package org.trustsoft.slastic.simulation.software.controller;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.trustsoft.slastic.simulation.DynamicSimulationModel;
import org.trustsoft.slastic.simulation.StopCondition;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.CPU;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.model.software.repository.ComponentTypeController;
import org.trustsoft.slastic.simulation.software.controller.controlflow.ControlFlowNode;
import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallEnterNode;
import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallReturnNode;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionNode;
import org.trustsoft.slastic.simulation.software.controller.exceptions.BranchException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.NoBranchProbabilitiesException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.NoSuchSeffException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.SumGreaterXException;
import org.trustsoft.slastic.simulation.util.Interval;

import com.google.inject.Injector;

import de.uka.ipd.sdq.pcm.repository.Parameter;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition;
import de.uka.ipd.sdq.pcm.seff.AbstractLoopAction;
import de.uka.ipd.sdq.pcm.seff.AcquireAction;
import de.uka.ipd.sdq.pcm.seff.BranchAction;
import de.uka.ipd.sdq.pcm.seff.CollectionIteratorAction;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.GuardedBranchTransition;
import de.uka.ipd.sdq.pcm.seff.InternalAction;
import de.uka.ipd.sdq.pcm.seff.LoopAction;
import de.uka.ipd.sdq.pcm.seff.ParametricResourceDemand;
import de.uka.ipd.sdq.pcm.seff.ProbabilisticBranchTransition;
import de.uka.ipd.sdq.pcm.seff.ReleaseAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.SetVariableAction;
import de.uka.ipd.sdq.pcm.seff.StartAction;
import de.uka.ipd.sdq.pcm.seff.StopAction;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CallHandler {
	private static final Log LOG = LogFactory.getLog(CallHandler.class);

	private static volatile CallHandler INSTANCE;

	private final Hashtable<BranchAction, List<Interval<ProbabilisticBranchTransition>>> probabilisticBranchIntervalCache = new Hashtable<BranchAction, List<Interval<ProbabilisticBranchTransition>>>();

	private final Hashtable<String, Stack<StackFrame>> stacks = new Hashtable<String, Stack<StackFrame>>();

	private final Hashtable<String, Integer> eoi = new Hashtable<String, Integer>();

	private final Hashtable<String, List<ControlFlowNode>> activeTraces = new Hashtable<String, List<ControlFlowNode>>();

	// TODO: Why marked unused and not removed?
	@SuppressWarnings("unused")
	private final Hashtable<String, ProgressingFlow> flows = new Hashtable<String, ProgressingFlow>();

	private final DynamicSimulationModel model;

	private volatile StopCondition stopCond;

	// TODO: Why marked unused and not removed?
	@SuppressWarnings("unused")
	private long ltime;

	private volatile boolean firstcall = true;

	public CallHandler(final DynamicSimulationModel dynamicSimulationModel) {
		INSTANCE = this;
		this.model = dynamicSimulationModel;
	}

	private volatile Injector injector;

	/**
	 * @return the injector
	 */
	public final Injector getInjector() {
		return this.injector;
	}

	/**
	 * @param injector
	 *            the injector to set
	 */
	public final void setInjector(final Injector injector) {
		this.injector = injector;
	}

	public final static CallHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * Generates a list of nodes (i.e. a path through the control flow graph)
	 * for a specific user and schedule the first node now.
	 * 
	 * FIXME: eval scheduling strategy (time!) <br />
	 * FIXME: component instead of asmcontext as entry!
	 * 
	 * @param service
	 *            the called service
	 * @param userId
	 *            the user or better traceId
	 * @param asmContext
	 *            the <code>AssemblyContext</code> that is called
	 * @return
	 * @throws NoSuchSeffException
	 * @throws BranchException
	 * @throws SumGreaterXException
	 */
	public final void call(String service, final String userId, final String componentName, final long time)
			throws NoSuchSeffException, BranchException, SumGreaterXException {
		if (this.firstcall) {
			this.firstcall = false;
			final SimTime starttime = new SimTime(time / (double) Constants.SIM_TIME_TO_MON_TIME);
			for (final Server s : ModelManager.getInstance().getHardwareController().getServers()) {
				if (s.isAllocated()) {
					for (final CPU cpu : s.getCpus()) {
						cpu.resumeMonitoringAt(starttime);
					}
				}
			}
		}
		if ((this.activeTraces.size() > 0) && Constants.SINGLE_TRACE) {
			return;
		}
		service = service.replaceAll("\\(.*\\)", "");

		final ResourceDemandingBehaviour rdseff = ComponentTypeController.getInstance().getSeffById(service);
		if (rdseff != null) {
			// generate control flow
			final String asmContext = ModelManager.getInstance().getAssemblyController().getASMContextBySystemService(service);
			final List<ControlFlowNode> nodes = new LinkedList<ControlFlowNode>();
			final Signature signature = ModelManager.getInstance().getAssemblyController().getSignatureByExternalServiceName(service);
			CallHandler.LOG.info("Creating call with service "
					+ service
					+ " -> "
					+ signature
					+ " "
					+ ModelManager.getInstance().getAssemblyController().getASMContextBySystemService(service)
					+ " for trace " + userId);
			final ExternalCallEnterNode entryCallNode = new ExternalCallEnterNode(signature, null, userId);
			this.injector.injectMembers(entryCallNode);
			nodes.add(entryCallNode);
			nodes.addAll(this.generateControlFlow(rdseff, userId, asmContext));
			nodes.add(new ExternalCallReturnNode(entryCallNode));
			// add control flow to active traces
			this.activeTraces.put(userId, nodes);
			// push initial stack frame
			final Stack<StackFrame> stack = new Stack<StackFrame>();
			final StackFrame frame = new StackFrame(userId, service, asmContext, null, time);
			stack.push(frame);
			this.stacks.put(userId, stack);
			// schedule
			nodes.get(0).schedule(SimTime.diff(new SimTime(time / (double) Constants.SIM_TIME_TO_MON_TIME), ModelManager.getInstance().getModel().currentTime()));
			this.ltime = System.nanoTime();
			return;
		} else {
			throw new NoSuchSeffException(service);
		}
	}

	/**
	 * Find <code>StartAction</code> of seff and walk through, generating a
	 * chain of <code>ControlFlowNode</code>s for a specific service call by a
	 * specific user.
	 * 
	 * @param rdseff
	 *            of the requested service
	 * @param userId
	 *            of the user who is requesting the service
	 * @param asmContext
	 * @throws NoBranchProbabilitiesException
	 *             if some probabilistic branch stuff fails
	 * @throws SumGreaterXException
	 *             if some probabilistic branch stuff fails
	 */
	private synchronized List<ControlFlowNode> generateControlFlow(
			final ResourceDemandingBehaviour rdseff, final String userId,
			final String asmContext) throws BranchException,
			SumGreaterXException {
		final List<AbstractAction> actions = rdseff.getSteps_Behaviour();
		final List<ControlFlowNode> ret = new LinkedList<ControlFlowNode>();
		AbstractAction next = null;
		final String asmContextCurrent = asmContext;
		// find the start and get the "entry point"
		for (final AbstractAction a : actions) {
			if (a instanceof StartAction) {
				next = a;
				break;
			}
		}
		// TODO get variable characterizations

		// traverse the action graph and generate a path from the start- to the
		// stop action
		while (!((next = next.getSuccessor_AbstractAction()) instanceof StopAction)) {
			if (next instanceof BranchAction) {
				final BranchAction ba = (BranchAction) next;
				final AbstractBranchTransition abt = this
						.handleProbilisticBranch(ba);
				ret.addAll(this.generateControlFlow(
						abt.getBranchBehaviour_BranchTransition(), userId,
						asmContextCurrent));

			} else if (next instanceof ExternalCallAction) {
				final ExternalCallAction eca = (ExternalCallAction) next;
				// mark entry point
				// TODO save time!
				final String calledContext = ModelManager
						.getInstance()
						.getAssemblyController()
						.asmContextForServiceCall(
								asmContextCurrent,
								eca.getCalledService_ExternalService()
										.getServiceName());
				CallHandler.LOG.info("Generating external call node for: "
						+ eca.getCalledService_ExternalService()
								.getServiceName() + " from asm context "
						+ asmContextCurrent + " to asm context "
						+ calledContext);
				final ExternalCallEnterNode ece = new ExternalCallEnterNode(
						eca.getCalledService_ExternalService(),
						asmContextCurrent, userId);
				this.injector.injectMembers(ece);
				ret.add(ece);
				// generate control flow for the called service recursively
				// final String componentByASMId = ModelManager.getInstance()
				// .getAssemblyCont()
				// .getComponentByASMId(ece.getASMCont());
				ret.addAll(this.generateControlFlow(ModelManager.getInstance()
						.getComponentTypeController().getSeffById(ece.getCalledService()),
						userId, ece.getASMContTo()));
				// mark return
				final ExternalCallReturnNode ecr = new ExternalCallReturnNode(
						ece);
				this.injector.injectMembers(ecr);
				ret.add(ecr);

				// final List<VariableUsage> inputParameter = eca
				// .getInputParameterUsages_ExternalCallAction();
			} else if (next instanceof InternalAction) {
				final InternalAction ia = (InternalAction) next;
				final List<ParametricResourceDemand> resourceDemands = ia
						.getResourceDemand_Action();
				final InternalActionNode currentIA = new InternalActionNode(
						ia.getId(), userId);
				for (final ParametricResourceDemand resDemand : resourceDemands) {
					final String requiredResource = ((InternalEObject) resDemand
							.getRequiredResource_ParametricResourceDemand())
							.eProxyURI().toString();
					final String demand = resDemand
							.getSpecification_ParametericResourceDemand()
							.getSpecification();
					currentIA.add(requiredResource, demand);
					CallHandler.LOG.info("Added demand: " + requiredResource + " "
							+ demand);
				}
				ret.add(currentIA);
			} else if (next instanceof AbstractLoopAction) {
				if (next instanceof LoopAction) {
					final LoopAction la = (LoopAction) next;
					CallHandler.LOG.info("Loop's iteration count is "
							+ la.getIterationCount_LoopAction()
									.getSpecification());
					final int max = // (Integer) EvaluationProxy.evaluate(
					Integer.parseInt(la.getIterationCount_LoopAction()
							.getSpecification().replaceAll("\\s", ""));
					for (int i = 0; i < max; i++) {
						final List<ControlFlowNode> body = this
								.generateControlFlow(
										la.getBodyBehaviour_Loop(), userId,
										asmContextCurrent);
						ret.addAll(body);
					}
					// , Integer.class, null);
					// TODO: eval loop body, add to list
					// FIXME Evaluation framework for stochastic expressions
				} else if (next instanceof CollectionIteratorAction) {
					final CollectionIteratorAction cia = (CollectionIteratorAction) next;
					final Parameter para = cia
							.getParameter_CollectionIteratorAction();
					para.getModifier__Parameter();
					// extract collection type and thus random variable, cache
					// it so the specification (report page 54) is met
				}

			} else if (next instanceof SetVariableAction) {
				@SuppressWarnings("unused")
				final SetVariableAction sva = (SetVariableAction) next;

			} else if (next instanceof AcquireAction) {
				final AcquireAction acq = (AcquireAction) next;
				acq.getPassiveresource_AcquireAction();
			} else if (next instanceof ReleaseAction) {
				final ReleaseAction ra = (ReleaseAction) next;
				ra.getPassiveResource_ReleaseAction();
			}
		}
		return ret;
	}

	private AbstractBranchTransition handleProbilisticBranch(
			final BranchAction ba) throws NoBranchProbabilitiesException,
			SumGreaterXException {
		// the random used to branch probabilistic branches
		final double randomResult = Math.random();
		// branch not in cache? cache it
		if (this.probabilisticBranchIntervalCache.get(ba) == null) {
			final List<AbstractBranchTransition> branches = ba
					.getBranches_Branch();
			final LinkedList<Interval<ProbabilisticBranchTransition>> probs = new LinkedList<Interval<ProbabilisticBranchTransition>>();
			double probSum = 0;
			for (final AbstractBranchTransition abt : branches) {
				if (abt instanceof GuardedBranchTransition) {
					// seems to be unsupported
					final GuardedBranchTransition gbt = (GuardedBranchTransition) abt;
					gbt.getBranchCondition_GuardedBranchTransition();
				} else if (abt instanceof ProbabilisticBranchTransition) {
					final double prob = ((ProbabilisticBranchTransition) abt)
							.getBranchProbability();
					final Interval<ProbabilisticBranchTransition> i = new Interval<ProbabilisticBranchTransition>();
					i.setAbt((ProbabilisticBranchTransition) abt);
					i.setLower(probSum);
					probSum += prob;
					i.setUpper(probSum);
					probs.add(i);
				}
			}
			if (probSum > 1.0) {
				throw new SumGreaterXException(ba.getEntityName());
			}
			if (probs.size() > 0) {
				this.probabilisticBranchIntervalCache.put(ba, probs);
			} else {
				throw new NoBranchProbabilitiesException(ba.getEntityName());
			}
		}
		for (final Interval<ProbabilisticBranchTransition> i : this.probabilisticBranchIntervalCache
				.get(ba)) {
			if ((randomResult >= i.getLower()) && (randomResult < i.getUpper())) {
				return i.getAbt();
			}
		}
		return null;
	}

	public final String getCurrentServer(final String traceId) {
		return this.stacks.get(traceId).peek().getServerId();
	}

	public final void pushContext(final String traceId,
			final StackFrame stackFrame) {
		final Stack<StackFrame> curStack = this.stacks.get(traceId);
		Integer eoi = this.eoi.get(traceId);
		if (eoi == null) {
			this.eoi.put(traceId, eoi = 0);
		}
		// int eoi = curStack.peek().getEoi();
		stackFrame.setEoi(eoi);
		this.eoi.put(traceId, eoi + 1);
		curStack.push(stackFrame);
	}

	public final StackFrame popContext(final String traceId) {
		return this.stacks.get(traceId).pop();
	}

	public final int getStackDepth(final String traceId) {
		return this.stacks.get(traceId).size() - 1;
	}

	public final void actionReturn(final String traceId) {
		final List<ControlFlowNode> nodes = this.activeTraces.get(traceId);
		if (nodes == null) {
			return;
		}
		if (nodes.size() > 1) {
			nodes.remove(0);
			final ControlFlowNode node = this.activeTraces.get(traceId).get(0);
			CallHandler.LOG.info("Attempting to schedule " + node.getClass());
			node.schedule(SimTime.NOW);
		} else {
			nodes.clear();
			this.activeTraces.remove(traceId);
			this.stacks.remove(traceId);
			this.eoi.remove(traceId);
			this.model.callReturns(traceId);
		}
		CallHandler.LOG.info(nodes.size());

		if (this.activeTraces.isEmpty()) {
			this.stopCond.setStopped(true);
			ModelManager.markEnd();
		}
	}

	public final void setStopCond(final StopCondition stopCond) {
		this.stopCond = stopCond;

	}

	public final void setTerminating(final boolean b) {}

	public ControlFlowNode getNextInTrace(final String traceId) {
		final List<ControlFlowNode> list = this.activeTraces.get(traceId);
		if (list.size() > 1) {
			return list.get(1);
		}
		return null;
	}

	public StackFrame getStackTop(final String traceId) {
		return this.stacks.get(traceId).peek();
	}

}
