package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FormatterTest {
	private final static String DIVISION_EXPECTED_RESULT = "_654754|654\n"
														 + " 654   -----\n"
														 + " ---   |1001\n"
														 + "   _754\n"
														 + "    654\n"
														 + "    ---\n"
														 + "    100\n";

	private final static String ZERO_DIVIDEND_EXPECTED_RESULT = "0 / 123 = 0";

	private int dividend;
	private int divider;
	private LongDivisionCalculator longDivisoinCalculatorMock;
	private Formatter formatterMock;
	private CalculatorFacade facade;

	@BeforeEach
	void setUp() {
		longDivisoinCalculatorMock = Mockito.mock(LongDivisionCalculator.class);
		formatterMock = Mockito.mock(Formatter.class);
		facade = new CalculatorFacade(longDivisoinCalculatorMock, formatterMock);
	}

	@Test
	void testLongDivideTwoNumbers() {
		dividend = 65400754;
		divider = 654;

		Mockito.when(facade.divide(dividend, divider)).thenReturn(DIVISION_EXPECTED_RESULT);
		assertEquals(DIVISION_EXPECTED_RESULT, facade.divide(dividend, divider));
	}

	@Test
	void testDivideZero() {
		dividend = 123;
		divider = 0;

		Mockito.when(facade.divide(dividend, divider)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> facade.divide(dividend, divider));
	}

	@Test
	void testZeroDividend() {
		dividend = 0;
		divider = 123;

		Mockito.when(facade.divide(dividend, divider)).thenReturn(ZERO_DIVIDEND_EXPECTED_RESULT);
		assertEquals(ZERO_DIVIDEND_EXPECTED_RESULT, facade.divide(dividend, divider));
	}
}
