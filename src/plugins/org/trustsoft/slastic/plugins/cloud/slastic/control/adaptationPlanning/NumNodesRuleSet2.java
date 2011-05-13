package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO: Junit Test!
 *  
 * @author Andre van Hoorn
 * 
 */
public class NumNodesRuleSet2 {
	private static final Log log = LogFactory
			.getLog(NumNodesRuleSet2.class);

	/**
	 * The key is the respective {@link Baseline#getCenter()}.
	 */
	private final TreeMap<Long, Baseline> baselines =
			new TreeMap<Long, Baseline>();

	/**
	 * 
	 * @return
	 */
	public Baseline getInitialBaseline() {
		return this.baselines.firstEntry().getValue();
	}

	/**
	 * 
	 * @param workloadIntensityIncreasingCondition
	 * @return
	 */
	public Baseline getNextBaseline(
			final Baseline oldBaseline,
			final long workloadIntensity) {
		final Baseline nextBaseline;

		if ((workloadIntensity >= oldBaseline.getLowerBorder()) &&
				(workloadIntensity <= oldBaseline.getUpperBorder())) {
			/* 1. Within old baseline interval */
			nextBaseline = oldBaseline;
		} else {
			/* 2. Exceeding upper border of old baseline */
			nextBaseline =
					this.baselines.floorEntry(workloadIntensity).getValue();
		}

		NumNodesRuleSet2.log.info("Old baseline " + oldBaseline +
				" , workload intensity " + workloadIntensity + " -> baseline "
				+ nextBaseline);
		return nextBaseline;
	}

	public void addBaselineRule(final long lowerBorder, final long center,
			final long upperBorder, final int numNodes) {
		this.baselines.put(center, new Baseline(upperBorder, center,
				lowerBorder, numNodes));
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean isRuleSetValid() {
		/*
		 * Check whether the center and numNode values are increasing
		 */
		final SortedMap<Long, Baseline> sortedBaselines =
				this.baselines.tailMap(0l);
		if (!this.isKeysAndValuesIncreasing(sortedBaselines)) {
			NumNodesRuleSet2.log.error("set of baselines is invalid");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	private boolean isKeysAndValuesIncreasing(
			final SortedMap<Long, Baseline> list) {
		Baseline prevBaseline = null;

		for (final Map.Entry<Long, Baseline> e : list.entrySet()) {
			final Baseline curBaseline = e.getValue();
			if (prevBaseline != null) {
				if (!((prevBaseline.getCenter() < curBaseline.getCenter()) && (prevBaseline
						.getNumNodes() < curBaseline.getNumNodes()))) {
					NumNodesRuleSet2.log.error("Invalid order of baselines: "
							+ prevBaseline
							+ " -> " + curBaseline);
				}
			}

			prevBaseline = e.getValue();
		}
		return true;
	}

	@Override
	public String toString() {
		return this.baselines.toString();
	}
}