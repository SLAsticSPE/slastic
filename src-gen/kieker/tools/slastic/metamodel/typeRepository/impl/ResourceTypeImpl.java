/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository.impl;

import kieker.tools.slastic.metamodel.core.impl.FQNamedEntityImpl;

import kieker.tools.slastic.metamodel.typeRepository.ResourceType;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ResourceTypeImpl extends FQNamedEntityImpl implements ResourceType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRepositoryPackage.Literals.RESOURCE_TYPE;
	}

} //ResourceTypeImpl
