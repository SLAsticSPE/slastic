package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import de.cau.se.slastic.metamodel.core.SystemModel;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class TestComponentMigration {
    private void createDeploymentComponent(){

    }

    public void testComponentMigration(){
        SystemModel systemModel = ModelManager.createInitializedSystemModel();
        ModelManager mgr = new ModelManager(systemModel);

    }
}
