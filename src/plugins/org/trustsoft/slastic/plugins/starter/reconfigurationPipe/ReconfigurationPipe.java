package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationPipe {
    private final String name;

    /** No construction employing default constructor */
    private ReconfigurationPipe(){ name = null; }

    public ReconfigurationPipe(final String name){
        this.name = name;
    }
}
