package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormatterTest {
	
	private final static String DIVISION_EXPECTED_RESULT = "_654754|654\n" + 
														   " 654   -----\n" + 
														   " ---   |1001\n" + 
														   "   _754\n" + 
														   "    654\n" + 
														   "    ---\n" + 
														   "    100\n";
	
	private final static String ZERO_DIVIDEND_EXPECTED_RESULT = "0 / 123 = 0";	

	@Test
	void testLongDivideTwoNumbers() {
		int dividend = 654754;
		int divider = 654;
		
		LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
		String[] digits = calculator.getDigitsFromDividen();
		calculator.longDivision(digits);
		
		CalculatorDTO dto = new CalculatorDTO();
        dto.collectAllData(calculator);
        
        Formatter formatter = new Formatter(dto);
        
		String actual = formatter.createResult();
		assertEquals(DIVISION_EXPECTED_RESULT, actual);
	}
	
	@Test
	void testDivideZero() {
		int dividend = 123;
		int divider = 0;
		
		LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
		String[] digits = calculator.getDigitsFromDividen();
		assertThrows(IllegalArgumentException.class, () -> calculator.longDivision(digits));
	}
	
	@Test
	void testZeroDividend() {
		int dividend = 0;
		int divider = 123;
		
		LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
		String[] digits = calculator.getDigitsFromDividen();
		calculator.longDivision(digits);
		
		CalculatorDTO dto = new CalculatorDTO();
        dto.collectAllData(calculator);
        
        Formatter formatter = new Formatter(dto);
        
		String actual = formatter.createResult();
		
		assertEquals(ZERO_DIVIDEND_EXPECTED_RESULT, actual);
	}

}
