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

package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.ExecutionTrace;
import de.cau.se.slastic.metamodel.usage.Message;
import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.SynchronousCallMessage;
import de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage;
import de.cau.se.slastic.metamodel.usage.ValidExecutionTrace;

/**
 * Tests the method
 * {@link TraceReconstructor#reconstructMessageTrace(java.util.List, de.cau.se.slastic.metamodel.monitoring.OperationExecution)}
 * .
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestTraceReconstructionValidTrace extends TestCase {

	/**
	 * Tests the method
	 * {@link TraceReconstructor#reconstructMessageTrace(Collection, OperationExecution)}
	 * for a valid trace.
	 * 
	 * @throws IllegalStateException
	 */
	public void testReconstructionOfValidTrace() throws IllegalStateException {
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76767676l;

		final Collection<? extends OperationExecution> bookstoreTrace =
				BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);

		final MessageTrace mt = TraceReconstructor.reconstructMessageTrace(bookstoreTrace, UsageModelManager.rootExec);
		this.checkResultValidTrace(mt, traceId);
	}

	/**
	 * Tests the method
	 * {@link TraceReconstructor#reconstructTraceSave(Collection, OperationExecution)}
	 * for a valid trace.
	 */
	public void testReconstructionOfValidTraceSave() {
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76768676l;

		final Collection<? extends OperationExecution> bookstoreTrace =
				BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);

		final ExecutionTrace et =
			TraceReconstructor.reconstructTraceSave(bookstoreTrace, UsageModelManager.rootExec);
		
		{ /* Check results */
			Assert.assertTrue("Expected execution trace to be instance of " + ValidExecutionTrace.class + "; found: "
					+ et.getClass(), et instanceof ValidExecutionTrace);
			final ValidExecutionTrace vet = (ValidExecutionTrace) et;
			Assert.assertEquals("Execution trace has unexpected trace id", traceId, vet.getTraceId());
			Assert.assertNotNull("Associated message trace is null", vet.getMessageTrace());
			Assert.assertEquals("Unexpected number of executions",
					BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE, vet.getOperationExecutions().size());
			Assert.assertSame("Unexpected execution trace nagivatible via associated message trace", vet, vet
					.getMessageTrace().getExecutionTrace());
		}
	}

	private void checkResultValidTrace(final MessageTrace mt, final long expectedTraceId) {
		final Collection<Message> messages = mt.getMessages();
		final Message[] messagesArr = messages.toArray(new Message[messages.size()]);
		Assert.assertEquals("Unexpected number of messages", BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE * 2,
				messagesArr.length);

		/* Compare trace id */
		Assert.assertEquals("Unexpected trace id", expectedTraceId, mt.getTraceId());

		/* Some checks on the associated valid execution trace */
		final ValidExecutionTrace vet = mt.getExecutionTrace();
		Assert.assertNotNull("No execution trace contained in message trace", vet);
		Assert.assertEquals("Associated execution trace has an unexpected trace id", mt.getTraceId(), vet.getTraceId());
		Assert.assertEquals("Associated execution trace as unexpected number of executions",
				BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE, vet.getOperationExecutions().size());

		Assert.assertSame("Unexpected message trace nagivatible via associated execution trace", mt, vet
				.getMessageTrace());
		
		/* 0. Call: $ [-1,-1] -> Bookstore.searchBook() [0,0] */
		this.checkMessage(messagesArr[0], CallOrReply.SYNCCALL,
				null, -1, -1, // root execution
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0);

		/* 1. Call: Bookstore.searchBook() [0,0] -> Catalog.getBook() [1,1] */
		this.checkMessage(messagesArr[1], CallOrReply.SYNCCALL,
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0,
				BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME, 1, 1);

		/* 2. Reply: Catalog.getBook() [1,1] -> Bookstore.searchBook() [0,0] */
		this.checkMessage(messagesArr[2], CallOrReply.SYNCREPLY,
				BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME, 1, 1,
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0);

		/* 3. Call: Bookstore.searchBook() [0,0] -> CRM.getOffers() [2,1] */
		this.checkMessage(messagesArr[3], CallOrReply.SYNCCALL,
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0,
				BookstoreTraceFactory.CRM_ASSEMBLY_COMPONENT_NAME, 2, 1);

		/* 4. Call: CRM.getOffers() [2,1] -> Catalog.getBook() [3,2] */
		this.checkMessage(messagesArr[4], CallOrReply.SYNCCALL,
				BookstoreTraceFactory.CRM_ASSEMBLY_COMPONENT_NAME, 2, 1,
				BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME, 3, 2);

		/* 5. Reply: Catalog.getBook() [3,2] -> CRM.getOffers() [2,1] */
		this.checkMessage(messagesArr[5], CallOrReply.SYNCREPLY,
				BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME, 3, 2,
				BookstoreTraceFactory.CRM_ASSEMBLY_COMPONENT_NAME, 2, 1);

		/* 6. Reply: CRM.getOffers() [2,1] -> Bookstore.searchBook() [0,0] */
		this.checkMessage(messagesArr[6], CallOrReply.SYNCREPLY,
				BookstoreTraceFactory.CRM_ASSEMBLY_COMPONENT_NAME, 2, 1,
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0);

		/* 7. Reply: Bookstore.searchBook() [0,0] -> $ [-1,-1] */
		this.checkMessage(messagesArr[7], CallOrReply.SYNCREPLY,
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0,
				null, -1, -1);
	}

	private enum CallOrReply {
		SYNCCALL, SYNCREPLY
	};

	private void checkMessage(
			final Message m,
			final CallOrReply callOrReply,
			final String expectedSenderName, final int expectedSenderEoi, final int expectedSenderEss,
			final String expectedReceiverName, final int expectedReceiverEoi, final int expectedReceiverEss) {
		/* Check the message type */
		switch (callOrReply) {
		case SYNCCALL:
			Assert.assertTrue("Expected m to be of type " + SynchronousCallMessage.class + " found " + m.getClass(),
					m instanceof SynchronousCallMessage);
			break;
		case SYNCREPLY:
			Assert.assertTrue("Expected m to be of type " + SynchronousReplyMessage.class + " found " + m.getClass(),
					m instanceof SynchronousReplyMessage);
			break;
		default:
			Assert.fail("Invalid message type: " + m.getClass());
			break;
		}

		/*
		 * Check sender execution
		 */
		DeploymentComponentOperationExecution senderCompOpExec = null;
		{
			final OperationExecution senderExecution = m.getSender();
			Assert.assertTrue("Expecting sending execution to be of type "
					+ DeploymentComponentOperationExecution.class + " found: " + senderExecution.getClass(),
					senderExecution instanceof DeploymentComponentOperationExecution);
			senderCompOpExec = (DeploymentComponentOperationExecution) senderExecution;
		}

		if ((expectedSenderName == null) && (expectedSenderEoi == -1) && (expectedSenderEss == -1)) {
			Assert.assertSame("Expected sender execution to be the root execution", UsageModelManager.rootExec,
					senderCompOpExec);
		} else {
			Assert.assertEquals("Unexpected component name of sender execution", senderCompOpExec
					.getDeploymentComponent()
					.getAssemblyComponent().getName(), expectedSenderName);
			// We could check much more but this should be OK so far
			Assert.assertEquals("Unexpected eoi of sender execution", expectedSenderEoi, senderCompOpExec.getEoi());
			Assert.assertEquals("Unexpected ess of sender execution", expectedSenderEss, senderCompOpExec.getEss());
		}

		/*
		 * Check receiver execution
		 */
		DeploymentComponentOperationExecution receiverCompOpExec = null;
		{
			final OperationExecution receiverExecution = m.getReceiver();
			Assert.assertTrue("Expecting receiver receiver to be of type "
					+ DeploymentComponentOperationExecution.class + " found: " + receiverExecution.getClass(),
					receiverExecution instanceof DeploymentComponentOperationExecution);
			receiverCompOpExec = (DeploymentComponentOperationExecution) receiverExecution;
		}

		if ((expectedReceiverName == null) && (expectedReceiverEoi == -1) && (expectedReceiverEss == -1)) {
			Assert.assertSame("Expected receiver execution to be the root execution", UsageModelManager.rootExec,
					receiverCompOpExec);
		} else {
			Assert.assertEquals("Unexpected component name of receiver execution",
					receiverCompOpExec.getDeploymentComponent().getAssemblyComponent().getName(), expectedReceiverName);
			// We could check much more but this should be OK so far
			Assert.assertEquals("Unexpected eoi of receiver execution",
					expectedReceiverEoi, receiverCompOpExec.getEoi());
			Assert.assertEquals("Unexpected ess of eeceiver execution",
					expectedReceiverEss, receiverCompOpExec.getEss());
		}
	}
}
