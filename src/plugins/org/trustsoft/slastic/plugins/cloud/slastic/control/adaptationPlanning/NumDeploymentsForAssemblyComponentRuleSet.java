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
public class NumDeploymentsForAssemblyComponentRuleSet {
	private static final Log log = LogFactory
			.getLog(NumDeploymentsForAssemblyComponentRuleSet.class);

	private final String fqAssemblyComponentName;
	private final String fqExecutionContainerTypeName;
	
	/**
	 * @param assemblyComponentName
	 * @param executionContainerTypeName
	 */
	public NumDeploymentsForAssemblyComponentRuleSet(final String assemblyComponentName, final String executionContainerTypeName) {
		this.fqAssemblyComponentName = assemblyComponentName;
		this.fqExecutionContainerTypeName = executionContainerTypeName;
	}

	/**
	 * The key is the respective {@link Baseline#getCenter()}.
	 */
	private final TreeMap<Long, Baseline> baselines =
			new TreeMap<Long, Baseline>();

	/**
	 * @return the assemblyComponentName
	 */
	public final String getFQAssemblyComponentName() {
		return this.fqAssemblyComponentName;
	}

	/**
	 * @return the executionContainerTypeName
	 */
	public final String getFQExecutionContainerTypeName() {
		return this.fqExecutionContainerTypeName;
	}

	/**
	 * @return
	 */
	public Baseline getInitialBaseline() {
		return this.baselines.firstEntry().getValue();
	}

	/**
	 * 
	 * @param oldBaseline
	 * @param workloadIntensity
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

		NumDeploymentsForAssemblyComponentRuleSet.log.info("Old baseline " + oldBaseline +
				" , workload intensity " + workloadIntensity + " -> baseline "
				+ nextBaseline);
		return nextBaseline;
	}

	/**
	 * 
	 * @param lowerBorder
	 * @param center
	 * @param upperBorder
	 * @param numNodes
	 */
	public void addBaselineRule(final long lowerBorder, final long center,
			final long upperBorder, final int numNodes) {
		this.baselines.put(center, new Baseline(upperBorder, center,
				lowerBorder, numNodes));
	}

	/**
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
			NumDeploymentsForAssemblyComponentRuleSet.log.error("set of baselines is invalid");
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
					NumDeploymentsForAssemblyComponentRuleSet.log.error("Invalid order of baselines: "
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