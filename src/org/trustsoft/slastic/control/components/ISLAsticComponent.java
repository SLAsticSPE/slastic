/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.control.components;

import kieker.common.logReader.RecordConsumerExecutionException;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticComponent {

    public boolean init(String initString);

    public boolean execute() throws RecordConsumerExecutionException;

    public void terminate();
}
