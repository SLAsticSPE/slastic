package org.trustsoft.slastic.control.exceptions;

import com.espertech.esper.client.hook.ExceptionHandler;
import com.espertech.esper.client.hook.ExceptionHandlerFactory;
import com.espertech.esper.client.hook.ExceptionHandlerFactoryContext;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class CEPEngineExceptionHandlerFactory implements
		ExceptionHandlerFactory {

	@Override
	public ExceptionHandler getHandler(
			final ExceptionHandlerFactoryContext context) {
		return new CEPEngineExceptionLoggingHandler(context);
	}

}
