package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class LongDivisionCalculator {
    public static final String DELIMITER = "";

    public CalculatorDTO longDivision(int dividend, int divider) {
        CalculatorDTO dto = new CalculatorDTO();

        if (divider == 0) {
            throw new IllegalArgumentException("Ñan`t divide by zero");
        }

        dividend = Math.abs(dividend);
        divider = Math.abs(divider);

        int[] digits = getDigitsFromDividend(dividend);
        int dividendTmp = makeFirstDividend(digits, divider);
        int fitsDividendTmp = makeFirstDividend(digits, divider);

        int result = 0;
        int remainder = 0;

        int dividendsZeros = -1;
        int dividersZeros = 0;

        List<Integer> dividendsTMP = new ArrayList<>();
        List<Integer> dividersTMP = new ArrayList<>();

        List<Integer> zerosBeforeDividend = new ArrayList<>();
        List<Integer> zerosBeforeDivider = new ArrayList<>();

        if (dividend < divider) {
            dto.collectAllData(dividend, divider, result, remainder,
                    dividendsTMP, dividersTMP, zerosBeforeDividend, zerosBeforeDivider);
            return dto;
        }

        int startPoint = String.valueOf(fitsDividendTmp).length() - 1;
        for (int i = startPoint; i < digits.length; ) {
            if (dividendTmp < divider) {
                dividendTmp = concatTwoDigits(dividendTmp, digits[i]);
                if (dividendTmp < divider) {
                    while (dividendTmp < divider) {
                        i++;
                        result = concatTwoDigits(result, 0);
                        dividendsZeros++;
                        dividersZeros++;
                        dividendTmp = concatTwoDigits(dividendTmp, digits[i]);
                    }
                }
            } else {
            	result = concatTwoDigits(result, (dividendTmp / divider));
                remainder = dividendTmp % divider;
                collectTmpResults(dividendTmp, dividendTmp - remainder, dividendsTMP, dividersTMP);
                dividendsZeros++;
                dividersZeros++;
                collectZeros(dividendsZeros, dividersZeros, zerosBeforeDividend, zerosBeforeDivider);

                dividendTmp = remainder;

                if (dividendTmp == 0 && i > startPoint) {
                    dividendsZeros++;
                    dividersZeros++;
                }
               
                i++;
            }
        }

        dto.collectAllData(dividend, divider, result, remainder,
                dividendsTMP, dividersTMP, zerosBeforeDividend, zerosBeforeDivider);

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

    private void collectZeros(int dividendsZeros, int dividersZeros, List<Integer> zerosBeforeDividend, List<Integer> zerosBeforeDivider) {
        zerosBeforeDividend.add(dividendsZeros);
        zerosBeforeDivider.add(dividersZeros);
    }

    private void collectTmpResults(int dividendTMP, int dividerTMP, List<Integer> dividendsTMP, List<Integer> dividersTMP) {
        dividendsTMP.add(dividendTMP);
        dividersTMP.add(dividerTMP);
    }
}
