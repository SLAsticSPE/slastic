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

package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

/**
 * The {@link MarketData} event used in Appendix A of the Esper Reference Guide.
 * 
 * @author Andre van Hoorn
 * 
 */
public class MarketData {

	final String symbol;

	public String getSymbol() {
		return this.symbol;
	}

	public int getVolume() {
		return this.volume;
	}

	public double getPrice() {
		return this.price;
	}

	final int volume;
	final double price;

	@SuppressWarnings("unused")
	private MarketData() {
		this.symbol = null;
		this.volume = -1;
		this.price = -1;
	}

	public MarketData(final String symbol, final int volume, final double price) {
		this.symbol = symbol;
		this.volume = volume;
		this.price = price;
	}
}
