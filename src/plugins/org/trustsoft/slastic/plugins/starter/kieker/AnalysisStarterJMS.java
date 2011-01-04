package org.trustsoft.slastic.plugins.starter.kieker;

import kieker.monitoring.core.MonitoringController;
import kieker.tools.logReplayer.JMSLogReplayer;

import org.apache.commons.cli.Option;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Should be moved to SLAstic framework.
 * 
 * @author Andre van Hoorn
 */
public class AnalysisStarterJMS extends AbstractAnalysisStarter {

	private static final Log log = LogFactory.getLog(AnalysisStarterJMS.class);

	private volatile String jmsProviderUrl;
	private volatile String jmsDestination;

	private final static Option[] ADDITIONAL_CMDL_OPTS =
	{ CmdlOptions.CMDL_OPT_JMS_PROVIDER_URL,
			CmdlOptions.CMDL_OPT_JMS_DESTINATION };

	public AnalysisStarterJMS(final String[] args) {
		super(args, AnalysisStarterJMS.ADDITIONAL_CMDL_OPTS);
	}

	public static void main(final String[] args) {
		final AnalysisStarterJMS starter = new AnalysisStarterJMS(args);
		if (!starter.start()) {
			System.err.println("Starter terminated with error.");
			System.exit(1);
		}
	}

	/**
	 * Starts the log Kieker log replayer instance and terminates after the
	 * replayer finished.
	 */
	@Override
	protected boolean startReader(
			final MonitoringController monitoringController) {
		monitoringController.enableReplayMode();

		final JMSLogReplayer jmsReplayer =
				new JMSLogReplayer(monitoringController,
						this.jmsProviderUrl,
						this.jmsDestination);
		return jmsReplayer.replay();
	}

	@Override
	protected boolean init() {
		try {
			this.jmsProviderUrl =
					super.getStringOptionValueNotEmpty(CmdlOptions.CMDL_OPT_JMS_PROVIDER_URL
							.getLongOpt());
			this.jmsDestination =
					super.getStringOptionValueNotEmpty(CmdlOptions.CMDL_OPT_JMS_DESTINATION
							.getLongOpt());
		} catch (final Exception exc) {
			AnalysisStarterJMS.log.error("Failed to initialize variables"
					+ exc.getMessage(), exc);
			return false;
		}

		return true;
	}
}
