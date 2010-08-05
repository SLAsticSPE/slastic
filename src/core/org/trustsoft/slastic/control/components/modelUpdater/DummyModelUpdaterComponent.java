/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.control.components.modelUpdater;

import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * A model update manager that simply doesn't do anything.
 *
 * @author Andree van Hoorn
 */
public class DummyModelUpdaterComponent extends AbstractModelUpdaterComponent {

    public void newObservation(IObservationEvent event) {
        // do nothing
    }

    public void handleEvent(IEvent ev) {
        // do nothing
    }

}
