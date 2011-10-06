package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.Message;
import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.SynchronousCallMessage;
import de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage;

/**
 * Tests the method
 * {@link TraceReconstructor#reconstructTrace(java.util.List, de.cau.se.slastic.metamodel.monitoring.OperationExecution)}
 * .
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestTraceReconstructionValidTrace extends TestCase {

	// TODO: acquire rootExecution from Usage Model
	private static final DeploymentComponentOperationExecution rootExec =
			MonitoringFactory.eINSTANCE.createDeploymentComponentOperationExecution();

	/**
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

		final MessageTrace mt = TraceReconstructor.reconstructTrace(bookstoreTrace, TestTraceReconstructionValidTrace.rootExec);
		this.checkResultValidTrace(mt);
	}

	private void checkResultValidTrace(final MessageTrace mt) {
		final Collection<Message> messages = mt.getMessages();
		final Message[] messagesArr = messages.toArray(new Message[messages.size()]);
		Assert.assertEquals("Unexpected number of messages", 8, messagesArr.length);

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
		
		/* 6. Reply: Bookstore.searchBook() [0,0] -> $ [-1,-1] */
		this.checkMessage(messagesArr[7], CallOrReply.SYNCREPLY,
				BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME, 0, 0,				
				null, -1, -1);
		
		// TODO: hier weiter!
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
			Assert.assertSame("Expected sender execution to be the root execution", TestTraceReconstructionValidTrace.rootExec,
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
			Assert.assertSame("Expected receiver execution to be the root execution", TestTraceReconstructionValidTrace.rootExec,
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
