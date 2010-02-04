package org.trustsoft.slastic.simulation.model.mapping;

import java.util.Collection;

public interface LoadBalancer {

	abstract public String getServerMapping(String asmId,
			Collection<String> possibilities);
}
