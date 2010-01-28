/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import org.trustsoft.slastic.plugins.starter.kiekerNamedMonitoringPipe.*;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationPipeException extends Exception{
    public ReconfigurationPipeException(String msg){
        super(msg);
    }

    public ReconfigurationPipeException(String msg, Throwable thrw){
        super(msg, thrw);
    }
}
