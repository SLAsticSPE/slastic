package org.trustsoft.slastic.reconfiguration;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationException extends Exception {
    public ReconfigurationException(String msg){
        super(msg);
    }

    public ReconfigurationException(String msg, Throwable thrw){
        super(msg, thrw);
    }
}
