package ua.com.foxminded.integerdivision;

public class CalculatorFacade {
    public String longDivision(int dividend, int divider) {
        LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
        CalculatorDTO dto = calculator.longDivision();
        Formatter formatter = new Formatter();

        return formatter.createResult(dto);
    }
}
