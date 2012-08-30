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

package org.trustsoft.slastic.plugins.cloud.common;

import java.util.List;

/**
 * Observer used for the different handlers. When the results of the external
 * program are ready, the resultsReady procedure is called
 * 
 * @author Florian Fittkau
 * 
 */
public interface IResultObserver {
	/**
	 * called when the results are complete and available for further use
	 * 
	 * @param results
	 */
	void resultsReady(List<String> results);
}
