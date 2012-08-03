package org.trustsoft.slastic.plugins.starter.kieker;

import org.apache.commons.cli.Option;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.logReplayer.JMSLogReplayer;

/**
 * Should be moved to SLAstic framework.
 * 
 * @author Andre van Hoorn
 */
public class AnalysisStarterJMS extends AbstractAnalysisStarter {

	private static final Log log = LogFactory.getLog(AnalysisStarterJMS.class);

	private volatile String jmsProviderUrl;
	private volatile String jmsDestination;
	private volatile String jmsFactoryLookupName;

	private final static Option[] ADDITIONAL_CMDL_OPTS =
	{ CmdlOptions.CMDL_OPT_JMS_PROVIDER_URL,
		CmdlOptions.CMDL_OPT_JMS_DESTINATION,
		CmdlOptions.CMDL_OPT_JMS_INITIAL_CONTEXT_FACTORY };

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

	@Override
	protected boolean init() {
		try {
			this.jmsProviderUrl = super.getStringOptionValueNotEmpty(CmdlOptions.CMDL_OPT_JMS_PROVIDER_URL.getLongOpt());
			this.jmsDestination = super.getStringOptionValueNotEmpty(CmdlOptions.CMDL_OPT_JMS_DESTINATION.getLongOpt());
			this.jmsFactoryLookupName = super.getStringOptionValueNotEmpty(CmdlOptions.CMDL_OPT_JMS_INITIAL_CONTEXT_FACTORY.getLongOpt());
		} catch (final Exception exc) {
			AnalysisStarterJMS.log.error("Failed to initialize variables"
					+ exc.getMessage(), exc);
			return false;
		}

		return true;
	}

	@Override
	protected boolean startReplay(final String controllerConfigurationFN) {
		final JMSLogReplayer jmsReplayer =
				new JMSLogReplayer(controllerConfigurationFN,
						this.jmsProviderUrl,
						this.jmsDestination,
						this.jmsFactoryLookupName);
		return jmsReplayer.replay();
	}
}
