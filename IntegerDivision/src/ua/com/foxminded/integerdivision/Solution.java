package ua.com.foxminded.integerdivision;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int dividend = readDigit();
        int divider = readDigit();

        CalculatorFacade calculator = new CalculatorFacade();
        System.out.println(calculator.longDivision(dividend, divider));
    }

    public static int readDigit() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
