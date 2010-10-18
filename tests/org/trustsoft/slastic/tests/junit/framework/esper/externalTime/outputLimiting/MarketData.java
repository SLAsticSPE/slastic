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
