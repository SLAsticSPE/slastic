package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;
import de.cau.se.slastic.metamodel.usage.FrequencyDistribution;
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

		for (final SystemProvidedInterfaceDelegationConnectorFrequency freqTmp : super.getModel()
				.getSystemProvidedInterfaceDelegationConnectorFrequencies()) {
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
		freq.setFrequency(oldFrequency + 1);
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
		freq.setFrequency(oldFrequency + 1);
	}

	@Override
	public void incCallingRelationshipFreq(final Operation operation, final Interface iface, final Signature signature,
			final long frequency) {
		CallingRelationship cr = null;

		for (final CallingRelationship crTmp : super.getModel().getCallingRelationships()) {
			if (crTmp.getCallingOperation().equals(operation) && crTmp.getCalledInterface().equals(iface)
					&& crTmp.getCalledSignature().equals(signature)) {
				// found the matching structure
				cr = crTmp;
				break;
			}
		}
		
		if (cr == null) {
			cr = UsageFactory.eINSTANCE.createCallingRelationship();
			cr.setCallingOperation(operation);
			cr.setCalledInterface(iface);
			cr.setCalledSignature(signature);
			cr.setFrequencyDistribution(UsageFactory.eINSTANCE.createFrequencyDistribution());
			super.getModel().getCallingRelationships().add(cr);
		}

		// Now, update frequency distribution
		UsageModelManager.incFrequency(cr.getFrequencyDistribution(), frequency);		
	}
	
	/**
	 * 
	 * @param fd note, that {@link FrequencyDistribution#getValues()} is ordered!
	 * @param frequency
	 */
	public static void incFrequency (final FrequencyDistribution fd, final long frequency) {
		final EList<Long> valueListSorted = fd.getValues();
		final EList<Long> frequencyList = fd.getFrequencies();
		
		final int pos = valueListSorted.indexOf(frequency);
		if (pos == -1) { // not in list, add
			valueListSorted.add(frequency);
			// We have to sort the list manually, as the ordered feature of 
			// EList doesn't seem to work
			ECollections.sort(valueListSorted);
			final int posAdded = valueListSorted.indexOf(frequency);
			frequencyList.add(posAdded, 1l);
		} else { // the easy case! just increment the value by one
			final long oldFrequency = frequencyList.get(pos);
			frequencyList.set(pos, oldFrequency+1);
		}
	}
}
