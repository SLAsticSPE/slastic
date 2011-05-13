package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class NumNodesRuleSet {
	private static final Log log = LogFactory.getLog(NumNodesRuleSet.class);

	private final TreeMap<Long, Integer> upList = new TreeMap<Long, Integer>();

	private final TreeMap<Long, Integer> downList =
			new TreeMap<Long, Integer>();

	private long intensityBaseline = 500;

	/**
	 * 
	 * @param workloadIntensityIncreasingCondition
	 * @return
	 */
	public int getNumNodeActionIncreasing(final int curNumNodes,
			final long workloadIntensityIncreasingCondition) {
		if (Math.abs(this.intensityBaseline
				- workloadIntensityIncreasingCondition) < 500) {
			return curNumNodes;
		} else {

			final Entry<Long, Integer> upListEntry =
			/*
			 * Key with biggest intensity value less or equal
			 * workloadIntensityDecreasing
			 */
			this.upList.floorEntry(workloadIntensityIncreasingCondition);

			final int numNodes = upListEntry.getValue();

			NumNodesRuleSet.log.info("Increased to "
					+ workloadIntensityIncreasingCondition + " ->" + numNodes);
			this.intensityBaseline = upListEntry.getKey();
			return numNodes;
		}
	}

	/**
	 * 
	 * @param workloadIntensityDecreasing
	 * @return
	 */
	public int getNumNodeActionDecreasing(final int curNumNodes,
			final long workloadIntensityDecreasing) {
		if (Math.abs(this.intensityBaseline - workloadIntensityDecreasing) < 500) {
			return curNumNodes;
		} else {
			final Entry<Long, Integer> downListEntry =
			/*
			 * value of the smallest intensity key greater or equal
			 * workloadIntensityDecreasing
			 */
			this.downList.ceilingEntry(workloadIntensityDecreasing);

			final int numNodes = downListEntry.getValue();

			NumNodesRuleSet.log.info("Decreased to "
					+ workloadIntensityDecreasing + " ->" + numNodes);
			this.intensityBaseline = downListEntry.getKey();
			return numNodes;
		}
	}

	/**
	 * 
	 * @param workloadIntensityIncreasingCondition
	 * @param numNodesAction
	 */
	public void addIncreasingRule(
			final long workloadIntensityIncreasingCondition,
			final int numNodesAction) {
		this.upList.put(workloadIntensityIncreasingCondition, numNodesAction);
	}

	/**
	 * 
	 * @param workloadIntensityDecreasingCondition
	 * @param numNodesAction
	 */
	public void addDecreasingRule(
			final long workloadIntensityDecreasingCondition,
			final int numNodesAction) {
		this.downList.put(workloadIntensityDecreasingCondition, numNodesAction);
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean isRuleSetValid() {
		/* Check length of uplist */

		/*
		 * Check whether the keys and values of both internal tables are
		 * increasing
		 */
		final SortedMap<Long, Integer> sortedUplist = this.upList.tailMap(0l);
		if (!this.isKeysAndValuesIncreasing(sortedUplist)) {
			NumNodesRuleSet.log.error("uplist is invalid");
			return false;
		}

		final SortedMap<Long, Integer> sortedDownlist =
				this.downList.tailMap(0l);
		if (!this.isKeysAndValuesIncreasing(sortedDownlist)) {
			NumNodesRuleSet.log.error("downlist is invalid");
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
			final SortedMap<Long, Integer> list) {
		int prevValue = Integer.MIN_VALUE;
		long prevKey = Long.MIN_VALUE;

		for (final Map.Entry<Long, Integer> e : list.entrySet()) {
			if (e.getKey() <= prevKey) {
				NumNodesRuleSet.log.error("Expected key > " + prevKey
						+ "; found: " + e.getKey());
				return false;
			}
			prevKey = e.getKey();

			if (e.getValue() <= prevValue) {
				NumNodesRuleSet.log.error("Expected key > " + prevKey
						+ "; found: " + e.getKey());
				return false;
			}
			prevValue = e.getValue();
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder();
		strB.append("Rule set for increasing workload: ").append(this.upList);
		strB.append("Rule set for decreasing workload: ").append(this.downList);
		return strB.toString();
	}
}