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

package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * 
 * @author Lena Stoever
 * 
 */
public class SLAViolationEvent implements IEvent {
	// private static final Log LOG = LogFactory.getLog(SLAViolationEvent.class);

	public int serviceID;

	SLAViolationEvent(final int serviceID) {
		this.serviceID = serviceID;
	}

	public int getServiceID() {
		return this.serviceID;
	}

}
