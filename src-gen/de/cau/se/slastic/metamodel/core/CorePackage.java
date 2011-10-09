/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.core;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see de.cau.se.slastic.metamodel.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/core.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.core";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = de.cau.se.slastic.metamodel.core.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent <em>Kieker Analysis Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getKiekerAnalysisEvent()
	 * @generated
	 */
	int KIEKER_ANALYSIS_EVENT = 0;

	/**
	 * The number of structural features of the '<em>Kieker Analysis Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIEKER_ANALYSIS_EVENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.impl.EntityImpl
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = 0;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ACTIVE = 1;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.impl.NamedEntityImpl <em>Named Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.impl.NamedEntityImpl
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getNamedEntity()
	 * @generated
	 */
	int NAMED_ENTITY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENTITY__ID = ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENTITY__ACTIVE = ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENTITY__NAME = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENTITY_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl <em>FQ Named Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getFQNamedEntity()
	 * @generated
	 */
	int FQ_NAMED_ENTITY = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FQ_NAMED_ENTITY__ID = NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FQ_NAMED_ENTITY__ACTIVE = NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FQ_NAMED_ENTITY__NAME = NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FQ_NAMED_ENTITY__PACKAGE_NAME = NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>FQ Named Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FQ_NAMED_ENTITY_FEATURE_COUNT = NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.IEvent <em>IEvent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.IEvent
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getIEvent()
	 * @generated
	 */
	int IEVENT = 4;

	/**
	 * The number of structural features of the '<em>IEvent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEVENT_FEATURE_COUNT = KIEKER_ANALYSIS_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl <em>SL Astic Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getSLAsticModel()
	 * @generated
	 */
	int SL_ASTIC_MODEL = 5;

	/**
	 * The number of structural features of the '<em>SL Astic Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SL_ASTIC_MODEL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.core.impl.SystemModelImpl <em>System Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.core.impl.SystemModelImpl
	 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getSystemModel()
	 * @generated
	 */
	int SYSTEM_MODEL = 6;

	/**
	 * The feature id for the '<em><b>Type Repository Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MODEL__TYPE_REPOSITORY_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Component Assembly Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Component Deployment Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Execution Environment Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL = 3;

	/**
	 * The number of structural features of the '<em>System Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MODEL_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent <em>Kieker Analysis Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kieker Analysis Event</em>'.
	 * @see de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent
	 * @generated
	 */
	EClass getKiekerAnalysisEvent();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.NamedEntity <em>Named Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Entity</em>'.
	 * @see de.cau.se.slastic.metamodel.core.NamedEntity
	 * @generated
	 */
	EClass getNamedEntity();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.core.NamedEntity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.cau.se.slastic.metamodel.core.NamedEntity#getName()
	 * @see #getNamedEntity()
	 * @generated
	 */
	EAttribute getNamedEntity_Name();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see de.cau.se.slastic.metamodel.core.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.core.Entity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.cau.se.slastic.metamodel.core.Entity#getId()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.core.Entity#isActive <em>Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active</em>'.
	 * @see de.cau.se.slastic.metamodel.core.Entity#isActive()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Active();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.FQNamedEntity <em>FQ Named Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FQ Named Entity</em>'.
	 * @see de.cau.se.slastic.metamodel.core.FQNamedEntity
	 * @generated
	 */
	EClass getFQNamedEntity();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.core.FQNamedEntity#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see de.cau.se.slastic.metamodel.core.FQNamedEntity#getPackageName()
	 * @see #getFQNamedEntity()
	 * @generated
	 */
	EAttribute getFQNamedEntity_PackageName();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.IEvent <em>IEvent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEvent</em>'.
	 * @see de.cau.se.slastic.metamodel.core.IEvent
	 * @generated
	 */
	EClass getIEvent();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.SLAsticModel <em>SL Astic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SL Astic Model</em>'.
	 * @see de.cau.se.slastic.metamodel.core.SLAsticModel
	 * @generated
	 */
	EClass getSLAsticModel();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.core.SystemModel <em>System Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Model</em>'.
	 * @see de.cau.se.slastic.metamodel.core.SystemModel
	 * @generated
	 */
	EClass getSystemModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.core.SystemModel#getTypeRepositoryModel <em>Type Repository Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Repository Model</em>'.
	 * @see de.cau.se.slastic.metamodel.core.SystemModel#getTypeRepositoryModel()
	 * @see #getSystemModel()
	 * @generated
	 */
	EReference getSystemModel_TypeRepositoryModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.core.SystemModel#getComponentAssemblyModel <em>Component Assembly Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Assembly Model</em>'.
	 * @see de.cau.se.slastic.metamodel.core.SystemModel#getComponentAssemblyModel()
	 * @see #getSystemModel()
	 * @generated
	 */
	EReference getSystemModel_ComponentAssemblyModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.core.SystemModel#getComponentDeploymentModel <em>Component Deployment Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Deployment Model</em>'.
	 * @see de.cau.se.slastic.metamodel.core.SystemModel#getComponentDeploymentModel()
	 * @see #getSystemModel()
	 * @generated
	 */
	EReference getSystemModel_ComponentDeploymentModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.core.SystemModel#getExecutionEnvironmentModel <em>Execution Environment Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Execution Environment Model</em>'.
	 * @see de.cau.se.slastic.metamodel.core.SystemModel#getExecutionEnvironmentModel()
	 * @see #getSystemModel()
	 * @generated
	 */
	EReference getSystemModel_ExecutionEnvironmentModel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

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
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent <em>Kieker Analysis Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getKiekerAnalysisEvent()
		 * @generated
		 */
		EClass KIEKER_ANALYSIS_EVENT = eINSTANCE.getKiekerAnalysisEvent();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.impl.NamedEntityImpl <em>Named Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.impl.NamedEntityImpl
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getNamedEntity()
		 * @generated
		 */
		EClass NAMED_ENTITY = eINSTANCE.getNamedEntity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ENTITY__NAME = eINSTANCE.getNamedEntity_Name();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.impl.EntityImpl
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__ID = eINSTANCE.getEntity_Id();

		/**
		 * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__ACTIVE = eINSTANCE.getEntity_Active();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl <em>FQ Named Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getFQNamedEntity()
		 * @generated
		 */
		EClass FQ_NAMED_ENTITY = eINSTANCE.getFQNamedEntity();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FQ_NAMED_ENTITY__PACKAGE_NAME = eINSTANCE.getFQNamedEntity_PackageName();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.IEvent <em>IEvent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.IEvent
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getIEvent()
		 * @generated
		 */
		EClass IEVENT = eINSTANCE.getIEvent();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl <em>SL Astic Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getSLAsticModel()
		 * @generated
		 */
		EClass SL_ASTIC_MODEL = eINSTANCE.getSLAsticModel();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.core.impl.SystemModelImpl <em>System Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.core.impl.SystemModelImpl
		 * @see de.cau.se.slastic.metamodel.core.impl.CorePackageImpl#getSystemModel()
		 * @generated
		 */
		EClass SYSTEM_MODEL = eINSTANCE.getSystemModel();

		/**
		 * The meta object literal for the '<em><b>Type Repository Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_MODEL__TYPE_REPOSITORY_MODEL = eINSTANCE.getSystemModel_TypeRepositoryModel();

		/**
		 * The meta object literal for the '<em><b>Component Assembly Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL = eINSTANCE.getSystemModel_ComponentAssemblyModel();

		/**
		 * The meta object literal for the '<em><b>Component Deployment Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL = eINSTANCE.getSystemModel_ComponentDeploymentModel();

		/**
		 * The meta object literal for the '<em><b>Execution Environment Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL = eINSTANCE.getSystemModel_ExecutionEnvironmentModel();

	}

} //CorePackage
