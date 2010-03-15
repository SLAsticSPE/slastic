package org.trustsoft.slastic.plugins.util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

        Rengine rEngine = new Rengine(
                new String[]{"--vanilla"},
                false,
                new TextConsole());
        log.info("Rengine created, waiting for R");
        if (!rEngine.waitForR()) {
            log.error("Cannot load R");
            throw new IllegalArgumentException("Cannot load R");
        }

        return rEngine;
    }

    public static final REngineFacade getInstance() {
        return INSTANCE;
    }

    public final void test() {
        REXP x;
        this.rEngine.eval("data(iris)", false);
        //System.out.println(x = this.rEngine.eval("iris"));
        System.out.println(x = this.rEngine.eval("5+5"));
    }

    public static void main(String[] args) {
        try {
            REngineFacade rFacade = REngineFacade.getInstance();
            rFacade.test();
        } catch (Exception exc) {
            log.error("An error occured", exc);
            System.err.println("An erroc occured. See log for details.");
            System.exit(1);
        }
    }
}

class TextConsole implements RMainLoopCallbacks {

    public void rWriteConsole(Rengine re, String text, int oType) {
        System.out.print(text);
    }

    public void rBusy(Rengine re, int which) {
        System.out.println("rBusy(" + which + ")");
    }

    public String rReadConsole(Rengine re, String prompt, int addToHistory) {
        System.out.print(prompt);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            return (s == null || s.length() == 0) ? s : s + "\n";
        } catch (Exception e) {
            System.out.println("jriReadConsole exception: " + e.getMessage());
        }
        return null;
    }

    public void rShowMessage(Rengine re, String message) {
        System.out.println("rShowMessage \"" + message + "\"");
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
