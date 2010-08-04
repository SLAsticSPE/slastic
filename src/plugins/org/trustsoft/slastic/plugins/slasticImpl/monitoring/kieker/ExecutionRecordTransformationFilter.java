package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import java.util.ArrayList;
import java.util.Collection;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.OperationExecutionRecord;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionRecordTransformationFilter implements IMonitoringRecordConsumerPlugin {

    private final static Collection<Class<? extends IMonitoringRecord>> recordTypeSubscriptionList =
            new ArrayList<Class<? extends IMonitoringRecord>>();

    static {
        recordTypeSubscriptionList.add(OperationExecutionRecord.class);
    }
    private final TypeRepositoryManager typeRepositoryManager;

    private ExecutionRecordTransformationFilter() {
        this.typeRepositoryManager = null;
    }

    private ExecutionRecordTransformationFilter(final TypeRepositoryManager typeRepositoryManager) {
        this.typeRepositoryManager = typeRepositoryManager;
    }

    @Override
    public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
        return recordTypeSubscriptionList;
    }

    private final boolean handleOperationExecutionRecord(OperationExecutionRecord execution) {
        String componentTypeName =
                execution.componentName;
        ComponentType componentType = this.typeRepositoryManager.lookupComponentType(componentTypeName);
        if (componentType == null) {
            this.typeRepositoryManager.createAndRegisterComponentType(componentTypeName);
        }
        // TODO signature/interface, execution container, ...

        return true;
    }

    @Override
    public boolean newMonitoringRecord(IMonitoringRecord imr) {
        if (imr instanceof OperationExecutionRecord) {
            return this.handleOperationExecutionRecord((OperationExecutionRecord) imr);
        }

        return true;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(boolean error) {
    }
}
