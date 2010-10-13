package org.trustsoft.slastic.tests.junit.framework.esper.quickStart;

/**
 * From http://esper.codehaus.org/tutorials/tutorial/quickstart.html
 * 
 * @author Andre van Hoorn
 * 
 */
public class OrderEvent {
	private String itemName;
	private double price;

	public OrderEvent(String itemName, double price) {
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public double getPrice() {
		return price;
	}
}
