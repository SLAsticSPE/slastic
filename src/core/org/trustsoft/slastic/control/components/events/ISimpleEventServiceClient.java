package org.trustsoft.slastic.control.components.events;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISimpleEventServiceClient {
    public void handleEvent (IEvent ev);
}
