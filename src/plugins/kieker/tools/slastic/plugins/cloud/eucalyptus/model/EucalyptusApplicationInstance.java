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

package kieker.tools.slastic.plugins.cloud.eucalyptus.model;

import kieker.tools.slastic.plugins.cloud.model.AbstractApplicationInstance;
import kieker.tools.slastic.plugins.cloud.model.ICloudedApplication;

/**
 * An instance of an {@link ICloudedApplication} which can be deployed to
 * Eucalyptus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusApplicationInstance extends AbstractApplicationInstance {

	/**
	 * @param name
	 * @param configuration
	 */
	public EucalyptusApplicationInstance(final String name,
			final EucalyptusCloudedApplication application,
			final EucalyptusApplicationInstanceConfiguration configuration,
			final EucalyptusCloudNode node) {
		super(name, application, configuration, node);
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: ").append(this.getName());
		strB.append("; application: ").append(this.getApplication());
		strB.append("; configuration: ").append(this.getConfiguration());
		strB.append("; node").append(this.getNode());
		return strB.append("]").toString();
	}
}
