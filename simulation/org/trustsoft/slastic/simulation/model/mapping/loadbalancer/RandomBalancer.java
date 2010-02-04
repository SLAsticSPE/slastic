package org.trustsoft.slastic.simulation.model.mapping.loadbalancer;

import java.util.Collection;
import java.util.Random;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.model.mapping.LoadBalancer;


public final class RandomBalancer implements LoadBalancer {

	// use Random as desmoj shit is based on it, too
	private final Random r;

	public RandomBalancer() {
		this(System.currentTimeMillis());
	}

	public RandomBalancer(final long seed) {
		r = new Random(seed);
	}

	@Override
	public String getServerMapping(final String asmId,
			final Collection<String> possibilities) {
		return ((String) (possibilities.toArray()[r.nextInt(possibilities
				.size())]));
	}
}
