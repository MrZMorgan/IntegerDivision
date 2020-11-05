package ua.com.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegerDivisionTest {
	
	private IntegerDivision integerDivision;
	
	private String divisionResult = "_654754│654\n" + 
									" 654   │----\n" + 
									" ---   │1001\n" + 
									"   _754\n" + 
									"    654\n" + 
									"    ---\n" + 
									"    100\n";
	
	@BeforeEach
	void setUpIntegerDivision() {
		integerDivision = new IntegerDivision();
	}

	@Test
	void testLongDivideTwoNumbers() {
		int dividend = 654754;
		int divider = 654;
		String actual = integerDivision.makeDivision(dividend, divider);
		assertEquals(divisionResult, actual);
	}
	
	@Test
	void testDivideZero() {
		int dividend = 123;
		int divider = 0;
		assertThrows(IllegalArgumentException.class, () -> integerDivision.makeDivision(dividend, divider));
	}
}
