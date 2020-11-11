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

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public List<Integer> getDividendsTMP() {
        return dividendsTMP;
    }

    public void setDividendsTMP(List<Integer> dividendsTMP) {
        this.dividendsTMP = dividendsTMP;
    }

    public List<Integer> getDividersTMP() {
        return dividersTMP;
    }

    public void setDividersTMP(List<Integer> dividersTMP) {
        this.dividersTMP = dividersTMP;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Integer> getZerosBeforeDividend() {
        return zerosBeforeDividend;
    }

    public void setZerosBeforeDividend(List<Integer> zerosBeforeDividend) {
        this.zerosBeforeDividend = zerosBeforeDividend;
    }

    public List<Integer> getZerosBeforeDivider() {
        return zerosBeforeDivider;
    }

    public void setZerosBeforeDivider(List<Integer> zerosBeforeDivider) {
        this.zerosBeforeDivider = zerosBeforeDivider;
    }

    public void collectAllData(int dividend, int divider, int result, int remainder,
                               List<Integer> dividendsTMP, List<Integer> dividersTMP,
                               List<Integer> zerosBeforeDividend, List<Integer> zerosBeforeDivider) {

        setDividend(dividend);
        setDivider(divider);
        setResult(result);
        setRemainder(remainder);
        setDividendsTMP(dividendsTMP);
        setDividersTMP(dividersTMP);
        setZerosBeforeDividend(zerosBeforeDividend);
        setZerosBeforeDivider(zerosBeforeDivider);
    }
}
