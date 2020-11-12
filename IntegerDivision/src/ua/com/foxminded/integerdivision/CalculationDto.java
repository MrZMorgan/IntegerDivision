package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class CalculationDto {
    private final int dividend;
    private final int divider;
    private final int remainder;
    private final int result;

    private List<Integer> intermediateDividends = new ArrayList<>();
    private List<Integer> intermediateDividers = new ArrayList<>();
    private List<Integer> zerosBeforeDividends = new ArrayList<>();
    private List<Integer> zerosBeforeDividers = new ArrayList<>();

    public CalculationDto(int dividend, int divider, int remainder, int result) {
        this.dividend = dividend;
        this.divider = divider;
        this.remainder = remainder;
        this.result = result;
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivider() {
        return divider;
    }

    public int getRemainder() {
        return remainder;
    }

    public int getResult() {
        return result;
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

    public List<Integer> getZerosBeforeDividends() { return zerosBeforeDividends; }

    public void setZerosBeforeDividends(List<Integer> zerosBeforeDividends) {
        this.zerosBeforeDividends = zerosBeforeDividends; }

    public List<Integer> getZerosBeforeDividers() {
        return zerosBeforeDividers;
    }

    public void setZerosBeforeDividers(List<Integer> zerosBeforeDividers) {
        this.zerosBeforeDividers = zerosBeforeDividers;
    }
}
