package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IUsageModelManager {

	/**
	 * Notifies about a call to the {@link Signature} of the given
	 * {@link AssemblyComponentConnector}.
	 * 
	 * @param assemblyConnector
	 * @param signature
	 */
	public void assemblyConnectorCall(final AssemblyComponentConnector assemblyConnector, final Signature signature);
}
