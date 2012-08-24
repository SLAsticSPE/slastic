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

/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.eucalyptus.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudedApplication;

/**
 * An application which can be deployed to Eucalyptus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusCloudedApplication extends AbstractCloudedApplication {

	private static final AtomicInteger nextInstanceID = new AtomicInteger(0);

	/**
	 * @param name
	 * @param configuration
	 */
	public EucalyptusCloudedApplication(final String name, final EucalyptusCloudedApplicationConfiguration configuration) {
		super(name, configuration);
	}

	/**
	 * Returns a unique ID which can be used to identify {@link EucalyptusApplicationInstance} associated with this {@link EucalyptusCloudedApplication}.
	 * 
	 * @return
	 */
	public int acquireInstanceId() {
		return EucalyptusCloudedApplication.nextInstanceID.getAndIncrement();
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: " + this.getName());
		strB.append("; configuration: ").append(this.getConfiguration());
		return strB.append("]").toString();
	}
}
