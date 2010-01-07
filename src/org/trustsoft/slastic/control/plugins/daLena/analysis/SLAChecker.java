/**
 * @author Lena
 */
package org.trustsoft.slastic.control.plugins.daLena.analysis;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluator;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.ISLAsticEvent;
import org.trustsoft.slastic.control.exceptions.ServiceIDDoesNotExistException;

import org.trustsoft.slastic.control.plugins.daLena.modelManager.ModelManager;
import slal.Model;
import slal.SLO;

/**
 * Implementation of the Performance Analyzer component of the SLAstic.CONTROL
 * framework. It checks out if given SLAs are violated. In that case it sends a
 * SLAViolationEvent to its Analysis component. This delegates it to the
 * responsible component.
 * It visualizes the SLAs and the calculated quantiles dynamically within a plot for each SLO of the SLAs
 * 
 * @author Lena Stoever
 * 
 */
public class SLAChecker extends AbstractPerformanceEvaluator {

    private static final Log log = LogFactory.getLog(SLAChecker.class);
    private QuantileCalculator quantileCalc;
    private slal.Model slas = null;
    private SLACheckerGUI[] guis;
    private int[] serviceIDs;
    private ISLAsticAnalysis ana;
    private ScheduledThreadPoolExecutor ex;

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far.
    }

    /**
     * Delegating the calculation to the QuantileCalculator object.
     * @param quantile array for identifying the quantiles that should be calculated
     * @param id serviceID of the service for which the quantiles should be calculated.
     * @return array with the values of the quantiles
     * @throws ServiceIDDoesNotExistException is thrown if there is no SLO for the given ID
     */
    private long[] getQuantilResponseTime(float[] quantile, int id)
            throws ServiceIDDoesNotExistException {
        //Delegation to the Quantile Calculator
        long[] rt = this.quantileCalc.getResponseTimeForQuantiles(quantile, id);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        //updating GUI
        for (int i = 0; i < this.serviceIDs.length; i++) {
            if (id == this.serviceIDs[i]) {
                guis[i].addResponseTime(rt);
                return rt;
            }
        }
        return rt;
    }

    private void startThreadPool() {
        // this.averageCalcThread = new
        // AverageCalculatorThread(this.responseTimes);
        // averageCalcThread.start();
        EList<SLO> slaslo = slas.getObligations().getSlo();
        ex = new ScheduledThreadPoolExecutor(slas.getObligations().getSlo().size());
        // final DateFormat m_ISO8601Local = new
        // SimpleDateFormat("yyyyMMdd'-'HHmmss");
        for (int i = 0; i < slaslo.size(); i++) {
            final int ID = slaslo.get(i).getServiceID();
            //RT_QUANTILE_TYPE is the only type that is supported at the moment
            if (slaslo.get(i).getType() == slal.Type.RT_QUANTILE_TYPE) {
                int size = slaslo.get(i).getValue().getPair().size();
                final float[] quantile = new float[size];
                final int[] responseTimes = new int[size];
                for (int k = 0; k < size; k++) {
                    float q = (slaslo.get(i).getValue().getPair().get(k).getQuantile() / 100.0f);
                    quantile[k] = q;

                    int responseTime = slaslo.get(i).getValue().getPair().get(k).getResponseTime();
                    responseTimes[k] = responseTime;
                }
                final SLO slo = slaslo.get(i);
                //checking SLAs in periodically
                ex.scheduleAtFixedRate(
                        new Runnable() {

                            public void run() {
                                long[] responseTimes = null;
                                try {
                                    responseTimes = getQuantilResponseTime(
                                            quantile, ID);
                                } catch (ServiceIDDoesNotExistException e) {
                                    e.printStackTrace();
                                }
                                for (int j = 0; j < responseTimes.length; j++) {
                                    int responseTime2 = slo.getValue().getPair().get(j).getResponseTime();
                                    if (responseTimes[j] > responseTime2) {
                                        //if response time quantile violates SLA, send an event to the Analysis component.
                                        SLAViolationEvent evt = new SLAViolationEvent(ID);
                                        ana.handleInternalEvent(evt);
                                        // log.info("SLAViolation sent");
                                    }

                                }
                            }
                        }, (1000 / (slaslo.size())) + i * 1000, 1000,
                        TimeUnit.MILLISECONDS);
            } else {
                log.error("No handling for this SLA-Type available");

            }
        }
    }

    public void terminate() {
        /*
         * In case we spawned a thread in execute(), we get the chance to kill
         * it here.
         */
        // averageCalcThread.terminate();
        this.quantileCalc.terminate();
        for (int i = 0; guis != null && i < guis.length; i++) {
            guis[i].terminate();
        }
        if (ex != null) {
            ex.shutdownNow();
        }
    }

    public boolean execute() {
        if (!this.stateIsValid()){
            log.error("determined invalid state");
            return false;
        }

        this.quantileCalc = new QuantileCalculator((ModelManager)this.getParentAnalysisComponent().getParentControlComponent().getModelManager());

        //Initialize GUIs and set of service IDs
        guis = new SLACheckerGUI[this.slas.getObligations().getSlo().size()];
        serviceIDs = new int[this.slas.getObligations().getSlo().size()];
        for (int i = 0; i < this.slas.getObligations().getSlo().size(); i++) {
            long[] quantiles = new long[this.slas.getObligations().getSlo().get(i).getValue().getPair().size()];
            for (int k = 0; k < this.slas.getObligations().getSlo().get(i).getValue().getPair().size(); k++) {
                quantiles[k] = this.slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getResponseTime();
            }
            guis[i] = new SLACheckerGUI(
                    "ServiceID: " + this.slas.getObligations().getSlo().get(i).getServiceID(), 30000, quantiles[0],
                    quantiles[1], quantiles[2]);
            guis[i].paint();
            serviceIDs[i] = this.slas.getObligations().getSlo().get(i).getServiceID();
        }
        this.startThreadPool();
        return true;
    }

    private boolean stateIsValid (){
        boolean isValidState = true;
        
        if  (this.slas == null) {
            log.error("this.slas == null");
            isValidState = false;
        }

        return isValidState;
    }

    public void setSLAs(Model slas) {
        log.info("called setSLAs");
        this.slas = slas;

    }

    public void handle(ISLAsticEvent event) {
        // not interested in internal events
    }
}
