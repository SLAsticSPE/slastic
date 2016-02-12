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

package kieker.tools.slastic.plugins.starter;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

import kieker.analysis.AnalysisController;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.analysis.plugin.reader.filesystem.FSReader;
import kieker.common.configuration.Configuration;
import kieker.tools.slastic.plugins.pcm.PCMModelReader;
import kieker.tools.slastic.plugins.pcm.control.PCMModelSet;
import kieker.tools.slastic.plugins.slasticImpl.ModelIOUtils;
import kieker.tools.slastic.plugins.starter.reconfigurationPipe.SLAsticSimPlanReceiver;
import kieker.tools.slastic.simulation.SimulationController;
import kieker.tools.slastic.simulation.listeners.IReconfigurationEventListener;
import kieker.tools.slastic.simulation.model.interfaces.IReconfigurationPlanReceiver;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimulatorInstance {

	private static final Log LOG = LogFactory.getLog(SLAsticSimulatorInstance.class);

	private final Properties props;

	private static final String PROP_NAME_PREFIX = "slastic.simulation";
	private static final String PROP_NAME_FSREADER_INPUTDIRS = PROP_NAME_PREFIX + ".fsreader.inputdirs";
	private static final String PROP_NAME_FSREADER_RTMODE = PROP_NAME_PREFIX + ".fsreader.rtmode";
	private static final String PROP_NAME_FSREADER_RTNUMTHREADS = PROP_NAME_PREFIX + ".fsreader.rtnumthreads";
	private static final String PROP_NAME_RECONF_PIPENAME = PROP_NAME_PREFIX + ".reconfplanreceiver.pipeName";
	private static final String PROP_NAME_PCM_REPOSITORY_FN = PROP_NAME_PREFIX + ".pcmrepository_fn";
	private static final String PROP_NAME_PCM_SYSTEM_FN = PROP_NAME_PREFIX + ".pcmsystem_fn";
	private static final String PROP_NAME_PCM_RESOURCEENV_FN = PROP_NAME_PREFIX + ".pcmresourceenv_fn";
	private static final String PROP_NAME_PCM_ALLOCATION_FN = PROP_NAME_PREFIX + ".pcmallocation_fn";
	private static final String PROP_NAME_SLASTIC_RECONFIGURATIONMODEL_FN = PROP_NAME_PREFIX + ".slasticreconfigurationmodel_fn";

	private volatile PCMModelSet pcmModel;

	private volatile ReconfigurationModel slasticReconfigurationModel;

	private volatile AnalysisController analysisInstance;
	private volatile String fsReaderInputDir;
	private volatile boolean fsReaderRTMode = false;
	private volatile int fsReaderRTNumThreads = -1;

	private volatile SimulationController simCtrl;

	private volatile SLAsticSimPlanReceiver reconfPlanReceiver;
	private volatile String reconfPipeName;

	/** No construction via default constructor. */
	@SuppressWarnings("unused")
	private SLAsticSimulatorInstance() {
		this.props = null;
	}

	public SLAsticSimulatorInstance(final Properties props) {
		this.props = props;
		this.initFromProps();
	}

	private void initFromProps() throws IllegalArgumentException {
		try {
			this.fsReaderInputDir = this.props.getProperty(PROP_NAME_FSREADER_INPUTDIRS);
			this.fsReaderRTMode = this.props.getProperty(PROP_NAME_FSREADER_RTMODE, "").trim().toLowerCase().equals("true");
			this.fsReaderRTNumThreads = Integer.parseInt(this.props.getProperty(PROP_NAME_FSREADER_RTNUMTHREADS));

			this.reconfPipeName = this.props.getProperty(PROP_NAME_RECONF_PIPENAME);

			if ((this.fsReaderInputDir == null) || (this.fsReaderInputDir.length() == 0)) {
				throw new IllegalArgumentException("Missing or empty property " + PROP_NAME_FSREADER_INPUTDIRS);
			}
			if (this.fsReaderRTMode && (this.fsReaderRTNumThreads <= 0)) {
				throw new IllegalArgumentException("Missing, empty or invalid property " + PROP_NAME_FSREADER_RTNUMTHREADS);
			}

			if ((this.reconfPipeName == null) || (this.reconfPipeName.length() == 0)) {
				throw new IllegalArgumentException("Missing or empty property " + PROP_NAME_RECONF_PIPENAME);
			}

			final String pcmRespositoryModel_fn = this.props.getProperty(PROP_NAME_PCM_REPOSITORY_FN);
			final String pcmSystemModel_fn = this.props.getProperty(PROP_NAME_PCM_SYSTEM_FN);
			final String pcmResourceEnvironmentModel_fn = this.props.getProperty(PROP_NAME_PCM_RESOURCEENV_FN);
			final String pcmAllocationModel_fn = this.props.getProperty(PROP_NAME_PCM_ALLOCATION_FN);
			final String slasticReconfigurationModel_fn = this.props.getProperty(PROP_NAME_SLASTIC_RECONFIGURATIONMODEL_FN);

			this.pcmModel = PCMModelReader.readPCMModel(pcmRespositoryModel_fn, pcmSystemModel_fn, pcmResourceEnvironmentModel_fn, pcmAllocationModel_fn);

			this.slasticReconfigurationModel = ModelIOUtils.readOLDReconfigurationModel(slasticReconfigurationModel_fn);
			if (this.slasticReconfigurationModel == null) {
				LOG.error("Failed to read SLAstic reconfiguration model from file '" + slasticReconfigurationModel_fn + "'");
				throw new IllegalArgumentException(
						"Failed to read SLAstic reconfiguration model from file '" + slasticReconfigurationModel_fn + "'");
			}
		} catch (final Exception exc) {
			LOG.error("Init error", exc);
			throw new IllegalArgumentException("Init error", exc);
		}
	}

	public void run() {
		/* Construct simulation instance */
		LOG.info("Instantiating Simulator with "
				+ "Repository: " + this.pcmModel.getPCMRepository()
				+ ", System: " + this.pcmModel.getPCMSystem()
				+ ", Resource Environment: " + this.pcmModel.getPCMResourceEnvironment()
				+ " Initial allocation: " + this.pcmModel.getPCMAllocation()
				+ " reconf: " + this.slasticReconfigurationModel);
		this.simCtrl = new SimulationController("A name",
				this.pcmModel.getPCMRepository(),
				this.pcmModel.getPCMSystem(),
				this.pcmModel.getPCMResourceEnvironment(),
				this.pcmModel.getPCMAllocation(),
				this.slasticReconfigurationModel);

		/* Construct and start reconfiguration plan receiver */
		this.reconfPlanReceiver = new SLAsticSimPlanReceiver(this.reconfPipeName, new IReconfigurationPlanReceiver() {

			@Override
			public void reconfigure(final SLAsticReconfigurationPlan plan) {
				LOG.debug("Received plan " + plan);
				SLAsticSimulatorInstance.this.simCtrl.getReconfigurationPlanReceiverPort().reconfigure(plan);
			}

			@Override
			public void addReconfigurationEventListener(final IReconfigurationEventListener listener) {
				SLAsticSimulatorInstance.this.simCtrl.getReconfigurationPlanReceiverPort().addReconfigurationEventListener(listener);
			}

			@Override
			public void removeReconfigurationEventListener(final IReconfigurationEventListener listener) {
				SLAsticSimulatorInstance.this.simCtrl.getReconfigurationPlanReceiverPort().removeReconfigurationEventListener(listener);
			}
		});
		this.reconfPlanReceiver.execute();

		this.analysisInstance = new AnalysisController();

		/* Construct and start Tpan instance with FS reader */
		final Configuration fsReaderConfig = new Configuration();
		fsReaderConfig.setProperty(FSReader.CONFIG_PROPERTY_NAME_INPUTDIRS, this.fsReaderInputDir); // TODO: just a single one here ...
		final FSReader fsReader = new FSReader(fsReaderConfig, this.analysisInstance);
		this.analysisInstance.registerReader(fsReader);
		final AbstractFilterPlugin recordReceiverFilter = this.simCtrl.getMonitoringRecordConsumerFilter(this.analysisInstance);
		this.analysisInstance.registerFilter(recordReceiverFilter);

		try {
			this.analysisInstance.connect(fsReader, FSReader.OUTPUT_PORT_NAME_RECORDS, recordReceiverFilter, SimulationController.INPUT_PORT_NAME_RECORDS);
			LOG.info("Starting to read workload ...");
			this.analysisInstance.run();
			LOG.info("Finished reading workload ...");
		} catch (final Exception ex) {
			LOG.error("Exception occurred:", ex);
		}
		LOG.info("Simulation starting ...");
		this.simCtrl.start();
		LOG.info("Simulation ended ...");
	}
}
