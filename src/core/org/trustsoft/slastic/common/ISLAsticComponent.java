/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.common;

import java.util.Properties;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticComponent {

    public void init(Properties props) throws IllegalArgumentException;
    
    /**
     * Initiates the start of a component.
     * This method is called once and must not be blocking!
     * Asynchronous components would spawn (an) aynchronous thread(s) in this
     * method.
     *
     * @return true on success; false otherwise.
     */
    public boolean execute();

    /**
     * Initiates a termination of the component. The value of the parameter
     * error indicates whether an error occured.
     *
     * @param error true iff an error occured.
     */
    public void terminate(boolean error);
}
