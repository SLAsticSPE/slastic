package org.trustsoft.slastic.control.exceptions;

public class IllegalReconfigurationOperationException extends Exception {
	public IllegalReconfigurationOperationException() {
		super("The ReconfigurationPlan contains a illegal Operation");
	}
}
