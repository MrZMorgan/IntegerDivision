package ua.com.foxminded.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.xml.stream.events.DTD;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorFacadeTest {
	private static final String DIVISION_EXPECTED_RESULT = "_654754|654\n"
			+ " 654   -----\n"
			+ " ---   |1001\n"
			+ "   _754\n"
			+ "    654\n"
			+ "    ---\n"
			+ "    100\n";

	private final static String ZERO_DIVIDEND_EXPECTED_RESULT = "0 / 123 = 0";

	int dividend;
	int divider;

	@BeforeEach
	void setUp() {

	}

	@Test
	void divideTwoNumbers() {
		dividend = 14587;
		divider = 6;

		LongDivisionCalculator calculator = new LongDivisionCalculator();
		Formatter formatterMock = Mockito.mock(Formatter.class);

		new CalculatorFacade(calculator, formatterMock).divide(dividend, divider);

		ArgumentCaptor<CalculationDto> captor = ArgumentCaptor.forClass(CalculationDto.class);
		verify(formatterMock).createResult(captor.capture());

		CalculationDto dto = captor.getValue();
		assertEquals(calculator.longDivision(dividend, divider), dto);
	}
}