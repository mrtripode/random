package es.mikostrategy.random;

import es.mikostrategy.random.utils.RandomNumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class RndNumberApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void whenNumberOfArrayLengthOkNumberLengthNullGenerate_ThenReturnAnArrayOf4RndIntegerBetweenZeroToNine() {
		// Given
		final int arrayLength = 2;

		// When
		Integer[] arr = RandomNumberUtils.generateRndNumbers().apply(arrayLength, null);
		final int lengthActual = arr.length;
		final int numberActual1 = String.valueOf(arr[0]).length();
		final int numberActual2 = String.valueOf(arr[1]).length();
		final int numberLengthDefault = 6;

		// Then
		assertEquals(2, lengthActual);
		assertEquals(numberLengthDefault, numberActual1);
		assertEquals(numberLengthDefault, numberActual2);
	}

	@Test
	public void whenNumberOfArrayLengthNumberLengthOkGenerate_ThenReturnAnArrayOf4RndIntegerBetweenZeroToNine() {
		// Given
		final int arrayLength = 2;
		final int numberLength = 4;

		// When
		Integer[] arr = RandomNumberUtils.generateRndNumbers().apply(arrayLength, numberLength);
		final int lengthActual = arr.length;
		final int numberActual1 = String.valueOf(arr[0]).length();
		final int numberActual2 = String.valueOf(arr[1]).length();

		// Then
		assertEquals(2, lengthActual);
		assertEquals(numberLength, numberActual1);
		assertEquals(numberLength, numberActual2);
	}

	@Test
	public void whenNumberOfArrayLengthNumberNegativeNumberLengthOkGenerate_ThenReturnAnArrayOf4RndIntegerBetweenZeroToNine() {
		// Given
		final int arrayLength = -2;
		final int numberLength = 4;

		// When
		try {
			Integer[] arr = RandomNumberUtils.generateRndNumbers().apply(arrayLength, numberLength);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

}

