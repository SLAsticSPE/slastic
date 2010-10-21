package org.trustsoft.slastic.common;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IComponentContext {
	public IComponentContext createSubContext(String name);
}
