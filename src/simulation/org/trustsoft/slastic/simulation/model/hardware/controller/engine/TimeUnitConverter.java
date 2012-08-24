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

package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: For what reason aren't we using the functionality provided by Java's TimeUnit class?
public class TimeUnitConverter {
	public static long toNS(final long in, final TimeUnit u) {
		switch (u) {
		case NS:
			return in;
		case MuS:
			return in * 1000;
		case MS:
			return in * 1000000;
		case S:
			return in * 1000000000;
		default:
			return -1;
		}
	}

}
