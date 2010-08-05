package org.trustsoft.slastic.control;

import java.util.ArrayList;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.events.ISimpleEventService;
import org.trustsoft.slastic.control.components.events.ISimpleEventServiceClient;
import org.trustsoft.slastic.monitoring.IObservationEventReceiver;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractControlComponent extends AbstractSLAsticComponent
        implements ISimpleEventService {

    private static final Log log = LogFactory.getLog(AbstractControlComponent.class);
    public static final String PROP_PREFIX = "slastic.control";
    private AbstractReconfigurationManagerComponent reconfigurationManager;
    private AbstractModelManagerComponent modelManager;
    private AbstractModelUpdaterComponent modelUpdater;
    private AbstractAnalysisComponent analysis;
    private final ArrayList<ISimpleEventServiceClient> listeners =
            new ArrayList<ISimpleEventServiceClient>();

    public abstract IObservationEventReceiver getMonitoringClientPort();

    public final AbstractAnalysisComponent getAnalysis() {
        return analysis;
    }

    public final AbstractModelManagerComponent getModelManager() {
        return modelManager;
    }

    public final AbstractModelUpdaterComponent getModelUpdater() {
        return modelUpdater;
    }

    public final AbstractReconfigurationManagerComponent getReconfigurationManager() {
        return reconfigurationManager;
    }

    public final void setAnalysis(final AbstractAnalysisComponent analysis) {
        this.analysis = analysis;
    }

    public final void setModelManager(final AbstractModelManagerComponent modelManager) {
        this.modelManager = modelManager;
    }

    public final void setModelUpdater(final AbstractModelUpdaterComponent modelUpdater) {
        this.modelUpdater = modelUpdater;
    }

    public final void setReconfigurationManager(final AbstractReconfigurationManagerComponent reconfigurationManager) {
        this.reconfigurationManager = reconfigurationManager;
    }

    @Override
    public void sendEvent(final IEvent ev) {
        for (ISimpleEventServiceClient l : this.listeners) {
            l.handleEvent(ev);
        }
    }

    @Override
    public boolean addListener(final ISimpleEventServiceClient l) {
        return this.listeners.add(l);
    }

    @Override
    public boolean removeListener(final ISimpleEventServiceClient l) {
        return this.listeners.remove(l);
    }
}
