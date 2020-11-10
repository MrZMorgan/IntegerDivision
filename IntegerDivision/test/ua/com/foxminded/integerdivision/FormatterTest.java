package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Callable;

class FormatterTest {

	private final static String DIVISION_EXPECTED_RESULT = "_654754|654\n"
														 + " 654   -----\n"
														 + " ---   |1001\n"
														 + "   _754\n"
														 + "    654\n"
														 + "    ---\n"
														 + "    100\n";

	private final static String ZERO_DIVIDEND_EXPECTED_RESULT = "0 / 123 = 0";

	private CalculatorFacade calculator;

	@BeforeEach
	public void setUp() {
		calculator = new CalculatorFacade();
	}

	@Test
	void testLongDivideTwoNumbers() {
		int dividend = 654754;
		int divider = 654;
		String actual = calculator.longDivision(dividend, divider);

		assertEquals(DIVISION_EXPECTED_RESULT, actual);
	}

	@Test
	void testDivideZero() {
		int dividend = 123;
		int divider = 0;

		assertThrows(IllegalArgumentException.class, () -> calculator.longDivision(dividend, divider));
	}

	@Test
	void testZeroDividend() {
		int dividend = 0;
		int divider = 123;
		String actual = calculator.longDivision(dividend, divider);

		assertEquals(ZERO_DIVIDEND_EXPECTED_RESULT, actual);
	}
}
