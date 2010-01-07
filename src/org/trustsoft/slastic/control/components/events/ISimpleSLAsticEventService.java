/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.control.components.events;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISimpleSLAsticEventService {
    public void sendEvent (ISLAsticEvent ev);
    public void addListener (ISimpleSLAsticEventListener l);
}
