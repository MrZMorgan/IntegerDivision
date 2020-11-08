package ua.com.foxminded.integerdivision;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int dividend = readDigit();
        int divider = readDigit();

        LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
        String[] digits = calculator.getDigitsFromDividen();
        calculator.longDivision(digits);

        CalculatorDTO dto = new CalculatorDTO();
        dto.collectAllData(calculator);

        Formatter formatter = new Formatter(dto);
        formatter.printResult();
    }

    public static int readDigit() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
