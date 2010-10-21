package org.trustsoft.slastic.control;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.events.ISimpleEventService;
import org.trustsoft.slastic.control.components.events.ISimpleEventServiceClient;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.control.exceptions.CEPEngineExceptionHandlerFactory;
import org.trustsoft.slastic.monitoring.IObservationEventReceiver;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.ConfigurationEventTypeLegacy;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.time.CurrentTimeEvent;
import com.espertech.esper.client.time.TimerControlEvent;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractControlComponent extends AbstractSLAsticComponent
		implements ISimpleEventService {

	private static final Log log = LogFactory
			.getLog(AbstractControlComponent.class);
	public static final String PROP_PREFIX = "slastic.control";
	private AbstractReconfigurationManagerComponent reconfigurationManager;
	private AbstractModelManagerComponent modelManager;
	private AbstractModelUpdaterComponent modelUpdater;
	private AbstractAnalysisComponent analysis;

	private final ArrayList<ISimpleEventServiceClient> listeners =
			new ArrayList<ISimpleEventServiceClient>();

	private final EPServiceProvider epServiceProvider;

	{
		/*
		 * We use a custom Esper configuration:
		 */
		final Configuration configuration = new Configuration();

		// /*
		// * Enable external time;
		// */
		// configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(false);

		/*
		 * Exclude some EMF classes from dynamic code generation and
		 * JavaBean-like access to members.
		 */
		final ConfigurationEventTypeLegacy legacyDef =
				new ConfigurationEventTypeLegacy();
		legacyDef
				.setCodeGeneration(ConfigurationEventTypeLegacy.CodeGeneration.DISABLED);
		legacyDef
				.setAccessorStyle(ConfigurationEventTypeLegacy.AccessorStyle.EXPLICIT);
		configuration.addEventType("EObject", EObject.class.getName(),
				legacyDef);
		configuration.addEventType("Notifier", Notifier.class.getName(),
				legacyDef);
		configuration.addEventType("EObjectImpl", EObjectImpl.class.getName(),
				legacyDef);
		configuration.addEventType("BasicEObjectImpl",
				BasicEObjectImpl.class.getName(), legacyDef);
		configuration.addEventType("BasicNotifierImpl",
				BasicNotifierImpl.class.getName(), legacyDef);
		configuration.addEventType("InternalEObject",
				InternalEObject.class.getName(), legacyDef);

		/* Register exception handler (factory class) */
		configuration.getEngineDefaults().getExceptionHandling()
				.addClass(CEPEngineExceptionHandlerFactory.class);
		
		/* Enable/disable path tracing */
//		configuration.getEngineDefaults().getLogging().setEnableExecutionDebug(true);
//		configuration.getEngineDefaults().getLogging().setEnableTimerDebug(true);

		this.epServiceProvider =
				EPServiceProviderManager.getProvider("custom", configuration);
		/* Enable external timing and set time to 0 */
		// TODO: enable/disable via parameters
		this.epServiceProvider.getEPRuntime().sendEvent(
				new TimerControlEvent(
						TimerControlEvent.ClockType.CLOCK_EXTERNAL));
		this.epServiceProvider.getEPRuntime()
				.sendEvent(new CurrentTimeEvent(0));
	}

	/**
	 * Sets the current time to the given value assumed to be the number of
	 * nanoseconds elapsed since January 1, 1970 (UTC).
	 * 
	 * @param timeInMillis
	 */
	public final void setCurrentTimeNanos(final long timeInNanos) {
		final long currentTimeMillis = timeInNanos / (1000 * 1000);
		this.setCurrentTimeMillis(currentTimeMillis);
	}

	/**
	 * Sets the current time to the given value assumed to be the number of
	 * milliseconds elapsed since January 1, 1970 (UTC).
	 * 
	 * @param timeInMillis
	 */
	public final void setCurrentTimeMillis(final long timeInMillis) {

		final CurrentTimeEvent timeEvent = new CurrentTimeEvent(timeInMillis);

		this.epServiceProvider.getEPRuntime().sendEvent(timeEvent);
	}

	/**
	 * Returns the current time in milliseconds since January 1, 1970 (UTC).
	 */
	public final void getCurrentTimeMillis() {
		this.epServiceProvider.getEPRuntime().getCurrentTime();
	}

	@Override
	public boolean addListener(final ISimpleEventServiceClient l) {
		return this.listeners.add(l);
	}

	public final AbstractAnalysisComponent getAnalysis() {
		return this.analysis;
	}

	public final EPServiceProvider getEPServiceProvider() {
		return this.epServiceProvider;
	}

	public final AbstractModelManagerComponent getModelManager() {
		return this.modelManager;
	}

	public final AbstractModelUpdaterComponent getModelUpdater() {
		return this.modelUpdater;
	}

	public abstract IObservationEventReceiver getMonitoringClientPort();

	public final AbstractReconfigurationManagerComponent getReconfigurationManager() {
		return this.reconfigurationManager;
	}

	@Override
	public boolean removeListener(final ISimpleEventServiceClient l) {
		return this.listeners.remove(l);
	}

	@Override
	public void sendEvent(final IEvent ev) {
		for (final ISimpleEventServiceClient l : this.listeners) {
			l.handleEvent(ev);
		}
	}

	public final void setAnalysis(final AbstractAnalysisComponent analysis) {
		this.analysis = analysis;
	}

	public final void setModelManager(
			final AbstractModelManagerComponent modelManager) {
		this.modelManager = modelManager;
	}

	public final void setModelUpdater(
			final AbstractModelUpdaterComponent modelUpdater) {
		this.modelUpdater = modelUpdater;
	}

	public final void setReconfigurationManager(
			final AbstractReconfigurationManagerComponent reconfigurationManager) {
		this.reconfigurationManager = reconfigurationManager;
	}
}
