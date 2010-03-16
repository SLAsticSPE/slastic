package org.trustsoft.slastic.plugins.util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private final ExecutorService jobQueue =
            Executors.newSingleThreadExecutor();
    private static final Log log = LogFactory.getLog(REngineFacade.class);
    private final static REngineFacade INSTANCE = new REngineFacade();
    private final Rengine rEngine;

    private REngineFacade() throws IllegalArgumentException {
        this.rEngine = this.initREngine();
    }

    private final Rengine initREngine() throws IllegalArgumentException {
        if (!Rengine.versionCheck()) {
            log.error("R Version mismatch - Java files don't match library version.");
            throw new IllegalArgumentException("R Version mismatch - Java files don't match library version.");
        }
        log.info("Creating Rengine (with arguments)");

        Rengine re = new Rengine(
                new String[]{"--vanilla"},
                false,
                new TextConsole());
        log.info("Rengine created, waiting for R");
        if (!re.waitForR()) {
            log.error("Cannot load R");
            throw new IllegalArgumentException("Cannot load R");
        }

        return re;
    }

    public static final REngineFacade getInstance() {
        return INSTANCE;
    }

    /** Terminates the REngineFacade. */
    public final void end() {
        this.jobQueue.shutdown();
        this.rEngine.end();
    }

    /**
     * Asynchronous execution of the passed R command.
     * This method returns immediately. The result is passed to the
     * caller via the callback handler.
     */
    protected final void rEvalAsync(String rCmd, IREvalCallbackHandler resultHandler) {
        this.jobQueue.submit(new RCallable(rCmd, resultHandler));
    }

    /**
     * Synchronous execution of the passed R command.
     *
     * @return the result
     */
    protected final REXP rEvalSync(String rCmd) throws REngineFacadeEvalException {
        try {
            return this.jobQueue.submit(new RCallable(rCmd)).get();
        } catch (Exception ex) {
            log.error("Error occured while executing R cmd ", ex);
            throw new REngineFacadeEvalException("Error occured while executing R cmd ", ex);
        }
    }

    public final void test() throws REngineFacadeEvalException {
        this.rEvalSync("source(\"bin/r-scripts/util.R\")");
        this.rEvalSync("plotFancyXYCurve(1:10, (1:10)^2)");
        this.rEvalSync("data(iris)");//, false);
        //System.out.println(x = this.rEngine.eval("iris"));
        //System.out.println(x = this.rEngine.eval("5+5"));
        System.out.println("[Sync:]" + (this.rEvalSync("5+5")));
        //this.rEvalSync("plot(1:10,2:11)");
        this.rEvalAsync("10+2", new IREvalCallbackHandler() {

            public void newREXP(REXP rExp) {
                System.out.println("[Async:]" + rExp);
            }
        });
        //this.rEngine.eval("plot(iris)", false);
    }

    public static void main(String[] args) {
        try {
            REngineFacade rFacade = REngineFacade.getInstance();
            rFacade.test();
            //rFacade.end();
        } catch (Exception exc) {
            log.error("An error occured", exc);
            System.err.println("An erroc occured. See log for details.");
            System.exit(1);
        }
    }

    class RCallable implements Callable<REXP> {

        private final String rCmd;
        private final IREvalCallbackHandler cbHandler;

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
         *  Executes the R cmd passed to the constructor and
         *  returns the result. A callback handler, if registered,
         *  is called.
         *
         *  @return the result; null if an error occured
         */
        public REXP call() {
            REXP result = null;
            try {
                result = rEngine.eval(this.rCmd);
            } catch (Exception exc) {
                log.error("Error occured while executing R cmd", exc);
            }
            try {
                if (this.cbHandler != null) {
                    this.cbHandler.newREXP(result);
                }
            } catch (Exception exc) {
                log.error("Error occured while notifying callback handler", exc);
            }
            return result;
        }
    }
}

class TextConsole implements RMainLoopCallbacks {

    private static final Log log = LogFactory.getLog(TextConsole.class);

    public void rWriteConsole(Rengine re, String text, int oType) {
        log.info(text);
    }

    public void rBusy(Rengine re, int which) {
        log.info("rBusy(" + which + ")");
    }

    public String rReadConsole(Rengine re, String prompt, int addToHistory) {
        System.out.print(prompt);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            return (s == null || s.length() == 0) ? s : s + "\n";
        } catch (Exception e) {
            log.error("jriReadConsole exception", e);
        }
        return null;
    }

    public void rShowMessage(Rengine re, String message) {
        log.info(message);
    }

    public String rChooseFile(Rengine re, int newFile) {
        FileDialog fd = new FileDialog(new Frame(), (newFile == 0) ? "Select a file" : "Select a new file", (newFile == 0) ? FileDialog.LOAD : FileDialog.SAVE);
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

    public void rFlushConsole(Rengine re) {
    }

    public void rLoadHistory(Rengine re, String filename) {
    }

    public void rSaveHistory(Rengine re, String filename) {
    }
}
