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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalculationDto dto = (CalculationDto) o;

        if (dividend != dto.dividend) return false;
        if (divider != dto.divider) return false;
        if (remainder != dto.remainder) return false;
        if (result != dto.result) return false;
        if (intermediateDividends != null ? !intermediateDividends.equals(dto.intermediateDividends) : dto.intermediateDividends != null)
            return false;
        if (intermediateDividers != null ? !intermediateDividers.equals(dto.intermediateDividers) : dto.intermediateDividers != null)
            return false;
        if (zerosBeforeDividends != null ? !zerosBeforeDividends.equals(dto.zerosBeforeDividends) : dto.zerosBeforeDividends != null)
            return false;
        return zerosBeforeDividers != null ? zerosBeforeDividers.equals(dto.zerosBeforeDividers) : dto.zerosBeforeDividers == null;
    }

    @Override
    public int hashCode() {
        int result1 = dividend;
        result1 = 31 * result1 + divider;
        result1 = 31 * result1 + remainder;
        result1 = 31 * result1 + result;
        result1 = 31 * result1 + (intermediateDividends != null ? intermediateDividends.hashCode() : 0);
        result1 = 31 * result1 + (intermediateDividers != null ? intermediateDividers.hashCode() : 0);
        result1 = 31 * result1 + (zerosBeforeDividends != null ? zerosBeforeDividends.hashCode() : 0);
        result1 = 31 * result1 + (zerosBeforeDividers != null ? zerosBeforeDividers.hashCode() : 0);
        return result1;
    }
}
