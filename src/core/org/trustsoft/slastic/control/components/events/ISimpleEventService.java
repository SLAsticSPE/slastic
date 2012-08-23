/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.control.components.events;

/**
 *
 * @author Andre van Hoorn
 */
@Deprecated
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
