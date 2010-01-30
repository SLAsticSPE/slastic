package org.trustsoft.slastic.plugins.pcm.control.modelManager;

public class ServerNotAllocatedException extends Exception {
	public ServerNotAllocatedException(){
		super("The given Server cannot be used, because it is not allocated. Try using the Allocate-Operation.");
	}
}
