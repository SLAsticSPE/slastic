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

package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.workloadIntensityBased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.ConfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

import com.espertech.esper.client.EPStatement;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class RuleBasedAdaptationPlanner extends AbstractAdaptationPlannerComponent {
	private static final Log LOG = LogFactory.getLog(RuleBasedAdaptationPlanner.class);

	private static final String PROPERTY_RULESET = "baselineRules";
	private static final String PROPERTY_WIN_SIZE_SECONDS = "windowSizeSeconds";
	private static final String PROPERTY_EVALUATION_PERIOD_SECONDS = "evaluationPeriodSeconds";
	private static final String PROPERTY_CONTAINER_DEALLOCATION_EXCLUDE_LIST = "containersNotToBeDeallocated";
	private static final String PROPERTY_MAX_NUM_INSTANCES = "maxNumContainers";

	private static final int DEFAULT_WIN_SIZE_SECONDS = 60;
	private static final int DEFAULT_EVALUATION_PERIOD_SECONDS = 60;

	/**
	 * Fully qualified {@link AssemblyComponent} name x rule set
	 */
	private volatile Map<String, NumDeploymentsForAssemblyComponentRuleSet> ruleSets = new HashMap<String, NumDeploymentsForAssemblyComponentRuleSet>();

	/**
	 * Fully qualified {@link ExecutionContainer} name x max. allowed # of
	 * instances
	 */
	private volatile Map<String, Integer> maxNumContainers = new HashMap<String, Integer>();

	private final Collection<String> containerExcludeList = new ArrayList<String>();

	private volatile WorkloadIntensityRuleEngine ruleEngine;

	private volatile int winSizeSeconds;
	private volatile int evaluationPeriodSeconds;

	@Override
	public boolean init() {
		boolean success = this.initExcludeList();
		if (!success) {
			LOG.error("Failed to init container exclude list");
			return false;
		}

		success = this.initMaxNumContainers();
		if (!success) {
			LOG.error("Failed to init max num container instances");
			return false;
		}

		success = this.initRuleSets();
		if (!success) {
			LOG.error("Failed to init rule sets");
			return false;
		}

		success = this.initTimeParameters();
		if (!success) {
			LOG.error("Failed to init time parameters");
			return false;
		}

		LOG.warn("Property 'maxNumNodes' not evaluated, yet");

		LOG.info(this.ruleSets);
		return success;
	}

	@Override
	public boolean execute() {
		final ModelManager modelManager = (ModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();

		boolean success = this.initRuleEngine(modelManager);
		if (!success) {
			LOG.error("Failed to init rule engine");
			return false;
		}

		success = this.registerCEPQueries();
		if (!success) {
			LOG.error("Failed to register CEP queries");
			return false;
		}

		return success;
	}

	@Override
	public void terminate(final boolean arg0) {
		if (this.ruleEngine != null) {
			this.ruleEngine.terminate();
		}

		return;
	}

	@Override
	public void handleEvent(final IEvent arg0) {
		throw new UnsupportedOperationException();
	}

	protected String createEPStatement(final String assemblyComponentPackageName, final String assemblyComponentName,
			final int winSizeSeconds, final int outputPeriodSeconds) {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent, count(*)"
				+ " from " + DeploymentComponentOperationExecution.class.getName() + ".win:time(" + winSizeSeconds
				+ " seconds)" + " where deploymentComponent.assemblyComponent.packageName = \""
				+ assemblyComponentPackageName + "\"" + "   and deploymentComponent.assemblyComponent.name = \""
				+ assemblyComponentName + "\"" + " group by deploymentComponent.assemblyComponent"
				+ " output all every " + outputPeriodSeconds + " seconds";
	}

	private boolean initRuleEngine(final ModelManager modelManager) {
		final ConfigurationManager configurationManager =
				new ConfigurationManager(modelManager, this.getReconfigurationManager(), this.containerExcludeList,
						this.maxNumContainers);
		this.ruleEngine = new WorkloadIntensityRuleEngine(modelManager, this.ruleSets, configurationManager);
		return true;
	}

	private boolean registerCEPQueries() {
		for (final NumDeploymentsForAssemblyComponentRuleSet rs : this.ruleSets.values()) {

			final String fqComponentAssemblyName = rs.getFQAssemblyComponentName();
			final String[] fqComponentAssemblyNameSplit = NameUtils.splitFullyQualifiedName(fqComponentAssemblyName);
			final String assemblyComponentPackageName = fqComponentAssemblyNameSplit[0];
			final String assemblyComponentName = fqComponentAssemblyNameSplit[1];

			final EPStatement epStatement =
					this.getParentAnalysisComponent()
							.getParentControlComponent()
							.getEPServiceProvider()
							.getEPAdministrator()
							.createEPL(
									this.createEPStatement(assemblyComponentPackageName, assemblyComponentName,
											this.winSizeSeconds, this.evaluationPeriodSeconds));
			epStatement.setSubscriber(this.ruleEngine);
		}
		return true;
	}

	/**
	 * Initialized the fields {@link #winSizeSeconds} and {@link #evaluationPeriodSeconds}.
	 * 
	 * @return
	 */
	private boolean initTimeParameters() {
		final int windowSizePropVal;
		final int evaluationPeriodPropVal;

		try {
			final String windowSizePropValStr = super.getInitProperty(PROPERTY_WIN_SIZE_SECONDS, Integer.toString(DEFAULT_WIN_SIZE_SECONDS));
			windowSizePropVal = Integer.parseInt(windowSizePropValStr);

			final String evaluationPeriodPropValStr = super.getInitProperty(PROPERTY_EVALUATION_PERIOD_SECONDS, Integer.toString(DEFAULT_EVALUATION_PERIOD_SECONDS));
			evaluationPeriodPropVal = Integer.parseInt(evaluationPeriodPropValStr);

			this.winSizeSeconds = windowSizePropVal;
			this.evaluationPeriodSeconds = evaluationPeriodPropVal;
		} catch (final Exception exc) {
			LOG.error("Failed to init property: " + exc.getMessage(), exc);
			return false;
		}

		return true;
	}

	/**
	 * Initializes the exclude list {@link #containerExcludeList}.
	 * 
	 * @return
	 */
	private boolean initExcludeList() {
		final String containerExludeListPropValStr = super.getInitProperty(PROPERTY_CONTAINER_DEALLOCATION_EXCLUDE_LIST, "");

		if (containerExludeListPropValStr.isEmpty()) {
			return true;
		}

		final String[] containerExludeListPropValStrSplit = containerExludeListPropValStr.split(";");

		this.containerExcludeList.addAll(Arrays.asList(containerExludeListPropValStrSplit));
		return true;
	}

	private boolean initMaxNumContainers() {
		final String maxNumPropValStr = super.getInitProperty(PROPERTY_MAX_NUM_INSTANCES, "");

		if (maxNumPropValStr.isEmpty()) {
			return true;
		}

		final String[] maxNumPropValStrSplit = maxNumPropValStr.split(";");
		for (final String maxNumKeyValuPair : maxNumPropValStrSplit) {
			final String[] maxNumKeyValuPairSplit = maxNumKeyValuPair.split(":");
			if (maxNumKeyValuPairSplit.length != 2) {
				LOG.error("Invalid max num containers key/value pair: '" + maxNumKeyValuPair + "'");
				return false;
			}
			final String fqContainerName = maxNumKeyValuPairSplit[0];
			final int maxNum = Integer.parseInt(maxNumKeyValuPairSplit[1]);
			this.maxNumContainers.put(fqContainerName, maxNum);
		}

		return true;
	}

	/**
	 * @param ruleSetStr
	 * @return
	 */
	private Collection<Baseline> parseRuleSetString(final String ruleSetStr) {
		final Collection<Baseline> ruleSet = new ArrayList<Baseline>();

		final StringTokenizer tokenizer = new StringTokenizer(ruleSetStr, ";");
		final int numRules = tokenizer.countTokens();
		if (numRules <= 0) {
			LOG.error("Invalid number of rules: " + numRules);
			return null;
		}
		while (tokenizer.hasMoreElements()) {
			final String rule = tokenizer.nextToken();
			final StringTokenizer conditionActionTokenizer = new StringTokenizer(rule, "-");
			if (conditionActionTokenizer.countTokens() != 2) {
				LOG.error("Expecting rules of format \"numNodes-lower:center:upper\"; found: " + rule);
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
					LOG.error("Expecting colon-separated list of lower, center, and upper border values. Found: '" + borderStr + "'");
					return null;
				}
				lower = Long.parseLong(borderTok.nextToken());
				center = Long.parseLong(borderTok.nextToken());
				upper = Long.parseLong(borderTok.nextToken());
				b = new Baseline(upper, center, lower, numNodes);
			} catch (final Exception exc) {
				LOG.error("Failed to extract number from rule: " + rule + "(" + exc.getMessage() + ")");
				return null;
			}
			ruleSet.add(b);
		}

		return ruleSet;
	}

	private boolean initRuleSets() {
		final String[] ruleSetPropertyValues = super.getInitProperties(PROPERTY_RULESET);

		if (ruleSetPropertyValues != null) {
			for (final String ruleSetPropertyVal : ruleSetPropertyValues) {
				final String[] ruleSetPropertyValSplit = ruleSetPropertyVal.split("::");
				if (ruleSetPropertyValSplit.length != 2) {
					LOG.error("Failed to split rule set property value into two segments by '::': '" + ruleSetPropertyVal + "'");
					return false;
				}

				final String asmCmpContainerTypePair = ruleSetPropertyValSplit[0];
				final String[] asmCmpContainerTypePairSplit = asmCmpContainerTypePair.split("-");
				if (asmCmpContainerTypePairSplit.length != 2) {
					LOG.error("Failed to split assembly component/container type specification by '-'': '" + asmCmpContainerTypePair + "'");
					return false;
				}

				final String fqAssemblyComponentName = asmCmpContainerTypePairSplit[0];
				final String containerTypeName = asmCmpContainerTypePairSplit[1];
				final String ruleSetString = ruleSetPropertyValSplit[1];

				/* Parse rule set for increasing workload */
				if ((ruleSetString == null) || ruleSetString.isEmpty()) {
					final String errorMsg = "Invalid or missing value for property '" + PROPERTY_RULESET + "'";
					LOG.error(errorMsg);
					return false;
				}

				final Collection<Baseline> baselines = this.parseRuleSetString(ruleSetString);
				if (baselines == null) {
					LOG.error("Failed to parse rule set for increasing workload:" + ruleSetString);
					return false;
				}

				final NumDeploymentsForAssemblyComponentRuleSet rs = new NumDeploymentsForAssemblyComponentRuleSet(fqAssemblyComponentName, containerTypeName);

				/* Add baselines to rule set */
				for (final Baseline b : baselines) {
					rs.addBaselineRule(b.getLowerBorder(), b.getCenter(), b.getUpperBorder(), b.getNumNodes());
				}

				if (!rs.isRuleSetValid()) {
					LOG.error("Rule set invalid");
					return false;
				}

				this.ruleSets.put(fqAssemblyComponentName, rs);
			}
		} else {
			LOG.warn("No rule sets defined (property '" + PROPERTY_RULESET + "'");
		}

		return true;
	}
}
