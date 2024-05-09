package ua.nure.jfm.task3.converter;

import static ua.nure.jfm.task3.Utils.parseInt;

// Roman numerals.
public class RomanConverter {
	private static final char[] digits = {
			'\u2160', // I
			'\u2164', // V
			'\u2169', // X
			'\u216C', // L
			'\u216D', // C
			'\u216E', // D
			'\u216F'  // M
	};

	private static final int[] values = { 1, 5, 10, 50, 100, 500, 1000 };

	public static String convert(String str) {
		int num = parseInt(str);
		if (num <= 0 || num >= 4000) {
			throw new IllegalArgumentException("Number out of range (1 to 3999)");
		}
		StringBuilder roman = new StringBuilder();

		for (int i = 0; i < values.length;) {
			int digit = num % 10;

			if (digit == 9) {
				roman.insert(0, digits[i]);
				roman.insert(1, digits[i + 2]);
			} else if (digit >= 5) {
				roman.insert(0, digits[i + 1]);
				for (int j = 0; j < digit - 5; j++) {
					roman.insert(1, digits[i]);
				}
			} else if (digit == 4) {
				roman.insert(0, digits[i]);
				roman.insert(1, digits[i + 1]);
			} else {
				for (int j = 0; j < digit; j++) {
					roman.insert(0, digits[i]);
				}
			}

			i += 2;
			num /= 10;
		}

		return roman.toString();
	}

	public static void main(String[] args) {
		System.out.println("All the digits");
		for (int j = 0; j < digits.length; j++) {
			System.out.printf("%s: %s%n", digits[j], values[j]);
		}
		String s;
		System.out.println("~~~");
		s = "444";
		System.out.println(s);
		System.out.println(RomanConverter.convert(s));
		System.out.println("~~~");
		s = "3_999";
		System.out.println(s);
		System.out.println(RomanConverter.convert(s));
	}
}
