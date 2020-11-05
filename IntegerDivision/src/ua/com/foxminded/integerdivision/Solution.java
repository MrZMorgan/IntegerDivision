package ua.com.foxminded.integerdivision;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
        int dividend = readDigit();
        int divider = readDigit();
        IntegerDivision integerDivision = new IntegerDivision();
        System.out.println(integerDivision.makeDivision(dividend, divider));
    }

    public static int readDigit() {
        Scanner scaner = new Scanner(System.in);
        return scaner.nextInt();
    }
}
