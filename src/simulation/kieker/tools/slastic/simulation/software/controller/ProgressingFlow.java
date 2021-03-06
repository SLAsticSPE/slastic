/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.simulation.software.controller;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.InternalEObject;

import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.software.controller.cfframes.CFFrame;
import kieker.tools.slastic.simulation.software.controller.cfframes.CallFrame;
import kieker.tools.slastic.simulation.software.controller.cfframes.LoopFrame;
import kieker.tools.slastic.simulation.software.controller.controlflow.AbstractControlFlowEvent;
import kieker.tools.slastic.simulation.software.controller.controlflow.ExternalCallEnterEvent;
import kieker.tools.slastic.simulation.software.controller.controlflow.ExternalCallReturnEvent;
import kieker.tools.slastic.simulation.software.controller.controlflow.InternalActionEvent;
import kieker.tools.slastic.simulation.software.controller.exceptions.NoBranchProbabilitiesException;
import kieker.tools.slastic.simulation.software.controller.exceptions.SumGreaterXException;
import kieker.tools.slastic.simulation.software.controller.threading.CFCreationStatus;
import kieker.tools.slastic.simulation.util.Interval;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition;
import de.uka.ipd.sdq.pcm.seff.AbstractLoopAction;
import de.uka.ipd.sdq.pcm.seff.BranchAction;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.GuardedBranchTransition;
import de.uka.ipd.sdq.pcm.seff.InternalAction;
import de.uka.ipd.sdq.pcm.seff.LoopAction;
import de.uka.ipd.sdq.pcm.seff.ParametricResourceDemand;
import de.uka.ipd.sdq.pcm.seff.ProbabilisticBranchTransition;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.SetVariableAction;
import de.uka.ipd.sdq.pcm.seff.StartAction;
import de.uka.ipd.sdq.pcm.seff.StopAction;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: It seems that this class is not used at all ...
public class ProgressingFlow {
	private static final Log LOG = LogFactory.getLog(ProgressingFlow.class);

	public static final Hashtable<BranchAction, List<Interval<ProbabilisticBranchTransition>>> probabilisticBranchIntervalCache = new Hashtable<BranchAction, List<Interval<ProbabilisticBranchTransition>>>();

	private final Stack<CFFrame> stack = new Stack<CFFrame>();

	private final List<AbstractControlFlowEvent> nodes = new LinkedList<AbstractControlFlowEvent>();

	private final String userId;

	private volatile Exception exceptionStatus;

	private volatile CFCreationStatus status = CFCreationStatus.PAUSED;

	public ProgressingFlow(final ResourceDemandingSEFF seff,
			final String initialAsmContext, final String userId) {
		final CFFrame initialFrame = new CFFrame(seff,
				this.getStartAction(seff), initialAsmContext);
		this.stack.push(initialFrame);
		this.userId = userId;
	}

	private AbstractAction getStartAction(final ResourceDemandingBehaviour seff) {
		final List<AbstractAction> actions = seff.getSteps_Behaviour();
		// find the start and get the "entry point"
		for (final AbstractAction a : actions) {
			if (a instanceof StartAction) {
				return a;
			}
		}
		return null;
	}

	private CFCreationStatus nextEvent() {
		final CFFrame currentFrame = this.stack.peek();
		if (currentFrame == null) {
			return CFCreationStatus.DONE;
		}
		final AbstractAction action = currentFrame.getAction();
		if (action instanceof StartAction) {
			currentFrame.setAction(action.getSuccessor_AbstractAction());
		}
		if (action instanceof BranchAction) {
			final BranchAction ba = (BranchAction) action;
			AbstractBranchTransition abt;
			try {
				abt = this.handleProbilisticBranch(ba);
			} catch (final NoBranchProbabilitiesException e) {
				this.exceptionStatus = e;
				return CFCreationStatus.ERROR;
			} catch (final SumGreaterXException e) {
				this.exceptionStatus = e;
				return CFCreationStatus.ERROR;
			}

			this.stack.push(new CFFrame(abt
					.getBranchBehaviour_BranchTransition(), this
					.getStartAction(abt.getBranchBehaviour_BranchTransition()),
					currentFrame.getAsmContextCurrent()));

			return CFCreationStatus.PAUSED;

		} else if (action instanceof ExternalCallAction) {
			final ExternalCallAction eca = (ExternalCallAction) action;
			// mark entry point
			// TODO save time!
			final String calledContext = ModelManager
					.getInstance()
					.getAssemblyController()
					.getAssemblyContextForServiceCall(
							currentFrame.getAsmContextCurrent(),
							eca.getCalledService_ExternalService()
									.getServiceName());
			ProgressingFlow.LOG.info("Generating external call node for: "
					+ eca.getCalledService_ExternalService().getServiceName()
					+ " from asm context "
					+ currentFrame.getAsmContextCurrent() + " to asm context "
					+ calledContext);

			final ExternalCallEnterEvent ece = new ExternalCallEnterEvent(
					eca.getCalledService_ExternalService(),
					currentFrame.getAsmContextCurrent(), this.userId);

			this.nodes.add(ece);

			final CFFrame frame = new CallFrame(
					ModelManager.getInstance().getComponentTypeController()
							.getSeffById(ece.getCalledService()),
					this.getStartAction(ModelManager.getInstance()
							.getComponentTypeController().getSeffById(ece.getCalledService())),
					ece.getASMContTo(), ece);

			this.stack.push(frame);
			return CFCreationStatus.PAUSED;
			// mark return
		} else if (action instanceof StopAction) {
			final CFFrame frame = this.stack.pop();
			if (frame instanceof CallFrame) {
				final CallFrame cframe = (CallFrame) frame;
				this.nodes.add(new ExternalCallReturnEvent(cframe.getEce()));
			} else if (frame instanceof LoopFrame) {
				final LoopFrame lframe = (LoopFrame) frame;
				if (lframe.inc() == lframe.getIterations()) {
					this.stack.push(lframe);
				} else {
					lframe.setAction(this.getStartAction(lframe.getSeff()));
				}
			}
			return CFCreationStatus.PAUSED;
		} else if (action instanceof InternalAction) {
			final InternalAction ia = (InternalAction) action;
			final List<ParametricResourceDemand> resourceDemands = ia
					.getResourceDemand_Action();
			final InternalActionEvent currentIA = new InternalActionEvent(
					ia.getId(), this.userId);
			for (final ParametricResourceDemand resDemand : resourceDemands) {
				final String requiredResource = ((InternalEObject) resDemand
						.getRequiredResource_ParametricResourceDemand())
						.eProxyURI().toString();
				final String demand = resDemand
						.getSpecification_ParametericResourceDemand()
						.getSpecification();
				currentIA.add(requiredResource, demand);
				ProgressingFlow.LOG.info("Added demand: " + requiredResource
						+ " " + demand);
			}
			this.nodes.add(currentIA);
			return CFCreationStatus.PAUSED;
		} else if (action instanceof AbstractLoopAction) {
			if (action instanceof LoopAction) {
				final LoopAction la = (LoopAction) action;
				ProgressingFlow.LOG.info("Loop's iteration count is "
						+ la.getIterationCount_LoopAction().getSpecification());
				final int max = Integer.parseInt(la
						.getIterationCount_LoopAction().getSpecification()
						.replaceAll("\\s", ""));
				final LoopFrame lframe = new LoopFrame(
						la.getBodyBehaviour_Loop(), this.getStartAction(la
								.getBodyBehaviour_Loop()),
						currentFrame.getAsmContextCurrent(), max);
				this.stack.push(lframe);
				return CFCreationStatus.PAUSED;
				// FIXME Evaluation framework for stochastic expressions
			} else {
				return CFCreationStatus.UNSUPPORTED;
				/*
				 * else if (action instanceof CollectionIteratorAction) { final
				 * CollectionIteratorAction cia = (CollectionIteratorAction)
				 * next; final Parameter para = cia
				 * .getParameter_CollectionIteratorAction();
				 * para.getModifier__Parameter(); // extract collection type and
				 * thus random variable, cache // it so the specification
				 * (report page 54) is met }
				 */
			}

		} else if (currentFrame.getAction() instanceof SetVariableAction) {
			@SuppressWarnings("unused")
			final SetVariableAction sva = (SetVariableAction) action;
			return CFCreationStatus.UNSUPPORTED;
		} else {
			return CFCreationStatus.UNSUPPORTED;
		}
	}

	private AbstractBranchTransition handleProbilisticBranch(
			final BranchAction ba) throws NoBranchProbabilitiesException,
			SumGreaterXException {
		// the random used to branch probabilistic branches
		final double randomResult = Math.random();
		// branch not in cache? cache it
		if (ProgressingFlow.probabilisticBranchIntervalCache.get(ba) == null) {
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
				ProgressingFlow.probabilisticBranchIntervalCache.put(ba, probs);
			} else {
				throw new NoBranchProbabilitiesException(ba.getEntityName());
			}
		}
		{
			for (final Interval<ProbabilisticBranchTransition> i : ProgressingFlow.probabilisticBranchIntervalCache
					.get(ba)) {
				if ((randomResult >= i.getLower()) && (randomResult < i.getUpper())) {
					return i.getAbt();
				}
			}
		}
		return null;
	}

	public Exception getExceptionStatus() {
		return this.exceptionStatus;
	}

	public boolean next() {
		return (this.status = this.nextEvent()) == CFCreationStatus.PAUSED;
	}

	/**
	 * @return the nodes
	 */
	public final List<AbstractControlFlowEvent> getNodes() {
		return this.nodes;
	}

	/**
	 * @return the status
	 */
	public final CFCreationStatus getStatus() {
		return this.status;
	}

}
