package org.trustsoft.slastic.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticStarter {

    private static final Log log = LogFactory.getLog(SLAsticStarter.class);
    private static CommandLine cmdl = null;
    private static final CommandLineParser cmdlParser = new BasicParser();
    private static final HelpFormatter cmdHelpFormatter = new HelpFormatter();
    private static final Options cmdlOpts = new Options();

    static {
        cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt("configuration").isRequired(true).withDescription("Configuration file").withValueSeparator('=').create("f"));
    }

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");

        if (!parseArgs(args)) {
            System.exit(1);
        }

        SLAsticInstance inst = initSLAsticInstanceFromArgs();
        if (inst == null) {
            log.error("initNewInstanceFromArgs() returned null");
            System.exit(1);
        }
        inst.run();
        log.info("SLAsticInstance started");

        log.info("Bye, this was SLAsticControl");
    }

    /**
     * Initializes and returns a ControlComponent analysis instance.
     *
     * @return the initialized instance; null on error
     */
    private static SLAsticInstance initSLAsticInstanceFromArgs() throws IllegalArgumentException {
        String configurationFile = cmdl.getOptionValue("configuration");


        if (configurationFile == null) {
            log.fatal("Configuration file parameter is null");


            throw new IllegalArgumentException("Configuration file parameter is null");


        }

        SLAsticInstance inst = null;

        // Load configuration file
        InputStream is = null;
        Properties prop = new Properties();


        try {
            is = new FileInputStream(configurationFile);
            log.info("Loading configuration from file '" + configurationFile + "'");
            prop.load(is);
            inst = new SLAsticInstance(prop);


        } catch (Exception ex) {
            log.error("Error creating SLAsticInstance", ex);


            throw new IllegalArgumentException("Error creating SLAsticInstance", ex);
            // TODO: introduce static variable 'terminated' or alike


        } finally {
            try {
                is.close();


            } catch (Exception ex) { /* nothing we can do */ }
        }
        return inst;


    }


    static boolean parseArgs(String[] args) {
        try {
            cmdl = cmdlParser.parse(cmdlOpts, args);


        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            printUsage();



            return false;


        }

        return true;



    }
    
    private static void printUsage() {
        cmdHelpFormatter.printHelp(SLAsticInstance.class.getName(), cmdlOpts);
    }
}
