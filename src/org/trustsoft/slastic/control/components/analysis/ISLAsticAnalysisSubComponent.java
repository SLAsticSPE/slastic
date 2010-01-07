package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.ISLAsticComponent;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticAnalysisSubComponent extends ISLAsticComponent {
	public void handle(ISLAsticAnalysisEvent event);
}