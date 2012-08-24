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
	public Baseline(final long upperBorder, final long center, final long lowerBorder, final int numNodes) {
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
