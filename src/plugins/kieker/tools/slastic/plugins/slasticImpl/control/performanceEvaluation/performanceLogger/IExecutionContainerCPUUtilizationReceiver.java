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

package kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.tools.slastic.metamodel.executionEnvironment.Resource;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IExecutionContainerCPUUtilizationReceiver {

	public void update(final long currentTimestampMillis,
			final Resource resource, final Double user, final Double system,
			Double wait, Double nice, Double irq, Double combined, Double idle);
}
