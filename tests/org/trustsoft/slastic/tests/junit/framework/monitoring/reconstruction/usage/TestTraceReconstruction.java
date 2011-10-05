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
public class TestTraceReconstruction extends TestCase {

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

		final MessageTrace mt = TraceReconstructor.reconstructTrace(bookstoreTrace, TestTraceReconstruction.rootExec);
		this.checkResultValidTrace(mt);
	}

	private void checkResultValidTrace(final MessageTrace mt) {
		final Collection<Message> messages = mt.getMessages();
		final Message[] messagesArr = messages.toArray(new Message[messages.size()]);
		Assert.assertEquals("Unexpected number of messages", 8, messagesArr.length);

		/* 0. Call: $ -> Bookstore.searchBook() */
		final Message m0 = messagesArr[0];
		
		
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
			Assert.assertTrue("Expected m to be of type" + SynchronousCallMessage.class + " found " + m.getClass(),
					m instanceof SynchronousCallMessage);
			break;
		case SYNCREPLY:
			Assert.assertTrue("Expected m to be of type" + SynchronousReplyMessage.class + " found " + m.getClass(),
					m instanceof SynchronousCallMessage);
			break;
		default:
			Assert.fail("Invalid message type: " + m.getClass());
			break;
		}

		DeploymentComponentOperationExecution senderCompOpExec = null;
		{
			final OperationExecution senderExecution = m.getSender();
			Assert.assertTrue("Expecting sending execution to be of type "
					+ DeploymentComponentOperationExecution.class + " found: " + senderExecution.getClass(),
					senderExecution instanceof DeploymentComponentOperationExecution);
			senderCompOpExec = (DeploymentComponentOperationExecution) senderExecution;
		}

		Assert.assertEquals("Unexpected component name of sender execution", senderCompOpExec.getDeploymentComponent()
				.getAssemblyComponent().getName(), expectedSenderName);
		
		// TODO: hier weiter!
	}
}
