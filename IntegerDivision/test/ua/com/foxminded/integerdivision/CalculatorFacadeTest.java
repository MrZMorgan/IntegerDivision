package ua.com.foxminded.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorFacadeTest {
	private int dividend;
	private int divider;
	private LongDivisionCalculator calculator;
	private Formatter formatterMock;
	private CalculatorFacade facade;
	private String result;

	@BeforeEach
	void setUp() {
		calculator = new LongDivisionCalculator();
		formatterMock = Mockito.mock(Formatter.class);
		facade = new CalculatorFacade(calculator, formatterMock);
	}

	@Test
	void divideTwoNumbersShouldEqualsDto() {
		dividend = 654754;
		divider = 654;

		facade.divide(dividend, divider);

		ArgumentCaptor<CalculationDto> captor = ArgumentCaptor.forClass(CalculationDto.class);
		verify(formatterMock).createResult(captor.capture());

		CalculationDto capturedDto = captor.getValue();
		CalculationDto actualDto = calculator.longDivision(dividend, divider);

		assertEquals(actualDto, capturedDto);
	}

	@Test void zeroDividendShouldEqualsDto() {
		dividend = 0;
		divider = 123;

		result = facade.divide(dividend, divider);

		ArgumentCaptor<CalculationDto> captor = ArgumentCaptor.forClass(CalculationDto.class);
		verify(formatterMock).createResult(captor.capture());

		CalculationDto capturedDto = captor.getValue();
		CalculationDto actualDto = calculator.longDivision(dividend, divider);

		assertEquals(actualDto, capturedDto);

	}

	@Test void zeroDivider() {
		dividend = 123;
		divider = 0;
		String expectedErrorMessage = "Can`t divide by zero";

		assertThrows(IllegalArgumentException.class, () -> facade.divide(dividend, divider));

		String actualErrorMessage = "";

		try {
			result = facade.divide(dividend, divider);
		} catch (IllegalArgumentException e) {
			actualErrorMessage = e.getMessage();
		}

		assertEquals(expectedErrorMessage, actualErrorMessage);
	}
}