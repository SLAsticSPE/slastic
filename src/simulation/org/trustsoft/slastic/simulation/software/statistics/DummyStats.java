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

package org.trustsoft.slastic.simulation.software.statistics;

import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import com.google.inject.Singleton;

/**
 * 
 * @author Robert von Massow
 * 
 */
@Singleton
public final class DummyStats implements ISystemStats {

	@Override
	public final void subSystemUser() {}

	@Override
	public final void addSystemUser() {}

	@Override
	public final void logComponentUsers(final String component, final String Host, final Integer users) {}

	@Override
	public final void logCPUUsage(final String server, final double usage) {}

	@Override
	public final void logExecution(final StackFrame frame, final int depth) {}

}
