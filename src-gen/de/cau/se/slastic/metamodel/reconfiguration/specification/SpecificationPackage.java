/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.reconfiguration.specification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.SpecificationFactory
 * @model kind="package"
 * @generated
 */
public interface SpecificationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "specification";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/reconfiguration/specification.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.reconfiguration.specification";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SpecificationPackage eINSTANCE = de.cau.se.slastic.metamodel.reconfiguration.specification.impl.SpecificationPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.specification.impl.ReconfigurationSpecificationImpl <em>Reconfiguration Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.impl.ReconfigurationSpecificationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.impl.SpecificationPackageImpl#getReconfigurationSpecification()
	 * @generated
	 */
	int RECONFIGURATION_SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Adaptation Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL = 0;

	/**
	 * The number of structural features of the '<em>Reconfiguration Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_SPECIFICATION_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification <em>Reconfiguration Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconfiguration Specification</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification
	 * @generated
	 */
	EClass getReconfigurationSpecification();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification#getAdaptationModel <em>Adaptation Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Adaptation Model</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification#getAdaptationModel()
	 * @see #getReconfigurationSpecification()
	 * @generated
	 */
	EReference getReconfigurationSpecification_AdaptationModel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SpecificationFactory getSpecificationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.specification.impl.ReconfigurationSpecificationImpl <em>Reconfiguration Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.impl.ReconfigurationSpecificationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.impl.SpecificationPackageImpl#getReconfigurationSpecification()
		 * @generated
		 */
		EClass RECONFIGURATION_SPECIFICATION = eINSTANCE.getReconfigurationSpecification();

		/**
		 * The meta object literal for the '<em><b>Adaptation Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL = eINSTANCE.getReconfigurationSpecification_AdaptationModel();

	}

} //SpecificationPackage
