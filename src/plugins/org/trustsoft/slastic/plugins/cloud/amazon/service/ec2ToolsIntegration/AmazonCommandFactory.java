package org.trustsoft.slastic.plugins.cloud.amazon.service.ec2ToolsIntegration;

/**
 * 
 * @author Florian Fittkau
 *
 */
public class AmazonCommandFactory {
	private final static String allocateNodeCommand =
		/* proxychains */"ec2-run-instances && --key && KEY-NAME && --group && GROUP-NAME && ";
	private final static String startRemoteCommandCommand =
			"ssh && -i && SSH_PRIV_KEY && -o && StrictHostKeyChecking=no && SSH_USER_NAME@DESTIP && 'REMOTE-COMMAND'";
	private final static String deallocateNodeCommand =
		/* proxychains */"ec2-terminate-instances && ";
	private final static String describeNodeCommand =
		/* proxychains */"ec2-describe-instances && ";

	// needs SSH StrictHostKeyChecking disabled
	private final static String applicationDeployCommand =
			"scp && -i && SSH_PRIV_KEY && -o && StrictHostKeyChecking=no && SOURCEFILE && SSH_USER_NAME@DESTIP:TOMCAT_HOME" + "webapps/";
	private final static String applicationUndeployCommand = "ls"; // TODO fixme

	private final static String cpKiekerConfigCommand =
		"scp && -i && SSH_PRIV_KEY && -o && StrictHostKeyChecking=no && SOURCEFILE && SSH_USER_NAME@DESTIP:TOMCAT_HOME" + "lib/META-INF/";
	
	private final static String fetchWebSiteCommand =
		"wget && URL";
	
	private AmazonCommandFactory() {
	}

	public static AmazonCommand getAllocateNodeCommand(
			final String emiNumber, final String keyName, final String group) {
		String command =
				AmazonCommandFactory.allocateNodeCommand + emiNumber;
		command = command.replaceAll("KEY-NAME", keyName);
		command = command.replaceAll("GROUP-NAME", group);
		return new AmazonCommand(command);

	}

	public static AmazonCommand getDescribeNodeCommand(
			final String instanceID) {
		return new AmazonCommand(
				AmazonCommandFactory.describeNodeCommand + instanceID);
	}

	public static AmazonCommand getDeallocateNodeCommand(
			final String instanceID) {
		return new AmazonCommand(
				AmazonCommandFactory.deallocateNodeCommand + instanceID);
	}

	public static AmazonCommand getApplicationDeployCommand(
			final String sshPrivKey, final String sshUserName,
			final String tomcatHome, final String warFile,
			final String instanceIP) {
		String command = AmazonCommandFactory.applicationDeployCommand;

		command = command.replace("SSH_PRIV_KEY", sshPrivKey);
		command = command.replace("SSH_USER_NAME", sshUserName);
		command = command.replace("TOMCAT_HOME", tomcatHome);
		command = command.replace("SOURCEFILE", warFile);
		command = command.replace("DESTIP", instanceIP);

		return new AmazonCommand(command);
	}
	
	public static AmazonCommand getCopyKiekerConfigCommand(
			final String sshPrivKey, final String sshUserName,
			final String tomcatHome, final String kiekerFile,
			final String instanceIP) {
		String command = AmazonCommandFactory.cpKiekerConfigCommand;

		command = command.replace("SSH_PRIV_KEY", sshPrivKey);
		command = command.replace("SSH_USER_NAME", sshUserName);
		command = command.replace("TOMCAT_HOME", tomcatHome);
		command = command.replace("SOURCEFILE", kiekerFile);
		command = command.replace("DESTIP", instanceIP);

		return new AmazonCommand(command);
	}

	public static AmazonCommand getFetchWebSiteCommand(
			final String url) {	
		String command = AmazonCommandFactory.fetchWebSiteCommand;
		
		command = command.replace("URL", url);
			
		return new AmazonCommand(command);
	}
	
	public static AmazonCommand getStartTomcatCommand(
			final String sshPrivKey, final String sshUserName,
			final String instanceIP, final String tomcatStartScript) {
		return AmazonCommandFactory.getStartRemoteCommandCommand(sshPrivKey, sshUserName,
				instanceIP, tomcatStartScript);
	}
	
	// TODO: turn /bin/hostname into property
	
	public static AmazonCommand getFetchHostnameCommand(
			final String sshPrivKey, final String sshUserName,
			final String instanceIP) {
		return AmazonCommandFactory.getStartRemoteCommandCommand(sshPrivKey, sshUserName,
				instanceIP, "/bin/hostname");
	}
	
	public static AmazonCommand getStartRemoteCommandCommand(
			final String sshPrivKey, final String sshUserName,
			final String instanceIP, final String remoteCommand) {
		String command = AmazonCommandFactory.startRemoteCommandCommand;

		command = command.replace("SSH_PRIV_KEY", sshPrivKey);
		command = command.replace("SSH_USER_NAME", sshUserName);
		command = command.replace("DESTIP", instanceIP);
		command = command.replace("REMOTE-COMMAND", remoteCommand);

		return new AmazonCommand(command);
	}

	public static AmazonCommand getApplicationUndeployCommand(
			final String warFile, final String instanceIP) {
		String command = AmazonCommandFactory.applicationUndeployCommand;

		command = command.replace("SOURCEFILE", warFile);
		command = command.replace("DESTIP", instanceIP);

		return new AmazonCommand(command);
	}
}
