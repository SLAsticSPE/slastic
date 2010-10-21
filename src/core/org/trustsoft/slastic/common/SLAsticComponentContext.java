package org.trustsoft.slastic.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
class SLAsticComponentContext implements IComponentContext {
	private static final Log log = LogFactory
			.getLog(SLAsticComponentContext.class);

	private final String name;
	private final File directory;

	private SLAsticComponentContext() {
		this.name = null;
		this.directory = null;
	}

	/**
	 * Creates a new {@link SLAsticComponentContext} with given name and
	 * (existing) output directory.
	 * 
	 * @param name
	 * @param directory
	 *            existing(!) directory associated to this
	 *            {@link SLAsticComponentContext}.
	 */
	private SLAsticComponentContext(final String name, final File directory) {
		this.name = name;
		this.directory = directory;
	}

	/**
	 * Returns a newly created {@link IComponentContext} with the given name and
	 * an associated output directory located in the default temporary
	 * directory.
	 * 
	 * @return
	 */
	public static IComponentContext createRootContext(final String name) {
		final String tmpdir = System.getProperty("java.io.tmpdir");
		final String dirname =
				SLAsticComponentContext.createRootContextDirname(tmpdir, name);
		return SLAsticComponentContext.createContext(name, dirname);

	}

	/**
	 * Returns a newly created {@link IComponentContext} with the given name; a
	 * directory with the given name will created an associated with the
	 * {@link IComponentContext}.
	 * 
	 * @param name
	 * @param dirname
	 * @return
	 */
	private static IComponentContext createContext(final String name,
			final String dirname) {
		return new SLAsticComponentContext(name,
				SLAsticComponentContext.createDirectory(dirname));
	}

	@Override
	public IComponentContext createSubcontext(final String name) {
		return SLAsticComponentContext.createContext(this.name + name,
				this.getDirectoryLocation() + "/" + name);
	}

	@Override
	public String getDirectoryLocation() {
		return this.directory.getAbsolutePath();
	}

	@Override
	public File createFileInContextDir(final String fn) {
		final String fqFilename = this.getDirectoryLocation() + "/" + this.name;
		final File f = new File(fqFilename);
		try {
			f.createNewFile();
		} catch (final IOException e) {
			SLAsticComponentContext.log.error("Failed to create file '"
					+ fqFilename + "':");
		}
		return f;
	}

	/**
	 * Creates a directory for this context in the given baseDirectory.
	 * 
	 * @return the {@link File} object for the created directory or null if the
	 *         creation failed.
	 * @throws IOException
	 */
	private static String createRootContextDirname(final String baseDirectory,
			final String namePostfix) {
		final DateFormat m_ISO8601UTC =
				new SimpleDateFormat("yyyyMMdd'-'HHmmssSS");
		m_ISO8601UTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		final String dateStr = m_ISO8601UTC.format(new java.util.Date());
		final String dirname =
				baseDirectory + "/slastic-" + dateStr + "-UTC-" + namePostfix
						+ "/";
		return dirname;
	}

	/**
	 * Creates a directory with the given name.
	 * 
	 * @param dirName
	 * @return
	 */
	private static final File createDirectory(final String dirname) {
		final File f = new File(dirname);
		if (!f.mkdir()) {
			SLAsticComponentContext.log.error("Failed to create directory '"
					+ dirname + "'");
			return null;
		}
		return f;
	}
}
