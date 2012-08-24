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

package org.trustsoft.slastic.tests.manual.cloud.eucalyptus;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.loadBalancer.LoadBalancerConnector;
import org.trustsoft.slastic.plugins.cloud.loadBalancerServlet.LoadBalancerServlet;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ManualLoadBalancerConnectorTest extends TestCase {

	private static final Log LOG = LogFactory.getLog(ManualLoadBalancerConnectorTest.class);

	/**
	 * 
	 */
	public void testServletLocalhostValidRequests() {
		final String servletURL = "http://localhost:8080/" + LoadBalancerServlet.class.getPackage().getName();

		final LoadBalancerConnector lbConnector = new LoadBalancerConnector(servletURL, /* do not spawn thread */false, "wget.log");

		final String contextId = "TestContext";

		/* 1. Create context */
		if (!lbConnector.createContext(contextId)) {
			LOG.error("Failed to create context");
		}

		final String host = "test.host";

		/* 2. Add host */
		if (!lbConnector.addHost(contextId, host)) {
			LOG.error("Failed to add host");
		}

		/* 3. Remove host */
		if (!lbConnector.removeHost(contextId, host)) {
			LOG.error("Failed to remove host");
		}

		/* 4. Remove all hosts */
		if (!lbConnector.removeAllHosts(contextId)) {
			LOG.error("Failed to remove all hosts");
		}

		/* 5. Remove context */
		if (!lbConnector.removeContext(contextId)) {
			LOG.error("Failed to remove context");
		}
	}

}
