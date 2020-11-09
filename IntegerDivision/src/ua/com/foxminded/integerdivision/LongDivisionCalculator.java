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

    public LongDivisionCalculator(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    public CalculatorDTO longDivision() {
        CalculatorDTO dto = new CalculatorDTO();

        if (divider == 0) {
            throw new IllegalArgumentException("Делить на ноль нельзя");
        }

        if (dividend < divider) {
            dto.collectAllData(this);
            return dto;
        }

        divider = Math.abs(divider);

        String[] digits = getDigitsFromDividend();

        int dividendTmp = makeFirstDividend(digits);

        String fitsDividendTmp = String.valueOf(dividendTmp);

        String dividendTmpStr = fitsDividendTmp;
        StringBuilder result = new StringBuilder();

        int dividendsZeros = -1;
        int dividersZeros = 0;

        int startPoint = fitsDividendTmp.length() - 1;
        for (int i = startPoint; i < digits.length; ) {
            if (dividendTmp < divider) {
                dividendTmpStr += digits[i];
                dividendTmp = Integer.parseInt(dividendTmpStr);
                if (dividendTmp < divider) {
                    while (dividendTmp < divider) {
                        i++;
                        result.append("0");
                        dividendsZeros++;
                        dividersZeros++;
                        dividendTmpStr += digits[i];
                        dividendTmp = Integer.parseInt(dividendTmpStr);
                    }
                }
            } else {
                dividendTmp = Integer.parseInt(dividendTmpStr);
                result.append(dividendTmp / divider);
                remainder = dividendTmp % divider;

                collectTmpResults(dividendTmp, dividendTmp - remainder);

                dividendsZeros++;
                dividersZeros++;
                collectZeros(dividendsZeros, dividersZeros);

                dividendTmpStr = String.valueOf(remainder);
                dividendTmp = Integer.parseInt(dividendTmpStr);

                if (dividendTmp == dividendTmp - remainder && i > startPoint) {
                    dividendsZeros++;
                    dividersZeros++;
                }
                i++;
            }
        }

        this.result = Integer.parseInt(result.toString());


        dto.collectAllData(this);
        return dto;
    }

    private int makeFirstDividend(String[] digits) {
        StringBuilder firstDividend = new StringBuilder("0");
        for (String digit : digits) {
            if (Integer.parseInt(firstDividend.toString()) < divider) {
                firstDividend.append(digit) ;
            }
        }
        return Integer.parseInt(firstDividend.toString());
    }

    public String[] getDigitsFromDividend() {
        dividend = Math.abs(dividend);
        String dividendTmp = String.valueOf(dividend);
        return dividendTmp.split(DELIMITER);
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
