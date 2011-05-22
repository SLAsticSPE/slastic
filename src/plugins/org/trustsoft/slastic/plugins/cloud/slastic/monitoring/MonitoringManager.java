// TODO: We should decide, whether this is really cloud-specific
// or could act as a general example, instead.

package org.trustsoft.slastic.plugins.cloud.slastic.monitoring;

import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.AbstractKiekerMonitoringManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.KiekerLogWriter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.MonitoringRecordConsumerFilterChain;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.CPUUtilizationRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.MemSwapUsageRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ResourceUtilizationRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

/**
 * 
 * 
 * @author Andre van Hoorn
 */
public class MonitoringManager extends AbstractKiekerMonitoringManager {

	private static final Log log = LogFactory.getLog(MonitoringManager.class);

	/**
	 * Delegates all Kieker framework events including records to the
	 * {@link #execRecordTransformation} filter and sends the resulting
	 * {@link OperationExecution}s to the {@link #getController()}.
	 */
	private final MonitoringRecordConsumerFilterChain monitoringRecordConsumerFilterChain =
			new MonitoringRecordConsumerFilterChain();

	@Override
	protected IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer() {
		return this.monitoringRecordConsumerFilterChain;
	}

	private volatile ExecutionRecordTransformationFilter execRecordTransformation;
	private volatile ResourceUtilizationRecordTransformationFilter resourceUtilizationRecordTransformation;
	private volatile CPUUtilizationRecordTransformationFilter cpuUtilizationRecordTransformationFilter;
	private volatile MemSwapUsageRecordTransformationFilter memSwapUsageRecordTransformationFilter;

	private static final String PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL =
			"component-discovery-level";
	private static final String PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS =
			"CLASS";
	private static final String PROP_VAL_COMPONENT_DISCOVERY_LEVEL_PACKAGE_STRICT =
			"PACKAGE_STRICT";

	/** Will be initialized in {@link #init()} */
	private volatile int componentDiscoveryHierarchyLevel;

	/**
	 * Matches the given String representation of the property value for
	 * {@value #PROP_NAME_COMPONENT_DISCOVERY_MODE} to the corresponding value
	 * {@link ExecutionRecordTransformationFilter.ComponentDiscoveryMode} and
	 * sets the private {@link #componentDiscoveryMode} field. If the given
	 * value is null, the default mode
	 * {@link ExecutionRecordTransformationFilter.ComponentDiscoveryMode#CLASS_NAME}
	 * will be used.
	 * 
	 * @return true on success; false otherwise
	 */
	private boolean initDiscoveryMode() {
		final String discoveryLevelPropValStr =
				super.getInitProperty(
						MonitoringManager.PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL,
						MonitoringManager.PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS);

		boolean success = true;

		if (discoveryLevelPropValStr
				.equals(MonitoringManager.PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS)) {
			this.componentDiscoveryHierarchyLevel =
					NameUtils.ABSTRACTION_MODE_CLASS;
		} else if (discoveryLevelPropValStr
				.equals(MonitoringManager.PROP_VAL_COMPONENT_DISCOVERY_LEVEL_PACKAGE_STRICT)) {
			this.componentDiscoveryHierarchyLevel =
					NameUtils.ABSTRACTION_MODE_PACKAGE_STRICT;
		} else {
			try {
				this.componentDiscoveryHierarchyLevel =
						Integer.parseInt(discoveryLevelPropValStr);
			} catch (final NumberFormatException exc) {
				MonitoringManager.log
						.error("Invalid property value for "
								+ MonitoringManager.PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL
								+ ": " + discoveryLevelPropValStr, exc);
				success = false;
			}
		}

		return success;
	}

	@Override
	public boolean init() {
		final boolean success = super.init() && this.initDiscoveryMode();

		return success;
	}

	@Override
	protected boolean concreteExecute() {
		/* Initialize filter chain for incoming records */

		/* Tee writer */
		final IComponentContext logWriterContext =
				this.getComponentContext().createSubcontext(
						KiekerLogWriter.class.getSimpleName());
		final KiekerLogWriter logWriter =
				new KiekerLogWriter(logWriterContext.getDirectoryLocation());
		this.monitoringRecordConsumerFilterChain
				.addSynchronousReceiver(logWriter);

		/* Actual filters: */
		this.monitoringRecordConsumerFilterChain.setControlComponent(this
				.getController());

		final ModelManager modelManager =
				(ModelManager) this.getController().getModelManager();

		this.execRecordTransformation =
				new ExecutionRecordTransformationFilter(modelManager,
						this.componentDiscoveryHierarchyLevel);
		this.resourceUtilizationRecordTransformation =
				new ResourceUtilizationRecordTransformationFilter(modelManager);
		this.cpuUtilizationRecordTransformationFilter =
				new CPUUtilizationRecordTransformationFilter(modelManager);
		this.memSwapUsageRecordTransformationFilter =
				new MemSwapUsageRecordTransformationFilter(modelManager);

		this.monitoringRecordConsumerFilterChain
				.addSynchronousFilter(this.execRecordTransformation);
		this.monitoringRecordConsumerFilterChain
				.addSynchronousFilter(this.resourceUtilizationRecordTransformation);
		this.monitoringRecordConsumerFilterChain
				.addSynchronousFilter(this.cpuUtilizationRecordTransformationFilter);
		this.monitoringRecordConsumerFilterChain
				.addSynchronousFilter(this.memSwapUsageRecordTransformationFilter);

		return true;
	}

	@Override
	protected void concreteTerminate(final boolean error) {
		MonitoringManager.log.debug("KiekerMonitoringManager now terminating");
	}
}
