package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IUsageModelManager {

	/**
	 * Notifies about a call to the {@link Signature} of the given
	 * {@link AssemblyConnector}.
	 * 
	 * @param assemblyConnector
	 * @param signature
	 */
	public void assemblyConnectorCall(final AssemblyConnector assemblyConnector, final Signature signature);
}
