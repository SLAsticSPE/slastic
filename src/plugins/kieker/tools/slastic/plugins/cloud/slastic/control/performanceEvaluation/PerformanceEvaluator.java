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

package kieker.tools.slastic.plugins.cloud.slastic.control.performanceEvaluation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPServiceProvider;

import kieker.tools.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import kieker.tools.slastic.control.components.events.IEvent;
import kieker.tools.slastic.plugins.cloud.slastic.control.performanceEvaluation.performanceLogger.PerformanceLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.IPerformanceLogger;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class PerformanceEvaluator extends AbstractPerformanceEvaluatorComponent {

	private static final Log LOG = LogFactory.getLog(PerformanceEvaluator.class);

	public static final String PROPERTY_IO_AUTO_FLUSH = "ioAutoFlush";
	public static final String PROPERTY_WIN_TIME_SEC = "winTimeSeconds";
	public static final String PROPERTY_OUTPUT_INTERVAL_SEC = "outputIntervalSeconds";
	public static final String PROPERTY_PERFORMANCE_LOGGER_CLASSES = "performanceLoggerClasses";

	private volatile boolean ioAutoFlush = true;
	private volatile int winTimeSec = 60;
	private volatile int outputIntervalSec = 60;
	private final List<String> loggerClassesToActivate = new ArrayList<String>();

	private volatile EPServiceProvider epServiceProvider;

	private volatile IPerformanceLogger performanceLogger;

	@Override
	public void handleEvent(final IEvent ev) {
		throw new UnsupportedOperationException("Event handler not implemented");
	}

	@Override
	public boolean init() {
		this.initLoggerClasses();
		this.ioAutoFlush = Boolean.parseBoolean(this.getInitProperty(PROPERTY_IO_AUTO_FLUSH, Boolean.toString(this.ioAutoFlush)));
		LOG.info(PROPERTY_IO_AUTO_FLUSH + ": " + this.ioAutoFlush);
		this.winTimeSec = Integer.parseInt(this.getInitProperty(PROPERTY_WIN_TIME_SEC, Integer.toString(this.winTimeSec)));
		LOG.info(PROPERTY_WIN_TIME_SEC + ": " + this.winTimeSec);
		this.outputIntervalSec = Integer.parseInt(this.getInitProperty(PROPERTY_OUTPUT_INTERVAL_SEC, Integer.toString(this.outputIntervalSec)));
		LOG.info(PROPERTY_OUTPUT_INTERVAL_SEC + ": " + this.outputIntervalSec);
		return true;
	}

	private void initLoggerClasses() {
		final String[] loggerClassnames = this.getInitProperties(PROPERTY_PERFORMANCE_LOGGER_CLASSES, new String[] {});
		for (final String clazz : loggerClassnames) {
			this.loggerClassesToActivate.add(clazz);
		}
	}

	@Override
	public boolean execute() {
		this.epServiceProvider = this.getParentAnalysisComponent().getParentControlComponent().getEPServiceProvider();
		this.performanceLogger =
				new PerformanceLogger(this.epServiceProvider, this.getComponentContext().createSubcontext(
						PerformanceLogger.class.getSimpleName()), this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush, this.loggerClassesToActivate);
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		if (this.performanceLogger != null) {
			this.performanceLogger.closeLogs();
		}
	}
}
