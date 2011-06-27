package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.loadBalancer;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
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
public class LoadBalancerConnector {
	private static final Log log = LogFactory
			.getLog(LoadBalancerConnector.class);

	private final List<Integer> loadbalancerPorts = new Vector<Integer>();

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
	private LoadBalancerConnector() {
		this(null, false, null);
	}

	/**
	 * 
	 * @param servletURL
	 * @param wgetLogFile
	 *            file to
	 */
	public LoadBalancerConnector(final String servletURL,
			final boolean spawnThreadForRequests, final String wgetLogFile) {
		this.loadbalancerPorts.add(9090);
		this.loadbalancerPorts.add(9100);

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
		return true;
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean removeContext(final String contextId) {
		return true;
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean removeAllHosts(final String contextId) {
		return true; // FIXME for testing purpose only
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public boolean addHost(final String contextId, final String host) {
		final String queryString = "add?" + host;
		final Integer port = this.getPortForContextID(contextId);
		return this.sendRequest(port, queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public boolean removeHost(final String contextId, final String host) {
		final String queryString = "delete?" + host;
		final Integer port = this.getPortForContextID(contextId);
		return this.sendRequest(port, queryString);
	}

	private Integer getPortForContextID(final String contextId) {
		Integer port = 9090;
		if (contextId.contains(EucalyptusApplicationCloudingService.WEBSTORE_REST_NAME)) {
			port = this.loadbalancerPorts.get(0);
		} else if (contextId.contains(EucalyptusApplicationCloudingService.POSTERITA_REST_NAME)) {
			port = this.loadbalancerPorts.get(0);
		} else {
			port = this.loadbalancerPorts.get(1);
		}
		
		return port;
	}

	/**
	 * 
	 * @param queryString
	 * @return
	 */
	private boolean sendRequest(final Integer port, final String queryString) {
		final String url = "http://127.0.0.1:" + port + "/" + queryString;
		LoadBalancerConnector.log.info("wget '" + url + "'");

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
			LoadBalancerConnector.log.info("Switched to dummy mode");
		} else {
			LoadBalancerConnector.log.info("Disabled dummy mode");
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
