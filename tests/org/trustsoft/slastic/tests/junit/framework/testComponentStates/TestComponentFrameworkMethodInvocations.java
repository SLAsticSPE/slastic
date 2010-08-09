package org.trustsoft.slastic.tests.junit.framework.testComponentStates;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import junit.framework.TestCase;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.plugins.starter.SLAsticAdaptationFrameworkConfiguration;
import org.trustsoft.slastic.plugins.starter.SLAsticAdaptationFrameworkInstance;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class TestComponentFrameworkMethodInvocations extends TestCase {

    /**
     * Tests whether on a successful run of a SLAstic instance, the framework
     * calls the methods init, execute, and terminate (in this order).
     */
    public void testAllFrameworkMethodsCalledSuccess(){
        Properties configuration = genTrackingConfiguration();

        SLAsticAdaptationFrameworkInstance slasticInstance =
                new SLAsticAdaptationFrameworkInstance(configuration);
        boolean success = slasticInstance.run();
        assertTrue("Test invalid: slasticInstance.run() returned false", success);
        slasticInstance.terminate(false); // no error

        checkComponentStateSequences(slasticInstance.getConfiguration());
    }

    /**
     * Make sure that all components had the desired sequence of method invocations.
     *
     * @param configuration the configuration containing the instantiated framework components
     */
    private void checkComponentStateSequences(SLAsticAdaptationFrameworkConfiguration configuration){
        final List<ComponentStateTracker.States> expectedSequence =
                new ArrayList<ComponentStateTracker.States>();
        expectedSequence.add(ComponentStateTracker.States.INIT);
        expectedSequence.add(ComponentStateTracker.States.EXECUTE);
        expectedSequence.add(ComponentStateTracker.States.TERMINATE_NO_ERROR);

        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.monitoringManagerComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.controlComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.modelManagerComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.modelUpdaterComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.analysisComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.performanceEvaluatorComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.workloadForecasterComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.performancePredictorComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.adaptationPlannerComponent).getComponentStateTracker().getStateSequence());
        assertEquals(expectedSequence, ((ITracksComponentStates)configuration.reconfigurationManagerComponent).getComponentStateTracker().getStateSequence());
    }

   /**
     * Creates a SLAstic framework configuration containing only commponents
    *  that track their state.
     *
     * @return
     */
    public static Properties genTrackingConfiguration(){
        Properties configuration =
                new Properties();
        /* set classname of monitoring manager component */
        configuration.setProperty(
                AbstractMonitoringManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingMonitoringManagerComponent.class.getName());
        /* set classname of control component */
        configuration.setProperty(
                AbstractControlComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingControlComponent.class.getName());
        /* set classname of model management component */
        configuration.setProperty(
                AbstractModelManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingModelManagerComponent.class.getName());
        /* set classname of model updating component */
        configuration.setProperty(
                AbstractModelUpdaterComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingModelUpdaterComponent.class.getName());
        /* set classname of analysis component */
        configuration.setProperty(
                AbstractAnalysisComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingAnalysisComponent.class.getName());
        /* set classname of performance evaluation component */
        configuration.setProperty(
                AbstractPerformanceEvaluatorComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingPerformanceEvaluatorComponent.class.getName());
        /* set classname of workload forecaster component */
        configuration.setProperty(
                AbstractWorkloadForecasterComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingWorkloadForecasterComponent.class.getName());
        /* set classname of performance predictor component */
        configuration.setProperty(
                AbstractPerformancePredictorComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingPerformancePredictorComponent.class.getName());
        /* set classname of adaptation planning component */
        configuration.setProperty(
                AbstractAdaptationPlannerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingAdaptationPlannerComponent.class.getName());
        /* set classname of reconfiguration component */
        configuration.setProperty(
                AbstractReconfigurationManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingReconfigurationManagerComponent.class.getName());

        return configuration;
    }
}
