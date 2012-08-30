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

package kieker.tools.slastic.tests.junit.framework.properties;

import java.util.Arrays;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.common.AbstractSLAsticComponent;
import kieker.tools.slastic.common.util.PropertiesFileUtils;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestInitProperties extends TestCase {
	private static final String PROP_PREFIX = PropertyComponent.class.getName() + ".";
	private static final String PROP_KEY_SINGLE = PROP_PREFIX + "singleValue";
	private static final String PROP_VAL_SINGLE = "iuu4thkjfm,";

	private static final String PROP_KEY_MANY = PROP_PREFIX + "multiValue";
	private static final String[] PROP_VALS_MANY = new String[] { "g34f", "ujjwe4r", "3465rgv" };

	public void testSingleValueProp() {
		final Properties prop;
		{ /* Init properties */
			prop = new Properties();
			prop.put(PROP_KEY_SINGLE, PROP_VAL_SINGLE);
		}

		final PropertyComponent comp = new PropertyComponent();
		comp.setProperties(prop);

		{ /* Check if the value is correctly returned by the getProperty method */
			Assert.assertEquals("Unexpected value (getProperty method)", PROP_VAL_SINGLE, comp.getProperty(PROP_KEY_SINGLE));
		}

		{ /* Check if the value is correctly returned by the getProperties method */
			final String[] retVal = comp.getProperties(PROP_KEY_SINGLE);
			Assert.assertEquals("Unexpected array length", 1, retVal.length);
		}
	}

	public void testMultiValueProp() {
		final Properties prop;
		{ /* Init properties */
			prop = new Properties();
			PropertiesFileUtils.putMultiValProp(prop, PROP_KEY_MANY, PROP_VALS_MANY);
		}

		final PropertyComponent comp = new PropertyComponent();
		comp.setProperties(prop);

		{ /*
		 * Check if the 0-th value is correctly returned by the getProperty
		 * method
		 */
			Assert.assertEquals("Unexpected value (getProperty method)", PROP_VALS_MANY[0], comp.getProperty(PROP_KEY_MANY));
		}

		{ /* Check if the value is correctly returned by the getProperties method */
			final String[] retVal = comp.getProperties(PROP_KEY_MANY);
			Assert.assertEquals("Unexpected array length", PROP_VALS_MANY.length, retVal.length);
			Assert.assertTrue(String.format("Property values differ: (expected %s; found %s)", PROP_VALS_MANY, comp.getProperties(PROP_KEY_MANY)),
					Arrays.equals(PROP_VALS_MANY, comp.getProperties(PROP_KEY_MANY)));
		}
	}

	public void testNonExistingProp() {
		final PropertyComponent comp = new PropertyComponent();
		comp.setProperties(new Properties());

		{ /* Check if getProperty returns null (expected for non-existing properties) */
			Assert.assertEquals("Expected null value for non-existing property (getProperty method)",
					comp.getProperty(PROP_KEY_SINGLE), null);
		}

		{ /* Check if getProperty returns the default value (expected for non-existing properties) */
			final String defaultValue = "32gds";
			Assert.assertEquals("Expected default value for non-existing property (getProperty method)", comp.getProperty(PROP_KEY_SINGLE, defaultValue),
					defaultValue);
		}

		{ /* Check if getProperties returns null (expected for non-existing properties) */
			Assert.assertEquals("Expected null value for non-existing property (getProperties method)", comp.getProperties(PROP_KEY_MANY), null);
		}

		{ /* Check if getProperties returns the default values (expected for non-existing properties) */
			final String[] defaultValue = { "32gds", "dsfg4", "sfdg344", "sfdgfd" };
			Assert.assertEquals("Expected default value for non-existing property (getProperties method)",
					comp.getProperties(PROP_KEY_MANY, defaultValue), defaultValue);
		}
	}
}

/**
 * Does nothing but returning its property values by means of the methods {@link PropertyComponent#getProperty(String)} and
 * {@link PropertyComponent#getProperties(String)}.
 * 
 * @author Andre van Hoorn
 * 
 */
class PropertyComponent extends AbstractSLAsticComponent {

	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {}

	public String getProperty(final String name) {
		return super.getInitProperty(name);
	}

	public String getProperty(final String name, final String defaultValue) {
		return super.getInitProperty(name, defaultValue);
	}

	public String[] getProperties(final String names) {
		return super.getInitProperties(names);
	}

	public String[] getProperties(final String name, final String[] defaultValues) {
		return super.getInitProperties(name, defaultValues);
	}
}
