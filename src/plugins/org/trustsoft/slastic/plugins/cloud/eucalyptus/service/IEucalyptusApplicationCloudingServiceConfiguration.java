package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import java.util.Collection;
import java.util.HashMap;

import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.loadBalancerServlet.LoadBalancerServlet;

public interface IEucalyptusApplicationCloudingServiceConfiguration {

	/**
	 * Returns the debug configuration value.
	 * 
	 * @return
	 */
	public boolean isDebugEnabled();

	/**
	 * In the dummy mode, the operation only claim to call the Eucalyptus tools
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
	 * Returns the file system location of this Eucalyptus tools (e.g.
	 * {@code /usr/local/bin/}).
	 * 
	 * @return
	 */
	public String getEucatoolsPath();

	/**
	 * Returns a map of image names x EMI.
	 * 
	 * @return
	 */
	public HashMap<String, String> getEMIs();

	/**
	 * Returns a list of node instances to create initially.
	 * Each element of the returned collection is a String array 
	 * with 3 elements: node name, ip, image name 
	 * 
	 * @return
	 * @see IEucalyptusApplicationCloudingServiceConfiguration#getEMIs()
	 */
	public Collection<String[]> getInitialNodeInstances(); 
	
	/**
	 * Returns a list of application to create initially.
	 * 
	 * @return
	 */
	public Collection<String> getInitialApplications();
	
	/**
	 * Returns a list of application instances to create initially.
	 * Each element of the returned collection is a String array
	 * with 2 elements: application name, node instance
	 * 
	 * @return
	 */
	public Collection<String[]> getInitialApplicationInstances();
	
	/**
	 * Returns the name of the public key used for authentication with
	 * Eucalyptus.
	 * 
	 * @return
	 */
	public String getEucalyptusKeyName();

	/**
	 * Returns the name of the Eucalyptus group to use (e.g., default)
	 * 
	 * @return
	 */
	public String getEucalyptusGroup();

	/**
	 * Returns the file system locations of the private SSH key used to deploy
	 * {@link EucalyptusApplicationInstance}s via scp.
	 * 
	 * @return
	 */
	public String getSSHPrivateKeyFile();

	/**
	 * Returns the user name used to deploy
	 * {@link EucalyptusApplicationInstance}s via scp.
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
