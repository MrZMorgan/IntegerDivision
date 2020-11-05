package ua.com.foxminded.integerdivision;

public class IntegerDivision {
	private StringBuilder result = new StringBuilder();
	private StringBuilder quotient = new StringBuilder();
	private StringBuilder reminder = new StringBuilder();

	public String makeDivision(int dividend, int divider) {
		if (divider == 0) {
			throw new IllegalArgumentException("Делить на ноль нельзя");
		}

		dividend = Math.abs(dividend);
		divider = Math.abs(divider);

		if (dividend < divider) {
			return "" + dividend + "/" + divider + " = 0";
		}

		String[] digits = String.valueOf(dividend).split("");
		int reminderNumber;
		int multiplyResult;
		int divisorDigit = calculateDigit(divider);
		int mod;

		for (int i = 0; i < digits.length; i++) {
			reminder.append(digits[i]);
			reminderNumber = Integer.parseInt(reminder.toString());

			if (reminderNumber >= divider) {
				mod = reminderNumber % divider;
				multiplyResult = reminderNumber / divider * divider;

				String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
				result.append(lastReminder).append("\n");

				String multiply = String.format("%" + (i + 2) + "d", multiplyResult);
				result.append(multiply).append("\n");

				Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
				result.append(makeDivider(reminderNumber, tab)).append("\n");

				quotient.append(reminderNumber / divider);

				reminder.replace(0, reminder.length(), Integer.toString(mod));
				reminderNumber = Integer.parseInt(reminder.toString());
			} else {
				if (i >= divisorDigit) {
					quotient.append(0);
				}
			}

			if (i == digits.length - 1) {
				result.append(String.format("%" + (i + 2) + "s", Integer.toString(reminderNumber))).append("\n");
			}
		}
		modifyResultToView(dividend, divider);
		return result.toString();
	}

	private String makeDivider(int reminderNumber, int tab) {
		return assemblyString(tab, ' ') + assemblyString(calculateDigit(reminderNumber), '-');
	}

	private void modifyResultToView(int dividend, int divider) {
		int[] index = new int[3];
		for (int i = 0, j = 0; i < result.length(); i++) {
			if (result.charAt(i) == '\n') {
				index[j] = i;
				j++;
			}

			if (j == 3) {
				break;
			}
		}

		int tab = calculateDigit(dividend) + 1 - index[0];
		result.insert(index[2], assemblyString(tab, ' ') + "│" + quotient.toString());
		result.insert(index[1], assemblyString(tab, ' ') + "│" + assemblyString(quotient.length(), '-'));
		result.insert(index[0], "│" + divider);
		result.replace(1, index[0], String.valueOf(dividend));
	}

	private int calculateDigit(int i) {
		return (int) Math.log10(i) + 1;
	}

	private String assemblyString(int numberOfSymbols, char symbol) {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < numberOfSymbols; i++) {
			string.append(symbol);
		}
		return string.toString();
	}
}
