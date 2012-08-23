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

package org.trustsoft.slastic.plugins.cloud.slastic.control.performanceEvaluation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.cloud.slastic.control.performanceEvaluation.performanceLogger.PerformanceLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.IPerformanceLogger;

import com.espertech.esper.client.EPServiceProvider;

public class PerformanceEvaluator extends AbstractPerformanceEvaluatorComponent {

	private static final Log log = LogFactory.getLog(PerformanceEvaluator.class);

	private volatile EPServiceProvider epServiceProvider;

	private volatile IPerformanceLogger performanceLogger;

	@Override
	public void handleEvent(final IEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean init() {
		return true;
	}

	// TODO: to be configured via properties
	private final int winTimeSec = 60;
	private final int outputIntervalSec = 15;

	@Override
	public boolean execute() {
		this.epServiceProvider = this.getParentAnalysisComponent().getParentControlComponent().getEPServiceProvider();
		this.performanceLogger =
				new PerformanceLogger(this.epServiceProvider, this.getComponentContext().createSubcontext(
						PerformanceLogger.class.getSimpleName()), this.winTimeSec, this.outputIntervalSec);
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		if (this.performanceLogger != null) {
			this.performanceLogger.closeLogs();
		}
	}
}