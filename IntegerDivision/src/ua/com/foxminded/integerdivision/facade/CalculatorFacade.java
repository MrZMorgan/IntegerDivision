package ua.com.foxminded.integerdivision.facade;

import ua.com.foxminded.integerdivision.calculator.CalculationDto;
import ua.com.foxminded.integerdivision.interfaces.Formatable;
import ua.com.foxminded.integerdivision.calculator.LongDivisionCalculator;

public class CalculatorFacade {
    private final LongDivisionCalculator calculator;
    private final Formatable formatter;

    public CalculatorFacade(LongDivisionCalculator calculator, Formatable formatter) {
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public String divide(int dividend, int divider) {
        CalculationDto dto = calculator.longDivision(dividend, divider);
        return formatter.createResult(dto);
    }
}
