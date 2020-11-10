package ua.com.foxminded.integerdivision;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int dividend = readDigit();
        int divider = readDigit();

        CalculatorFacade calculator = new CalculatorFacade(dividend, divider);
        System.out.println(calculator.longDivision());
    }

    public static int readDigit() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
