package org.trustsoft.slastic.plugins.slasticImpl.model;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractModelManager<T extends SLAsticModel> {

    private final T model;

    public T getModel() {
        return this.model;
    }

    public AbstractModelManager(final T model) {
        this.model = model;
    }

}
