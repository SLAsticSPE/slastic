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

package kieker.tools.slastic.control.exceptions;

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
	private static final Log LOG = LogFactory.getLog(CEPEngineExceptionLoggingHandler.class);

	private final ExceptionHandlerFactoryContext exceptionHandlerFactoryContext;

	@SuppressWarnings("unused")
	private CEPEngineExceptionLoggingHandler() {
		this.exceptionHandlerFactoryContext = null;
	}

	/**
	 * @param exceptionHandlerContext
	 */
	public CEPEngineExceptionLoggingHandler(final ExceptionHandlerFactoryContext exceptionHandlerFactoryContext) {
		this.exceptionHandlerFactoryContext = exceptionHandlerFactoryContext;
	}

	@Override
	public void handle(final ExceptionHandlerContext context) {
		LOG.error("CEP engine reported exception. ExceptionHandlerContext:" + context);
		LOG.error("ExceptionHandlerFactoryContext" + this.exceptionHandlerFactoryContext);

	}

}
