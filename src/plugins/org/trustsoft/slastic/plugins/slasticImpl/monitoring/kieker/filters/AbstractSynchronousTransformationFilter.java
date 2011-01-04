package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

public abstract class AbstractSynchronousTransformationFilter extends
		AbstractTransformationComponent implements ISynchronousTransformationFilter {

	/**
	 * 
	 * @param modelManager
	 */
	public AbstractSynchronousTransformationFilter(
			final ModelManager modelManager) {
		super(modelManager);
	}
}
