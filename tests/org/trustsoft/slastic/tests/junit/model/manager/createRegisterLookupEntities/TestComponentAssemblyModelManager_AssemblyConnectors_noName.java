package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.AssemblyConnectorsManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 * Tests the functionalities provided by the connector assembly manager for creating,
 * registering, and looking up assembly connectors without passing a name, i.e. a 
 * unique name is assigned by the connector assembly manager. 
 *
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_AssemblyConnectors_noName extends TestCase {

    //private static final Log log = LogFactory.getLog(TestComponentAssemblyModelManager_AssemblyConnectors_noName.class);

    public void testCreateLookupConnectorWithGeneratedName() throws Exception {   	
    	final SystemModel systemModel = ModelManager.createInitializedSystemModel();
    	final ModelManager systemModelManager = new ModelManager(systemModel);
    	final ComponentAssemblyModelManager componentAssemblyModelManager =  systemModelManager.getComponentAssemblyModelManager();
    
        /*
         * A connector type is needed to create an assembly connector -- we'll
         * create one with a generated name or use an existing one, if a
         * connector type with this name exists already.
         */
        final String connectorTypeName = "AConnectorType";
        final TypeRepositoryModelManager typeModelMgr = systemModelManager.getTypeRepositoryManager();
        ConnectorType connectorType = // use existing type instance if it exists already
        	typeModelMgr.lookupConnectorType(connectorTypeName);
        if (connectorType == null){
            final Interface iface = typeModelMgr.createAndRegisterInterface("ISomething");
            connectorType = typeModelMgr.createAndRegisterConnectorType(connectorTypeName, iface);
        }
        Assert.assertNotNull("Test invalid: connectorType == null", connectorType);
        
        /* Now, we'll create an assembly connector without providing a name and do some checks on the name */ 
        final AssemblyComponentConnector assemblyConnector = componentAssemblyModelManager.createAndRegisterAssemblyConnector(connectorType);
        final String fqConnectorName = NameUtils.createFQName(assemblyConnector.getPackageName(), assemblyConnector.getName());
        Assert.assertNotNull("Connector name is null!", fqConnectorName);
        Assert.assertTrue("Expecting connector name to start with " + AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX,
        		fqConnectorName.startsWith(AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX));
        Assert.assertTrue("Connector name should be longer than prefix", fqConnectorName.length()>AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX.length());
        
        /* Now, we'll perform a lookup */
        final AssemblyComponentConnector lookedUpConnector = componentAssemblyModelManager.lookupAssemblyConnector(fqConnectorName);
        Assert.assertNotNull("Failed to lookup assembly connector with name " + fqConnectorName, lookedUpConnector);
    }
}
