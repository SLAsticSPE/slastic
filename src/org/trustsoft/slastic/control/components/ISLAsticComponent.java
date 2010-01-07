/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.control.components;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticComponent {

    public void init(String initString) throws IllegalArgumentException;

    public boolean execute();

    public void terminate();
}
