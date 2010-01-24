package org.trustsoft.slastic.control.components.events;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISimpleSLAsticEventService {
    public void sendEvent (ISLAsticEvent ev);
    public void addListener (ISimpleSLAsticEventServiceClient l);
}
