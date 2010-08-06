/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionEnvironmentManager implements IExecutionContainersManager {

    @Override
    public ExecutionContainer lookupExecutionContainer(String fullyQualifiedName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExecutionContainer lookupExecutionContainer(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExecutionContainer createAndRegisterExecutionContainer(String fullyQualifiedName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
