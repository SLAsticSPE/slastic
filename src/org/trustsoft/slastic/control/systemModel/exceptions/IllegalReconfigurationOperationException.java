package org.trustsoft.slastic.control.systemModel.exceptions;

public class IllegalReconfigurationOperationException extends Exception {
	public IllegalReconfigurationOperationException() {
		super("The ReconfigurationPlan contains a illegal Operation");
	}
}
