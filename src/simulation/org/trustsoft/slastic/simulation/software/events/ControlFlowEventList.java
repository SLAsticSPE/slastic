package org.trustsoft.slastic.simulation.software.events;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class ControlFlowEventList {
	private final List<ControlFlowEvent<?>> eventlist = new LinkedList<ControlFlowEvent<?>>();

	private final Stack<SimulatedStackframe<Object>> stack = new Stack<SimulatedStackframe<Object>>();

	private final Model model;

	public ControlFlowEventList(final Model model) {
		this.model = model;
	}

	public ControlFlowEvent<? extends Entity> dequeue() {
		if (eventlist.get(0) instanceof ExternalCallEvent) {
			stack.push(new SimulatedStackframe<Object>(stack.empty() ? null
					: stack.peek()));
		} else if (eventlist.get(0) instanceof ExternalCallReturn) {
			stack.pop();
		}
		return eventlist.remove(0);

	}

	// FIXME: GRRRRRRR
	public void scheduleNext() {
		final ControlFlowEvent event = dequeue();
		// eventlist.get(0).schedule(event., SimTime.NOW);
	}

	public void queue(final ControlFlowEvent<?> cfe) {
		eventlist.add(cfe);
	}

	public SimulatedStackframe<?> currentStackFrame() {
		return stack.peek();
	}
}
