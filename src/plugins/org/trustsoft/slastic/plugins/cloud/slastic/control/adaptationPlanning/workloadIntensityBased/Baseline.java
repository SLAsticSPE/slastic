package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.workloadIntensityBased;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class Baseline {
	private final long upperBorder;
	private final long center;
	private final long lowerBorder;
	private final int numNodes;

	/**
	 * @param upperBorder
	 * @param center
	 * @param lowerBorder
	 * @param numNodes
	 */
	public Baseline(final long upperBorder, final long center,
			final long lowerBorder,
			final int numNodes) {
		this.upperBorder = upperBorder;
		this.center = center;
		this.lowerBorder = lowerBorder;
		this.numNodes = numNodes;
	}

	/**
	 * @return the upperBorder
	 */
	public final long getUpperBorder() {
		return this.upperBorder;
	}

	/**
	 * @return the center
	 */
	public final long getCenter() {
		return this.center;
	}

	/**
	 * @return the lowerBorder
	 */
	public final long getLowerBorder() {
		return this.lowerBorder;
	}

	/**
	 * @return the numNodes
	 */
	public final int getNumNodes() {
		return this.numNodes;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("lower: ").append(this.getLowerBorder());
		strB.append("; center: ").append(this.getCenter());
		strB.append("; upper: ").append(this.getUpperBorder());
		strB.append("; numNodes: ").append(this.getNumNodes());
		strB.append("]");
		return strB.toString();
	}
}
