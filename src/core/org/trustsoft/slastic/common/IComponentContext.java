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

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IComponentContext {

	/**
	 * Returns a new {@link IComponentContext} for a sub
	 * 
	 * @param name
	 * @return
	 */
	public IComponentContext createSubcontext(String name);

	/**
	 * Returns the absolute path of the output directory assigned to the
	 * context.
	 * 
	 * @return
	 */
	public String getDirectoryLocation();

	/**
	 * Creates a file with the given name located in this context's directory,
	 * as returned by {@link #getDirectoryLocation()}.
	 * 
	 * @return the newly created file; null on error
	 */
	public File createFileInContextDir(String fn);
}
