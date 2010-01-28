/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.reconfiguration;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticReconfigurationException extends Exception {
    public SLAsticReconfigurationException(String msg){
        super(msg);
    }

    public SLAsticReconfigurationException(String msg, Throwable thrw){
        super(msg, thrw);
    }
}
