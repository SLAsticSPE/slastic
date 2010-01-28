/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.simulation.kiekerNamedMonitoringPipe;

/**
 *
 * @author Andre van Hoorn
 */
public class PipeException extends Exception{
    public PipeException(String msg){
        super(msg);
    }

    public PipeException(String msg, Throwable thrw){
        super(msg, thrw);
    }
}
