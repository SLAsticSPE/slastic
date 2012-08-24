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

package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.agg;

import java.util.Map;

import com.espertech.esper.epl.agg.AggregationSupport;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IAggregationFunction {
	public String createEPLExpression(final String arg);

	/**
	 * Returns the name to be used for registering the function to the CEP
	 * engine.
	 * 
	 * @return
	 */
	public String getEPLFunctionName();

	/**
	 * Returns the class implementing the aggregation function to be registered
	 * or null, of a built-in function shall be used which needn't be
	 * registered.
	 * 
	 * @return
	 */
	public Class<? extends AggregationSupport> getEPLFunctionClass();

	/**
	 * Returns the key/value pairs of this function's parameters.
	 * For example, the {@link AvgFunction} returns an empty map, 
	 * since it is not parameterized but the PercentileFunction
	 * instance used to computer the 90% percentile returns a 
	 * map with the key/value pair with <code>p=90</code>. 
	 * 
	 * @return
	 */
	public Map<String, String> getFunctionParams();
}
