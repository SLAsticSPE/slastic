package org.trustsoft.slastic.control.systemModel.exceptions;

public class ServiceIDDoesNotExistException extends Exception {
	public ServiceIDDoesNotExistException(){
		super("There couldn't be found a Service that belongs to the given ServiceID");
	}
}
