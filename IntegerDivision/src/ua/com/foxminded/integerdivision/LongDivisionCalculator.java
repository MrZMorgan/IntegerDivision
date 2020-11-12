package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class LongDivisionCalculator {
    public static final String DELIMITER = "";

    public CalculationDto longDivision(int dividend, int divider) {
        dividend = Math.abs(dividend);
        divider = Math.abs(divider);

        int[] digits = getDigitsFromDividend(dividend);
        int intermediateDividend = makeFirstDividend(digits, divider);
        int fitsIntermediateDividend = makeFirstDividend(digits, divider);

        int result = 0;
        int remainder = 0;

        if (divider == 0) {
            throw new IllegalArgumentException("Ñan`t divide by zero"); 
        }

        int dividendsZeros = -1;
        int dividersZeros = 0;

        List<Integer> intermediateDividends = new ArrayList<>();
        List<Integer> intermediateDividers = new ArrayList<>();

        List<Integer> zerosBeforeDividend = new ArrayList<>();
        List<Integer> zerosBeforeDivider = new ArrayList<>();

        CalculationDto intermediateDto = new CalculationDto(dividend, divider, remainder, result);

        if (dividend < divider) {
            return intermediateDto;
        }

        int startPoint = String.valueOf(fitsIntermediateDividend).length() - 1;
        for (int i = startPoint; i < digits.length; ) {
            if (intermediateDividend < divider) {
                intermediateDividend = concatTwoDigits(intermediateDividend, digits[i]);
                if (intermediateDividend < divider) {
                    while (intermediateDividend < divider) {
                        i++;
                        result = concatTwoDigits(result, 0);
                        dividendsZeros++;
                        dividersZeros++;
                        intermediateDividend = concatTwoDigits(intermediateDividend, digits[i]);
                    }
                }
            } else {
            	result = concatTwoDigits(result, (intermediateDividend / divider));
                remainder = intermediateDividend % divider;
                collectTmpResults(intermediateDividend, intermediateDividend - remainder, intermediateDividends, intermediateDividers);
                dividendsZeros++;
                dividersZeros++;
                collectZeros(dividendsZeros, dividersZeros, zerosBeforeDividend, zerosBeforeDivider);

                intermediateDividend = remainder;

                if (intermediateDividend == 0 && i > startPoint) {
                    dividendsZeros++;
                    dividersZeros++;
                }
               
                i++;
            }
        }

        CalculationDto dto = new CalculationDto(dividend, divider, remainder, result);

        dto.setIntermediateDividends(intermediateDividends);
        dto.setIntermediateDividers(intermediateDividers);
        dto.setZerosBeforeDividends(zerosBeforeDividend);
        dto.setZerosBeforeDividers(zerosBeforeDivider);

        return dto;
    }

    private int makeFirstDividend(int[] digits, int divider) {
        int firstDividend = 0;
        for (int digit : digits) {
            if (firstDividend < divider) {
                firstDividend = concatTwoDigits(firstDividend, digit) ;
            }
        }
        return firstDividend;
    }
    
    private int concatTwoDigits(int digit1, int digit2) {
		int result;
		result = (int) (digit1 * (Math.pow(10, String.valueOf(digit2).length())) + digit2);
		
		return result;
	}

    public int[] getDigitsFromDividend(int dividend) {
        dividend = Math.abs(dividend);
        String dividendTmp = String.valueOf(dividend);
        String[] digitsTmp = dividendTmp.split(DELIMITER);
        int[] digits = new int[dividendTmp.length()];
        
        for (int i = 0; i < digitsTmp.length; i++) {
			digits[i] = Integer.parseInt(digitsTmp[i]);
		}
        
        return digits;
    }

    private void collectZeros(int dividendsZeros, int dividersZeros,
                              List<Integer> zerosBeforeDividend, List<Integer> zerosBeforeDivider) {
        zerosBeforeDividend.add(dividendsZeros);
        zerosBeforeDivider.add(dividersZeros);
    }

    private void collectTmpResults(int intermediateDividend, int intermediateDivider,
                                   List<Integer> intermediateDividends, List<Integer> intermediateDividers) {
        intermediateDividends.add(intermediateDividend);
        intermediateDividers.add(intermediateDivider);
    }
}
