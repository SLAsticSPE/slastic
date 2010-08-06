package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;

/**
 *
 * @author Andre van Hoorn
 */
public interface IAssemblyComponentsManager {

    /**
     * Returns the assembly component with the given fully-qualified name or null
     * if no assembly component with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the assembly component to
     * lookup
     * @return the looked up assembly component
     */
    public AssemblyComponent lookupAssemblyComponent(final String fullyQualifiedName);

    /**
     * Returns the assembly component with the given id or null if no execution
     * container with this id.
     *
     * @param id the id of the assembly component to lookup
     * @return the looked up assembly component
     */
    public AssemblyComponent lookupAssemblyComponent(final long id);

    /**
     * Creates and registers a new assembly component with the given
     * full-qualified name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new assembly component
     * @throws IllegalArgumentException if an assembly component with the given
     * fully-qualified name has already been registered
     */
    public AssemblyComponent createAndRegisterAssemblyComponent (final String fullyQualifiedName);
}
