package org.trustsoft.slastic.control.components.events;

/**
 *
 * @author Andre van Hoorn
 */
@Deprecated
public interface ISimpleEventServiceClient {
    public void handleEvent (IEvent ev);
}
