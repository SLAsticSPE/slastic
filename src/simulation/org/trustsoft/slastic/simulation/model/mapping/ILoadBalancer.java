package org.trustsoft.slastic.simulation.model.mapping;

import java.util.Collection;

/**
 * 
 * @author Robert von Massow
 * 
 */
public interface ILoadBalancer {

	abstract public String getServerMapping(String asmId,
			Collection<String> possibilities);
}
