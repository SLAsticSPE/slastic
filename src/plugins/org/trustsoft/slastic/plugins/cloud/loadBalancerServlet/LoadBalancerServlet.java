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

package org.trustsoft.slastic.plugins.cloud.loadBalancerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class LoadBalancerServlet extends HttpServlet {
	// private static final Log LOG = LogFactory.getLog(LoadBalancerServlet.class);

	private final Random rnd = new Random();

	private static final long serialVersionUID = -3655750912361464631L;

	private void dumpError(final PrintWriter out, final String msg) {
		out.println("<div style=\"color:red\">ERROR: " + msg + "</div>");
	}

	private void dumpInfo(final PrintWriter out, final String msg) {
		out.println("<div style=\"color:green\">INFO: " + msg + "</div>");
	}

	public static final String PARAM_NAME_ACTION = "action";
	public static final String ACTION_SHOWALL = "showAll";
	public static final String ACTION_ADDHOST = "addHost";
	public static final String ACTION_REMOVEHOST = "removeHost";
	public static final String ACTION_REMOVEALLHOSTS = "removeAllHosts";
	public static final String ACTION_CREATECONTEXT = "createContext";
	public static final String ACTION_REMOVECONTEXT = "removeContext";
	public static final String ACTION_SELECTRANDOMHOST = "selectRandomHost";
	public static final String PARAM_NAME_CONTEXTID = "contextId";
	public static final String PARAM_NAME_HOST = "host";

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected synchronized void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		final PrintWriter out = response.getWriter();

		final List<String> errorMessages = new ArrayList<String>();
		final List<String> infoMessages = new ArrayList<String>();
		final StringBuilder pageContentBuilder = new StringBuilder();

		String action = request.getParameter(PARAM_NAME_ACTION);
		if (action == null) {
			action = "";
		}

		String contextId = request.getParameter(PARAM_NAME_CONTEXTID);
		if (contextId != null) {
			contextId = contextId.trim();
		}

		String host = request.getParameter(PARAM_NAME_HOST);
		if (host != null) {
			host = host.trim();
		}

		final boolean showStatusPage = true;

		if ((request.getParameterMap().size() == 0) || action.equals(ACTION_SHOWALL)) {
		} else if (action.equals(ACTION_CREATECONTEXT)) {
			this.doCreateContext(contextId, errorMessages, infoMessages, pageContentBuilder);
		} else if (action.equals(ACTION_REMOVECONTEXT)) {
			this.doRemoveContext(contextId, errorMessages, infoMessages, pageContentBuilder);
		} else if (action.equals(ACTION_ADDHOST)) {
			this.doAddHost2Context(contextId, host, errorMessages, infoMessages, pageContentBuilder);
		} else if (action.equals(ACTION_REMOVEHOST)) {
			this.doRemoveHostFromContext(contextId, host, errorMessages, infoMessages, pageContentBuilder);
		} else if (action.equals(ACTION_REMOVEALLHOSTS)) {
			this.doRemoveAllHostsFromContext(contextId, errorMessages, infoMessages, pageContentBuilder);
		} else if (action.equals(ACTION_SELECTRANDOMHOST)) {
			this.doSelectRandomHostFromContext(contextId, errorMessages, infoMessages, pageContentBuilder);
			// TODO: provide verbose and quiet mode
			// showStatusPage = false;
		} else {
			errorMessages.add("Invalid action: " + action);
		}

		if (showStatusPage) {
			out.println("[<a href=\"" + request.getRequestURL().toString() + "\"/>Home]</a>");
			this.doShowStatusPage(errorMessages, infoMessages, pageContentBuilder);
		}

		this.renderPage(out, errorMessages, infoMessages, pageContentBuilder);
		out.close();
	}

	/**
	 * 
	 * @param out
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 */
	private void renderPage(final PrintWriter out,
			final List<String> errorMessages, final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>" + this.getServletInfo() + "</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + this.getServletInfo() + "</h1>");
		for (final String errorMsg : errorMessages) {
			this.dumpError(out, errorMsg);
		}
		for (final String infoMsg : infoMessages) {
			this.dumpInfo(out, infoMsg);
		}
		out.print(pageContentBuilder.toString());
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * Context Id x list of hosts
	 */
	private final HashMap<String, Vector<String>> contexts = new HashMap<String, Vector<String>>();

	private final static String QUERY_STRING_TEMPLATE__createContext =
			PARAM_NAME_ACTION + "=" + ACTION_CREATECONTEXT + "&" + PARAM_NAME_CONTEXTID + "=" + "CONTEXTID";

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public final static String createQueryString_createContext(
			final String contextId) {
		final String queryString = QUERY_STRING_TEMPLATE__createContext.replace("CONTEXTID", contextId);
		return queryString;
	}

	/**
	 * 
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return true on success; false on error
	 */
	private boolean doCreateContext(final String contextId, final List<String> errorMessages, final List<String> infoMessages, final StringBuilder pageContentBuilder) {
		if ((contextId == null) || contextId.isEmpty()) {
			errorMessages.add("Invalid context ID: '" + contextId + "'");
			return false;
		}

		if (this.contexts.containsKey(contextId)) {
			errorMessages.add("Context with ID '" + contextId + "' is already registered.");
			return false;
		}

		final Vector<String> hostsForContext = new Vector<String>();
		this.contexts.put(contextId, hostsForContext);
		infoMessages.add("Created context with ID '" + contextId + "'.");
		return true;
	}

	private final static String QUERY_STRING_TEMPLATE__removeContext =
			PARAM_NAME_ACTION + "=" + ACTION_REMOVECONTEXT + "&" + PARAM_NAME_CONTEXTID + "=" + "CONTEXTID";

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public final static String createQueryString_removeContext(final String contextId) {
		final String queryString = QUERY_STRING_TEMPLATE__removeContext.replace("CONTEXTID", contextId);
		return queryString;
	}

	/**
	 * 
	 * @param contextId
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return true on success; false on error
	 */
	private boolean doRemoveContext(final String contextId,
			final List<String> errorMessages, final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {
		if ((contextId == null) || contextId.isEmpty()) {
			errorMessages.add("Invalid context ID: '" + contextId + "'");
			return false;
		}

		if (this.contexts.remove(contextId) == null) {
			errorMessages.add("No context with ID '" + contextId + "'.");
			return false;
		}

		infoMessages.add("Removed context with ID '" + contextId + "'.");

		return true;
	}

	private final static String QUERY_STRING_TEMPLATE__addHost =
			PARAM_NAME_ACTION + "=" + ACTION_ADDHOST + "&" + PARAM_NAME_CONTEXTID + "=" + "CONTEXTID" + "&" + PARAM_NAME_HOST + "=" + "HOST";

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public final static String createQueryString_addHost(
			final String contextId, final String host) {
		final String queryString = QUERY_STRING_TEMPLATE__addHost.replace("CONTEXTID", contextId).replace("HOST", host);
		return queryString;
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return
	 */
	private boolean doAddHost2Context(final String contextId,
			final String host, final List<String> errorMessages,
			final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {
		if ((contextId == null) || contextId.isEmpty()) {
			errorMessages.add("Invalid context ID: '" + contextId + "'");
			return false;
		}

		if ((host == null) || host.isEmpty()) {
			errorMessages.add("Invalid host: '" + host + "'");
			return false;
		}

		final Vector<String> hostsForContext = this.contexts.get(contextId);
		if (hostsForContext == null) {
			errorMessages.add("No context with ID '" + contextId + "'.");
			return false;
		}

		hostsForContext.add(host);
		infoMessages.add("Added host '" + host + "' to context with ID '" + contextId + "'.");
		return true;
	}

	private final static String QUERY_STRING_TEMPLATE__removeHost =
			PARAM_NAME_ACTION + "=" + ACTION_REMOVEHOST + "&" + PARAM_NAME_CONTEXTID + "=" + "CONTEXTID" + "&" + PARAM_NAME_HOST + "=" + "HOST";

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @return
	 */
	public final static String createQueryString_removeHost(
			final String contextId, final String host) {
		final String queryString = QUERY_STRING_TEMPLATE__removeHost.replace("CONTEXTID", contextId).replace("HOST", host);
		return queryString;
	}

	/**
	 * 
	 * @param contextId
	 * @param host
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return
	 */
	private boolean doRemoveHostFromContext(final String contextId,
			final String host, final List<String> errorMessages,
			final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {
		if ((contextId == null) || contextId.isEmpty()) {
			errorMessages.add("Invalid context ID: '" + contextId + "'");
			return false;
		}

		if ((host == null) || host.isEmpty()) {
			errorMessages.add("Invalid host: '" + host + "'");
			return false;
		}

		final Vector<String> hostsForContext = this.contexts.get(contextId);
		if (hostsForContext == null) {
			errorMessages.add("No context with ID '" + contextId + "'.");
			return false;
		}

		if (!hostsForContext.remove(host)) {
			infoMessages.add("No host '" + host + "' within context with ID '" + contextId + "'.");
			return false;
		}

		infoMessages.add("Removed host '" + host + "' from context with ID '" + contextId + "'.");
		return true;
	}

	private final static String QUERY_STRING_TEMPLATE__removeAllHosts =
			PARAM_NAME_ACTION + "=" + ACTION_REMOVEALLHOSTS + "&" + PARAM_NAME_CONTEXTID + "=" + "CONTEXTID";

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public final static String createQueryString_removeAllHosts(final String contextId) {
		final String queryString = QUERY_STRING_TEMPLATE__removeAllHosts.replace("CONTEXTID", contextId);
		return queryString;
	}

	/**
	 * 
	 * @param contextId
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return
	 */
	private boolean doRemoveAllHostsFromContext(final String contextId,
			final List<String> errorMessages, final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {
		if ((contextId == null) || contextId.isEmpty()) {
			errorMessages.add("Invalid context ID: '" + contextId + "'");
			return false;
		}

		final Vector<String> hostsForContext = this.contexts.get(contextId);
		if (hostsForContext == null) {
			errorMessages.add("No context with ID '" + contextId + "'.");
			return false;
		}

		hostsForContext.clear();
		infoMessages.add("Removed all hosts from context with ID '" + contextId + "'.");
		return true;
	}

	public static final String HOST_PREFIX = "host";

	private final static String QUERY_STRING_TEMPLATE__selectRandomHost =
			PARAM_NAME_ACTION + "=" + ACTION_SELECTRANDOMHOST + "&" + PARAM_NAME_CONTEXTID + "=" + "CONTEXTID";

	/**
	 * 
	 * @param contextId
	 * @return
	 */
	public final static String createQueryString_selectRandomHost(
			final String contextId) {
		final String queryString = QUERY_STRING_TEMPLATE__selectRandomHost.replace("CONTEXTID", contextId);
		return queryString;
	}

	/**
	 * 
	 * @param contextId
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return
	 */
	private boolean doSelectRandomHostFromContext(final String contextId,
			final List<String> errorMessages, final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {
		if ((contextId == null) || contextId.isEmpty()) {
			errorMessages.add("Invalid context ID: '" + contextId + "'");
			return false;
		}

		final Vector<String> hostsForContext = this.contexts.get(contextId);
		if (hostsForContext == null) {
			errorMessages.add("No context with ID '" + contextId + "'.");
			return false;
		}

		final int numHosts = hostsForContext.size();

		if (numHosts == 0) {
			errorMessages.add("Context with ID '" + contextId + "' has 0 hosts");
			return false;
		}

		final int rndIndex = this.rnd.nextInt(numHosts);
		final String rndHost = hostsForContext.elementAt(rndIndex);
		pageContentBuilder.append(HOST_PREFIX + "=" + rndHost + "\n");

		return true;
	}

	/**
	 * 
	 * @param contextId
	 * @param errorMessages
	 * @param infoMessages
	 * @param pageContentBuilder
	 * @return
	 */
	private boolean doShowStatusPage(final List<String> errorMessages,
			final List<String> infoMessages,
			final StringBuilder pageContentBuilder) {

		pageContentBuilder.append("<ul>\n");
		pageContentBuilder.append("<li><FORM ACTION=\"index\" METHOD=\"GET\"> ");
		pageContentBuilder.append("<INPUT TYPE=\"HIDDEN\" NAME=\"" + PARAM_NAME_ACTION + "\" VALUE=\"" + ACTION_CREATECONTEXT + "\">");
		pageContentBuilder.append("<INPUT TYPE=\"TEXT\" SIZE=\"6\" NAME=\"" + PARAM_NAME_CONTEXTID + "\" value=\" \"/>");
		pageContentBuilder.append(" <INPUT TYPE=\"SUBMIT\" VALUE=\"Create context\"> ");
		pageContentBuilder.append("</FORM></li>");
		for (final Entry<String, Vector<String>> entry : this.contexts.entrySet()) {
			final String contextId = entry.getKey();
			final Vector<String> hostsForContext = entry.getValue();
			pageContentBuilder.append("<li>" + contextId + "\n");
			pageContentBuilder.append(" [<a href=\"?" + LoadBalancerServlet.createQueryString_removeContext(contextId) + "\">Remove context</a>] \n");
			pageContentBuilder.append(" [<a href=\"?" + LoadBalancerServlet.createQueryString_selectRandomHost(contextId) + "\">Random host</a>] \n");
			pageContentBuilder.append(" [<a href=\"?" + LoadBalancerServlet.createQueryString_removeAllHosts(contextId) + "\">Remove all hosts</a>] \n");
			pageContentBuilder.append("<ol>\n");
			pageContentBuilder.append("<li><FORM ACTION=\"index\" METHOD=\"GET\"> ");
			pageContentBuilder.append("<INPUT TYPE=\"HIDDEN\" NAME=\"" + PARAM_NAME_ACTION + "\" VALUE=\"" + ACTION_ADDHOST + "\">");
			pageContentBuilder.append("<INPUT TYPE=\"HIDDEN\" NAME=\"" + PARAM_NAME_CONTEXTID + "\" VALUE=\"" + contextId + "\">");
			pageContentBuilder.append("<INPUT TYPE=\"TEXT\" SIZE=\"15\" NAME=\"" + PARAM_NAME_HOST + "\" value=\" \"/>");
			pageContentBuilder.append(" <INPUT TYPE=\"SUBMIT\" VALUE=\"Add host\"> ");
			pageContentBuilder.append("</FORM></li>");

			for (final String host : hostsForContext) {
				pageContentBuilder.append("<li>");
				pageContentBuilder.append(host);
				pageContentBuilder.append(" [<a href=\"?" + LoadBalancerServlet.createQueryString_removeHost(contextId, host) + "\">Remove host</a>] \n");
				pageContentBuilder.append("</li>");
			}
			pageContentBuilder.append("</ol>\n");
			pageContentBuilder.append("</li>\n");
		}
		pageContentBuilder.append("</ul>\n");

		return true;
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "LoadBalancerServlet";
	}
}
