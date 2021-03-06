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

package kieker.tools.slastic.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class PropertiesFileUtils {

	private static final Log LOG = LogFactory.getLog(PropertiesFileUtils.class);

	/**
	 * 
	 * @param fn
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Properties loadPropertiesFile(final String fn) throws IllegalArgumentException {
		InputStream is = null;
		final Properties prop = new Properties();

		try {
			is = new FileInputStream(fn);
			LOG.debug("Loading properties from file '" + fn + "'");
			prop.load(is);
		} catch (final Exception ex) {
			LOG.error("Failed to load properties from file '" + fn + "'", ex);
			throw new IllegalArgumentException("Failed to load properties from file '" + fn + "'", ex);
		} finally {
			try {
				is.close();
			} catch (final Exception ex) {
				LOG.error("Failed to close property input stream", ex);
			}
		}
		return prop;
	}

	/**
	 * 
	 * @param prop
	 * @param fn
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Properties storePropertiesFile(final Properties prop, final String fn)
			throws IllegalArgumentException {
		OutputStream os = null;

		try {
			os = new FileOutputStream(fn);
			LOG.info("Storing properties to file '" + fn + "'");
			prop.store(os, null); // no comment
		} catch (final Exception ex) {
			LOG.error("Failed to store properties to file '" + fn + "'", ex);
			throw new IllegalArgumentException("Failed to store properties to file '" + fn + "'", ex);
		} finally {
			try {
				os.close();
			} catch (final Exception ex) {
				LOG.error("Failed to close property output stream", ex);
			}
		}
		return prop;
	}

	public static final String genMultiValPropKey(final String propName, final int idx) {
		return String.format("%s[%s]", propName, idx);
	}

	public static final void putMultiValProp(final Properties prop, final String propName, final String[] values) {
		for (int i = 0; i < values.length; i++) {
			prop.put(PropertiesFileUtils.genMultiValPropKey(propName, i), values[i]);
		}
	}
}
