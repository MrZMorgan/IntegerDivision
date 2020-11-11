package ua.com.foxminded.integerdivision;

public class CalculatorFacade {
    private final LongDivisionCalculator calculator;
    private final Formatter formatter;

    public CalculatorFacade(LongDivisionCalculator calculator, Formatter formatter) {
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public String divide(int dividend, int divider) {
        CalculationDto dto = calculator.longDivision(dividend, divider);
        return formatter.createResult(dto);
    }
}
