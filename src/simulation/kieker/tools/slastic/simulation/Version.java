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

package kieker.tools.slastic.simulation;

/**
 * 
 * @author Andre van Hoorn
 */
// TODO: Why is this class in the simulation package?
public class Version {

	/*
	 * The VERSION string is updated by the Ant build file, which looks for the
	 * pattern: VERSION = <quote>.*<quote>
	 */
	private static final String VERSION = "0.95-20090624";

	static final String COPYRIGHT = "2012 SLAstic Project";

	/**
	 * Not instantiable.
	 */
	private Version()
	{
		super();
	}

	/**
	 * Returns the version String.
	 * 
	 * @return the version String.
	 */
	public static final String getVERSION() {
		return VERSION;
	}
}
