package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class LongDivisionCalculator {
	private int dividend;
	private int divider;

	private int remainder;
	private int result;

	private List<Integer> dividendsTMP = new ArrayList<>();
	private List<Integer> dividersTMP = new ArrayList<>();
	private List<Integer> zerosBeforeDividend = new ArrayList<>();
	private List<Integer> zerosBeforeDivider = new ArrayList<>();

	public static final String DELIMITER = "";

	public LongDivisionCalculator(int dividend, int divider) {
		this.dividend = dividend;
		this.divider = divider;
	}

	public void longDivision(String[] digits) {
		if (divider == 0) {
			throw new IllegalArgumentException("На ноль делить нельзя");
		}

		if (dividend < divider) {
			return;
		}

		divider = Math.abs(divider);

		int dividendTmp = makeFirstDividend(digits);
		String firtsDividendTmp = String.valueOf(dividendTmp);

		String dividendTmpStr = firtsDividendTmp;
		String result = "";

		int dividendsZeros = -1;
		int dividersZeros = 0;

		int startPoint = firtsDividendTmp.length() - 1;
		for (int i = startPoint; i < digits.length;) {
			if (dividendTmp < divider) {
				dividendTmpStr += digits[i];
				dividendTmp = Integer.parseInt(dividendTmpStr);
				if (dividendTmp < divider) {
					while (dividendTmp < divider) {
						i++;
						result += "0";
						dividendsZeros++;
						dividersZeros++;
						dividendTmpStr += digits[i];
						dividendTmp = Integer.parseInt(dividendTmpStr);
					}
				}
			} else {
				dividendTmp = Integer.parseInt(dividendTmpStr);
				result += dividendTmp / divider;
				remainder = dividendTmp % divider;

				collectTmpResults(dividendTmp, dividendTmp - remainder);

				dividendsZeros++;
				dividersZeros++;
				collectZeros(dividendsZeros, dividersZeros);

				dividendTmpStr = String.valueOf(remainder);
				dividendTmp = Integer.parseInt(dividendTmpStr);

				if (dividendTmp == dividendTmp - remainder && i > startPoint) {
					dividendsZeros++;
					dividersZeros++;
				}
				i++;
			}
		}

		this.result = Integer.parseInt(result);
	}

	private int makeFirstDividend(String[] digits) {
		String firstDividend = "0";
		for (int i = 0; i < digits.length; i++) {
			if (Integer.parseInt(firstDividend) < divider) {
				firstDividend += digits[i];
			}
		}
		return Integer.parseInt(firstDividend);
	}

	public String[] getDigitsFromDividen() {
		dividend = Math.abs(dividend);
		String dividendTmp = String.valueOf(dividend);
		return dividendTmp.split(DELIMITER);
	}

	private void collectZeros(int dividendsZeros, int dividersZeros) {
		zerosBeforeDividend.add(dividendsZeros);
		zerosBeforeDivider.add(dividersZeros);
	}

	private void collectTmpResults(int dividendTMP, int dividerTMP) {
		dividendsTMP.add(dividendTMP);
		dividersTMP.add(dividerTMP);
	}

	public int getDividend() {
		return dividend;
	}

	public int getDivider() {
		return divider;
	}

	public int getResult() {
		return result;
	}

	public int getRemainder() {
		return remainder;
	}

	public List<Integer> getDividendsTMP() {
		return dividendsTMP;
	}

	public List<Integer> getDividersTMP() {
		return dividersTMP;
	}

	public List<Integer> getZerosBeforeDividend() {
		return zerosBeforeDividend;
	}

	public List<Integer> getZerosBeforeDivider() {
		return zerosBeforeDivider;
	}
}
