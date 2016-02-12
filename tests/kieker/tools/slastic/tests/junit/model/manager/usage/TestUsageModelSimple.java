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

package kieker.tools.slastic.tests.junit.model.manager.usage;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.common.util.signature.ClassOperationSignaturePair;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.core.SystemModel;
import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.metamodel.usage.AssemblyComponentConnectorCallFrequency;
import kieker.tools.slastic.metamodel.usage.CallingRelationship;
import kieker.tools.slastic.metamodel.usage.FrequencyDistribution;
import kieker.tools.slastic.metamodel.usage.OperationCallFrequency;
import kieker.tools.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency;
import kieker.tools.slastic.metamodel.usage.UsageFactory;
import kieker.tools.slastic.metamodel.usage.UsageModel;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.usage.UsageModelManager;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import kieker.tools.slastic.tests.junit.model.ModelEntityCreationUtils;

/**
 * In this test, we are manually filling a {@link UsageModel} without the use of
 * the {@link UsageModelManager}.
 *
 * @author Andre van Hoorn
 *
 */
public class TestUsageModelSimple extends TestCase {
	/** Create {@link ModelManager} with empty {@link SystemModel} and {@link UsageModel} */
	private final ModelManager systemModelManager = new ModelManager();
	private final TypeRepositoryModelManager typeMgr = this.systemModelManager.getTypeRepositoryManager();
	private final ComponentAssemblyModelManager assemblyMgr = this.systemModelManager.getComponentAssemblyModelManager();

	/** Since we are modifying the {@link UsageModel} directly, we need to retrieve it. */
	private final UsageModel usageModel = this.systemModelManager.getUsageModelManager().getModel();

	private final ExecutionRecordTransformationFilter execRecFilter =
			new ExecutionRecordTransformationFilter(this.systemModelManager, NameUtils.ABSTRACTION_MODE_CLASS);

	private volatile AssemblyComponent requiringAsmCompA;
	private volatile DeploymentComponentOperationExecution opExecAa;
	private volatile Interface providedInterfaceA;

	private volatile AssemblyComponent providingAsmCompB;
	private volatile DeploymentComponentOperationExecution opExecBb;
	private volatile Interface commonInterfaceB;

	private volatile AssemblyComponentConnector asmConnector;

	private volatile SystemProvidedInterfaceDelegationConnector sysProvDelegationConnector;

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
	 * Manually fills the {@link UsageModel} without using the {@link UsageModelManager}.
	 *
	 * @throws IOException
	 */
	public void testManuallyFillModel() throws IOException {
		this.initModelAndVars();
		this.addOperationCallFrequencies();
		this.addCallingRelationship();
		this.addAssemblyConnectorCallFrequencies();
		this.saveModels();
	}

	/**
	 * Initializes a {@link SystemModel} by creating (among other entities) the {@link AssemblyComponent}s {@link #requiringAsmCompA} and {@link #providingAsmCompB}
	 * properly connected via the {@link AssemblyComponentConnector} {@link #asmConnector} with the common Interface {@link #commonInterfaceB}.
	 */
	private void initModelAndVars() {
		// Create asmCompB providing the interface commonInterfaceB
		this.opExecBb = this.fillSystemModelByOperationExecution("package.ComponentB",
				/* no required interface: */null,
				"opB", "void", new String[] { String.class.getName() });

		this.providingAsmCompB = this.opExecBb.getDeploymentComponent().getAssemblyComponent();
		this.commonInterfaceB = this.providingAsmCompB.getComponentType().getProvidedInterfaces().get(0);

		// Create asmCompA whose type requires asmCompB's type's providing
		// interface
		this.opExecAa = this.fillSystemModelByOperationExecution("package.ComponentA", this.commonInterfaceB,
				"opA", Long.class.getName(), new String[] { Boolean.class.getName() });
		this.requiringAsmCompA = this.opExecAa.getDeploymentComponent().getAssemblyComponent();
		this.providedInterfaceA = this.requiringAsmCompA.getComponentType().getProvidedInterfaces().get(0);

		// Connect the two components
		this.connect(this.requiringAsmCompA, this.providingAsmCompB, this.commonInterfaceB);

		// Delegate system-provided interface
		this.assemblyMgr.registerSystemProvidedInterface(this.providedInterfaceA);
		this.delegate(this.requiringAsmCompA, this.providedInterfaceA);
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
	 * example implemented by B.b(..)) with the frequency distribution {@link #frequencyMap} to the {@link #usageModel}.
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
	 * Adds frequencies for calls to the {@link Signature} b(..) of the {@link AssemblyComponentConnector} {@link #asmConnector}.
	 */
	private void addAssemblyConnectorCallFrequencies() {
		int numCallsAa = 0;
		int numCallsBb = 0;
		for (final Entry<Long, Long> e : this.frequencyMap.entrySet()) {
			numCallsAa += e.getValue();
			numCallsBb += e.getValue() * e.getKey();
		}

		/*
		 * Frequency for assembly component connector
		 */
		final AssemblyComponentConnectorCallFrequency asmCallFreqAaToBb =
				UsageFactory.eINSTANCE.createAssemblyComponentConnectorCallFrequency();
		asmCallFreqAaToBb.setConnector(this.asmConnector);
		asmCallFreqAaToBb.setSignature(this.commonInterfaceB.getSignatures().get(0));
		asmCallFreqAaToBb.setFrequency(numCallsBb);
		this.usageModel.getAssemblyComponentConnectorCallFrequencies().add(asmCallFreqAaToBb);

		/*
		 * Frequency for system-provided interface delegation connector
		 */
		final SystemProvidedInterfaceDelegationConnectorFrequency sysProvDelegFreqAa =
				UsageFactory.eINSTANCE.createSystemProvidedInterfaceDelegationConnectorFrequency();
		sysProvDelegFreqAa.setConnector(this.sysProvDelegationConnector);
		sysProvDelegFreqAa.setSignature(this.providedInterfaceA.getSignatures().get(0));
		sysProvDelegFreqAa.setFrequency(numCallsAa);
		this.usageModel.getSystemProvidedInterfaceDelegationConnectorFrequencies().add(sysProvDelegFreqAa);
	}

	private void saveModels() throws IOException {
		/*
		 * Create a tmp files the models will be saved to
		 * and mark the files to be deleted on jvm termination
		 */
		final File tmpSystemModelFile = File.createTempFile(this.getClass().getSimpleName() + "-systemModel-", "");
		// tmpSystemModelFile.deleteOnExit();

		final File tmpUsageModelFile = File.createTempFile(this.getClass().getSimpleName() + "-usageModel-", "");
		// tmpSystemModelFile.deleteOnExit();

		this.systemModelManager.saveModels(tmpSystemModelFile.getAbsolutePath(), tmpUsageModelFile.getAbsolutePath());
	}

	private final CallingRelationship createCallingRelationship(final Operation op, final Interface iface,
			final Signature signature, final TreeMap<Long, Long> frequencyDistribution) {
		final CallingRelationship cr = UsageFactory.eINSTANCE.createCallingRelationship();
		cr.setCallingOperation(op);
		cr.setCalledInterface(iface);
		// TODO: make sure that signature and operation's signature contained in
		// interface
		cr.setCalledSignature(signature);
		final FrequencyDistribution fd = UsageFactory.eINSTANCE.createFrequencyDistribution();

		fd.getValues().addAll(frequencyDistribution.keySet());
		Assert.assertEquals("Unexpected number of values in frequency distribution", frequencyDistribution.keySet().size(), fd.getValues().size());
		fd.getFrequencies().addAll(frequencyDistribution.values());
		Assert.assertEquals("Unexpected number of frequencies in frequency distribution", frequencyDistribution.values().size(), fd.getFrequencies().size());
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
		 * <li>Interface <i>package.IComponentA__T</i> with a signature <code>opA(java.lang.String)</code></li>
		 * <li>ComponentType <i>package.ComponentA__T</i> providing interface <i>package.TComponentAT</i></li>
		 * <li>AssemblyComponent <i>package.ComponentA</i></li>
		 * </ul>
		 */
		final kieker.common.util.signature.Signature signature = new kieker.common.util.signature.Signature(opName, new String[] {}, returnType, paramTypes);

		final OperationExecutionRecord kiekerRecord;
		{
			final String fqClassname = fqAssemblyComponentName;
			final String operationSignatureStr = ClassOperationSignaturePair.createOperationSignatureString(fqClassname, signature);
			final String sessionId = "ZUKGHGF435JJ";
			final long traceId = 88878787877887l;
			final long tin = 65656868l;
			final long tout = 9878787887l;
			final String hostname = "theHostname";
			final int eoi = 77;
			final int ess = 98;

			kiekerRecord = new OperationExecutionRecord(operationSignatureStr, sessionId, traceId, tin, tout, hostname, eoi, ess);
		}

		final DeploymentComponentOperationExecution slasticRecord =
				(DeploymentComponentOperationExecution) this.execRecFilter.transformExecutionRecord(kiekerRecord);

		final AssemblyComponent asmComp = slasticRecord.getDeploymentComponent().getAssemblyComponent();

		if (requiredInterface != null) {
			this.typeMgr.registerRequiredInterface(asmComp.getComponentType(), requiredInterface);
		}

		return slasticRecord;
	}

	private void connect(final AssemblyComponent requiringComponent, final AssemblyComponent providingComponent,
			final Interface iface) {
		this.asmConnector = ModelEntityCreationUtils.createAssemblyConnector(this.systemModelManager, "ConnectorT", iface);
		Assert.assertTrue("Failed to connect", this.assemblyMgr.connect(this.asmConnector, requiringComponent, providingComponent));
	}

	private void delegate(final AssemblyComponent providingComponent, final Interface iface) {
		this.sysProvDelegationConnector = ModelEntityCreationUtils.createSystemProvidedDelegationConnector(this.systemModelManager, "SysProvConnectT", iface);
		Assert.assertTrue("Failed to delegate", this.assemblyMgr.delegate(this.sysProvDelegationConnector, iface, providingComponent));
	}
}
