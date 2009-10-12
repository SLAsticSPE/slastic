package org.trustsoft.slastic.control.systemModel.exceptions;

public class AllocationContextNotInModelException extends Exception {
	public AllocationContextNotInModelException() {
		super("The given component does not belong to the current model.");
	}
}
