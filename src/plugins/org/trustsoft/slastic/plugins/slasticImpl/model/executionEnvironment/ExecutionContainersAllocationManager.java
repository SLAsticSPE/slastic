package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionContainersAllocationManager implements IExecutionContainersAllocationManager {
    private static final Log log = LogFactory.getLog(ExecutionContainersAllocationManager.class);

    private final Map<Long, ExecutionContainer> allocatedContainersById =
            new HashMap<Long, ExecutionContainer>();
    private final List<ExecutionContainer> allocatedExecutionContainers;

    private ExecutionContainersAllocationManager(){
        this.allocatedExecutionContainers = null;
    }

    public ExecutionContainersAllocationManager (final List<ExecutionContainer> allocatedExecutionContainers){
        this.allocatedExecutionContainers = allocatedExecutionContainers;
        for (ExecutionContainer container : allocatedExecutionContainers){
            this.allocatedContainersById.put(container.getId(), container);
        }
    }


    @Override
    public boolean allocateExecutionContainer(ExecutionContainer executionContainer) {
        long executionContainerId = executionContainer.getId();
        if (this.allocatedContainersById.containsKey(executionContainerId)){
            log.warn("Execution container with id " + executionContainerId + " already allocated");
            return false;
        }
        this.allocatedContainersById.put(executionContainerId, executionContainer);
        return this.allocatedExecutionContainers.add(executionContainer);
    }

    @Override
    public boolean deallocateExecutionContainer(ExecutionContainer executionContainer) {
        long executionContainerId = executionContainer.getId();
        if (!this.allocatedContainersById.containsKey(executionContainerId)){
            log.warn("Execution container with id " + executionContainerId + " not allocated");
            return false;
        }
        ExecutionContainer removedContainer = this.allocatedContainersById.remove(executionContainerId);
        if (removedContainer != executionContainer){
            IllegalStateException ise =
                    new IllegalStateException("Unexpected element removed with id" +
                    executionContainerId+". Expected: " + executionContainer + " ; removed: " + removedContainer);
            log.error("IllegalStateException: " + ise.getMessage(), ise);
            throw ise;
        }
        return this.allocatedExecutionContainers.remove(executionContainer);
    }
}
