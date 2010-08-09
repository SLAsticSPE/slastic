package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 *
 * @author Andre van Hoorn
 */
public interface IExecutionContainersAllocationManager {

    /**
     * Marks execution container executionContainer as allocated.
     *
     * @param executionContainer
     * @return iff the execution container is newly allocated, false if it was already allocated
     */
    public boolean allocateExecutionContainer (final ExecutionContainer executionContainer);
    
    /**
     * Marks execution container executionContainer as not allocated.
     *
     * @param executionContainer
     * @return iff the execution container is newly deallocated, false if wasn't allocated
     */
    public boolean deallocateExecutionContainer (final ExecutionContainer executionContainer);
}
