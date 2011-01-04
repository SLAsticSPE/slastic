package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

public abstract class AbstractPerformanceMeasureLogger<T> {
	private static final Log log = LogFactory
			.getLog(AbstractPerformanceMeasureLogger.class);

	private final IComponentContext context;

	private final Map<T, File> entityFiles = new HashMap<T, File>();
	private final Map<T, PrintWriter> entityPrintWriters =
			new HashMap<T, PrintWriter>();

	public AbstractPerformanceMeasureLogger(final IComponentContext context) {
		this.context = context;
	}

	private final String CSV_FIELD_DELIM = ";";

	protected final void writeRow(final T entity, final String[] columns) {
		PrintWriter pw = this.entityPrintWriters.get(entity);
		if (pw == null) {
			/* Haven't seen this entity before; create new Writer */
			pw = this.addNewPrintWriter(entity);
		}

		if (pw == null) {
			AbstractPerformanceMeasureLogger.log
					.warn("Failed to acquired writer for"
							+ entity);
			/* what shall we do with the gummischuh? */
			return;
		}
		this.writeRow(pw, columns);
	}

	private final void writeRow(final PrintWriter pw, final String[] columns) {
		final StringBuilder strB = new StringBuilder();
		for (final String field : columns) {
			strB.append(this.CSV_FIELD_DELIM);
			strB.append(field);
		}
		strB.deleteCharAt(0); // delete first CSV_FIELD_DELIM

		pw.println(strB.toString());
	}

	protected abstract String createFilename(T entity);

	/**
	 * Returns the column names of the result rows.
	 * 
	 * @return
	 */
	protected abstract String[] createHeader();

	protected abstract String createEPStatement();

	/**
	 * Creates a new {@link PrintWriter} for the given
	 * {@link DeploymentComponent} and stores the corresponding {@link File} and
	 * {@link PrintWriter} to the tables {@link #deplCompAvgRTsFiles} and
	 * {@link #deplCompAvgRTsPrintWriters}.
	 * 
	 * @param deplComp
	 * @return the writer, null on error
	 */
	private PrintWriter addNewPrintWriter(final T entity) {

		final String fn = this.createFilename(entity);
		final File file = this.context.createFileInContextDir(fn);

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (final FileNotFoundException e) {
			AbstractPerformanceMeasureLogger.log.error(
					"Failed to create FileWriter for " + file.getAbsolutePath()
							+ "':" + e.getMessage(), e);
		}

		if (pw != null) {
			/*
			 * Add file and writers to the tables *
			 */
			this.writeRow(pw, this.createHeader());
			this.entityFiles.put(entity, file);
			this.entityPrintWriters.put(entity, pw);
		} else {
			// TOOD: set a marker, since now this will be tried over and over
			// again for this deplComp
		}

		return pw;
	}

	public final void closePrintWriters() {
		for (final PrintWriter pw : this.entityPrintWriters.values()) {
			pw.close();
		}
	}
}
