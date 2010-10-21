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
