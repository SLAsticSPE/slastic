package org.trustsoft.slastic.control.components.events;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISimpleEventService {

    /**
     * Send the event <i>ev</i> to all registered listeners.
     *
     * @param ev the event
     */
    public void sendEvent (IEvent ev);

    /**
     * Adds the listener <i>l</i> to the list of all listeners.
     *
     * @param l the listener
     * @return true if <i>l</i> was successfully added
     */
    public boolean addListener (ISimpleEventServiceClient l);

    /**
     * Removes the listener <i>l</i> from the list of all listeners.
     *
     * @param l the listener
     * @return true if <i>l</i> was successfully removed; false if it is wasn't registered before.
     */
    public boolean removeListener (ISimpleEventServiceClient l);
}
