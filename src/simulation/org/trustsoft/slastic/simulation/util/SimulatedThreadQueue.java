package org.trustsoft.slastic.simulation.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;

public class SimulatedThreadQueue implements Iterable<EntryCall> {

	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();
	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final AtomicBoolean finished = new AtomicBoolean(false);
	private final Log log = LogFactory.getLog(this.getClass());

	private final TreeSet<EntryCall> queue = new TreeSet<EntryCall>(
			new Comparator<EntryCall>() {

				public int compare(EntryCall t, EntryCall t1) {
					return t.getTin() < t1.getTin() ? -1 : 1;
				}

			});

	public void add(final EntryCall ec) {
		synchronized (this.queue) {
			if (this.queue.size() > Constants.PRE_BUFFER
					&& this.queue.first().getTout() > ec.getTin()) {
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
		while (this.waiters.peek() != current
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
