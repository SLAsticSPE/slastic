package org.trustsoft.slastic.control;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

public class JPetStoreReconfigurationManager implements IReconfigurationManager {
	private static final Log log = LogFactory.getLog(JPetStoreReconfigurationManager.class);

	@Override
	public void doReconfiguration(SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		log.info("ReconfigurationPlan added");
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub

	}

}
