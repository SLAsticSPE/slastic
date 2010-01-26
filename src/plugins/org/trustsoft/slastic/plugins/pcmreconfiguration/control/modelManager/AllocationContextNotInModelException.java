package org.trustsoft.slastic.plugins.pcmreconfiguration.control.modelManager;

public class AllocationContextNotInModelException extends Exception {
	public AllocationContextNotInModelException() {
		super("The given component does not belong to the current model.");
	}
}
