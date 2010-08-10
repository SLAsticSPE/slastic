package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import junit.framework.TestCase;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.tests.junit.model.ModelEntityCreationUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class TestComponentDereplication extends TestCase {

    /**
     * Creates a deployment component, dereplicates it, and makes sure that the
     * deployment component is removed from the model.
     */
    public void testComponentDereplication(){
        SystemModel systemModel = ModelManager.createInitializedSystemModel();
        ModelManager mgr = new ModelManager(systemModel);
        DeploymentComponent deploymentComponent =
                ModelEntityCreationUtils.createDeploymentComponent(
                mgr,
                "ComponentTypeName",
                "AssemblyComponentName",
                "ExecutionContainerTypeName",
                "ExecutionContainernName");
        mgr.dereplicateComponent(deploymentComponent);
        assertNull(mgr.getComponentDeploymentModelManager().lookupDeploymentComponent(deploymentComponent.getId()));
    }
}