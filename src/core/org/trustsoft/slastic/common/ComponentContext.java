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
class ComponentContext implements IComponentContext {
	private static final Log LOG = LogFactory.getLog(ComponentContext.class);

	private final String name;
	private final File directory;

	private ComponentContext() {
		this.name = null;
		this.directory = null;
	}

	/**
	 * Creates a new {@link ComponentContext} with given name and
	 * (existing) output directory.
	 * 
	 * @param name
	 * @param directory
	 *            existing(!) directory associated to this {@link ComponentContext}.
	 */
	private ComponentContext(final String name, final File directory) {
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
		final String dirname = ComponentContext.createRootContextDirname(tmpdir, name);
		return ComponentContext.createContext(name, dirname);

	}

	/**
	 * Returns a newly created {@link IComponentContext} with the given name; a
	 * directory with the given name will created an associated with the {@link IComponentContext}.
	 * 
	 * @param name
	 * @param dirname
	 * @return
	 */
	private static IComponentContext createContext(final String name, final String dirname) {
		return new ComponentContext(name, ComponentContext.createDirectory(dirname));
	}

	@Override
	public IComponentContext createSubcontext(final String name) {
		return ComponentContext.createContext(this.name + name, this.getDirectoryLocation() + "/" + name + "/");
	}

	@Override
	public String getDirectoryLocation() {
		return this.directory.getAbsolutePath();
	}

	@Override
	public File createFileInContextDir(final String fn) {
		final String fqFilename = this.getDirectoryLocation() + "/" + fn;
		final File f = new File(fqFilename);
		try {
			f.createNewFile();
		} catch (final IOException e) {
			LOG.error("Failed to create file '"
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
		final DateFormat m_ISO8601UTC = new SimpleDateFormat("yyyyMMdd'-'HHmmssSS");
		m_ISO8601UTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		final String dateStr = m_ISO8601UTC.format(new java.util.Date());
		final String dirname = baseDirectory + "/slastic-" + dateStr + "-UTC" + namePostfix + "/";
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
			LOG.error("Failed to create directory '" + dirname + "'");
			return null;
		}
		return f;
	}
}
