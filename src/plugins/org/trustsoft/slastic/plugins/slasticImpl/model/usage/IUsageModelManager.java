package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IUsageModelManager {

	/**
	 * Increments the number of calls to the given {@link Signature} of the given
	 * {@link SystemProvidedInterfaceDelegationConnector} by 1.
	 * 
	 * @param connector
	 * @param signature
	 */
	public void incSystemProvidedInterfaceSignatureCallFreq(final SystemProvidedInterfaceDelegationConnector connector,
			final Signature signature);
	
	/**
	 * Increments the number of calls to the given {@link Operation} by the given
	 * frequency.
	 * 
	 * @param operation
	 * @param signature
	 */
	public void incOperationCallFreq(final Operation operation, final long frequency);
}
