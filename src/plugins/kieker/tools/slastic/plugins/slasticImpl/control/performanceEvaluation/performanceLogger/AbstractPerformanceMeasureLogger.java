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

package kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.common.IComponentContext;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 * 
 * @author Andre van Hoorn
 * 
 * @param <T>
 */
public abstract class AbstractPerformanceMeasureLogger<T> {
	private static final Log LOG = LogFactory.getLog(AbstractPerformanceMeasureLogger.class);

	private final IComponentContext context;

	private final ConcurrentMap<T, File> entityFiles = new ConcurrentHashMap<T, File>();
	// TODO #20, remove? private final ConcurrentMap<T, PrintWriter> entityPrintWriters = new ConcurrentHashMap<T, PrintWriter>();

	public static final boolean IO_FLUSH_AFTER_EACH_RECORD_DEFAULT = true;

	private final boolean ioFlushAfterEachRecord; // TODO #20, remove?

	public AbstractPerformanceMeasureLogger(final IComponentContext context, final boolean ioFlushAfterEachRecord) {
		this.ioFlushAfterEachRecord = ioFlushAfterEachRecord;
		this.context = context;
	}

	private final String CSV_FIELD_DELIM = ";";

	protected final void writeRow(final T entity, final String[] columns) {
		File file = this.entityFiles.get(entity);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			if (file == null) {
				/* Haven't seen this entity before; create new File */
				file = this.createNewFileForEntity(entity);
			}

			fileWriter = new FileWriter(file, true); // append
			bufferedWriter = new BufferedWriter(fileWriter);
			this.writeRow(bufferedWriter, columns);
		} catch (final IOException e) {
			LOG.error("Failed to write line to " + file.getAbsolutePath() + "':" + e.getMessage(), e);
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (final IOException e) {
					// can't change it
				}
			}
		}
	}

	private final void writeComment(final Writer pw, final String comment) throws IOException {
		pw.write("# " + comment);
		pw.write('\n');
	}

	private final void writeRow(final Writer pw, final String[] columns) throws IOException {
		boolean firstCol = true;
		for (final String field : columns) {
			if (firstCol) {
				firstCol = false;
			} else {
				pw.write(this.CSV_FIELD_DELIM);
			}
			pw.write(field);
		}
		pw.write('\n');
	}

	protected abstract String createFilename(T entity);

	/**
	 * Returns the column names of the result rows.
	 * 
	 * @return
	 */
	protected abstract String[] createHeader();

	/**
	 * Allows to add meta-info about the data, e.g., sample intervals. This data
	 * will be written as a comment line following the header as returned by {@link #createHeader()}.
	 * 
	 * @return
	 */
	protected abstract String createMetaInfoLine();

	protected abstract String createEPStatement();

	/**
	 * Returns a string that is the given file name with all characters not
	 * allowed in file names removed.
	 */
	// TODO: Refine
	private final String escapeFileName(final String filename) {
		return filename.replaceAll("/", "-");
	}

	/**
	 * Creates a new {@link PrintWriter} for the given {@link DeploymentComponent} and stores the corresponding {@link File} and {@link PrintWriter} to the tables
	 * {@link #deplCompAvgRTsFiles} and {@link #deplCompAvgRTsPrintWriters}.
	 * 
	 * @param deplComp
	 * @return the writer, null on error
	 * @throws IOException
	 */
	private File createNewFileForEntity(final T entity) throws IOException {

		final String fn = this.escapeFileName(this.createFilename(entity));
		final File file = this.context.createFileInContextDir(fn);

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file, true); // append
			bufferedWriter = new BufferedWriter(fileWriter);

			/*
			 * Add file and writers to the tables *
			 */
			this.writeRow(bufferedWriter, this.createHeader());
			this.writeComment(bufferedWriter, this.createMetaInfoLine());
			this.entityFiles.put(entity, file);
			// TODO #20, disabled: this.entityPrintWriters.put(entity, pw);
		} catch (final FileNotFoundException e) {
			LOG.error("Failed to write to initialize file " + file.getAbsolutePath() + "':" + e.getMessage(), e);
		} finally {
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
		}

		return file;
	}

	public final void closePrintWriters() {
		// TODO #20, disabled:
		// for (final PrintWriter pw : this.entityPrintWriters.values()) {
		// pw.close();
		// }
	}
}
