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
