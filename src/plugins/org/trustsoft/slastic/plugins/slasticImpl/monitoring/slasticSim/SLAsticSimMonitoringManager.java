package org.trustsoft.slastic.plugins.slasticImpl.monitoring.slasticSim;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.OperationExecutionRecord;
import kieker.common.util.LoggingTimestampConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.pcm.control.analysis.AdaptationPlannerBookstoreSamplePlan;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.AbstractKiekerMonitoringManager;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.UtilizationRecord;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimMonitoringManager extends AbstractKiekerMonitoringManager {
    private static final Log log = LogFactory.getLog(SLAsticSimMonitoringManager.class);

    @Override
    protected IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer() {
        return new IMonitoringRecordConsumerPlugin() {

            @Override
            public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
                return null;
            }

            volatile long minLoggingTimestamp = Long.MAX_VALUE;
            volatile boolean planSent = false;
            final int sendPlanAtElapsedSecond = 30;

            private long computeSecondsElapsed (final long currentTimeStamp){
                return (currentTimeStamp - minLoggingTimestamp) / (1000*1000*1000);
            }

            @Override
            public boolean newMonitoringRecord(IMonitoringRecord record) {
                if (record instanceof OperationExecutionRecord){

                } else if (record instanceof UtilizationRecord) {
                    UtilizationRecord utilRec = (UtilizationRecord) record;
                    log.info("Received utilization:"
                            + LoggingTimestampConverter.convertLoggingTimestampToUTCString(utilRec.getTime())
                            + "-- " + utilRec.getUtilization());
                    if (record.getLoggingTimestamp() < minLoggingTimestamp){
                        this.minLoggingTimestamp = utilRec.getTime();
                    }
                    long secondsElapsed = this.computeSecondsElapsed(utilRec.getTime());
                    log.info("Seconds elapsed: " + secondsElapsed);

                    if (secondsElapsed >= sendPlanAtElapsedSecond && ! planSent){
                        try {
                            planSent = true;
                            log.info(
                                    LoggingTimestampConverter.convertLoggingTimestampToUTCString(utilRec.getTime()) +
                                    ": Sending plan ... ");
                            AdaptationPlannerBookstoreSamplePlan planSender = (AdaptationPlannerBookstoreSamplePlan) getController().getAnalysis().getAdaptationPlanner();
                            planSender.schwing();
                        } catch (ReconfigurationException ex) {
                            log.error(
                                    LoggingTimestampConverter.convertLoggingTimestampToUTCString(utilRec.getTime()) +
                                    ": Plan Failed");
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean execute() {
                return true;
            }

            @Override
            public void terminate(boolean bln) { }
        };
    }

    @Override
    protected boolean concreteExecute() {
        return true;
    }

    @Override
    public void terminate(boolean error) { }

}
