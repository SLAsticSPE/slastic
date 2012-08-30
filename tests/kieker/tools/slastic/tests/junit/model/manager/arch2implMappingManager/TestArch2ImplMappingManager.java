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

package kieker.tools.slastic.tests.junit.model.manager.arch2implMappingManager;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager;
import kieker.tools.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestArch2ImplMappingManager extends TestCase {
	private static final int NUM_ENTRIES_PER_TYPE = 12;

	public void testValidMappings() {
		final Arch2ImplNameMappingManager mgr = new Arch2ImplNameMappingManager();

		for (int i = 0; i < TestArch2ImplMappingManager.NUM_ENTRIES_PER_TYPE; i++) {
			for (final EntityType type : EntityType.values()) {
				final String[] rndMapping = this.genRndMapping();
				final String archName = rndMapping[0];
				final String implName = rndMapping[1];

				mgr.registerArch2implNameMapping(type, archName, implName);

				Assert.assertEquals("Unexected impl name for arch name '" + archName + "'", implName, mgr.lookupImplName4ArchName(type, archName));

				Assert.assertEquals("Unexpected arch name for impl name '" + implName + "'", archName, mgr.lookupArchName4ImplName(type, implName));
			}
		}
	}

	public void testDuplicateImplNameForArchName() {
		final Arch2ImplNameMappingManager mgr =
				new Arch2ImplNameMappingManager();

		final String[] rndMapping = this.genRndMapping();
		final String archName = rndMapping[0];
		final String implName = rndMapping[1];

		final EntityType type = EntityType.ASSEMBLY_COMPONENT; // just to have chosen one

		mgr.registerArch2implNameMapping(type, archName, implName);

		try {
			mgr.registerArch2implNameMapping(type, archName, implName + "_");

			Assert.fail("Expected IllegalArgumentException since we added duplicate mapping");
		} catch (final IllegalArgumentException exc) {
			/* We expect this exc to be thrown! */
		}
	}

	public void testDuplicateArchNameForImplName() {
		final Arch2ImplNameMappingManager mgr =
				new Arch2ImplNameMappingManager();

		final String[] rndMapping = this.genRndMapping();
		final String archName = rndMapping[0];
		final String implName = rndMapping[1];

		final EntityType type = EntityType.EXECUTION_CONTAINER; // just to have
																// chosen one

		mgr.registerArch2implNameMapping(type, archName, implName);

		try {
			mgr.registerArch2implNameMapping(type, archName + "_", implName);

			Assert.fail("Expected IllegalArgumentException since we added duplicate mapping");
		} catch (final IllegalArgumentException exc) {
			/* We expect this exc to be thrown! */
		}
	}

	private int nextNum = 0;
	private final static String ARCH_PREFIX = "arch_";
	private final static String IMPL_PREFIX = "impl_";

	/**
	 * Returns a random mapping between an architectural (idx 0) and an
	 * implementation-level (idx 1) name.
	 * 
	 * @return
	 */
	private String[] genRndMapping() {
		final String archName = TestArch2ImplMappingManager.ARCH_PREFIX + this.nextNum;
		final String implName = TestArch2ImplMappingManager.IMPL_PREFIX + this.nextNum;
		this.nextNum++;
		return new String[] { archName, implName };
	}
}
