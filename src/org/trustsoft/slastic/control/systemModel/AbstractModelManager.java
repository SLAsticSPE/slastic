package org.trustsoft.slastic.control.systemModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

import reconfMM.ReconfigurationModel;

public abstract class AbstractModelManager {
	private static final Log log = LogFactory.getLog(ModelManager.class);
	private static ReconfigurationModel model;
	private static AbstractModelManager instance;
	
	public synchronized static AbstractModelManager getInstance(){
		if(instance==null){
			log.error("ModelUpdater is not yet initialized");
			return null;
		}else{
			return instance;
		}
	}
	
	
	public abstract void initModel(ReconfigurationModel model);
	
	public abstract void add(BasicComponent component, ResourceContainer container);
	
	public abstract void remove(BasicComponent component);
	
	public abstract void migrate(BasicComponent component, ResourceContainer oldServer, ResourceContainer newServer);
	
	public abstract void replicate(BasicComponent component);
	
	public abstract void replicate(BasicComponent component, ResourceContainer destination);
	
	public abstract BasicComponent getComponent(int serviceID);

}
