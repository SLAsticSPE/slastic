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

	private static final Log LOG = LogFactory.getLog(AnalysisStarterJMS.class);

	private volatile String jmsProviderUrl;
	private volatile String jmsDestination;
	private volatile String jmsFactoryLookupName;

	private final static Option[] ADDITIONAL_CMDL_OPTS = {
		CmdlOptions.CMDL_OPT_JMS_PROVIDER_URL,
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
			AnalysisStarterJMS.LOG.error("Failed to initialize variables" + exc.getMessage(), exc);
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
