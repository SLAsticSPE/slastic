package org.trustsoft.slastic.plugins.starter;

import java.util.Properties;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticAdaptationFrameworkConfiguration {

    public AbstractControlComponent controlComponent;
    public AbstractMonitoringManagerComponent monitoringManagerComponent;
    public AbstractReconfigurationManagerComponent reconfigurationManagerComponent;
    public AbstractModelManagerComponent modelManagerComponent;
    public AbstractModelUpdaterComponent modelUpdaterComponent;
    public AbstractAnalysisComponent analysisComponent;
    public AbstractPerformanceEvaluatorComponent performanceEvaluatorComponent;
    public AbstractWorkloadForecasterComponent workloadForecasterComponent;
    public AbstractPerformancePredictorComponent performancePredictorComponent;
    public AbstractAdaptationPlannerComponent adaptationPlannerComponent;
    public final Properties controlComponentProperties = new Properties();
    public final Properties monitoringManagerComponentProperties = new Properties();
    public final Properties reconfigurationManagerComponentProperties = new Properties();
    public final Properties modelManagerComponentProperties = new Properties();
    public final Properties modelUpdaterComponentProperties = new Properties();
    public final Properties analysisComponentProperties = new Properties();
    public final Properties performanceEvaluatorComponentProperties = new Properties();
    public final Properties workloadForecasterProperties = new Properties();
    public final Properties performancePredictorComponentProperties = new Properties();
    public final Properties adaptationPlannerComponentProperties = new Properties();
}
