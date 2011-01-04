package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class AbstractPerformanceLogger {
	private static final Log log = LogFactory.getLog(AbstractPerformanceLogger.class);

	private volatile EPServiceProvider epServiceProvider;
	private final IComponentContext context;

	private final Collection<AbstractPerformanceMeasureLogger<?>> performanceMeasureLoggers =
			new ArrayList<AbstractPerformanceMeasureLogger<?>>();

	public AbstractPerformanceLogger(final EPServiceProvider epServiceProvider,
			final IComponentContext context) {
		this.epServiceProvider = epServiceProvider;
		this.context = context;
	}

	/**
	 * 
	 * @param loggerClasses
	 */
	protected final void startLoggersByClassname(
			final Collection<Class<? extends AbstractPerformanceMeasureLogger<?>>> loggerClasses) {
		for (final Class<? extends AbstractPerformanceMeasureLogger<?>> lc : loggerClasses) {
			final AbstractPerformanceMeasureLogger<?> logger =
					this.createLoggerFromClass(lc);
			this.addAndRegisterLoggerAsSubscriber(logger);
		}
	}

	/**
	 * 
	 * @param loggerClass
	 * @return
	 */
	private AbstractPerformanceMeasureLogger<?> createLoggerFromClass(
			final Class<? extends AbstractPerformanceMeasureLogger<?>> loggerClass) {
		AbstractPerformanceMeasureLogger<?> loggerInst = null;
		try {
			final Constructor<? extends AbstractPerformanceMeasureLogger<?>> con =
					loggerClass.getConstructor(IComponentContext.class);
			loggerInst =
					con.newInstance(this.context.createSubcontext(loggerClass
							.getName()));
		} catch (final Exception e) {
			AbstractPerformanceLogger.log.error(
					"Error instantiating logger:" + e.getMessage(), e);
		}
		return loggerInst;
	}

	/**
	 * 
	 * @param logger
	 */
	protected final void addAndRegisterLoggerAsSubscriber(
			final AbstractPerformanceMeasureLogger<?> logger) {
		final String epStatementStr = logger.createEPStatement();
		final EPStatement epStatement =
				this.epServiceProvider.getEPAdministrator().createEPL(
						epStatementStr);
		epStatement.setSubscriber(logger);
		this.performanceMeasureLoggers.add(logger);
	}

	/**
	 * 
	 */
	public final void closeLogs() {
		for (final AbstractPerformanceMeasureLogger<?> l : this.performanceMeasureLoggers) {
			l.closePrintWriters();
		}
	}
}
