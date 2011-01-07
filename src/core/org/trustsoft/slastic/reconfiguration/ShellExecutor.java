/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.reconfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Vector;

import org.omg.CORBA.SystemException;

/**
 * @author Andre van Hoorn
 */
public class ShellExecutor {

    public static boolean invoke(final String command, final List<String> args, final boolean spawn) {
        int errorCode;

        try {
            final Execute execute = new Execute(command, spawn);
            for (final String arg : args) {
                execute.addArg(arg);
            }
            errorCode = execute.exec();
            if (errorCode != 0) {
                return false;
            }
        } catch (final Exception exc) {
            exc.printStackTrace();
            return false;
        }
        return true;
    }
}

class Execute {
    private String executable = "";
    private final Vector<String> args = new Vector<String>();
    private boolean spawn = false;
    BufferedReader stdout, stderr;

    @SuppressWarnings("unused")
	private Execute() {
    }

    public Execute(final String executable, final boolean spawn) {
        this.setExecutable(executable);
        this.setSpawn(spawn);
    }

    public String getExecutable() {
        return this.executable != null ? this.executable : null;
    }

    public void setExecutable(final String executable) {
        if (executable == null) {
            throw new InvalidParameterException("executable must not be null!");
        }
        this.executable = executable;
    }

    public void setArgs(final String args[]) {

    }

    public String[] getArgs() {
        return this.args.toArray(new String[0]);
    }

    public void clearArgs() {
        this.args.clear();
    }

    public void addArg(final String arg) {
        if (arg == null) {
            throw new InvalidParameterException("arg must not be null!");
        }
        this.args.add(arg);
    }

    public boolean isSpawn() {
        return this.spawn;
    }

    public void setSpawn(final boolean spawn) {
        this.spawn = spawn;
    }

    public String getCommandline() {
        final StringBuilder cmdString = new StringBuilder(this.executable);
        for (final String arg : this.args) {
            cmdString.append(" " + arg);
        }
        return cmdString.toString();
    }

    /**
     * Executes the command and returns the process.
     *
     * @return the system error code (0-127) when spawn set to false; -1 else.
     */
    public int exec() throws SystemException, IOException,
            NullPointerException, IllegalArgumentException, InterruptedException {

        //sometimes it helps to explicity call garbage collection before starting a new process:
        System.gc();
        Thread.sleep(2000); // addionally we wait two secs


        final Vector<String> fullCmd = new Vector<String>(this.args);
        fullCmd.add(0, this.executable);
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            fullCmd.add(0, "cmd" ); // call winnt command line interpreter first
            fullCmd.add(0, "/c" );
        }
        final Process p = Runtime.getRuntime().exec(fullCmd.toArray(new String[0]));
        int errorCode = -1;
        if (!this.spawn) {
            this.stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
            this.stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            final Thread stdoutThread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            String line;
                            while ((line = Execute.this.stdout.readLine()) != null) {
                                System.out.println(line);
                            }
                            Execute.this.stdout.close();
                        } catch (final IOException ie) {
                            System.out.println("IO exception on stdout: " + ie);

                    }
                }
            };
            stdoutThread.start();
            final Thread stderrThread = new Thread() {

                @Override
                public void run() {
                    try {
                        String line;
                        while ((line = Execute.this.stderr.readLine()) != null) {
                            System.out.print(line);
                        }
                        Execute.this.stderr.close();
                    } catch (final IOException ie) {
                        System.out.println("IO exception on stderr: " + ie);
                    }
                }
            };
            stderrThread.start();
            errorCode = p.waitFor();
        }
        return errorCode;
    }
}