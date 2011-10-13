package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.usage.OperationCallFrequency;
import de.cau.se.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class UsageModelManager extends AbstractModelManager<UsageModel> implements IUsageModelManager {
	public static final DeploymentComponentOperationExecution rootExec =
			MonitoringFactory.eINSTANCE.createDeploymentComponentOperationExecution();
	
	public UsageModelManager() {
		this(UsageFactory.eINSTANCE.createUsageModel());
	}
	
	public UsageModelManager(final UsageModel model) {
		super(model);
		// TODO: fill internal maps
	}

	@Override
	public void incSystemProvidedInterfaceSignatureCallFreq(final SystemProvidedInterfaceDelegationConnector connector,
			final Signature signature) {
		SystemProvidedInterfaceDelegationConnectorFrequency freq = null;
		
		for (final SystemProvidedInterfaceDelegationConnectorFrequency freqTmp : super.getModel().getSystemProvidedInterfaceDelegationConnectorFrequencies()) {
			if (freqTmp.getConnector().equals(connector) && freqTmp.getSignature().equals(signature)) {
				// found the matching structure
				freq = freqTmp;
				break;
			}
			// else: continue
		}
		
		if (freq == null) {
			// no observations for connector signature, yet -> create and add
			freq = UsageFactory.eINSTANCE.createSystemProvidedInterfaceDelegationConnectorFrequency();
			freq.setConnector(connector);
			freq.setSignature(signature);
			super.getModel().getSystemProvidedInterfaceDelegationConnectorFrequencies().add(freq);
		}
		
		// Now, increment frequency:
		final long oldFrequency = freq.getFrequency();
		freq.setFrequency(oldFrequency+1);
	}

	@Override
	public void incOperationCallFreq(final Operation operation, final long frequency) {
		OperationCallFrequency freq = null;
		
		for (final OperationCallFrequency freqTmp : super.getModel().getOperationCallFrequencies()) {
			if (freqTmp.getOperation().equals(operation)) {
				// found the matching structure
				freq = freqTmp;
				break;
			}
		}
		
		if (freq == null) {
			freq = UsageFactory.eINSTANCE.createOperationCallFrequency();
			freq.setOperation(operation);
			super.getModel().getOperationCallFrequencies().add(freq);
		}
		
		// Now, increment frequency
		final long oldFrequency = freq.getFrequency();
		freq.setFrequency(oldFrequency+1);
	}
	
//	/**
//	 * Operation call frequencies (operation id x frequency) 
//	 */
//	private final Map<Long, OperationCallFrequency> opCallFrequencies = new HashMap<Long, OperationCallFrequency>();
//	
//	/**
//	 * Calling relationships among {@link Operation}s and {@link Signature}s of an {@link Interface}.   
//	 */
//	private final Map<Long, CallingRelationship> opCallRelationships = new HashMap<Long, CallingRelationship>();
//	
//	/** Frequencies of calls to {@link Signature}s of an {@link AssemblyComponentConnector} */
//	private final Map<Long, AssemblyConnectorCallFrequency> connectorCallFrequencies = new HashMap<Long, AssemblyConnectorCallFrequency>();
//
//	/**
//	 * 
//	 * 
//	 * @param operation
//	 * @param iface
//	 * @param signature
//	 * @return
//	 */
//	public long opCallRelationshipHashCode(final Operation operation, final Interface iface, final Signature signature) {
//		final int prime = 31;
//		long result = 1;
//		result = prime * result + operation.getId();
//		result = prime * result + iface.getId();
//		result = prime * result + signature.getId();
//		return result;
//	}	
}
