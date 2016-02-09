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

package kieker.tools.slastic.plugins.slasticImpl.model.usage;

import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.metamodel.usage.CallingRelationship;
import kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship;
import kieker.tools.slastic.metamodel.usage.DeploymentOperationCallFrequency;

/**
 *
 * @author Andre van Hoorn
 *
 */
public interface IUsageModelManager {

	/**
	 * Returns the number of times, the given {@link Signature} of the given {@link SystemProvidedInterfaceDelegationConnector} is called.
	 *
	 * @param connector
	 * @param signature
	 * @return
	 */
	public long lookupSystemProvidedInterfaceDelegationConnectorFrequency(
			final SystemProvidedInterfaceDelegationConnector connector,
			final Signature signature);

	/**
	 * Increments the number of calls to the given {@link Signature} of the
	 * given {@link SystemProvidedInterfaceDelegationConnector} by 1.
	 *
	 * @param connector
	 * @param signature
	 */
	public void incSystemProvidedInterfaceSignatureCallFreq(final SystemProvidedInterfaceDelegationConnector connector,
			final Signature signature);

	/**
	 * Returns the number of times, the given {@link Operation} was called.
	 *
	 * @param operation
	 * @return
	 */
	public long lookupOperationCallFreq(final Operation operation);

	/**
	 * Increments the number of calls to the given {@link Operation} by the
	 * given frequency.
	 *
	 * @param operation
	 *            the called {@link Operation}
	 * @param signature
	 *            the called {@link Signature}
	 */
	public void incOperationCallFreq(final Operation operation, final long frequency);

	public void incDeploymentOperationCallFreq(final Operation operation, final DeploymentComponent deploymentComponent, final long frequency);

	/**
	 * Returns the {@link CallingRelationship} for the given {@link Operation} calling the given {@link Interface}'s {@link Signature}.
	 *
	 * @param operation
	 * @param iface
	 * @param signature
	 * @return
	 */
	public CallingRelationship lookupCallingRelationship(final Operation operation, final Interface iface, final Signature signature);

	public DeploymentOperationCallFrequency lookupDeploymentOperationCallFrequency(final Operation operation, final DeploymentComponent deploymentComponent);

	/**
	 * Adds the information that the given {@link Interface} {@link Signature} has been called the given number of times within an execution of the
	 * given {@link Operation}.
	 *
	 * @param operation
	 *            the calling {@link Operation}
	 * @param iface
	 *            the called {@link Interface}
	 * @param signature
	 *            the called {@link Signature}
	 * @param frequency
	 */
	public void incCallingRelationshipFreq(final Operation operation, final Interface iface, final Signature signature, final long frequency);

	public void incDeploymentCallingRelationshipFreq(final DeploymentComponent callingDeploymentComponent, final Operation operation,
			final Interface iface, final Signature signature, final DeploymentComponent calledDeploymentComponent, final long frequency);

	public DeploymentCallingRelationship lookupDeploymentCallingRelationship(DeploymentComponent callingDeploymentComponent, Operation operation,
			DeploymentComponent calledDeploymentComponent, Interface iface, Signature signature);
}
