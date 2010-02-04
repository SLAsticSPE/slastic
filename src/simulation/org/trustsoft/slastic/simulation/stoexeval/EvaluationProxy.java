package org.trustsoft.slastic.simulation.stoexeval;

import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class EvaluationProxy {

	public static Object evaluate(final String string,
			final Class<?> expectedType,
			final SimulatedStackframe<Object> stackframe) {
		if (stackframe != null) {
			return StackContext
					.evaluateStatic(string, expectedType, stackframe);
		} else {
			return StackContext.evaluateStatic(string, expectedType);
		}
	}
}
