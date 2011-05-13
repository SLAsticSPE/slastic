package org.trustsoft.slastic.plugins.cloud.service;

public class ApplicationCloudingServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7869451878655306894L;
	
	/**
	 * Creates a new {@link ApplicationCloudingServiceException}.
	 */
	public ApplicationCloudingServiceException() {
		super();
	}
	
	/**
	 * Creates a new {@link ApplicationCloudingServiceException} with the 
	 * given message.
	 */
	public ApplicationCloudingServiceException(final String message) {
		super(message);
	}
	
	/**
	 * Creates a new {@link ApplicationCloudingServiceException} with the 
	 * given message and cause.
	 */
	public ApplicationCloudingServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
