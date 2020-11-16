package ua.com.foxminded.integerdivision;

import ua.com.foxminded.integerdivision.calculator.*;
import ua.com.foxminded.integerdivision.facede.*;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int dividend = readDigit();
        int divider = readDigit();

        CalculatorFacade calculator = new CalculatorFacade(new LongDivisionCalculator(), new Formatter());

        System.out.println(calculator.divide(dividend, divider));
    }

    public static int readDigit() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
