package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class LongDivisionCalculator {
    private int dividend;
    private int divider;

    private int remainder;
    private int result;

    private final List<Integer> dividendsTMP = new ArrayList<>();
    private final List<Integer> dividersTMP = new ArrayList<>();
    private final List<Integer> zerosBeforeDividend = new ArrayList<>();
    private final List<Integer> zerosBeforeDivider = new ArrayList<>();

    public static final String DELIMITER = "";

    public CalculatorDTO longDivision(int dividend, int divider) {
        CalculatorDTO dto = new CalculatorDTO();

        if (divider == 0) {
            throw new IllegalArgumentException("Делить на ноль нельзя");
        }

        dividend = Math.abs(dividend);
        divider = Math.abs(divider);
        this.dividend = dividend;
        this.divider = divider;

        if (dividend < divider) {
            dto.collectAllData(this);
            return dto;
        }

        int[] digits = getDigitsFromDividend(dividend);
        int dividendTmp = makeFirstDividend(digits);

        int fitsDividendTmp = makeFirstDividend(digits);
        int result = 0;

        int dividendsZeros = -1;
        int dividersZeros = 0;

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
                collectTmpResults(dividendTmp, dividendTmp - remainder);
                dividendsZeros++;
                dividersZeros++;
                collectZeros(dividendsZeros, dividersZeros);

                dividendTmp = remainder;

                if (dividendTmp == 0 && i > startPoint) {
                    dividendsZeros++;
                    dividersZeros++;
                }
               
                i++;
            }
        }

        this.result = result;

        dto.collectAllData(this);
        return dto;
    }

    private int makeFirstDividend(int[] digits) {
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

    private void collectZeros(int dividendsZeros, int dividersZeros) {
        zerosBeforeDividend.add(dividendsZeros);
        zerosBeforeDivider.add(dividersZeros);
    }

    private void collectTmpResults(int dividendTMP, int dividerTMP) {
        dividendsTMP.add(dividendTMP);
        dividersTMP.add(dividerTMP);
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivider() {
        return divider;
    }

    public int getResult() {
        return result;
    }

    public int getRemainder() {
        return remainder;
    }

    public List<Integer> getDividendsTMP() {
        return dividendsTMP;
    }

    public List<Integer> getDividersTMP() {
        return dividersTMP;
    }

    public List<Integer> getZerosBeforeDividend() {
        return zerosBeforeDividend;
    }

    public List<Integer> getZerosBeforeDivider() {
        return zerosBeforeDivider;
    }
}
