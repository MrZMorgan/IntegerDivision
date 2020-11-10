package ua.com.foxminded.integerdivision;

public class CalculatorFacade {
    LongDivisionCalculator calculator;
    CalculatorDTO dto;
    Formatter formatter;

    public CalculatorFacade(LongDivisionCalculator calculator, Formatter formatter) {
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public String divide(int dividend, int divider) {
        dto = calculator.longDivision(dividend, divider);
        return formatter.createResult(dto);
    }
}
