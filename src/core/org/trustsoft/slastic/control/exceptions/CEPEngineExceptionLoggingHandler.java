package org.trustsoft.slastic.control.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.hook.ExceptionHandler;
import com.espertech.esper.client.hook.ExceptionHandlerContext;
import com.espertech.esper.client.hook.ExceptionHandlerFactoryContext;

/**
 * Logs the String representation of received CEP exceptions.
 * 
 * @author Andre van Hoorn
 * 
 */
public class CEPEngineExceptionLoggingHandler implements ExceptionHandler {
	private static final Log log = LogFactory
			.getLog(CEPEngineExceptionLoggingHandler.class);

	private final ExceptionHandlerFactoryContext exceptionHandlerFactoryContext;

	@SuppressWarnings("unused")
	private CEPEngineExceptionLoggingHandler() {
		this.exceptionHandlerFactoryContext = null;
	}

	/**
	 * @param exceptionHandlerContext
	 */
	public CEPEngineExceptionLoggingHandler(
			final ExceptionHandlerFactoryContext exceptionHandlerFactoryContext) {
		this.exceptionHandlerFactoryContext = exceptionHandlerFactoryContext;
	}

	@Override
	public void handle(final ExceptionHandlerContext context) {
		CEPEngineExceptionLoggingHandler.log
				.error("CEP engine reported exception. ExceptionHandlerContext:"
						+ context);
		CEPEngineExceptionLoggingHandler.log
				.error("ExceptionHandlerFactoryContext"
						+ this.exceptionHandlerFactoryContext);

	}

}
