package org.trustsoft.slastic.plugins.slasticImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelUpdater extends AbstractModelUpdaterComponent {

    private static final Log log = LogFactory.getLog(ModelUpdater.class);

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    @Override
    public void handleEvent(IEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void newObservation(IObservationEvent ime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
