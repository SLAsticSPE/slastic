package kieker.tools.slastic.plugins.cloud.common;

public abstract class AbstractCommand {
	private String commandString = "";

	public AbstractCommand(final String commandString) {
		this.commandString = commandString;
	}

	public String getCommandString() {
		return this.commandString;
	}
}
