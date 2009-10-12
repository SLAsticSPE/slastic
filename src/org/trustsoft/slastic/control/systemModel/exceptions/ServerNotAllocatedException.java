package org.trustsoft.slastic.control.systemModel.exceptions;

public class ServerNotAllocatedException extends Exception {
	public ServerNotAllocatedException(){
		super("The given Server cannot be used, because it is not allocated. Try using the Allocate-Operation.");
	}
}
