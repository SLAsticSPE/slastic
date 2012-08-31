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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class FileUtils {

	/**
	 * Copies the contents of the file <i>fromFile</i> the a newly created file <i>toFile</i>.
	 * 
	 * @param fromFile
	 * @param toFile
	 * @throws IOException
	 */
	public static void copyFileToFile(final String fromFn, final String toFn) throws IOException {
		final File fromFile = new File(fromFn);
		final File toFile = new File(toFn);

		FileUtils.copyFileToFile(fromFile, toFile);
	}

	public static void copyFileToFile(final File fromFile, final File toFile) throws IOException {
		// TODO: assert that both fromFile and toFile are regular files

		FileInputStream is = null;
		FileOutputStream os = null;

		try {

			is = new FileInputStream(fromFile);

			os = new FileOutputStream(toFile);

			final byte[] buffer = new byte[1024];

			int length;

			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}

	public static void copyFileToDir(final File fromFile, final File toDir) throws IOException {
		// TODO: assert that fromFile is a regular file and that toDir is a directory

		final String fromFileBasename = fromFile.getName();
		final File toFile = new File(toDir, fromFileBasename);
		FileUtils.copyFileToFile(fromFile, toFile);
	}
}
