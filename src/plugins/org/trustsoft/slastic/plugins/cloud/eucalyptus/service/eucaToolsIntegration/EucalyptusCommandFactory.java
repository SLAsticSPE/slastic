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

package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.eucaToolsIntegration;

/**
 * 
 * @author Florian Fittkau
 * 
 */
public class EucalyptusCommandFactory {
	private final static String allocateNodeCommand = /* proxychains */"euca-run-instances && --key && KEY-NAME && --group && GROUP-NAME && ";
	private final static String startRemoteCommandCommand =
			"ssh && -i && SSH_PRIV_KEY && -o && StrictHostKeyChecking=no && SSH_USER_NAME@DESTIP && 'REMOTE-COMMAND'";
	private final static String deallocateNodeCommand = /* proxychains */"euca-terminate-instances && ";
	private final static String describeNodeCommand = /* proxychains */"euca-describe-instances && ";

	// needs SSH StrictHostKeyChecking disabled
	private final static String applicationDeployCommand = "scp && -i && SSH_PRIV_KEY && -o && StrictHostKeyChecking=no && SOURCEFILE && SSH_USER_NAME@DESTIP:TOMCAT_HOME";
	private final static String applicationUndeployCommand = "ls"; // TODO fixme

	private final static String cpKiekerConfigCommand = "scp && -i && SSH_PRIV_KEY && -o && StrictHostKeyChecking=no && SOURCEFILE && SSH_USER_NAME@DESTIP:TOMCAT_HOME";

	private final static String fetchWebSiteCommand = "wget && URL";

	private EucalyptusCommandFactory() {}

	public static EucalyptusCommand getAllocateNodeCommand(
			final String emiNumber, final String keyName, final String group) {
		String command = EucalyptusCommandFactory.allocateNodeCommand + emiNumber;
		command = command.replaceAll("KEY-NAME", keyName);
		command = command.replaceAll("GROUP-NAME", group);
		return new EucalyptusCommand(command);

	}

	public static EucalyptusCommand getDescribeNodeCommand(final String instanceID) {
		return new EucalyptusCommand(EucalyptusCommandFactory.describeNodeCommand + instanceID);
	}

	public static EucalyptusCommand getDeallocateNodeCommand(
			final String instanceID) {
		return new EucalyptusCommand(EucalyptusCommandFactory.deallocateNodeCommand + instanceID);
	}

	public static EucalyptusCommand getApplicationDeployCommand(
			final String sshPrivKey, final String sshUserName,
			final String tomcatHome, final String warFile,
			final String instanceIP) {
		String command = EucalyptusCommandFactory.applicationDeployCommand;

		command = command.replace("SSH_PRIV_KEY", sshPrivKey);
		command = command.replace("SSH_USER_NAME", sshUserName);
		command = command.replace("TOMCAT_HOME", tomcatHome);
		command = command.replace("SOURCEFILE", warFile);
		command = command.replace("DESTIP", instanceIP);

		return new EucalyptusCommand(command);
	}

	public static EucalyptusCommand getCopyKiekerConfigCommand(
			final String sshPrivKey, final String sshUserName,
			final String tomcatHome, final String warFile,
			final String instanceIP) {
		String command = EucalyptusCommandFactory.cpKiekerConfigCommand;

		command = command.replace("SSH_PRIV_KEY", sshPrivKey);
		command = command.replace("SSH_USER_NAME", sshUserName);
		command = command.replace("TOMCAT_HOME", tomcatHome);
		command = command.replace("SOURCEFILE", warFile);
		command = command.replace("DESTIP", instanceIP);

		return new EucalyptusCommand(command);
	}

	public static EucalyptusCommand getFetchWebSiteCommand(final String url) {
		String command = EucalyptusCommandFactory.fetchWebSiteCommand;

		command = command.replace("URL", url);

		return new EucalyptusCommand(command);
	}

	public static EucalyptusCommand getStartTomcatCommand(
			final String sshPrivKey, final String sshUserName,
			final String instanceIP, final String tomcatStartScript) {
		return EucalyptusCommandFactory.getStartRemoteCommandCommand(sshPrivKey, sshUserName, instanceIP, tomcatStartScript);
	}

	// TODO: turn /bin/hostname into property

	public static EucalyptusCommand getFetchHostnameCommand(
			final String sshPrivKey, final String sshUserName,
			final String instanceIP) {
		return EucalyptusCommandFactory.getStartRemoteCommandCommand(sshPrivKey, sshUserName, instanceIP, "/bin/hostname");
	}

	public static EucalyptusCommand getStartRemoteCommandCommand(
			final String sshPrivKey, final String sshUserName,
			final String instanceIP, final String remoteCommand) {
		String command = EucalyptusCommandFactory.startRemoteCommandCommand;

		command = command.replace("SSH_PRIV_KEY", sshPrivKey);
		command = command.replace("SSH_USER_NAME", sshUserName);
		command = command.replace("DESTIP", instanceIP);
		command = command.replace("REMOTE-COMMAND", remoteCommand);

		return new EucalyptusCommand(command);
	}

	public static EucalyptusCommand getApplicationUndeployCommand(final String warFile, final String instanceIP) {
		String command = EucalyptusCommandFactory.applicationUndeployCommand;

		command = command.replace("SOURCEFILE", warFile);
		command = command.replace("DESTIP", instanceIP);

		return new EucalyptusCommand(command);
	}
}
