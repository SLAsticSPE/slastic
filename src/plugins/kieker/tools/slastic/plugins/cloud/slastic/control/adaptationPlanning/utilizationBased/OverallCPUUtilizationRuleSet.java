package kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.utilizationBased;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Florian Fittkau
 * 
 */
public class OverallCPUUtilizationRuleSet {

	private final String fqExecutionContainerTypeName;
	private final String assemblyComponentName;

	private final List<CPUUtilizationRule> rules =
			new ArrayList<CPUUtilizationRule>();

	public OverallCPUUtilizationRuleSet(final String assemblyComponentName, final String executionContainerTypeName) {
		this.assemblyComponentName = assemblyComponentName;
		this.fqExecutionContainerTypeName = executionContainerTypeName;
	}

	public final String getFQExecutionContainerTypeName() {
		return this.fqExecutionContainerTypeName;
	}

	public final String getAssemblyComponentName() {
		return this.assemblyComponentName;
	}

	public final List<CPUUtilizationRule> getRules() {
		return this.rules;
	}

	public void addCPUUtilizationRule(final double utilizationBorder, final int durationInSec, final Relation relation,
			final int relativeNumNodes) {
		this.getRules().add(new CPUUtilizationRule(utilizationBorder, durationInSec,
				relation, relativeNumNodes));
	}

	@Override
	public String toString() {
		String result = "";
		for (final CPUUtilizationRule rule : this.getRules()) {
			result = " " + rule.toString();
		}
		return result;
	}
}