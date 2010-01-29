package org.trustsoft.slastic.plugins.pcmreconfiguration;

/**
 *
 * @author Andre van Hoorn
 */
public class PCMModelReader {
    private static final PCMModelReader INSTANCE = new PCMModelReader();

    public static final PCMModelReader getInstance(){
        return INSTANCE;
    }


}
