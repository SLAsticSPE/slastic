package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.utilizationBased;

/**
 * 
 * @author Florian Fittkau
 * 
 */
public class CPUUtilizationRule {
	private final double utilizationBorder;
	private final int durationInSec;
	private final Relation relation;
	private final int relativeNumNodes;


	public CPUUtilizationRule(final double utilizationBorder, final int durationInSec, final Relation relation,
			final int relativeNumNodes) {
				this.utilizationBorder = utilizationBorder;
				this.durationInSec = durationInSec;
				this.relation = relation;
				this.relativeNumNodes = relativeNumNodes;
	}


	public double getUtilizationBorder() {
		return this.utilizationBorder;
	}


	public int getDurationInSec() {
		return this.durationInSec;
	}


	public Relation getRelation() {
		return this.relation;
	}


	public final int getRelativeNumNodes() {
		return this.relativeNumNodes;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("utilization: ").append(this.getUtilizationBorder());
		strB.append("; duration: ").append(this.getDurationInSec());
		strB.append("; relation: ").append(this.getRelation().toString());
		strB.append("; relativeNumNodes: ").append(this.getRelativeNumNodes());
		strB.append("]");
		return strB.toString();
	}
}
