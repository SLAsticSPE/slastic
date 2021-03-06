package kieker.tools.slastic.plugins.cloud.amazon.service.configuration;

import java.util.Collection;
import java.util.Map;

import kieker.tools.slastic.plugins.cloud.amazon.model.AmazonApplicationInstance;
import kieker.tools.slastic.plugins.cloud.loadBalancerServlet.LoadBalancerServlet;

public interface IAmazonApplicationCloudingServiceConfiguration {

	/**
	 * Returns the debug configuration value.
	 * 
	 * @return
	 */
	public boolean isDebugEnabled();

	/**
	 * In the dummy mode, the operation only claim to call the Amazon tools
	 * and return dummy values.
	 * 
	 * @return
	 */
	public boolean isDummyModeEnabled();

	/**
	 * Returns whether the load balancer is enabled or not.
	 * 
	 * @return
	 */
	public boolean isLoadBalancerEnabled();

	/**
	 * Returns the URL of a deployed {@link LoadBalancerServlet}.
	 * 
	 * @return
	 */
	public String getLoadBalancerServletURL();

	/**
	 * Returns the file system location of this Amazon tools (e.g.
	 * {@code /usr/local/bin/}).
	 * 
	 * @return
	 */
	public String getEC2toolsPath();

	/**
	 * Returns a map of image names x EMI.
	 * 
	 * @return
	 */
	public Map<String, String> getAMIs();

	/**
	 * Returns a list of node instances to create initially. Each element of the
	 * returned collection is a String array with 4 elements: node name, ip,
	 * instance id, image name
	 * 
	 * @return
	 * @see IAmazonApplicationCloudingServiceConfiguration#getAMIs()
	 */
	public Collection<String[]> getInitialNodeInstances();

	/**
	 * Returns a list of application to create initially.
	 * 
	 * @return
	 */
	public Collection<String> getInitialApplications();

	/**
	 * Returns a list of application instances to create initially. Each element
	 * of the returned collection is a String array with 2 elements: application
	 * name, node instance
	 * 
	 * @return
	 */
	public Collection<String[]> getInitialApplicationInstances();

	/**
	 * Returns the number of seconds to wait after a node de-allocation
	 * request before an instance is actually released. This property can be
	 * used to avoid the termination of active requests on instances.
	 * 
	 * @return
	 */
	public int getNodeShutDownDelaySeconds();

	/**
	 * Returns the maximum time to wait for an allocated node to be available.
	 * 
	 * @return
	 */
	public int getNodeAllocationMaxWaitTimeSeconds();
	
	/**
	 * Returns the poll period with which to check if an allocated node is available.
	 */
	public int getNodeAllocationPollPeriodSeconds();
	
	/**
	 * Returns the maximum time to wait for an application to be available.
	 * 
	 * @return
	 */
	public int getApplicationInstanceDeployMaxWaitTimeSeconds();

	/**
	 * Returns the poll period with which to check if an application instance is
	 * available.
	 * 
	 * @return
	 */
	public int getApplicationInstanceDeployPollPeriodSeconds();

	/**
	 * Returns the default port to send an HTTP request to (in order to check
	 * whether a newly deployed application instance became available).
	 * 
	 * @return
	 * @see #getDefaultApplicationInstanceQueryPath()
	 */
	public int getDefaultApplicationInstanceQueryPort();

	/**
	 * Returns the path to send an HTTP request to (in order to check whether a
	 * newly deployed application instance became available).
	 * 
	 * @return
	 * @see #getDefaultApplicationInstanceQueryPort()
	 */
	public String getDefaultApplicationInstanceQueryPath();

	/**
	 * Returns the file system location of the default artifact to copy to the
	 * directory specified by the property {@link #getTomcatHome()}.
	 * 
	 * @return
	 */
	public String getDefaultApplicationDeploymentArtifact();

	/**
	 * Returns the name of the public key used for authentication with
	 * Amazon.
	 * 
	 * @return
	 */
	public String getAmazonKeyName();

	/**
	 * Returns the name of the Amazon group to use (e.g., default)
	 * 
	 * @return
	 */
	public String getAmazonGroup();

	/**
	 * Returns the file system locations of the private SSH key used to deploy
	 * {@link AmazonApplicationInstance}s via scp.
	 * 
	 * @return
	 */
	public String getSSHPrivateKeyFile();

	/**
	 * Returns the user name used to deploy
	 * {@link AmazonApplicationInstance}s via scp.
	 * 
	 * @return
	 */
	public String getSSHUserName();

	/**
	 * Returns the file system location of the tomcat (e.g.,
	 * {@code /opt/tomcat/})
	 * 
	 * @return
	 */
	public String getTomcatHome();
}
