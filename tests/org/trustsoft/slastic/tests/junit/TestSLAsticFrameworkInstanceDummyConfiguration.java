package org.trustsoft.slastic.tests.junit;

import java.util.Properties;
import junit.framework.TestCase;
import kieker.analysis.plugin.DummyRecordConsumer;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.BasicControlComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.analysis.BasicAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.DummyAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.DummyPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.DummyPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.DummyWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelManager.DummyModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.control.components.modelUpdater.DummyModelUpdaterComponent;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.monitoring.DummyMonitoringManagerComponent;
import org.trustsoft.slastic.plugins.starter.SLAsticAdaptationFrameworkInstance;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.DummyReconfigurationManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class TestSLAsticFrameworkInstanceDummyConfiguration extends TestCase {

    private Properties genDummyProperties(){
        Properties configuration =
                new Properties();
        /* set classname of monitoring manager component */
        configuration.setProperty(
                AbstractMonitoringManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyMonitoringManagerComponent.class.getName());
        /* set classname of control component */
        configuration.setProperty(
                AbstractControlComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                BasicControlComponent.class.getName());
        /* set classname of model management component */
        configuration.setProperty(
                AbstractModelManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyModelManagerComponent.class.getName());
        /* set classname of model updating component */
        configuration.setProperty(
                AbstractModelUpdaterComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyModelUpdaterComponent.class.getName());
        /* set classname of analysis component */
        configuration.setProperty(
                AbstractAnalysisComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                BasicAnalysisComponent.class.getName());
        /* set classname of performance evaluation component */
        configuration.setProperty(
                AbstractPerformanceEvaluatorComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyPerformanceEvaluatorComponent.class.getName());
        /* set classname of workload forecaster component */
        configuration.setProperty(
                AbstractWorkloadForecasterComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyWorkloadForecasterComponent.class.getName());
        /* set classname of performance predictor component */
        configuration.setProperty(
                AbstractPerformancePredictorComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyPerformancePredictorComponent.class.getName());
        /* set classname of adaptation planning component */
        configuration.setProperty(
                AbstractAdaptationPlannerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyAdaptationPlannerComponent.class.getName());
        /* set classname of reconfiguration component */
        configuration.setProperty(
                AbstractReconfigurationManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyReconfigurationManagerComponent.class.getName());

        return configuration;
    }

    public void testInitAndRunInstanceByProperties(){
        Properties configuration = genDummyProperties();

        SLAsticAdaptationFrameworkInstance slasticInstance =
                new SLAsticAdaptationFrameworkInstance(configuration);
        slasticInstance.run();
        slasticInstance.terminate(false); // no error
    }
}
