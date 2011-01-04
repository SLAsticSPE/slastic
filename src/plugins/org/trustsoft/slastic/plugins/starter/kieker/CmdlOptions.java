package org.trustsoft.slastic.plugins.starter.kieker;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class CmdlOptions {
	public final static Option CMDL_OPT_START_FRAMEWORK = CmdlOptions
			.createOptionWithArgAndShortOpt("start-framework", "f",
					"SLAstic.Framework configuration file",
					true /* isRequired */, "file");
	public final static Option CMDL_OPT_KIEKER_PIPENAME =
			CmdlOptions
					.createOptionWithArgAndShortOpt(
							"kieker-pipeName",
							"p",
							"Name of named pipe used to receive records from Kieker reader",
							true /* isRequired */, "name");

	public final static Option CMDL_OPT_JMS_PROVIDER_URL =
			CmdlOptions.createOptionWithArgAndShortOpt(
					"jmsProviderUrl", "u",
					"example: \"tcp://127.0.0.1:3035/\"",
					true /* isRequired */, "url");

	public final static Option CMDL_OPT_JMS_DESTINATION =
			CmdlOptions.createOptionWithArgAndShortOpt(
					"jmsDestination", "d", "example: \"queue1\"",
					true /* isRequired */, "queue");

	public final static Option CMDL_OPT_FS_INPUT_DIRS =
			CmdlOptions
					.createOptionWithArgAndShortOpt(
							"kieker-dirs",
							"d",
							"List of Kieker input directories (separated by semicolon)",
							true /* isRequired */, "dir0 ... dirN");

	public final static Option CMDL_OPT_FS_REALTIME_MODE =
			CmdlOptions.createOptionWithArgAndShortOpt("realtime-mode",
					"r",
					"Replay log data in realtime?", true /* isRequired */,
					"true|false");

	public final static int CMDL_OPT_FS_NUM_REALTIME_WORKERS_DEFAULT_VALUE = 1;
	public final static Option CMDL_OPT_FS_NUM_REALTIME_WORKERS =
			CmdlOptions
					.createOptionWithArgAndShortOpt(
							"realtime-worker-threads",
							"n",
							"Number of worker threads used in realtime mode (defaults to "
									+ CmdlOptions.CMDL_OPT_FS_NUM_REALTIME_WORKERS_DEFAULT_VALUE
									+ ").",
							false /* !isRequired */,
							"num");

	public final static Option CMDL_OPT_IGNORERECORDSBEFOREDATE =
			CmdlOptions
					.createOptionWithArgAndShortOpt(
							"ignore-records-before-date",
							null /* No short name */,
							"Records logged before this date (UTC timezone) are ignored (disabled by default).",
							false /* !isRequired */,
							CmdlOptions.DATE_FORMAT_PATTERN_CMD_USAGE_HELP);

	public final static Option CMDL_OPT_IGNORERECORDSAFTERDATE =
			CmdlOptions
					.createOptionWithArgAndShortOpt(
							"ignore-records-after-date",
							null /* No short name */,
							"Records logged after this date (UTC timezone) are ignored (disabled by default).",
							false /* !isRequired */,
							CmdlOptions.DATE_FORMAT_PATTERN_CMD_USAGE_HELP);

	public static final String DATE_FORMAT_PATTERN = "yyyyMMdd'-'HHmmss";
	public static final String DATE_FORMAT_PATTERN_CMD_USAGE_HELP =
			CmdlOptions.DATE_FORMAT_PATTERN
					.replaceAll("'", ""); // only for usage info

	public static final char DEFAULT_VALUE_SEPARATOR = '=';

	/**
	 * 
	 * @param longOpt
	 * @param shortName
	 * @param description
	 * @param isRequired
	 * @param argName
	 * @return
	 */
	public static Option createOptionWithArgAndShortOpt(
			final String longOpt, final String shortName,
			final String description, final boolean isRequired,
			final String argName) {
		final Option o =
				new Option(shortName, longOpt, true /* hasArg */, description);
		o.setRequired(isRequired);
		o.setArgName(argName);
		o.setValueSeparator(CmdlOptions.DEFAULT_VALUE_SEPARATOR);
		return o;
	}

	/**
	 * 
	 * @param cmdLine
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static String stringOptionValueNotEmpty(final CommandLine cmdLine,
			final String optName) throws NullPointerException,
			IllegalArgumentException {
		final String stringValue =
				cmdLine.getOptionValue(optName);

		if (stringValue == null) {
			throw new NullPointerException("Value of option '" + optName
					+ "' is null");
		}

		if (stringValue.isEmpty()) {
			throw new IllegalArgumentException("Value of option '" + optName
					+ "' is the empty String");
		}

		return stringValue;
	}

	public static String[] stringOptionValuesNotNullNotEmpty(
			final CommandLine cmdLine,
			final String optName) throws NullPointerException,
			IllegalArgumentException {
		final String[] values =
				cmdLine.getOptionValues(optName);

		if (values == null) {
			throw new NullPointerException("Values of option '" + optName
					+ "' is null");
		}

		if (values.length == 0) {
			throw new IllegalArgumentException("Values of option '" + optName
					+ "' is the empty String array");
		}

		return values;
	}

	/**
	 * 
	 * @param cmdLine
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 */
	public static boolean booleanOptionValue(final CommandLine cmdLine,
			final String optName) throws NullPointerException {
		final String stringValue =
				cmdLine.getOptionValue(optName);

		if (stringValue == null) {
			throw new NullPointerException("Value of option '" + optName
					+ "' is null");
		}

		return Boolean.parseBoolean(stringValue);
	}

	/**
	 * 
	 * @param cmdLine
	 * @param optName
	 * @param defaultValue
	 * @return
	 */
	public static boolean booleanOptionValue(final CommandLine cmdLine,
			final String optName, final boolean defaultValue) {
		final String stringValue =
				cmdLine.getOptionValue(optName, Boolean.toString(defaultValue));

		return Boolean.parseBoolean(stringValue);
	}

	/**
	 * 
	 * @param cmdLine
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 */
	public static int intOptionValue(final CommandLine cmdLine,
			final String optName) throws NullPointerException {
		final String stringValue =
				cmdLine.getOptionValue(optName);

		if (stringValue == null) {
			throw new NullPointerException("Value of option '" + optName
					+ "' is null");
		}

		return Integer.parseInt(stringValue);
	}

	/**
	 * 
	 * @param cmdLine
	 * @param optName
	 * @param defaultValue
	 * @return
	 * @throws NumberFormatException
	 */
	public static int intOptionValue(final CommandLine cmdLine,
			final String optName, final int defaultValue)
			throws NumberFormatException {
		final String stringValue =
				cmdLine.getOptionValue(optName, Integer.toString(defaultValue));

		return Integer.parseInt(stringValue);
	}
}
