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
