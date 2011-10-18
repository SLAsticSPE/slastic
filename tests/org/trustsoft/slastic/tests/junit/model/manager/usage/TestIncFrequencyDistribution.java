package org.trustsoft.slastic.tests.junit.model.manager.usage;

import java.util.Arrays;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;

import de.cau.se.slastic.metamodel.usage.FrequencyDistribution;
import de.cau.se.slastic.metamodel.usage.UsageFactory;

/**
 * Tests the method
 * {@link UsageModelManager#incOperationCallFreq(de.cau.se.slastic.metamodel.typeRepository.Operation, long)}
 * .
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestIncFrequencyDistribution extends TestCase {

	/**
	 * Increments an already registered frequency by one.
	 */
	public void testAddExistingFrequency() {
		final long valueSeven = 7;
		final long frequencySeven = 3;

		final Long[] values = { 2l, 5l, valueSeven, 9l };
		final Long[] frequencies = { 43l, 22l, frequencySeven, 33l };

		final FrequencyDistribution fd = this.createFrequencyDistribution(values, frequencies);
		UsageModelManager.incFrequency(fd, valueSeven);

		final Long[] expectedValues = values; // unchanged
		final Long[] observedValues = fd.getValues().toArray(new Long[] {});
		Assert.assertTrue(
				"Value arrays not equal: " + Arrays.toString(expectedValues) + " vs. "
						+ Arrays.toString(observedValues),
				Arrays.equals(expectedValues, observedValues));

		final Long[] expectedFrequencies = { 43l, 22l, frequencySeven + 1, 33l };
		final Long[] observedFrequencies = fd.getFrequencies().toArray(new Long[] {});
		Assert.assertTrue("Frequency arrays not equal: " + Arrays.toString(expectedFrequencies) + " vs. "
						+ Arrays.toString(observedFrequencies), Arrays.equals(expectedFrequencies, observedFrequencies));
	}

	/**
	 * Adds a not-yet existing frequency.
	 */
	public void testAddNewFrequency() {
		final long newValueThree = 3;
		final long newFrequencyForThree = 1;

		final Long[] values = { 2l, 5l, 7l, 9l };
		final Long[] frequencies = { 43l, 22l, 7l, 33l };

		final FrequencyDistribution fd = this.createFrequencyDistribution(values, frequencies);
		UsageModelManager.incFrequency(fd, newValueThree);

		final Long[] expectedValues = { 2l, newValueThree, 5l, 7l, 9l };
		final Long[] observedValues = fd.getValues().toArray(new Long[] {});
		Assert.assertTrue(
				"Value arrays not equal: " + Arrays.toString(expectedValues) + " vs. "
						+ Arrays.toString(observedValues),
				Arrays.equals(expectedValues, observedValues));

		final Long[] expectedFrequencies = { 43l, newFrequencyForThree, 22l, 7l, 33l };
		final Long[] observedFrequencies = fd.getFrequencies().toArray(new Long[] {});
		Assert.assertTrue("Frequency arrays not equal: " + Arrays.toString(expectedFrequencies) + " vs. "
						+ Arrays.toString(observedFrequencies), Arrays.equals(expectedFrequencies, observedFrequencies));
	}

	/**
	 * 
	 * @param values
	 *            <b>sorted</b> list of values
	 * @param frequencies
	 *            list of frequencies in an orer corresponding to the values
	 * @return
	 */
	private FrequencyDistribution createFrequencyDistribution(final Long[] values, final Long[] frequencies) {
		final FrequencyDistribution fd = UsageFactory.eINSTANCE.createFrequencyDistribution();
		Assert.assertEquals("Test invalied: values and frequencies must be of equal length ", values.length,
				frequencies.length);

		final EList<Long> valueListSorted = fd.getValues();
		final EList<Long> frequencyList = fd.getFrequencies();

		for (int i = 0; i < values.length; i++) {
			valueListSorted.add(values[i]);
			frequencyList.add(frequencies[i]);
		}

		return fd;
	}
}
