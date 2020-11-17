package ua.com.foxminded.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ua.com.foxminded.integerdivision.calculator.*;
import ua.com.foxminded.integerdivision.facade.*;
import ua.com.foxminded.integerdivision.formatter.Formatter;
import ua.com.foxminded.integerdivision.interfaces.Formatable;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorTest {
	private int dividend;
	private int divider;
	private LongDivisionCalculator calculator;
	private Formatable formatterMock;
	private Formatable formatter;
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

	@Test
	void zeroDividendShouldEqualsDto() {
		dividend = 0;
		divider = 123;

		result = facade.divide(dividend, divider);

		ArgumentCaptor<CalculationDto> captor = ArgumentCaptor.forClass(CalculationDto.class);
		verify(formatterMock).createResult(captor.capture());

		CalculationDto capturedDto = captor.getValue();
		CalculationDto actualDto = calculator.longDivision(dividend, divider);

		assertEquals(actualDto, capturedDto);
	}



	@Test
	void nullCalculationDtoShouldThrowNullPointerException() {
		String expectedErrorMessage = "DTO cannot be \"null\"";
		formatter = new Formatter();

		assertThrows(NullPointerException.class, () -> formatter.createResult(null));

		String actualErrorMessage = "";
		try {
			formatter.createResult(null);
		} catch (NullPointerException e) {
			actualErrorMessage = e.getMessage();
		}

		assertEquals(expectedErrorMessage, actualErrorMessage);
	}

	@Test
	void emptyCalculationDtoShouldThrowNullPointerException() {
		String expectedErrorMessage = "DTO cannot be empty";
		formatter = new Formatter();

		CalculationDto emptyDto = new CalculationDto(0, 0, 0, 0);

		assertThrows(NullPointerException.class,
				() -> formatter.createResult(emptyDto));

		String actualErrorMessage = "";
		try {
			formatter.createResult(emptyDto);
		} catch (NullPointerException e) {
			actualErrorMessage = e.getMessage();
		}

		assertEquals(expectedErrorMessage, actualErrorMessage);
	}


	@Test
	void zeroDividerShouldThrowIllegalArgumentException() {
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

	@Test
	void orderTest() {
		dividend = 654754;
		divider = 654;

		LongDivisionCalculator calculatorMock = mock(LongDivisionCalculator.class);
		formatterMock = mock(Formatter.class);

		CalculationDto dto = new LongDivisionCalculator().longDivision(dividend, divider);
		String resultOfDivision = new Formatter().createResult(dto);

		when(calculatorMock.longDivision(dividend, divider))
				.thenReturn(dto);
		when(formatterMock.createResult(dto))
				.thenReturn(resultOfDivision);

		CalculatorFacade calculatorFacade = new CalculatorFacade(calculatorMock, formatterMock);
		calculatorFacade.divide(dividend, divider);

		InOrder inOrder = inOrder(calculatorMock, formatterMock);

		inOrder.verify(calculatorMock).longDivision(dividend, divider);
		inOrder.verify(formatterMock).createResult(dto);
	}
}