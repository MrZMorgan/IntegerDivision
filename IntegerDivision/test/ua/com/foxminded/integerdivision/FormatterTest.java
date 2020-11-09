package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormatterTest {

	private final static String DIVISION_EXPECTED_RESULT = "_654754|654\n" 
														 + " 654   -----\n" 
														 + " ---   |1001\n"
														 + "   _754\n" 
														 + "    654\n" 
														 + "    ---\n" 
														 + "    100\n";

	private final static String ZERO_DIVIDEND_EXPECTED_RESULT = "0 / 123 = 0";

	@Test
	void testLongDivideTwoNumbers() {
		int dividend = 654754;
		int divider = 654;

		LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
		CalculatorDTO dto = calculator.longDivision();
		Formatter formatter = new Formatter();

		String actual = formatter.createResult(dto);
		assertEquals(DIVISION_EXPECTED_RESULT, actual);
	}

	@Test
	void testDivideZero() {
		int dividend = 123;
		int divider = 0;

		LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
		assertThrows(IllegalArgumentException.class, () -> calculator.longDivision());
	}

	@Test
	void testZeroDividend() {
		int dividend = 0;
		int divider = 123;

		LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
		CalculatorDTO dto = calculator.longDivision();
		Formatter formatter = new Formatter();

		String actual = formatter.createResult(dto);
		assertEquals(ZERO_DIVIDEND_EXPECTED_RESULT, actual);
	}
}
