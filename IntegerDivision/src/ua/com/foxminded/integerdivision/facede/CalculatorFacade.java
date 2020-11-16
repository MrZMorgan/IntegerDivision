package ua.com.foxminded.integerdivision.facede;

import ua.com.foxminded.integerdivision.calculator.CalculationDto;
import ua.com.foxminded.integerdivision.calculator.Formatter;
import ua.com.foxminded.integerdivision.calculator.LongDivisionCalculator;

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
