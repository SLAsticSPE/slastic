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

package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class ReconfigurationPipeBroker {
	private static final Log LOG = LogFactory.getLog(ReconfigurationPipeBroker.class);

	private static final ReconfigurationPipeBroker INSTANCE = new ReconfigurationPipeBroker();

	/* Not synchronized! */
	private final HashMap<String, ReconfigurationPipe> pipeMap = new HashMap<String, ReconfigurationPipe>();

	public static ReconfigurationPipeBroker getInstance() {
		return ReconfigurationPipeBroker.INSTANCE;
	}

	/**
	 * Returns a connection with name @a pipeName. If a connection with
	 * this name does not exist prior to the call, it is created.
	 */
	public synchronized ReconfigurationPipe acquirePipe(final String pipeName) throws IllegalArgumentException {
		if ((pipeName == null) || (pipeName.length() == 0)) {
			LOG.error("Invalid connection name " + pipeName);
			throw new IllegalArgumentException("Invalid connection name " + pipeName);
		}
		ReconfigurationPipe conn = this.pipeMap.get(pipeName);
		if (conn == null) {
			conn = new ReconfigurationPipe(pipeName);
			this.pipeMap.put(pipeName, conn);
		}

		return conn;
	}
}
