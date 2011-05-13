package org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.probe.aspectJ.sla;

import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.core.registry.ControlFlowRegistry;
import kieker.monitoring.probe.IMonitoringProbe;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * @author Andre van Hoorn
 */
@Aspect
public class SLAMonitoringProbe implements IMonitoringProbe {

	protected static final IMonitoringController ctrlInst = MonitoringController
			.getInstance();
	protected static final ControlFlowRegistry cfRegistry = ControlFlowRegistry
			.getInstance();
	private static final String vmName = SLAMonitoringProbe.ctrlInst
			.getHostName();

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

	@Around(value = "execution(@org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe * *(..)) && @annotation(sla)", argNames = "thisJoinPoint,sla")
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
