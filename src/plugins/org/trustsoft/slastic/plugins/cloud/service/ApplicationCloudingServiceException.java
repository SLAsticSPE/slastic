/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.plugins.cloud.service;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
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
