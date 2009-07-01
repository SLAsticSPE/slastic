package org.trustsoft.slastic.control.recordConsumer;

import java.util.EventListener;

public interface ICalculateAverageListener extends EventListener {
	
	public void  calculateAverageEventOccured(CalculateAverageEvent evt);

}
