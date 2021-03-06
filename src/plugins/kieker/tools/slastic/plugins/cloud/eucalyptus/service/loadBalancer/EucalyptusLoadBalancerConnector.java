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

package kieker.tools.slastic.plugins.cloud.eucalyptus.service.loadBalancer;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.cloud.loadBalancerServlet.LoadBalancerServlet;
import kieker.tools.slastic.reconfiguration.ShellExecutor;

/**
 * Acts as a facade to a deployed {@link LoadBalancerServlet}. Requests to the {@link LoadBalancerServlet} are sent via system calls to the wget command
 * which needs to be in the system path.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusLoadBalancerConnector {
	private static final Log LOG = LogFactory.getLog(EucalyptusLoadBalancerConnector.class);

	private final String servletURL;
	private final boolean spawnThreadForRequests;
	private final String wgetLogFile;

	/**
	 * In this mode, the connector does not call the {@link LoadBalancerServlet} but simply logs the corresponding commands.
	 */
	private boolean dummyMode;

	/**
	 * Must not be used for construction.
	 */
	@SuppressWarnings("unused")
	private EucalyptusLoadBalancerConnector() {
		this(null, false, null);
	}

	/**
	 * 
	 * @param servletURL
	 * @param wgetLogFile
	 *            file to
	 */
	public EucalyptusLoadBalancerConnector(final String servletURL,
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
		final String queryString = LoadBalancerServlet.createQueryString_createContext(contextId);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean removeContext(final String contextId) {
		final String queryString = LoadBalancerServlet.createQueryString_removeContext(contextId);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public boolean removeAllHosts(final String contextId) {
		final String queryString = LoadBalancerServlet.createQueryString_removeAllHosts(contextId);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public boolean addHost(final String contextId, final String host) {
		final String queryString = LoadBalancerServlet.createQueryString_addHost(contextId, host);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public boolean removeHost(final String contextId, final String host) {
		final String queryString = LoadBalancerServlet.createQueryString_removeHost(contextId, host);
		return this.sendRequest(queryString);
	}

	/**
	 * 
	 * @param queryString
	 * @return
	 */
	private boolean sendRequest(final String queryString) {
		final String url = this.servletURL + "?" + queryString;
		LOG.info("wget '" + url + "'");

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
			LOG.info("Switched to dummy mode");
		} else {
			LOG.info("Disabled dummy mode");
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
