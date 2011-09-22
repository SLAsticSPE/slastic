package org.trustsoft.slastic.plugins.slasticImpl.model.usage;

import java.util.HashMap;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;
import de.cau.se.slastic.metamodel.usage.OperationCallFrequency;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class UsageModelManager extends AbstractModelManager<UsageModel> implements IUsageModelManager {

	public UsageModelManager() {
		this(UsageFactory.eINSTANCE.createUsageModel());
	}
	
	public UsageModelManager(final UsageModel model) {
		super(model);
		// TODO: fill internal maps
	}

	@Override
	public void assemblyConnectorCall(final AssemblyConnector assemblyConnector, final Signature signature) {
		// TODO
	}
	
	/**
	 * Operation call frequencies (operation id x frequency) 
	 */
	private final HashMap<Long, OperationCallFrequency> opCallFrequencies = new HashMap<Long, OperationCallFrequency>();
	
	/**
	 * Calling relationships among {@link Operation}s and {@link Signature}s of an {@link Interface}.   
	 */
	private final HashMap<Long, CallingRelationship> opCallRelationships = new HashMap<Long, CallingRelationship>();
	
	/** Frequencies of calls to {@link Signature}s of an {@link AssemblyConnector} */
	private final HashMap<Long, AssemblyConnectorCallFrequency> connectorCallFrequencies = new HashMap<Long, AssemblyConnectorCallFrequency>();

	/**
	 * 
	 * 
	 * @param operation
	 * @param iface
	 * @param signature
	 * @return
	 */
	public long opCallRelationshipHashCode(final Operation operation, final Interface iface, final Signature signature) {
		final int prime = 31;
		long result = 1;
		result = prime * result + operation.getId();
		result = prime * result + iface.getId();
		result = prime * result + signature.getId();
		return result;
	}	
}
