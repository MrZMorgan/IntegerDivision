package ua.com.foxminded.integerdivision;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int dividend = readDigit();
        int divider = readDigit();

        LongDivisionCalculator calculator = new LongDivisionCalculator(dividend, divider);
        CalculatorDTO dto = calculator.longDivision();
        Formatter formatter = new Formatter();

        System.out.println(formatter.createResult(dto));
    }

    public static int readDigit() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
