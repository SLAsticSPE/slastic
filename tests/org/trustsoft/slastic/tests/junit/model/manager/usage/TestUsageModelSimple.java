package org.trustsoft.slastic.tests.junit.model.manager.usage;

import java.util.Map.Entry;
import java.util.TreeMap;

import junit.framework.Assert;
import junit.framework.TestCase;
import kieker.common.record.OperationExecutionRecord;

import org.apache.commons.lang.StringUtils;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.model.ModelEntityCreationUtils;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;
import de.cau.se.slastic.metamodel.usage.FrequencyDistribution;
import de.cau.se.slastic.metamodel.usage.OperationCallFrequency;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * In this test, we are manually filling a {@link UsageModel} without the use of
 * the {@link UsageModelManager}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestUsageModelSimple extends TestCase {
	/** Create {@link ModelManager} for empty {@link SystemModel} */
	private final ModelManager systemModelManager = new ModelManager();
	private final TypeRepositoryModelManager typeMgr = this.systemModelManager.getTypeRepositoryManager();
	private final ComponentAssemblyModelManager assemblyMgr = this.systemModelManager
			.getComponentAssemblyModelManager();

	/**
	 * The {@link UsageModelManager} is only used to retrieve the
	 * {@link UsageModel}.
	 */
	private final UsageModelManager usageModelManager = new UsageModelManager();
	private final UsageModel usageModel = this.usageModelManager.getModel();

	private final ExecutionRecordTransformationFilter execRecFilter =
			new ExecutionRecordTransformationFilter(this.systemModelManager,
					NameUtils.ABSTRACTION_MODE_CLASS);

	private volatile AssemblyComponent requiringAsmCompA;
	private volatile DeploymentComponentOperationExecution opExecAa;

	private volatile AssemblyComponent providingAsmCompB;
	private volatile DeploymentComponentOperationExecution opExecBb;
	private volatile Interface commonInterfaceB;

	private volatile AssemblyConnector asmConnector;

	/**
	 * Intermediate data structure capturing the distribution of calls from
	 * operation A.a(..) to Signature b(..).
	 * */
	private final TreeMap<Long, Long> frequencyMap = new TreeMap<Long, Long>();
	{
		this.frequencyMap.put(0l, 2l);
		// implicitly included: frequencyMap.put(1, 0);
		this.frequencyMap.put(2l, 1l);
		this.frequencyMap.put(3l, 2l);
		// implicitly included: frequencyMap.put(4, 0);
		// implicitly included: frequencyMap.put(5, 0);
		this.frequencyMap.put(6l, 1l);
	}

	/**
	 * Manually fills the {@link UsageModel} without using the
	 * {@link UsageModelManager}.
	 */
	public void testManuallyFillModel() {
		this.initModelAndVars();
		this.addOperationCallFrequencies();
		this.addCallingRelationship();
		this.addAssemblyConnectorCallFrequencies();
	}

	/**
	 * Initializes a {@link SystemModel} by creating (among other entities) the
	 * {@link AssemblyComponent}s {@link #requiringAsmCompA} and
	 * {@link #providingAsmCompB} properly connected via the
	 * {@link AssemblyConnector} {@link #asmConnector} with the common Interface
	 * {@link #commonInterfaceB}.
	 */
	private void initModelAndVars() {
		// Create asmCompB providing the interface commonInterfaceB
		this.opExecBb =
				this.fillSystemModelByOperationExecution("package.ComponentB",
						/* no required interface: */null,
						"opB", "void", new String[] { String.class.getName() });

		this.providingAsmCompB = this.opExecBb.getDeploymentComponent().getAssemblyComponent();
		this.commonInterfaceB = this.providingAsmCompB.getComponentType().getProvidedInterfaces().get(0);

		// Create asmCompA whose type requires asmCompB's type's providing
		// interface
		this.opExecAa =
				this.fillSystemModelByOperationExecution("package.ComponentA", this.commonInterfaceB,
						"opA", Long.class.getName(), new String[] { Boolean.class.getName() });
		this.requiringAsmCompA = this.opExecAa.getDeploymentComponent().getAssemblyComponent();

		// Connect the two components
		this.connect(this.requiringAsmCompA, this.providingAsmCompB, this.commonInterfaceB);
	}

	/**
	 * Adds {@link OperationCallFrequency}s for the {@link Operation}s of the
	 * two components {@link #requiringAsmCompA} and {@link #providingAsmCompB}.
	 */
	private void addOperationCallFrequencies() {
		int numCallsAa = 0;
		int numCallsBb = 0;
		for (final Entry<Long, Long> e : this.frequencyMap.entrySet()) {
			numCallsAa += e.getValue();
			numCallsBb += e.getValue() * e.getKey();
		}

		final OperationCallFrequency opCallFrequencyOpAa =
				UsageFactory.eINSTANCE.createOperationCallFrequency();
		opCallFrequencyOpAa.setOperation(this.opExecAa.getOperation());
		opCallFrequencyOpAa.setFrequency(numCallsAa);
		this.usageModel.getOperationCallFrequencies().add(opCallFrequencyOpAa);

		final OperationCallFrequency opCallFrequencyOpBb =
				UsageFactory.eINSTANCE.createOperationCallFrequency();
		opCallFrequencyOpBb.setOperation(this.opExecBb.getOperation());
		opCallFrequencyOpBb.setFrequency(numCallsBb);
		this.usageModel.getOperationCallFrequencies().add(opCallFrequencyOpBb);
	}

	/**
	 * Adds the relationship A.a(..) calls interface signature b(..) (for
	 * example implemented by B.b(..)) with the frequency distribution
	 * {@link #frequencyMap} to the {@link #usageModel}.
	 */
	private void addCallingRelationship() {
		final CallingRelationship cr =
				this.createCallingRelationship(
						/* calling operation: */this.opExecAa.getOperation(),
						/* called interface: */this.commonInterfaceB,
						/* called signature of interface: */this.opExecBb.getOperation().getSignature(),
						/* frequency distribution (internal representation) */this.frequencyMap);
		this.usageModel.getCallingRelationships().add(cr);
	}

	/**
	 * Adds frequencies for calls to the {@link Signature} b(..) of the
	 * {@link AssemblyConnector} {@link #asmConnector}.
	 */
	private void addAssemblyConnectorCallFrequencies() {
		int numCallsAa = 0;
		int numCallsBb = 0;
		for (final Entry<Long, Long> e : this.frequencyMap.entrySet()) {
			numCallsAa += e.getValue();
			numCallsBb += e.getValue() * e.getKey();
		}

		final AssemblyConnectorCallFrequency asmCallFreqAaToBb =
				UsageFactory.eINSTANCE.createAssemblyConnectorCallFrequency();
		asmCallFreqAaToBb.setAssemblyConnector(this.asmConnector);
		asmCallFreqAaToBb.setSignature(this.opExecBb.getOperation().getSignature());
		asmCallFreqAaToBb.setFrequency(numCallsBb);
		this.usageModel.getAssemblyConnectorCallFrequencies().add(asmCallFreqAaToBb);

		// TODO: Add frequency for system-provided connector
	}

	private final CallingRelationship createCallingRelationship(final Operation op, final Interface iface,
			final Signature signature,
			final TreeMap<Long, Long> frequencyDistribution) {
		final CallingRelationship cr = UsageFactory.eINSTANCE.createCallingRelationship();
		cr.setCallingOperation(op);
		cr.setCalledInterface(iface);
		// TODO: make sure that signature and operation's signature contained in
		// interface
		cr.setCalledSignature(signature);
		final FrequencyDistribution fd = UsageFactory.eINSTANCE.createFrequencyDistribution();

		fd.getValues().addAll(frequencyDistribution.keySet());
		Assert.assertEquals("Unexpected number of values in frequency distribution",
				frequencyDistribution.keySet().size(), fd.getValues().size());
		fd.getFrequencies().addAll(frequencyDistribution.values());
		Assert.assertEquals("Unexpected number of frequencies in frequency distribution",
				frequencyDistribution.values().size(), fd.getFrequencies().size());
		cr.setFrequencyDistribution(fd);

		return cr;
	}

	private DeploymentComponentOperationExecution fillSystemModelByOperationExecution(
			final String fqAssemblyComponentName, final Interface requiredInterface,
			final String opName, final String returnType, final String[] paramTypes) {
		/**
		 * Record used to setup the following model entities (excerpt of the
		 * relevant ones):
		 * <ul>
		 * <li>Interface <i>package.IComponentA__T</i> with a signature
		 * <code>opA(java.lang.String)</code></li>
		 * <li>ComponentType <i>package.ComponentA__T</i> providing interface
		 * <i>package.TComponentAT</i></li>
		 * <li>AssemblyComponent <i>package.ComponentA</i></li>
		 * </ul>
		 */
		final OperationExecutionRecord kiekerRecord =
				new OperationExecutionRecord();
		{
			kiekerRecord.className = fqAssemblyComponentName;
			kiekerRecord.eoi = 77;
			kiekerRecord.ess = 98;
			kiekerRecord.hostName = "theHostname";
			kiekerRecord.operationName =
					new StringBuilder(opName).append("(").append(StringUtils.join(paramTypes, ',')).append(')')
							.toString();
			kiekerRecord.sessionId = "ZUKGHGF435JJ";
			kiekerRecord.tin = 65656868l;
			kiekerRecord.tout = 9878787887l;
			kiekerRecord.traceId = 88878787877887l;
		}

		final DeploymentComponentOperationExecution slasticRecord =
				(DeploymentComponentOperationExecution) this.execRecFilter
						.transformExecutionRecord(kiekerRecord);

		final AssemblyComponent asmComp = slasticRecord.getDeploymentComponent().getAssemblyComponent();

		if (requiredInterface != null) {
			this.typeMgr.registerRequiredInterface(asmComp.getComponentType(), requiredInterface);
		}

		return slasticRecord;
	}

	private void connect(final AssemblyComponent requiringComponent, final AssemblyComponent providingComponent,
			final Interface iface) {
		this.asmConnector =
				ModelEntityCreationUtils.createAssemblyConnector(this.systemModelManager, "ConnectorT", iface);
		this.assemblyMgr.connect(this.asmConnector, requiringComponent, providingComponent);
	}
}
