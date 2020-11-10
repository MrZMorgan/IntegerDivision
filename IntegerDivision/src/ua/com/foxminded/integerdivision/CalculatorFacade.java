package ua.com.foxminded.integerdivision;

public class CalculatorFacade {
    private final int dividend;
    private final int divider;

    public CalculatorFacade(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }

    public String longDivision() {
        LongDivisionCalculator calculator = new LongDivisionCalculator();
        CalculatorDTO dto = calculator.longDivision(dividend, divider);
        Formatter formatter = new Formatter();

        return formatter.createResult(dto);
    }
}
