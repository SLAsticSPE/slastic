//TODO: We should decide, whether this is really cloud-specific
//        or could act as a general example, instead.

package org.trustsoft.slastic.plugins.cloud.slastic.monitoring;

import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
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
				new ExecutionRecordTransformationFilter(modelManager);
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
		MonitoringManager.log.info("KiekerMonitoringManager now terminating");
	}
}
