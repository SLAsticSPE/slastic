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

package kieker.tools.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractApplicationInstance extends AbstractNamedEntity implements IApplicationInstance {

	private final ICloudedApplication application;
	private final IApplicationInstanceConfiguration configuration;
	private final ICloudNode node;

	/**
	 * Must not be used for construction.
	 */
	@SuppressWarnings("unused")
	private AbstractApplicationInstance() {
		super(null);
		this.application = null;
		this.configuration = null;
		this.node = null;
	}

	public AbstractApplicationInstance(final String name,
			final ICloudedApplication application, final IApplicationInstanceConfiguration configuration, final ICloudNode node) {
		super(name);
		this.application = application;
		this.configuration = configuration;
		this.node = node;
	}

	@Override
	public IApplicationInstanceConfiguration getConfiguration() {
		return this.configuration;
	}

	@Override
	public ICloudedApplication getApplication() {
		return this.application;
	}

	@Override
	public ICloudNode getNode() {
		return this.node;
	}
}
