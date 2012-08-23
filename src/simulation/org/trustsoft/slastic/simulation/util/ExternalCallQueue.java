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

package org.trustsoft.slastic.simulation.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class ExternalCallQueue implements Iterable<EntryCall> {

	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();
	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final AtomicBoolean finished = new AtomicBoolean(false);

	private final TreeSet<EntryCall> queue = new TreeSet<EntryCall>(
			new Comparator<EntryCall>() {

				public int compare(final EntryCall t, final EntryCall t1) {
					return t.getTin() < t1.getTin() ? -1 : 1;
				}

			});

	public void add(final EntryCall ec) {
		synchronized (this.queue) {
			if ((this.queue.size() > Constants.PRE_BUFFER) && (this.queue.first().getTout() > ec.getTin())) {
				this.lock();
			}
			this.queue.add(ec);
			this.queue.notify();
		}
	}

	public EntryCall removeFirstBlocking() {
		EntryCall ret;
		synchronized (this.finished) {
			synchronized (this.locked) {
				if (!this.locked.get() && this.finished.get()) {
					return null;
				}
			}
			synchronized (this.queue) {
				if (this.queue.isEmpty()) {
					try {
						this.queue.wait();
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
				ret = this.queue.first();
				if (!this.finished.get()) {
					this.queue.remove(ret);
				}
				this.unlock();
			}
		}
		return ret;
	}

	public void clear() {
		this.lock();
		this.queue.clear();
		this.unlock();
	}

	public void lock() {
		boolean wasInterrupted = false;
		final Thread current = Thread.currentThread();
		this.waiters.add(current);

		// Block while not first in queue or cannot acquire lock
		while ((this.waiters.peek() != current)
				|| !this.locked.compareAndSet(false, true)) {
			LockSupport.park(this);
			if (Thread.interrupted()) {
				wasInterrupted = true;
			}
		}

		this.waiters.remove();
		if (wasInterrupted) {
			current.interrupt();
		}
	}

	public void unlock() {
		this.locked.set(false);
		LockSupport.unpark(this.waiters.peek());
	}

	@Override
	public Iterator<EntryCall> iterator() {
		return this.queue.iterator();
	}

	public void finish() {
		this.finished.compareAndSet(false, true);
	}

}
