package org.trustsoft.slastic.plugins.cloud.amazon.service.loadBalancer;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.loadBalancerServlet.LoadBalancerServlet;
import org.trustsoft.slastic.reconfiguration.ShellExecutor;

/**
 * Acts as a facade to a deployed {@link LoadBalancerServlet}. Requests to the
 * {@link LoadBalancerServlet} are sent via system calls to the wget command
 * which needs to be in the system path.
 * 
 * @author Andre van Hoorn
 * 
 */
public class AmazonLoadBalancerConnector {
	private static final Log log = LogFactory
			.getLog(AmazonLoadBalancerConnector.class);

	private final String servletURL;
	private final boolean spawnThreadForRequests;
	private final String wgetLogFile;

	/**
	 * In this mode, the connector does not call the {@link LoadBalancerServlet}
	 * but simply logs the corresponding commands.
	 */
	private boolean dummyMode;

	/**
	 * Must not be used for construction.
	 */
	@SuppressWarnings("unused")
	private AmazonLoadBalancerConnector() {
		this(null, false, null);
	}

	/**
	 * 
	 * @param servletURL
	 * @param wgetLogFile
	 *            file to
	 */
	public AmazonLoadBalancerConnector(final String servletURL,
			final boolean spawnThreadForRequests, final String wgetLogFile) {
		this.servletURL = servletURL;
		this.spawnThreadForRequests = spawnThreadForRequests;
		this.wgetLogFile = wgetLogFile;
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean createContext(final String contextId) {
		final String queryString = LoadBalancerServlet
				.createQueryString_createContext(contextId);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean removeContext(final String contextId) {
		final String queryString = LoadBalancerServlet
				.createQueryString_removeContext(contextId);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean removeAllHosts(final String contextId) {
		final String queryString = LoadBalancerServlet
				.createQueryString_removeAllHosts(contextId);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public boolean addHost(final String contextId, final String host) {
		final String queryString = LoadBalancerServlet
				.createQueryString_addHost(contextId, host);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public boolean removeHost(final String contextId, final String host) {
		final String queryString = LoadBalancerServlet
				.createQueryString_removeHost(contextId, host);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param queryString
	 * @return
	 */
	private boolean sendRequest(final String queryString) {
		final String url = this.servletURL + "?" + queryString;
		AmazonLoadBalancerConnector.log.info("wget '" + url + "'");

		if (this.isDummyMode()) {
			return true;
		}

		final ArrayList<String> argList = new ArrayList<String>();
		if (this.wgetLogFile != null) {
			argList.add("-a" + this.wgetLogFile);
		}
		argList.add(url);
		return ShellExecutor.invoke("wget", /* command */
		argList, /* arg list */
		this.spawnThreadForRequests /* spawn? */);
	}

	/**
	 * 
	 * @param dummyMode
	 */
	public final void setDummyMode(final boolean dummyMode) {
		if (dummyMode) {
			AmazonLoadBalancerConnector.log.info("Switched to dummy mode");
		} else {
			AmazonLoadBalancerConnector.log.info("Disabled dummy mode");
		}
		this.dummyMode = dummyMode;
	}

	/**
	 * 
	 * @return
	 */
	public final boolean isDummyMode() {
		return this.dummyMode;
	}
}
