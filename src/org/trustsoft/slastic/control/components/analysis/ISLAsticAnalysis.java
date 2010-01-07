package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.ISLAsticComponent;

/**
 * This Interface represents the Analysis component of the SLAstic.CONTROL framework. It holds different componente that are responsible for different analysis'.
 * @author Lena Stoever
 *
 */
public interface ISLAsticAnalysis extends ISLAsticComponent {

    // TODO: remove
    public void setSLAs(slal.Model slas);
}
