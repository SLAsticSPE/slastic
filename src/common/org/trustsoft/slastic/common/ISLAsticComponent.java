/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.common;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticComponent {

    public void init(String initString) throws IllegalArgumentException;

    public boolean execute();

    public void terminate();
}
