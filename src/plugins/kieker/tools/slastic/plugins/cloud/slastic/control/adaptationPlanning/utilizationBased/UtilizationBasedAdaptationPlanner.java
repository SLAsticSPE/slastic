package kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.utilizationBased;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPStatement;

import kieker.tools.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import kieker.tools.slastic.control.components.events.IEvent;
import kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.ConfigurationManager;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.monitoring.CPUUtilization;

/**
 * 
 * @author Florian Fittkau
 * 
 */
public class UtilizationBasedAdaptationPlanner extends AbstractAdaptationPlannerComponent {
	private static final Log log = LogFactory.getLog(UtilizationBasedAdaptationPlanner.class);

	private static final String PROPERTY_RULESET = "rules";
	private static final String PROPERTY_WIN_SIZE_SECONDS = "windowSizeSeconds";
	private static final String PROPERTY_EVALUATION_PERIOD_SECONDS = "evaluationPeriodSeconds";
	private static final String PROPERTY_CONTAINER_DEALLOCATION_EXCLUDE_LIST = "containersNotToBeDeallocated";
	private static final String PROPERTY_MAX_NUM_INSTANCES = "maxNumContainers";

	private static final int DEFAULT_WIN_SIZE_SECONDS = 60;
	private static final int DEFAULT_EVALUATION_PERIOD_SECONDS = 60;

	private volatile OverallCPUUtilizationRuleSet ruleSet;

	/**
	 * Fully qualified {@link ExecutionContainer} name x max. allowed # of
	 * instances
	 */
	private volatile Map<String, Integer> maxNumContainers = new HashMap<String, Integer>();

	private final Collection<String> containerExcludeList = new ArrayList<String>();

	private volatile OverallCPUUtilizationRuleEngine ruleEngine;

	private volatile int winSizeSeconds;
	private volatile int evaluationPeriodSeconds;

	@Override
	public boolean init() {
		boolean success = this.initExcludeList();
		if (!success) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to init container exclude list");
			return false;
		}

		success = this.initMaxNumContainers();
		if (!success) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to init max num container instances");
			return false;
		}

		success = this.initRuleSets();
		if (!success) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to init rule sets");
			return false;
		}

		success = this.initTimeParameters();
		if (!success) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to init time parameters");
			return false;
		}

		UtilizationBasedAdaptationPlanner.log.warn("Property 'maxNumNodes' not evaluated, yet");

		UtilizationBasedAdaptationPlanner.log.info(this.ruleSet);
		return success;
	}

	@Override
	public boolean execute() {
		final ModelManager modelManager =
				(ModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();

		boolean success = this.initRuleEngine(modelManager);
		if (!success) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to init rule engine");
			return false;
		}

		success = this.registerCEPQueries();
		if (!success) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to register CEP queries");
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

	protected String createEPStatement(
			final int winSizeSeconds, final int outputPeriodSeconds) {
		return "select "
				+ "current_timestamp as currentTimestampMillis, average as avgUtil"
				+ " from " + CPUUtilization.class.getName() + "((1 - idle) <= 10.0 and (1 - idle) >= 0.0).win:ext_timed(current_timestamp, 10 second).stat:uni(1 - idle)" 
				+ " output last every 1 seconds";
	}

	private boolean initRuleEngine(final ModelManager modelManager) {
		final ConfigurationManager configurationManager =
				new ConfigurationManager(modelManager,
						this.getReconfigurationManager(), this.containerExcludeList,
						this.maxNumContainers);

		this.ruleEngine = new OverallCPUUtilizationRuleEngine(modelManager, this.ruleSet, configurationManager);
		return true;
	}

	private boolean registerCEPQueries() {
		final EPStatement epStatement =
				this.getParentAnalysisComponent()
						.getParentControlComponent()
						.getEPServiceProvider()
						.getEPAdministrator()
						.createEPL(
								this.createEPStatement(
										this.winSizeSeconds, this.evaluationPeriodSeconds));
		epStatement.setSubscriber(this.ruleEngine);

		return true;
	}

	/**
	 * Initialized the fields {@link #winSizeSeconds} and
	 * {@link #evaluationPeriodSeconds}.
	 * 
	 * @return
	 */
	private boolean initTimeParameters() {
		final int windowSizePropVal;
		final int evaluationPeriodPropVal;

		try {
			final String windowSizePropValStr =
					super.getInitProperty(UtilizationBasedAdaptationPlanner.PROPERTY_WIN_SIZE_SECONDS,
							Integer.toString(UtilizationBasedAdaptationPlanner.DEFAULT_WIN_SIZE_SECONDS));
			windowSizePropVal = Integer.parseInt(windowSizePropValStr);

			final String evaluationPeriodPropValStr =
					super.getInitProperty(UtilizationBasedAdaptationPlanner.PROPERTY_EVALUATION_PERIOD_SECONDS,
							Integer.toString(UtilizationBasedAdaptationPlanner.DEFAULT_EVALUATION_PERIOD_SECONDS));
			evaluationPeriodPropVal = Integer.parseInt(evaluationPeriodPropValStr);

			this.winSizeSeconds = windowSizePropVal;
			this.evaluationPeriodSeconds = evaluationPeriodPropVal;
		} catch (final Exception exc) {
			UtilizationBasedAdaptationPlanner.log.error("Failed to init property: " + exc.getMessage(), exc);
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
		final String containerExludeListPropValStr =
				super.getInitProperty(UtilizationBasedAdaptationPlanner.PROPERTY_CONTAINER_DEALLOCATION_EXCLUDE_LIST,
						"");

		if (containerExludeListPropValStr.isEmpty()) {
			return true;
		}

		final String[] containerExludeListPropValStrSplit = containerExludeListPropValStr.split(";");

		this.containerExcludeList.addAll(Arrays.asList(containerExludeListPropValStrSplit));
		return true;
	}

	private boolean initMaxNumContainers() {
		final String maxNumPropValStr =
				super.getInitProperty(UtilizationBasedAdaptationPlanner.PROPERTY_MAX_NUM_INSTANCES, "");

		if (maxNumPropValStr.isEmpty()) {
			return true;
		}

		final String[] maxNumPropValStrSplit = maxNumPropValStr.split(";");
		for (final String maxNumKeyValuPair : maxNumPropValStrSplit) {
			final String[] maxNumKeyValuPairSplit = maxNumKeyValuPair.split(":");
			if (maxNumKeyValuPairSplit.length != 2) {
				UtilizationBasedAdaptationPlanner.log.error("Invalid max num containers key/value pair: '"
						+ maxNumKeyValuPair
						+ "'");
				return false;
			}
			final String fqContainerName = maxNumKeyValuPairSplit[0];
			final int maxNum = Integer.parseInt(maxNumKeyValuPairSplit[1]);
			this.maxNumContainers.put(fqContainerName, maxNum);
		}

		return true;
	}

	private Collection<CPUUtilizationRule> parseRuleSetString(final String ruleSetStr) {
		final Collection<CPUUtilizationRule> ruleSet = new ArrayList<CPUUtilizationRule>();

		final StringTokenizer tokenizer = new StringTokenizer(ruleSetStr, ";");
		final int numRules = tokenizer.countTokens();
		if (numRules <= 0) {
			UtilizationBasedAdaptationPlanner.log.error("Invalid number of rules: " + numRules);
			return null;
		}
		while (tokenizer.hasMoreElements()) {
			final String rule = tokenizer.nextToken();
			double utilizationBorder;
			final int durationInSec;
			final Relation relation;
			final int relativeNumNodes;

			final CPUUtilizationRule r;
			try {
				final StringTokenizer borderTok = new StringTokenizer(rule, ":");
				if (borderTok.countTokens() != 4) {
					UtilizationBasedAdaptationPlanner.log
							.error("Expecting rules of format \"relativeNumNodes:utilizationBorder:relation:durationInSec:\"; found: \""
									+ rule);
					return null;
				}
				relativeNumNodes = Integer.parseInt(borderTok.nextToken());
				utilizationBorder = Double.valueOf(borderTok.nextToken());
				utilizationBorder /= 100.0;
				relation = Relation.valueOf(borderTok.nextToken());
				durationInSec = Integer.parseInt(borderTok.nextToken());
				r = new CPUUtilizationRule(utilizationBorder, durationInSec, relation, relativeNumNodes);
			} catch (final Exception exc) {
				UtilizationBasedAdaptationPlanner.log.error("Failed to extract number from rule: " + rule + "("
						+ exc.getMessage() + ")");
				return null;
			}
			ruleSet.add(r);
		}

		return ruleSet;
	}

	private boolean initRuleSets() {
		final String[] ruleSetPropertyValues =
				super.getInitProperties(UtilizationBasedAdaptationPlanner.PROPERTY_RULESET);

		if (ruleSetPropertyValues != null) {
			for (final String ruleSetPropertyVal : ruleSetPropertyValues) {
				final String[] ruleSetPropertyValSplit = ruleSetPropertyVal.split("::");
				if (ruleSetPropertyValSplit.length != 2) {
					UtilizationBasedAdaptationPlanner.log
							.error("Failed to split rule set property value into two segments by '::': '"
									+ ruleSetPropertyVal + "'");
					return false;
				}

				final String asmCmpContainerTypePair = ruleSetPropertyValSplit[0];
				final String[] asmCmpContainerTypePairSplit = asmCmpContainerTypePair.split("-");
				if (asmCmpContainerTypePairSplit.length != 2) {
					UtilizationBasedAdaptationPlanner.log
							.error("Failed to split assembly component/container type specification by '-'': '"
									+ asmCmpContainerTypePair + "'");
					return false;
				}

				final String fqAssemblyComponentName =
						asmCmpContainerTypePairSplit[0];
				final String containerTypeName = asmCmpContainerTypePairSplit[1];
				final String ruleSetString = ruleSetPropertyValSplit[1];

				/* Parse rule set for increasing workload */
				if ((ruleSetString == null) || ruleSetString.isEmpty()) {
					final String errorMsg =
							"Invalid or missing value for property '"
									+ UtilizationBasedAdaptationPlanner.PROPERTY_RULESET
									+ "'";
					UtilizationBasedAdaptationPlanner.log.error(errorMsg);
					return false;
				}

				final Collection<CPUUtilizationRule> rules = this.parseRuleSetString(ruleSetString);
				if (rules == null) {
					UtilizationBasedAdaptationPlanner.log.error("Failed to parse rule set:"
							+ ruleSetString);
					return false;
				}

				final OverallCPUUtilizationRuleSet rs =
						new OverallCPUUtilizationRuleSet(fqAssemblyComponentName, containerTypeName);

				for (final CPUUtilizationRule r : rules) {
					rs.addCPUUtilizationRule(r.getUtilizationBorder(), r.getDurationInSec(), r.getRelation(),
							r.getRelativeNumNodes());
				}

				this.ruleSet = rs;
			}
		} else {
			UtilizationBasedAdaptationPlanner.log.warn("No rule sets defined (property '"
					+ UtilizationBasedAdaptationPlanner.PROPERTY_RULESET + "'");
		}

		return true;
	}
}
