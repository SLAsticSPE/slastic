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

/**
 * @author Lena
 */
package kieker.tools.slastic.plugins.slachecker.control.analysis;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;

import kieker.tools.slastic.control.components.analysis.AbstractAnalysisComponent;
import kieker.tools.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import kieker.tools.slastic.control.components.events.IEvent;
import kieker.tools.slastic.plugins.slachecker.control.ServiceIDDoesNotExistException;
import kieker.tools.slastic.plugins.slachecker.control.modelManager.SLOModelManager;

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
public class SLAChecker extends AbstractPerformanceEvaluatorComponent {

	private static final Log LOG = LogFactory.getLog(SLAChecker.class);

	private QuantileCalculator quantileCalc;
	private slal.Model slas = null;
	private SLACheckerGUI[] guis;
	private int[] serviceIDs;
	private AbstractAnalysisComponent ana;
	private ScheduledThreadPoolExecutor ex;

	// public void init(String initString) throws IllegalArgumentException {
	// super.initVarsFromInitString(initString);
	// // we don't expect init properties so far.
	// }
	/**
	 * Delegating the calculation to the QuantileCalculator object.
	 * 
	 * @param quantile
	 *            array for identifying the quantiles that should be calculated
	 * @param id
	 *            serviceID of the service for which the quantiles should be calculated.
	 * @return array with the values of the quantiles
	 * @throws ServiceIDDoesNotExistException
	 *             is thrown if there is no SLO for the given ID
	 */
	private long[] getQuantilResponseTime(final float[] quantile, final int id)
			throws ServiceIDDoesNotExistException {
		// Delegation to the Quantile Calculator
		final long[] rt = this.quantileCalc.getResponseTimeQuantiles(quantile, id);
		try {
		} catch (final Exception e) {
			e.printStackTrace();
		}
		// updating GUI
		for (int i = 0; i < this.serviceIDs.length; i++) {
			if (id == this.serviceIDs[i]) {
				this.guis[i].addResponseTime(rt);
				return rt;
			}
		}
		return rt;
	}

	private void startThreadPool() {
		// this.averageCalcThread = new
		// AverageCalculatorThread(this.responseTimes);
		// averageCalcThread.start();
		final EList<SLO> slaslo = this.slas.getObligations().getSlo();
		this.ex = new ScheduledThreadPoolExecutor(this.slas.getObligations().getSlo().size());
		// final DateFormat m_ISO8601Local = new
		// SimpleDateFormat("yyyyMMdd'-'HHmmss");
		for (int i = 0; i < slaslo.size(); i++) {
			final int ID = slaslo.get(i).getServiceID();
			// RT_QUANTILE_TYPE is the only type that is supported at the moment
			if (slaslo.get(i).getType() == slal.Type.RT_QUANTILE_TYPE) {
				final int size = slaslo.get(i).getValue().getPair().size();
				final float[] quantile = new float[size];
				final int[] responseTimes = new int[size];
				for (int k = 0; k < size; k++) {
					final float q = (slaslo.get(i).getValue().getPair().get(k).getQuantile() / 100.0f);
					quantile[k] = q;

					final int responseTime = slaslo.get(i).getValue().getPair().get(k).getResponseTime();
					responseTimes[k] = responseTime;
				}
				final SLO slo = slaslo.get(i);
				// checking SLAs in periodically
				this.ex.scheduleAtFixedRate(
						new Runnable() {

							public void run() {
								try {
									long[] responseTimes = null;
									responseTimes = SLAChecker.this.getQuantilResponseTime(
											quantile, ID);
									for (int j = 0; j < responseTimes.length; j++) {
										final int responseTime2 = slo.getValue().getPair().get(j).getResponseTime();
										if (responseTimes[j] > responseTime2) {
											// if response time quantile violates SLA, send an event to the Analysis component.
											final SLAViolationEvent evt = new SLAViolationEvent(ID);
											SLAChecker.this.getSimpleSLAsticEventService().sendEvent(evt);
											// log.info("SLAViolation sent");
										}
									}
								} catch (final Exception exc) {
									LOG.fatal("Exception occured in executor pool. Will rethrow exception", exc);
									throw new RuntimeException(exc); // rethrow
								}
							}
						}, (1000 / slaslo.size()) + (i * 1000), 1000,
						TimeUnit.MILLISECONDS);
			} else {
				LOG.error("No handling for this SLA-Type available");

			}
		}
	}

	@Override
	public void terminate(final boolean error) {
		LOG.info("Terminating");
		/*
		 * In case we spawned a thread in execute(), we get the chance to kill
		 * it here.
		 */
		// averageCalcThread.terminate();
		this.quantileCalc.terminate();
		for (int i = 0; (this.guis != null) && (i < this.guis.length); i++) {
			this.guis[i].terminate();
		}
		if (this.ex != null) {
			this.ex.shutdownNow();
		}
	}

	@Override
	public boolean init() {
		this.slas = ((SLOModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager()).getSlas();
		if (this.slas == null) {
			LOG.error("this.slas == null");
			throw new IllegalArgumentException("this.slas == null");
		}
		LOG.info(this.slas);
		return true;
	}

	@Override
	public boolean execute() {
		this.quantileCalc = new QuantileCalculator((SLOModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager());

		// Initialize GUIs and set of service IDs
		this.guis = new SLACheckerGUI[this.slas.getObligations().getSlo().size()];
		this.serviceIDs = new int[this.slas.getObligations().getSlo().size()];
		for (int i = 0; i < this.slas.getObligations().getSlo().size(); i++) {
			final long[] quantiles = new long[this.slas.getObligations().getSlo().get(i).getValue().getPair().size()];
			for (int k = 0; k < this.slas.getObligations().getSlo().get(i).getValue().getPair().size(); k++) {
				quantiles[k] = this.slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getResponseTime();
			}
			this.guis[i] = new SLACheckerGUI(
					"ServiceID: " + this.slas.getObligations().getSlo().get(i).getServiceID(), 30000, quantiles[0],
					quantiles[1], quantiles[2]);
			LOG.info("SLOS[" + i + "]:" + quantiles[0] + ";" + quantiles[1] + ";" + quantiles[2]);
			this.guis[i].paint();
			this.serviceIDs[i] = this.slas.getObligations().getSlo().get(i).getServiceID();
		}
		this.startThreadPool();
		LOG.info("Started " + SLAChecker.class);
		return true;
	}

	@Override
	public void handleEvent(final IEvent ev) {}
}
