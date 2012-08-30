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

package kieker.tools.slastic.simulation.software.controller;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class EntryCall {

	private final long tin;
	private final long traceId;
	private final String opname;
	private final String componentName;
	private final long tout;
	private volatile boolean scheduled = false;

	public EntryCall(final String componentName, final String opname, final long traceId, final long tin, final long tout) {
		this.componentName = componentName;
		this.opname = opname;
		this.traceId = traceId;
		this.tin = tin;
		this.tout = tout;
	}

	public final long getTin() {
		return this.tin;
	}

	public final long getTraceId() {
		return this.traceId;
	}

	public final String getOpname() {
		return this.opname;
	}

	public final String getComponentName() {
		return this.componentName;
	}

	public final long getTout() {
		return this.tout;
	}

	public final boolean isScheduled() {
		return this.scheduled;
	}

	public final void setScheduled(final boolean state) {
		this.scheduled = state;
	}

}
