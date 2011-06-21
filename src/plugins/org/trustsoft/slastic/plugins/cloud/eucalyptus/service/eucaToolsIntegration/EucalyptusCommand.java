package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.eucaToolsIntegration;

/**
 * 
 * @author Florian Fittkau
 *
 */
public class EucalyptusCommand {
	private String commandString = "";

	public EucalyptusCommand(final String commandString) {
		this.commandString = commandString;
	}

	public String getCommandString() {
		return this.commandString;
	}
}
