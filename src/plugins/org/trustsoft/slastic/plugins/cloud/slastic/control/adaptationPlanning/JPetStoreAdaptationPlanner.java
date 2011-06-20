package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration.EucalyptusReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import com.espertech.esper.client.EPStatement;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class JPetStoreAdaptationPlanner extends AbstractAdaptationPlannerComponent {

	private static final String PROPERTY_RULESET = "baselineRules";

	private static final Log log = LogFactory.getLog(JPetStoreAdaptationPlanner.class);

	private volatile NumNodesRuleSet2 ruleSet;

	@Override
	public void handleEvent(final IEvent arg0) {
		throw new UnsupportedOperationException();
	}

	protected String createEPStatement(final String assemblyComponentPackageName, final String assemblyComponentName,
			final int winSizeSeconds, final int outputPeriodSeconds) {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent as assemblyComponent, count(*)"
				+ " from " + DeploymentComponentOperationExecution.class.getName() + ".win:time(" + winSizeSeconds
				+ " seconds)" + " where assemblyComponent.packageName = \"" + assemblyComponentPackageName + "\""
				+ "   and assemblyComponent.name = \"" + assemblyComponentName + "\""
				+ " group by deploymentComponent.assemblyComponent" + " output all every " + outputPeriodSeconds
				+ " seconds";
	}

	private volatile WorkloadIntensityRuleEngine ruleEngine;

	@Override
	public boolean execute() {
		final ConfigurationManager configurationManager;

		final String assemblyComponentPackageName = "a.b.c";
		final String assemblyComponentName = "ComponentName";

		// TODO: pull out parameters to configuration file
		final int winSizeSeconds = 60;
		final int outputPeriodSeconds = 60;

		{ // init configuration manager and rule engine
			configurationManager =
					new ConfigurationManager((ModelManager) this.getParentAnalysisComponent()
							.getParentControlComponent().getModelManager(),
							(EucalyptusReconfigurationManager) this.getReconfigurationManager());
			this.ruleEngine =
			// TODO: pass rule set(s!)
					new WorkloadIntensityRuleEngine(this.ruleSet, configurationManager);
		}

		{ // register statement and subscriber
			final EPStatement epStatement =
					this.getParentAnalysisComponent()
							.getParentControlComponent()
							.getEPServiceProvider()
							.getEPAdministrator()
							.createEPL(
									this.createEPStatement(assemblyComponentPackageName, assemblyComponentName,
											winSizeSeconds, outputPeriodSeconds));
			epStatement.setSubscriber(this.ruleEngine);
		}

		return true;
	}

	@Override
	public boolean init() {
		this.ruleSet = this.createRuleSet();
		if (this.ruleSet == null) {
			JPetStoreAdaptationPlanner.log.error("Failed to init rule set");
			return false;
		}

		JPetStoreAdaptationPlanner.log.info(this.ruleSet);
		return true;
	}

	@Override
	public void terminate(final boolean arg0) {
		if (this.ruleEngine != null) {
			this.ruleEngine.terminate();
		}

		return;
	}

	/**
	 * Example {@code }
	 * 
	 * @param ruleSetStr
	 * @return
	 */
	private Collection<Baseline> parseRuleSetString(final String ruleSetStr) {
		final Collection<Baseline> ruleSet = new ArrayList<Baseline>();

		final StringTokenizer tokenizer = new StringTokenizer(ruleSetStr, ";");
		final int numRules = tokenizer.countTokens();
		if (numRules <= 0) {
			JPetStoreAdaptationPlanner.log.error("Invalid number of rules: " + numRules);
			return null;
		}
		while (tokenizer.hasMoreElements()) {
			final String rule = tokenizer.nextToken();
			final StringTokenizer conditionActionTokenizer = new StringTokenizer(rule, "-");
			if (conditionActionTokenizer.countTokens() != 2) {
				JPetStoreAdaptationPlanner.log
						.error("Expecting rules of format \"numNodes-lower:center:upper\"; found: " + rule);
				return null;
			}
			final int numNodes;
			final long lower, center, upper;
			final Baseline b;
			try {
				numNodes = Integer.parseInt(conditionActionTokenizer.nextToken().trim());
				final String borderStr = conditionActionTokenizer.nextToken();
				final StringTokenizer borderTok = new StringTokenizer(borderStr, ":");
				if (borderTok.countTokens() != 3) {
					JPetStoreAdaptationPlanner.log
							.error("Expecting colon-separated list of lower, center, and upper border values. Found: '"
									+ borderStr + "'");
					return null;
				}
				lower = Long.parseLong(borderTok.nextToken());
				center = Long.parseLong(borderTok.nextToken());
				upper = Long.parseLong(borderTok.nextToken());
				b = new Baseline(upper, center, lower, numNodes);
				JPetStoreAdaptationPlanner.log.info("Adding baseline" + b);
			} catch (final Exception exc) {
				JPetStoreAdaptationPlanner.log.error("Failed to extract number from rule: " + rule + "("
						+ exc.getMessage() + ")");
				return null;
			}
			ruleSet.add(b);
		}

		return ruleSet;
	}

	private NumNodesRuleSet2 createRuleSet() {
		final NumNodesRuleSet2 rs = new NumNodesRuleSet2();

		// TODO: read the set of rulesets

		// TODO: for all rulesets, extract assembly component name, execution
		// container type name,
		// and actual rule set.

		/* Parse rule set for increasing workload */
		final String ruleSetString = super.getInitProperty(JPetStoreAdaptationPlanner.PROPERTY_RULESET);
		if ((ruleSetString == null) || ruleSetString.isEmpty()) {
			final String errorMsg =
					"Invalid or missing value for property '" + JPetStoreAdaptationPlanner.PROPERTY_RULESET + "'";
			JPetStoreAdaptationPlanner.log.error(errorMsg);
			return null;
		}

		final Collection<Baseline> baselines = this.parseRuleSetString(ruleSetString);
		if (baselines == null) {
			JPetStoreAdaptationPlanner.log.error("Failed to parse rule set for increasing workload:" + ruleSetString);
			return null;
		}

		/* Add baselines to rule set */
		for (final Baseline b : baselines) {
			rs.addBaselineRule(b.getLowerBorder(), b.getCenter(), b.getUpperBorder(), b.getNumNodes());
		}

		if (!rs.isRuleSetValid()) {
			JPetStoreAdaptationPlanner.log.error("Rule set invalid");
			return null;
		}

		return rs;
	}
}
