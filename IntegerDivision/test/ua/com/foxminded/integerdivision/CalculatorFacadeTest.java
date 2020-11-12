package ua.com.foxminded.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorFacadeTest {
	private LongDivisionCalculator calculatorMock;
	private Formatter formatterMock;
	private CalculatorFacade facade;
	private int dividend;
	private int divider;
	private String actual;

	private static final String DIVISION_EXPECTED_RESULT = "_654754|654\n"
			+ " 654   -----\n"
			+ " ---   |1001\n"
			+ "   _754\n"
			+ "    654\n"
			+ "    ---\n"
			+ "    100\n";

	private final static String ZERO_DIVIDEND_EXPECTED_RESULT = "0 / 123 = 0";

	@BeforeEach
	void setUp() {
		calculatorMock = Mockito.mock(LongDivisionCalculator.class);
		formatterMock = Mockito.mock(Formatter.class);
		facade = new CalculatorFacade(calculatorMock, formatterMock);
	}

	@Test
	void divideTwoNumbers() {
		dividend = 654754;
		divider = 654;

		when(facade.divide(anyInt(), anyInt()))
				.thenReturn(DIVISION_EXPECTED_RESULT);

		actual = facade.divide(dividend, divider);

		assertEquals(DIVISION_EXPECTED_RESULT, actual);
	}

	@Test
	void zeroDividend() {
		dividend = 0;
		divider = 123;

		when(facade.divide(anyInt(), anyInt()))
				.thenReturn(ZERO_DIVIDEND_EXPECTED_RESULT);

		actual = facade.divide(dividend, divider);

		assertEquals(ZERO_DIVIDEND_EXPECTED_RESULT, actual);
	}

	@Test
	void divideByZero() {
		dividend = 123;
		divider = 0;

		when(facade.divide(anyInt(), anyInt()))
				.thenThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> facade.divide(dividend, divider));
	}
}
