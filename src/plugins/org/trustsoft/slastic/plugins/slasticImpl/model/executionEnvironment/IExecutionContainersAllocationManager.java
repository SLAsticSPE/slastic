/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

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
