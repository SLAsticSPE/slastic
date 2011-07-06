package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.agg;

import java.util.HashMap;
import java.util.Map;

import com.espertech.esper.epl.agg.AggregationSupport;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class AvgFunction implements IAggregationFunction {

	@Override
	public String createEPLExpression(final String arg) {
		return "avg(" + arg + ")";
	}

	@Override
	public String getEPLFunctionName() {
		return "avg";
	}

	@Override
	public Map<String, String> getFunctionParams() {
		return new HashMap<String, String>();
	}
	
	@Override
	public Class<? extends AggregationSupport> getEPLFunctionClass() {
		return null; // avg is a built-in function
	}
}
