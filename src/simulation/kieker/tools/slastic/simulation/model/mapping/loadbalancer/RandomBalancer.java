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

package kieker.tools.slastic.simulation.model.mapping.loadbalancer;

import java.util.Collection;
import java.util.Random;

import kieker.tools.slastic.simulation.model.mapping.ILoadBalancer;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class RandomBalancer implements ILoadBalancer {

	// use Random as desmoj is based on it, too
	private final Random r;

	public RandomBalancer() {
		this(System.currentTimeMillis());
	}

	public RandomBalancer(final long seed) {
		this.r = new Random(seed);
	}

	@Override
	public final String getServerMapping(final String asmId, final Collection<String> possibilities) {
		return (String) possibilities.toArray()[this.r.nextInt(possibilities.size())];
	}
}
