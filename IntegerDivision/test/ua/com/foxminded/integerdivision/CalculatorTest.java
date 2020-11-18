package ua.com.foxminded.integerdivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ua.com.foxminded.integerdivision.calculator.*;
import ua.com.foxminded.integerdivision.facade.*;
import ua.com.foxminded.integerdivision.formatter.Formatter;
import ua.com.foxminded.integerdivision.interfaces.Formatable;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorTest {
	private int dividend;
	private int divider;
	private LongDivisionCalculator calculator;
	private LongDivisionCalculator calculatorMock;
	private Formatable formatterMock;
	private Formatable formatter;
	private CalculatorFacade facade;
	private String result;
	private CalculationDto divideTwoDigitsDto;

	private static String DIVIDE_TWO_DIGITS_EXPECTED_RESULT = "_654754|654\n" +
															  " 654   -----\n" +
															  " ---   |1001\n" +
															  "   _754\n" +
															  "    654\n" +
															  "    ---\n" +
															  "    100\n";

	private static final String DIVIDEND_LESS_THEN_DIVIDER_EXPECTED_RESULT = "0 / 123 = 0";

	@BeforeEach
	void setUp() {
		calculator = new LongDivisionCalculator();
		calculatorMock = mock(LongDivisionCalculator.class);
		formatter = new Formatter();
		formatterMock = mock(Formatter.class);
	}

	@Test
	void divideTwoNumbersWithMockingFormatterShouldEqualsDto() {
		dividend = 654754;
		divider = 654;

		divideTwoDigitsDto = createDivideTwoDigitsDto();

		facade = new CalculatorFacade(calculator, formatterMock);
		facade.divide(dividend, divider);

		ArgumentCaptor<CalculationDto> captor = ArgumentCaptor.forClass(CalculationDto.class);
		verify(formatterMock).createResult(captor.capture());

		CalculationDto capturedDto = captor.getValue();

		assertEquals(divideTwoDigitsDto, capturedDto);
	}

	@Test
	void zeroDividendWithMockingFormatterShouldEqualsDto() {
		dividend = 0;
		divider = 123;

		CalculationDto actualDto = new CalculationDto(dividend, divider, 0, 0);

		facade = new CalculatorFacade(calculator, formatterMock);
		result = facade.divide(dividend, divider);

		ArgumentCaptor<CalculationDto> captor = ArgumentCaptor.forClass(CalculationDto.class);
		verify(formatterMock).createResult(captor.capture());

		CalculationDto capturedDto = captor.getValue();

		assertEquals(actualDto, capturedDto);
	}

	@Test
	void nullCalculationDtoShouldThrowNullPointerException() {
		String expectedErrorMessage = "DTO cannot be \"null\"";

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

		facade = new CalculatorFacade(calculator, formatter);
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

	@Test
	void divideTwoNumbersWithMockingCalculatorShouldEqualsDto() {
		dividend = 654754;
		divider = 654;

		divideTwoDigitsDto = createDivideTwoDigitsDto();

		facade = new CalculatorFacade(calculatorMock, formatter);

		when(calculatorMock.longDivision(dividend, divider))
				.thenReturn(divideTwoDigitsDto);

		String result = facade.divide(dividend, divider);

		assertEquals(DIVIDE_TWO_DIGITS_EXPECTED_RESULT, result);
	}

	@Test
	void zeroDividendWithMockingCalculatorShouldEqualsDto() {
		dividend = 0;
		divider = 123;
		CalculationDto dividendLessThanDividerDto = new CalculationDto(dividend, divider, 0, 0);

		facade = new CalculatorFacade(calculatorMock, formatter);

		when(calculatorMock.longDivision(dividend, divider))
				.thenReturn(dividendLessThanDividerDto);

		String result = facade.divide(dividend, divider);

		assertEquals(DIVIDEND_LESS_THEN_DIVIDER_EXPECTED_RESULT, result);
	}

	CalculationDto createDivideTwoDigitsDto(){
		dividend = 654754;
		divider = 654;
		int divisionResult = 1001;
		int remainder = 100;

		List<Integer> intermediateDividends = new ArrayList<>();
		List<Integer> intermediateDividers = new ArrayList<>();
		List<Integer> zerosBeforeDividends = new ArrayList<>();
		List<Integer> zerosBeforeDividers = new ArrayList<>();

		intermediateDividends.add(654);
		intermediateDividends.add(754);

		intermediateDividers.add(654);
		intermediateDividers.add(654);

		zerosBeforeDividends.add(0);
		zerosBeforeDividends.add(3);

		zerosBeforeDividers.add(1);
		zerosBeforeDividers.add(4);

		CalculationDto actualDto = new CalculationDto(dividend, divider, remainder, divisionResult);
		actualDto.setIntermediateDividends(intermediateDividends);
		actualDto.setIntermediateDividers(intermediateDividers);
		actualDto.setZerosBeforeDividends(zerosBeforeDividends);
		actualDto.setZerosBeforeDividers(zerosBeforeDividers);

		return actualDto;
	}
}