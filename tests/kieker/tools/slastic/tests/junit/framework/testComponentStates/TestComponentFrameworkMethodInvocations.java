/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.tests.junit.framework.testComponentStates;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import junit.framework.TestCase;

import kieker.tools.slastic.common.Configuration;
import kieker.tools.slastic.common.FrameworkInstance;
import kieker.tools.slastic.control.AbstractControlComponent;
import kieker.tools.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import kieker.tools.slastic.control.components.analysis.AbstractAnalysisComponent;
import kieker.tools.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import kieker.tools.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import kieker.tools.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import kieker.tools.slastic.control.components.modelManager.AbstractModelManagerComponent;
import kieker.tools.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import kieker.tools.slastic.monitoring.AbstractMonitoringManagerComponent;
import kieker.tools.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

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

        FrameworkInstance slasticInstance =
                new FrameworkInstance(configuration);
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
    private void checkComponentStateSequences(Configuration configuration){
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
                AbstractMonitoringManagerComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingMonitoringManagerComponent.class.getName());
        /* set classname of control component */
        configuration.setProperty(
                AbstractControlComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingControlComponent.class.getName());
        /* set classname of model management component */
        configuration.setProperty(
                AbstractModelManagerComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingModelManagerComponent.class.getName());
        /* set classname of model updating component */
        configuration.setProperty(
                AbstractModelUpdaterComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingModelUpdaterComponent.class.getName());
        /* set classname of analysis component */
        configuration.setProperty(
                AbstractAnalysisComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingAnalysisComponent.class.getName());
        /* set classname of performance evaluation component */
        configuration.setProperty(
                AbstractPerformanceEvaluatorComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingPerformanceEvaluatorComponent.class.getName());
        /* set classname of workload forecaster component */
        configuration.setProperty(
                AbstractWorkloadForecasterComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingWorkloadForecasterComponent.class.getName());
        /* set classname of performance predictor component */
        configuration.setProperty(
                AbstractPerformancePredictorComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingPerformancePredictorComponent.class.getName());
        /* set classname of adaptation planning component */
        configuration.setProperty(
                AbstractAdaptationPlannerComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingAdaptationPlannerComponent.class.getName());
        /* set classname of reconfiguration component */
        configuration.setProperty(
                AbstractReconfigurationManagerComponent.PROP_PREFIX+"."+FrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                StateTrackingReconfigurationManagerComponent.class.getName());

        return configuration;
    }
}
