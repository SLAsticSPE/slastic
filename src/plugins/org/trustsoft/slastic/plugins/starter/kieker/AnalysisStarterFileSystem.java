package org.trustsoft.slastic.plugins.starter.kieker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import kieker.monitoring.core.MonitoringController;
import kieker.tools.logReplayer.FilesystemLogReplayer;

import org.apache.commons.cli.Option;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Should be moved to SLAstic framework.
 * 
 * @author Andre van Hoorn
 */
public class AnalysisStarterFileSystem extends AbstractAnalysisStarter {

	private static final Log log = LogFactory
			.getLog(AnalysisStarterFileSystem.class);

	private volatile long ignoreRecordsBeforeTimestamp =
			FilesystemLogReplayer.MIN_TIMESTAMP;
	private volatile long ignoreRecordsAfterTimestamp =
			FilesystemLogReplayer.MAX_TIMESTAMP;

	private volatile String[] inputDirsArr;

	private volatile boolean realtimeMode;

	private volatile int numRealtimeWorkerThreads;

	private final static Option[] ADDITIONAL_CMDL_OPTS =
	{ CmdlOptions.CMDL_OPT_FS_INPUT_DIRS,
			CmdlOptions.CMDL_OPT_FS_REALTIME_MODE,
			CmdlOptions.CMDL_OPT_FS_NUM_REALTIME_WORKERS,
			CmdlOptions.CMDL_OPT_IGNORERECORDSBEFOREDATE,
			CmdlOptions.CMDL_OPT_IGNORERECORDSAFTERDATE};

	public AnalysisStarterFileSystem(final String[] args) {
		super(args, AnalysisStarterFileSystem.ADDITIONAL_CMDL_OPTS);
	}

	public static void main(final String[] args) {
		final AnalysisStarterFileSystem starter =
				new AnalysisStarterFileSystem(args);
		if (!starter.start()) {
			System.err.println("Starter terminated with error.");
			System.exit(1);
		}
	}

	/**
	 * Starts the log Kieker log replayer instance and terminates after the
	 * replayer finished.
	 */
	@Override
	protected boolean startReader(
			final MonitoringController monitoringController) {
		monitoringController.enableReplayMode();

		final FilesystemLogReplayer fsReplayer = new FilesystemLogReplayer(
				monitoringController,
						this.inputDirsArr,
						this.realtimeMode,
						this.numRealtimeWorkerThreads,
						this.ignoreRecordsBeforeTimestamp,
						this.ignoreRecordsAfterTimestamp);
		return fsReplayer.replay();
	}

	@Override
	protected boolean init() {
		/* init ignoreRecordsBefore/After */
		final DateFormat m_ISO8601UTC =
				new SimpleDateFormat(
						CmdlOptions.DATE_FORMAT_PATTERN);
		m_ISO8601UTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		try {
			final String ignoreRecordsBeforeTimestampString =
					super.getStringOptionValue(CmdlOptions.CMDL_OPT_IGNORERECORDSBEFOREDATE
							.getLongOpt());
			final String ignoreRecordsAfterTimestampString =
					super.getStringOptionValue(CmdlOptions.CMDL_OPT_IGNORERECORDSAFTERDATE
							.getLongOpt());
			if (ignoreRecordsBeforeTimestampString != null) {
				final Date ignoreBeforeDate =
						m_ISO8601UTC.parse(ignoreRecordsBeforeTimestampString);
				this.ignoreRecordsBeforeTimestamp =
						ignoreBeforeDate.getTime() * (1000 * 1000);
				AnalysisStarterFileSystem.log
						.info("Ignoring records before "
								+ m_ISO8601UTC.format(ignoreBeforeDate)
								+ " ("
								+ this.ignoreRecordsBeforeTimestamp
								+ ")");
			}
			if (ignoreRecordsAfterTimestampString != null) {
				final Date ignoreAfterDate =
						m_ISO8601UTC.parse(ignoreRecordsAfterTimestampString);
				this.ignoreRecordsAfterTimestamp =
						ignoreAfterDate.getTime() * (1000 * 1000);
				AnalysisStarterFileSystem.log
						.info("Ignoring records after "
								+ m_ISO8601UTC.format(ignoreAfterDate)
								+ " ("
								+ this.ignoreRecordsAfterTimestamp
								+ ")");
			}
		} catch (final java.text.ParseException ex) {
			System.err
					.println("Error parsing date/time string. Please use the following pattern: "
							+ CmdlOptions.DATE_FORMAT_PATTERN_CMD_USAGE_HELP);
			AnalysisStarterFileSystem.log
					.error("Error parsing date/time string. Please use the following pattern: "
							+ CmdlOptions.DATE_FORMAT_PATTERN_CMD_USAGE_HELP,
							ex);
			return false;
		}

		try {
			/* init inputDirsArr */
			this.inputDirsArr =
					super.stringOptionValuesNotNullNotEmpty(CmdlOptions.CMDL_OPT_FS_INPUT_DIRS
							.getLongOpt());

			/* init realtimeMode */
			this.realtimeMode =
					super.getBooleanOptionValue(CmdlOptions.CMDL_OPT_FS_REALTIME_MODE
							.getLongOpt());

			/* init numRealtimeWorkerThreads */
			this.numRealtimeWorkerThreads =
					super.getIntOptionValue(
							CmdlOptions.CMDL_OPT_FS_NUM_REALTIME_WORKERS
									.getLongOpt(),
							CmdlOptions.CMDL_OPT_FS_NUM_REALTIME_WORKERS_DEFAULT_VALUE);
		} catch (final Exception exc) {
			AnalysisStarterFileSystem.log.error(
					"Failed to initialize variables: "
							+ exc.getMessage(), exc);
			return false;
		}

		return true;
	}
}
