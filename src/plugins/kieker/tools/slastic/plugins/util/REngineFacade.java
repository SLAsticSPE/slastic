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

package kieker.tools.slastic.plugins.util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

/**
 * 
 * @author Andre van Hoorn
 */
public class REngineFacade {
	private static final Log LOG = LogFactory.getLog(REngineFacade.class);

	private final ExecutorService jobQueue = Executors.newSingleThreadExecutor();

	private final Rengine rEngine;

	private REngineFacade() throws IllegalArgumentException {
		this.rEngine = this.initREngine();
	}

	private final Rengine initREngine() throws IllegalArgumentException {
		if (!Rengine.versionCheck()) {
			LOG.error("R Version mismatch - Java files don't match library version.");
			throw new IllegalArgumentException("R Version mismatch - Java files don't match library version.");
		}
		LOG.info("Creating Rengine (with arguments)");

		final Rengine re = new Rengine(
				new String[] { "--vanilla" },
				false,
				new TextConsole());
		LOG.info("Rengine created, waiting for R");
		if (!re.waitForR()) {
			LOG.error("Cannot load R");
			throw new IllegalArgumentException("Cannot load R");
		}

		return re;
	}

	public static final REngineFacade getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * SINGLETON
	 */
	private final static class LazyHolder {
		static {
			INSTANCE = new REngineFacade();
		}
		private final static REngineFacade INSTANCE;
	}

	/** Terminates the */
	public final void end() {
		this.jobQueue.shutdown();
		this.rEngine.end();
	}

	/**
	 * Asynchronous execution of the passed R command.
	 * This method returns immediately. The result is passed to the
	 * caller via the callback handler.
	 */
	protected final void rEvalAsync(final String rCmd, final IREvalCallbackHandler resultHandler) {
		this.jobQueue.submit(new RCallable(rCmd, resultHandler));
	}

	/**
	 * Synchronous execution of the passed R command.
	 * 
	 * @return the result
	 */
	protected final REXP rEvalSync(final String rCmd) throws REngineFacadeEvalException {
		try {
			return this.jobQueue.submit(new RCallable(rCmd)).get();
		} catch (final Exception ex) {
			LOG.error("Error occured while executing R cmd ", ex);
			throw new REngineFacadeEvalException("Error occured while executing R cmd ", ex);
		}
	}

	public final void test() throws REngineFacadeEvalException {
		this.rEvalSync("source(\"bin/r-scripts/util.R\")");
		this.rEvalSync("plotFancyXYCurve(1:10, (1:10)^2)");
		this.rEvalSync("data(iris)");// , false);
		// System.out.println(x = this.rEngine.eval("iris"));
		// System.out.println(x = this.rEngine.eval("5+5"));
		System.out.println("[Sync:]" + (this.rEvalSync("5+5")));
		// this.rEvalSync("plot(1:10,2:11)");
		this.rEvalAsync("10+2", new IREvalCallbackHandler() {

			@Override
			public void newREXP(final REXP rExp) {
				System.out.println("[Async:]" + rExp);
			}
		});
		// this.rEngine.eval("plot(iris)", false);
	}

	public static void main(final String[] args) {
		try {
			LOG.info("R_HOME=" + System.getProperty("R_HOME"));
			LOG.info("LD_LIBRARY_PATH=" + System.getProperty("LD_LIBRARY_PATH"));
			LOG.info("java.library.path=" + System.getProperty("java.library.path"));
			final REngineFacade rFacade = REngineFacade.getInstance();
			rFacade.test();
			// rFacade.end();
		} catch (final Exception exc) {
			LOG.error("An error occured", exc);
			System.err.println("An erroc occured. See log for details.");
			System.exit(1);
		}
	}

	class RCallable implements Callable<REXP> {

		private final String rCmd;
		private final IREvalCallbackHandler cbHandler;

		@SuppressWarnings("unused")
		private RCallable() {
			this(null, null);
		}

		public RCallable(final String rCmd) {
			this(rCmd, null);
		}

		public RCallable(final String rCmd, final IREvalCallbackHandler cbHandler) {
			this.rCmd = rCmd;
			this.cbHandler = cbHandler;
		}

		/**
		 * Executes the R cmd passed to the constructor and
		 * returns the result. A callback handler, if registered,
		 * is called.
		 * 
		 * @return the result; null if an error occurred
		 */
		@Override
		public REXP call() {
			REXP result = null;
			try {
				result = REngineFacade.this.rEngine.eval(this.rCmd);
			} catch (final Exception exc) {
				LOG.error("Error occured while executing R cmd", exc);
			}
			try {
				if (this.cbHandler != null) {
					this.cbHandler.newREXP(result);
				}
			} catch (final Exception exc) {
				LOG.error("Error occured while notifying callback handler", exc);
			}
			return result;
		}
	}
}

class TextConsole implements RMainLoopCallbacks {

	private static final Log log = LogFactory.getLog(TextConsole.class);

	@Override
	public void rWriteConsole(final Rengine re, final String text, final int oType) {
		TextConsole.log.info(text);
	}

	@Override
	public void rBusy(final Rengine re, final int which) {
		TextConsole.log.info("rBusy(" + which + ")");
	}

	@Override
	public String rReadConsole(final Rengine re, final String prompt, final int addToHistory) {
		System.out.print(prompt);
		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			final String s = br.readLine();
			return ((s == null) || (s.length() == 0)) ? s : s + "\n";
		} catch (final Exception e) {
			TextConsole.log.error("jriReadConsole exception", e);
		}
		return null;
	}

	@Override
	public void rShowMessage(final Rengine re, final String message) {
		TextConsole.log.info(message);
	}

	@Override
	public String rChooseFile(final Rengine re, final int newFile) {
		final FileDialog fd = new FileDialog(new Frame(), (newFile == 0) ? "Select a file" : "Select a new file", (newFile == 0) ? FileDialog.LOAD : FileDialog.SAVE);
		fd.show();
		String res = null;
		if (fd.getDirectory() != null) {
			res = fd.getDirectory();
		}
		if (fd.getFile() != null) {
			res = (res == null) ? fd.getFile() : (res + fd.getFile());
		}
		return res;
	}

	@Override
	public void rFlushConsole(final Rengine re) {}

	@Override
	public void rLoadHistory(final Rengine re, final String filename) {}

	@Override
	public void rSaveHistory(final Rengine re, final String filename) {}
}
