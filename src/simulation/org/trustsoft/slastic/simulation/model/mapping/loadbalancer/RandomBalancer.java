package org.trustsoft.slastic.simulation.model.mapping.loadbalancer;

import java.util.Collection;
import java.util.Random;

import org.trustsoft.slastic.simulation.model.mapping.LoadBalancer;

public final class RandomBalancer implements LoadBalancer {

	// use Random as desmoj shit is based on it, too
	private final Random r;

	public RandomBalancer() {
		this(System.currentTimeMillis());
	}

	public RandomBalancer(final long seed) {
		this.r = new Random(seed);
	}

	@Override
	public final String getServerMapping(final String asmId,
			final Collection<String> possibilities) {
		return (String) possibilities.toArray()[this.r.nextInt(possibilities
				.size())];
	}
}
