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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.util.PropertiesFileUtils;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticComponent implements ISLAsticComponent {

	private static final Log log = LogFactory.getLog(AbstractSLAsticComponent.class);
	private volatile Properties properties;

	private volatile IComponentContext componentContext;

	@Override
	public final IComponentContext getComponentContext() {
		return this.componentContext;
	}

	@Override
	public final void setComponentContext(
			final IComponentContext componentContext) {
		this.componentContext = componentContext;
	}

	@Override
	public final void setProperties(final Properties properties) {
		this.properties = properties;
	}

	/**
	 * Returns the value for the initialization property @a propName or the the
	 * passed default value @a default if no value for this property exists.
	 */
	protected final String getInitProperty(final String propName,
			final String defaultVal) {
		final String[] retVals = this.getInitProperties(propName, new String[] { defaultVal });

		return retVals[0];
	}

	/**
	 * Returns the value for the initialization property @a propName or null if
	 * no value for this property exists.
	 */
	protected final String getInitProperty(final String propName) {
		return this.getInitProperty(propName, null);
	}

	/**
	 * Returns the values for the multi-value property @ propName or the given
	 * default values if no value for this property exists.
	 * 
	 * @return
	 */
	protected final String[] getInitProperties(final String propName, final String[] defaultValues) {
		String[] retVals = this.getInitProperties(propName);
		if (retVals == null) {
			retVals = defaultValues;
		}

		return retVals;
	}

	/**
	 * Returns the values for the multi-value property @ propName or null if no
	 * value for this property exists.
	 * 
	 * @return
	 */
	protected final String[] getInitProperties(final String propName) {
		int curIdx = 0;

		if (this.properties == null) {
			AbstractSLAsticComponent.log.error("properties are null");
			return null;
		}

		final Collection<String> values = new ArrayList<String>();

		String val = this.properties.getProperty(propName);
		if (val != null) {
			/* 1. try non-indexed variant for single value (propName=...) */
			values.add(val);
		} else {
			/* 2. try indexed variant (propName[0..n]=...) */
			while (true) {
				val =
						this.properties.getProperty(PropertiesFileUtils.genMultiValPropKey(propName, curIdx++));
				if (val == null) {
					break;
				}
				values.add(val);
			}
		}

		if (values.size() == 0) {
			return null; // no such property
		}
		return values.toArray(new String[] {});
	}
}
