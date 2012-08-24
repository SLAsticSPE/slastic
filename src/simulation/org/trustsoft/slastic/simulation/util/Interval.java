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

package org.trustsoft.slastic.simulation.util;

/**
 * 
 * @author Robert von Massow
 * 
 * @param <T>
 */
public class Interval<T> {
	private double lower, upper;

	private T abt;

	public double getLower() {
		return this.lower;
	}

	public void setLower(final double lower) {
		this.lower = lower;
	}

	public double getUpper() {
		return this.upper;
	}

	public void setUpper(final double upper) {
		this.upper = upper;
	}

	public T getAbt() {
		return this.abt;
	}

	public void setAbt(final T abt) {
		this.abt = abt;
	}

	public boolean contains(final double val) {
		return (this.lower <= val) && (this.upper > val);
	}
}
