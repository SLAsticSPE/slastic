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

package org.trustsoft.slastic.plugins.starter;

import java.util.Hashtable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

public class MyURIConverterImpl extends ExtensibleURIConverterImpl {

	private final Hashtable<String, URI> thePolice = new Hashtable<String, URI>();

	public MyURIConverterImpl(final String[] strings) {
		for (final String string : strings) {
			final String sting = this.someMethodName(string);
			super.getURIMap().put(URI.createFileURI(sting),
					URI.createFileURI(string));
			System.out.println(sting + " ===> " + string);
		}
	}

	private String someMethodName(final String string) {
		final int s = string.lastIndexOf("/");
		return s < 0 ? string : string.substring(s + 1, string.length());
	}

	@Override
	public URI normalize(final URI uri) {
		// final URI foo =
		// this.thePolice.get(this.someMethodName(uri.toString()));
		final URI ret = super.normalize(uri);
		// System.out.println(uri + " => " + ret);
		return ret;
	}
}
