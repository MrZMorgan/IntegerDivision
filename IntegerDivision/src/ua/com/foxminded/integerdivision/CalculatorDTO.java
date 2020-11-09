package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class CalculatorDTO {
    private int dividend;
    private int divider;
    private int remainder;
    private int result;

    private List<Integer> dividendsTMP = new ArrayList<>();
    private List<Integer> dividersTMP = new ArrayList<>();
    private List<Integer> zerosBeforeDividend = new ArrayList<>();
    private List<Integer> zerosBeforeDivider = new ArrayList<>();

    public int getDividend() {
        return dividend;
    }

    private void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public int getDivider() {
        return divider;
    }

    private void setDivider(int divider) {
        this.divider = divider;
    }

    public List<Integer> getDividendsTMP() {
        return dividendsTMP;
    }

    private void setDividendsTMP(List<Integer> dividendsTMP) {
        this.dividendsTMP = dividendsTMP;
    }

    public List<Integer> getDividersTMP() {
        return dividersTMP;
    }

    private void setDividersTMP(List<Integer> dividersTMP) {
        this.dividersTMP = dividersTMP;
    }

    public int getRemainder() {
        return remainder;
    }

    private void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public int getResult() {
        return result;
    }

    private void setResult(int result) {
        this.result = result;
    }

    public List<Integer> getZerosBeforeDividend() {
        return zerosBeforeDividend;
    }

    private void setZerosBeforeDividend(List<Integer> zerosBeforeDividend) {
        this.zerosBeforeDividend = zerosBeforeDividend;
    }

    public List<Integer> getZerosBeforeDivider() {
        return zerosBeforeDivider;
    }

    private void setZerosBeforeDivider(List<Integer> zerosBeforeDivider) {
        this.zerosBeforeDivider = zerosBeforeDivider;
    }

    public void collectAllData(LongDivisionCalculator calculator) {
        setDividend(calculator.getDividend());
        setDivider(calculator.getDivider());
        setDividendsTMP(calculator.getDividendsTMP());
        setDividersTMP(calculator.getDividersTMP());
        setRemainder(calculator.getRemainder());
        setResult(calculator.getResult());
        setZerosBeforeDividend(calculator.getZerosBeforeDividend());
        setZerosBeforeDivider(calculator.getZerosBeforeDivider());
    }
}
