package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.core.Entity;
import de.cau.se.slastic.metamodel.core.SLAsticModel;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSubmodelManagerCreateRegisterLookupEntityTest<M extends SLAsticModel, T extends Entity> extends TestCase {

    private static final Log log = LogFactory.getLog(AbstractSubmodelManagerCreateRegisterLookupEntityTest.class);

    protected abstract M createModel();

    protected abstract AbstractModelManager<M> getModelManager(ModelManager systemModelMgr);

    protected abstract T lookupEntity(AbstractModelManager<M> mgr, long entityId);
}
