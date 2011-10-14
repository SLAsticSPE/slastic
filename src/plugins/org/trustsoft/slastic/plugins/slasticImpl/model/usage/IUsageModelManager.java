package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IUsageModelManager {

	/**
	 * Returns the number of times, the given {@link Signature} of the given
	 * {@link SystemProvidedInterfaceDelegationConnector} is called.
	 * 
	 * @param connector
	 * @param signature
	 * @return
	 */
	public long lookupSystemProvidedInterfaceDelegationConnectorFrequency(
			final SystemProvidedInterfaceDelegationConnector connector,
			final Signature signature);

	/**
	 * Increments the number of calls to the given {@link Signature} of the
	 * given {@link SystemProvidedInterfaceDelegationConnector} by 1.
	 * 
	 * @param connector
	 * @param signature
	 */
	public void incSystemProvidedInterfaceSignatureCallFreq(final SystemProvidedInterfaceDelegationConnector connector,
			final Signature signature);

	/**
	 * Returns the number of times, the given {@link Operation} was called.
	 * 
	 * @param operation
	 * @return
	 */
	public long lookupOperationCallFreq(final Operation operation);

	/**
	 * Increments the number of calls to the given {@link Operation} by the
	 * given frequency.
	 * 
	 * @param operation
	 *            the called {@link Operation}
	 * @param signature
	 *            the called {@link Signature}
	 */
	public void incOperationCallFreq(final Operation operation, final long frequency);

	/**
	 * Returns the {@link CallingRelationship} for the given {@link Operation}
	 * calling the given {@link Interface}'s {@link Signature}.
	 * 
	 * @param operation
	 * @param iface
	 * @param signature
	 * @return
	 */
	public CallingRelationship lookupCallingRelationship(final Operation operation, final Interface iface,
			final Signature signature);

	/**
	 * Adds the information that the given {@link Interface} {@link Signature}
	 * has been called the given number of times within an execution of the
	 * given {@link Operation}.
	 * 
	 * @param operation
	 *            the calling {@link Operation}
	 * @param iface
	 *            the called {@link Interface}
	 * @param signature
	 *            the called {@link Signature}
	 * @param frequency
	 */
	public void incCallingRelationshipFreq(final Operation operation, final Interface iface, final Signature signature,
			final long frequency);
}
