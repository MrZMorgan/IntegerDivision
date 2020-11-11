package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class CalculationDto {
    private int dividend;
    private int divider;
    private int remainder;
    private int result;

    private List<Integer> intermediateDividends = new ArrayList<>();
    private List<Integer> intermediateDividers = new ArrayList<>();
    private List<Integer> zerosBeforeDividends = new ArrayList<>();
    private List<Integer> zerosBeforeDividers = new ArrayList<>();

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

    public List<Integer> getIntermediateDividends() {
        return intermediateDividends;
    }

    public void setIntermediateDividends(List<Integer> intermediateDividends) {
        this.intermediateDividends = intermediateDividends;
    }

    public List<Integer> getIntermediateDividers() {
        return intermediateDividers;
    }

    public void setIntermediateDividers(List<Integer> intermediateDividers) {
        this.intermediateDividers = intermediateDividers;
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

    public List<Integer> getZerosBeforeDividends() { return zerosBeforeDividends; }

    public void setZerosBeforeDividends(List<Integer> zerosBeforeDividends) {
        this.zerosBeforeDividends = zerosBeforeDividends; }

    public List<Integer> getZerosBeforeDividers() {
        return zerosBeforeDividers;
    }

    public void setZerosBeforeDividers(List<Integer> zerosBeforeDividers) {
        this.zerosBeforeDividers = zerosBeforeDividers;
    }

    public void collectAllData(int dividend, int divider, int result, int remainder,
                               List<Integer> intermediateDividends, List<Integer> intermediateDividers,
                               List<Integer> zerosBeforeDividend, List<Integer> zerosBeforeDivider) {

        setDividend(dividend);
        setDivider(divider);
        setResult(result);
        setRemainder(remainder);
        setIntermediateDividends(intermediateDividends);
        setIntermediateDividers(intermediateDividers);
        setZerosBeforeDividends(zerosBeforeDividend);
        setZerosBeforeDividers(zerosBeforeDivider);
    }
}
