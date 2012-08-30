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

package kieker.tools.slastic.tests.junit.framework;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.common.FrameworkInstance;
import kieker.tools.slastic.common.util.PropertiesFileUtils;
import kieker.tools.slastic.control.AbstractControlComponent;

/**
 * 
 * @author Andre van Hoorn
 */
public class TestSLAsticFrameworkInstanceDummyConfiguration extends TestCase {
	private static final Log LOG = LogFactory.getLog(AbstractControlComponent.class);

	/**
	 * Makes sure that a dummy instance can be initialized and executed.
	 */
	public void testInitAndRunInstanceByProperties() {
		final Properties configuration = FrameworkInstance.genDummyConfiguration();

		final FrameworkInstance slasticInstance = new FrameworkInstance(configuration);
		final boolean success = slasticInstance.run();
		Assert.assertTrue("slasticInstance.run() returned false", success);
		slasticInstance.terminate(false); // no error
	}

	/**
	 * Makes sure that a dummy instance can be initialized and executed where
	 * the configuration is read from a configuration file.
	 */
	public void testInitAndRunInstanceByPropertiesFile() throws IOException {
		final Properties configuration = FrameworkInstance.genDummyConfiguration();

		/*
		 * Create a tmp file the configuration will be saved to
		 * and mark the file to be deleted on jvm termination
		 */
		final File tmpFile = File.createTempFile("slastic-configuration-", "");
		// tmpFile.deleteOnExit();

		/* Save properties to file */
		LOG.info("Writing dummy configuration to file " + tmpFile.getAbsolutePath());
		PropertiesFileUtils.storePropertiesFile(configuration, tmpFile.getAbsolutePath());

		final FrameworkInstance slasticInstance = new FrameworkInstance(tmpFile.getAbsolutePath());
		final boolean success = slasticInstance.run();
		Assert.assertTrue("slasticInstance.run() returned false", success);
		slasticInstance.terminate(false); // no error
	}
}
