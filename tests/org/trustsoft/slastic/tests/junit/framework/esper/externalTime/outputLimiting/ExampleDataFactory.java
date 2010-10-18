package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import java.util.ArrayList;
import java.util.List;

import com.espertech.esper.client.time.CurrentTimeEvent;

public class ExampleDataFactory {
	private static final String SYMBOL_IBM = "IBM";
	private static final String SYMBOL_MSFT = "MSFT";
	private static final String SYMBOL_YAH = "YAH";

	/**
	 * Returns the sequence of input events ({@link MarketData} and
	 * {@link CurrentTimeEvent}s) according to Appendix A.2.2.
	 * 
	 * @return
	 */
	public static List<Object> A_2_2__inputEvents() {
		final List<Object> inputStream = new ArrayList<Object>();

		// inputStream.add(new CurrentTimeEvent(200));
		inputStream
				.add(new MarketData(ExampleDataFactory.SYMBOL_IBM, 100, 25.0));
		inputStream.add(new CurrentTimeEvent(800));
		inputStream.add(new MarketData(ExampleDataFactory.SYMBOL_MSFT, 5000,
				9.0));
		inputStream.add(new CurrentTimeEvent(1000));
		inputStream.add(new CurrentTimeEvent(1200));
		inputStream.add(new CurrentTimeEvent(1500));
		inputStream
				.add(new MarketData(ExampleDataFactory.SYMBOL_IBM, 150, 24.0));
		inputStream.add(new MarketData(ExampleDataFactory.SYMBOL_YAH, 10000,
				1.0));
		inputStream.add(new CurrentTimeEvent(2000));
		inputStream.add(new CurrentTimeEvent(2100));
		inputStream
				.add(new MarketData(ExampleDataFactory.SYMBOL_IBM, 155, 26.0));
		inputStream.add(new CurrentTimeEvent(2200));
		inputStream.add(new CurrentTimeEvent(2500));
		inputStream.add(new CurrentTimeEvent(3000));
		inputStream.add(new CurrentTimeEvent(3200));
		inputStream.add(new CurrentTimeEvent(3500));
		inputStream.add(new MarketData(ExampleDataFactory.SYMBOL_YAH, 11000,
				2.0));
		inputStream.add(new CurrentTimeEvent(4000));
		inputStream.add(new CurrentTimeEvent(4200));
		inputStream.add(new CurrentTimeEvent(4300));
		inputStream
				.add(new MarketData(ExampleDataFactory.SYMBOL_IBM, 150, 22.0));
		inputStream.add(new CurrentTimeEvent(4900));
		inputStream.add(new MarketData(ExampleDataFactory.SYMBOL_YAH, 11500,
				3.0));
		inputStream.add(new CurrentTimeEvent(5000));
		inputStream.add(new CurrentTimeEvent(5200));
		inputStream.add(new CurrentTimeEvent(5700));
		inputStream.add(new CurrentTimeEvent(5900));
		inputStream.add(new MarketData(ExampleDataFactory.SYMBOL_YAH, 10500,
				1.0));
		inputStream.add(new CurrentTimeEvent(6000));
		inputStream.add(new CurrentTimeEvent(6200));
		inputStream.add(new CurrentTimeEvent(6300));
		inputStream.add(new CurrentTimeEvent(7000));
		inputStream.add(new CurrentTimeEvent(7200));

		return inputStream;
	}

	/**
	 * Creates the sequence of rows on the insert stream according to Appendix
	 * A.2.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_2_2__expectedIStream() {
		final List<StreamRecord> expectedIStream =
				new ArrayList<StreamRecord>();

		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 100, 25.0 }));
		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 5000, 9.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 150, 24.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 10000, 1.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 155, 26.0 }));
		/* (empty result) in the reference guide means no row: */
		// expectedIStream.add(new StreamRecord(3200, null));
		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 11000, 2.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 150, 22.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 11500, 3.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 10500, 1.0 }));

		return expectedIStream;
	}

	/**
	 * Creates the sequence of rows on the remove stream according to Appendix
	 * A.2.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_2_2__expectedRStream() {
		final List<StreamRecord> expectedRStream =
				new ArrayList<StreamRecord>();

		/* (empty result) in the reference guide means no row: */
		// expectedRStream.add(new StreamRecord(3200, null));
		expectedRStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 100, 25.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 5000, 9.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 150, 24.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 10000, 1.0 }));

		return expectedRStream;
	}

	/**
	 * Returns the sequence of input events ({@link MarketData} and
	 * {@link CurrentTimeEvent}s) according to Appendix A.5.2.
	 * 
	 * @return
	 */
	public static List<Object> A_5_2__inputEvents() {
		/* Input stream equals input stream of Appendix A.2.2 */
		return ExampleDataFactory.A_2_2__inputEvents();
	}

	/**
	 * Creates the sequence of rows on the insert stream according to Appendix
	 * A.5.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_5_2__expectedIStream() {
		final List<StreamRecord> expectedIStream =
				new ArrayList<StreamRecord>();

		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 25.0 }));
		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 49.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		/* (empty result) in the reference guide means no row: */
		// expectedIStream.add(new StreamRecord(3200, null));
		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 3.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 97.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 72.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 7.0 }));
		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, null }));
		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));
		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 48.0 }));
		return expectedIStream;
	}

	/**
	 * Creates the sequence of rows on the remove stream according to Appendix
	 * A.5.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_5_2__expectedRStream() {
		final List<StreamRecord> expectedRStream =
				new ArrayList<StreamRecord>();

		expectedRStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, null }));
		expectedRStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, null }));
		expectedRStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 25.0 }));
		expectedRStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, null }));
		expectedRStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 49.0 }));
		/* (empty result) in the reference guide means no row: */
		// expectedRStream.add(new StreamRecord(3200, null));
		expectedRStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));
		expectedRStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedRStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 3.0 }));
		expectedRStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 97.0 }));
		expectedRStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 7.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 72.0 }));
		return expectedRStream;
	}

	/**
	 * Returns the sequence of input events ({@link MarketData} and
	 * {@link CurrentTimeEvent}s) according to Appendix A.5.3.
	 * 
	 * @return
	 */
	public static List<Object> A_5_3__inputEvents() {
		/* Input stream equals input stream of Appendix A.2.2 */
		return ExampleDataFactory.A_2_2__inputEvents();
	}

	/**
	 * Creates the sequence of rows on the insert stream according to Appendix
	 * A.5.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_5_3__expectedIStream() {
		final List<StreamRecord> expectedIStream =
				new ArrayList<StreamRecord>();

		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 25.0 }));
		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));

		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));

		expectedIStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedIStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));

		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 3.0 }));

		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 97.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));

		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 72.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 7.0 }));

		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 48.0 }));
		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, null }));
		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));

		return expectedIStream;
	}

	/**
	 * Creates the sequence of rows on the remove stream according to Appendix
	 * A.5.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_5_3__expectedRStream() {
		final List<StreamRecord> expectedRStream =
				new ArrayList<StreamRecord>();

		expectedRStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, null }));
		expectedRStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, null }));

		expectedRStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 25.0 }));
		expectedRStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, null }));

		expectedRStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedRStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));

		expectedRStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedRStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));

		expectedRStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedRStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 3.0 }));

		expectedRStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 97.0 }));
		expectedRStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));

		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 72.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedRStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 7.0 }));

		return expectedRStream;
	}

	/**
	 * Returns the sequence of input events ({@link MarketData} and
	 * {@link CurrentTimeEvent}s) according to Appendix A.5.6.
	 * 
	 * @return
	 */
	public static List<Object> A_5_6__inputEvents() {
		/* Input stream equals input stream of Appendix A.2.2 */
		return ExampleDataFactory.A_2_2__inputEvents();
	}

	/**
	 * Creates the sequence of rows on the insert stream according to Appendix
	 * A.5.6.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_5_6__expectedIStream() {
		final List<StreamRecord> expectedIStream =
				new ArrayList<StreamRecord>();

		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 25.0 }));
		expectedIStream.add(new StreamRecord(1200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));

		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(2200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));

		expectedIStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedIStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(3200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 1.0 }));

		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 75.0 }));
		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(4200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 3.0 }));

		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 97.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(5200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));

		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 72.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_MSFT, 9.0 }));
		expectedIStream.add(new StreamRecord(6200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 7.0 }));

		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_IBM, 48.0 }));
		expectedIStream.add(new StreamRecord(7200, new Object[] {
				ExampleDataFactory.SYMBOL_YAH, 6.0 }));

		return expectedIStream;
	}

	/**
	 * Creates the sequence of rows on the remove stream according to Appendix
	 * A.5.2.
	 * 
	 * @return
	 */
	public static List<StreamRecord> A_5_6__expectedRStream() {
		final List<StreamRecord> expectedRStream =
				new ArrayList<StreamRecord>();


		/* no events on the stream */

		return expectedRStream;
	}
}
