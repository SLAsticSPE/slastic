package org.trustsoft.slastic.reconfiguration;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 857441585526958662L;

	public ReconfigurationException(final String msg){
        super(msg);
    }

    public ReconfigurationException(final String msg, final Throwable thrw){
        super(msg, thrw);
    }
}
