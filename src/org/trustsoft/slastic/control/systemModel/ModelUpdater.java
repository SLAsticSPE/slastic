package org.trustsoft.slastic.control.systemModel;

import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import reconfMM.*;

public class ModelUpdater  {
	
	private static final Log log = LogFactory.getLog(ModelUpdater.class);
	private static ReconfigurationModel model;
	private static ModelUpdater instance;
	//private final static TreeMap<Integer, ConcurrentSkipListSet<SLOMonitoringRecord>> map = new TreeMap<Integer, ConcurrentSkipListSet<SLOMonitoringRecord>>();
	
	private  ModelUpdater(ReconfigurationModel reconfigurationModel){
		model = reconfigurationModel;
		this.initSet();
	}
	
	private void initSet() {
		for(int i = 0; i< model.getComponents().size(); i++){
			for(int k = 0; k < model.getComponents().get(i).getServices().size(); k++){
				//It is important not to use a Set of Longs, because of the possibility of equal values of response times
				ConcurrentSkipListSet<SLOMonitoringRecord> list = new ConcurrentSkipListSet<SLOMonitoringRecord>();
				model.getComponents().get(i).getServices().get(k).setResponseTimes(list);				
			}
		}
		
	}

	public static void initModel(ReconfigurationModel model){
		if(instance == null){
			instance = new ModelUpdater(model);
			log.info("ModelUpdater initialized!");
		}else{
			log.info("ModelUpdater is already initialized");
		}
	}
	
	public synchronized static ModelUpdater getInstance(){
		if(instance==null){
			log.error("ModelUpdater is not yet initialized");
			return null;
		}else{
			return instance;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void updateModel(SLOMonitoringRecord newRecord, SLOMonitoringRecord oldRecord){
		int serviceID = newRecord.serviceId;
		boolean updated = false;
		for(int i = 0; i< model.getComponents().size();i++){
			for(int k = 0; k<model.getComponents().get(i).getServices().size(); k++){
				Service service = model.getComponents().get(i).getServices().get(k);
				if(service.getServiceID() == serviceID){
					((ConcurrentSkipListSet<SLOMonitoringRecord>)service.getResponseTimes()).add(newRecord);
					updated = true;
					if(oldRecord != null){
						if(service.getServiceID() == oldRecord.serviceId){
							((ConcurrentSkipListSet<SLOMonitoringRecord>)service.getResponseTimes()).remove(oldRecord);
							oldRecord = null;
						}
					}
				}
				
			}
		}
		if(updated)
			log.info("Model updated for Service: "+serviceID);
		updated = false;
	}
	
	@SuppressWarnings("unchecked")
	public static ConcurrentSkipListSet<SLOMonitoringRecord> getResponseTimes(int serviceID){
		for(int i = 0; i< model.getComponents().size();i++){
			for(int k = 0; k< model.getComponents().get(i).getServices().size(); k++){
				if(model.getComponents().get(i).getServices().get(k).getServiceID() == serviceID){
					return (ConcurrentSkipListSet<SLOMonitoringRecord>) model.getComponents().get(i).getServices().get(k).getResponseTimes();
				}
			}
		}
		log.error("No entry for ServiceID:"+serviceID+" found");
		return null;
	}
}
