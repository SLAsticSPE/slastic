/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.trustsoft.slastic.common;

import java.util.Properties;

/**
 * 
 * @author Andre van Hoorn
 */
public interface ISLAsticComponent {

	/**
	 * Returns the {@link IComponentContext} associated to the component.
	 * 
	 * @return
	 */
	public IComponentContext getComponentContext();

	/**
	 * Sets the {@link IComponentContext} associated to the component.
	 * 
	 * @param context
	 */
	public void setComponentContext(IComponentContext context);

	/**
	 * Sets the properties of the component.
	 * 
	 * This is a method used by the framework and must not be called by users.
	 * 
	 * @param properties
	 *            the component properties
	 */
	public void setProperties(Properties props) throws IllegalArgumentException;

	/**
	 * Initializes the component <b>before it is connected to other framework
	 * components</b> and before it is started by a call to the
	 * {@link #execute()} method.
	 * 
	 * @return true on success; false otherwise
	 */
	public boolean init();

	/**
	 * Initiates the start of a component. This method is called once and must
	 * not be blocking! Asynchronous components would spawn (an) asynchronous
	 * thread(s) in this method.
	 * 
	 * @return true on success; false otherwise
	 */
	public boolean execute();

	/**
	 * Initiates a termination of the component. The value of the parameter
	 * error indicates whether an error occurred.
	 * 
	 * @param error
	 *            true iff an error occured
	 */
	public void terminate(boolean error);
}
