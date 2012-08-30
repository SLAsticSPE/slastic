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

package kieker.tools.slastic.plugins.slachecker.monitoring.kieker.probe.aspectJ.sla;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.core.registry.ControlFlowRegistry;
import kieker.monitoring.probe.IMonitoringProbe;
import kieker.tools.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe;
import kieker.tools.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * @author Andre van Hoorn
 */
@Aspect
public class SLAMonitoringProbe implements IMonitoringProbe {

	protected static final IMonitoringController ctrlInst = MonitoringController.getInstance();
	protected static final ControlFlowRegistry cfRegistry = ControlFlowRegistry.INSTANCE;
	private static final String vmName = SLAMonitoringProbe.ctrlInst.getHostname();

	protected SLOMonitoringRecord initMonitoringRecord(
			final ProceedingJoinPoint thisJoinPoint) {
		// e.g. "getBook"
		final String methodname = thisJoinPoint.getSignature().getName();
		// toLongString provides e.g.
		// "public kieker.tests.springTest.Book kieker.tests.springTest.CatalogService.getBook()"
		String paramList = thisJoinPoint.getSignature().toLongString();
		final int paranthIndex = paramList.lastIndexOf('(');
		paramList = paramList.substring(paranthIndex); // paramList is now e.g.,
														// "()"

		final SLOMonitoringRecord record = new SLOMonitoringRecord(
				thisJoinPoint.getSignature().getDeclaringTypeName() /* component */,
				methodname + paramList /* operation */,
				SLAMonitoringProbe.vmName);
		return record;
	}

	@Around(value = "execution(@kieker.tools.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe * *(..)) && @annotation(sla)", argNames = "thisJoinPoint,sla")
	public Object doBasicProfiling(final ProceedingJoinPoint thisJoinPoint,
			final SLAsticSLAMonitoringProbe sla) throws Throwable {
		if (!SLAMonitoringProbe.ctrlInst.isMonitoringEnabled()) {
			return thisJoinPoint.proceed();
		}
		final SLOMonitoringRecord record = this
				.initMonitoringRecord(thisJoinPoint);
		record.serviceId = sla.serviceId();
		try {
			this.proceedAndMeasure(thisJoinPoint, record);
		} catch (final Exception e) {
			throw e; // exceptions are forwarded
		} finally {
			/*
			 * note that proceedAndMeasure(...) even sets the variable name in
			 * case the execution of the joint point resulted in an exception!
			 */
			SLAMonitoringProbe.ctrlInst.newMonitoringRecord(record);
		}
		return record.retVal;
	}

	protected void proceedAndMeasure(final ProceedingJoinPoint thisJoinPoint,
			final SLOMonitoringRecord record) throws Throwable {
		record.timestamp = SLAMonitoringProbe.ctrlInst.getTimeSource().getTime(); // startint
		// stopwatch
		try {
			record.retVal = thisJoinPoint.proceed();
		} catch (final Exception e) {
			throw e; // exceptions are forwarded
		} finally {
			record.rtNseconds = SLAMonitoringProbe.ctrlInst.getTimeSource().getTime()
					- record.timestamp;
		}
	}
}
