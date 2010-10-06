/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationPipeException extends Exception{
    public ReconfigurationPipeException(final String msg){
        super(msg);
    }

    public ReconfigurationPipeException(final String msg, final Throwable thrw){
        super(msg, thrw);
    }
}
