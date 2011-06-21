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

	private static final Log log = LogFactory
			.getLog(ManualLoadBalancerConnectorTest.class);

	/**
	 * 
	 */
	public void testServletLocalhostValidRequests() {
		final String servletURL =
				"http://localhost:8080/"
						+ LoadBalancerServlet.class.getPackage().getName();

		final LoadBalancerConnector lbConnector =
				new LoadBalancerConnector(servletURL, /*
													 * do not spawn thread
													 */false, "wget.log");

		final String contextId = "TestContext";

		/* 1. Create context */
		if (!lbConnector.createContext(contextId)) {
			ManualLoadBalancerConnectorTest.log
					.error("Failed to create context");
		}

		final String host = "test.host";

		/* 2. Add host */
		if (!lbConnector.addHost(contextId, host)) {
			ManualLoadBalancerConnectorTest.log.error("Failed to add host");
		}

		/* 3. Remove host */
		if (!lbConnector.removeHost(contextId, host)) {
			ManualLoadBalancerConnectorTest.log.error("Failed to remove host");
		}

		/* 4. Remove all hosts */
		if (!lbConnector.removeAllHosts(contextId)) {
			ManualLoadBalancerConnectorTest.log
					.error("Failed to remove all hosts");
		}

		/* 5. Remove context */
		if (!lbConnector.removeContext(contextId)) {
			ManualLoadBalancerConnectorTest.log
					.error("Failed to remove context");
		}
	}

}
