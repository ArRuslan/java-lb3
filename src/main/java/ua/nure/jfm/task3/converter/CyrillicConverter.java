package ua.nure.jfm.task3.converter;

import static ua.nure.jfm.task3.Utils.parseInt;

// Cyrillic numerals.
public class CyrillicConverter {

	private static char[][] DIGITS = {
			// а   0     в   1     г   2     д   3     є   4     ѕ   5     з   6     и   7     ѳ   9
			{'\u0430', '\u0432', '\u0433', '\u0434', '\u0454', '\u0455', '\u0437', '\u0438', '\u0473'},
			// і  10     к  20     л  30     м  40     н  50     ѯ  60     о  70     п  80     ч  90
			{'\u0456', '\u043A', '\u043B', '\u043C', '\u043D', '\u046F', '\u043E', '\u043F', '\u0447'},
			// р 100     с 200     т 300     у 400     ф 500     х 600     ѱ 700     ѡ 800     ц 900
			{'\u0440', '\u0441', '\u0442', '\u0443', '\u0444', '\u0445', '\u0471', '\u0461', '\u0446'}
		};
	
	/**
	 * The thousands sign to multiply the number's value.
	 */
	private static char kMul = '\u0482'; // ҂
	
	public static String convert(String input) {
		int number = parseInt(input);

		StringBuilder result = new StringBuilder();

		int thousands = number / 1000;

		if (thousands > 0) {
			appendDigits(result, thousands, true);
			number -= thousands * 1000;
		}

		appendDigits(result, number, false);

		return result.toString();
	}

	private static void appendDigits(StringBuilder result, int number, boolean isThousands) {
		int digits = number % 10;
		int tens = (number / 10) % 10;
		int hundreds = (number / 100) % 10;

		if(hundreds > 0) {
			if(isThousands) {
				result.append(kMul);
			}
			result.append(DIGITS[2][hundreds-1]);
		}
		if(tens > 0) {
			if(isThousands) {
				result.append(kMul);
			}
			result.append(DIGITS[1][tens-1]);
		}
		if(digits > 0) {
			if(isThousands) {
				result.append(kMul);
			}
			result.append(DIGITS[0][digits-1]);
		}
	}

	public static void main(String[] args) {
		System.out.println("All the digits:");
		int k = 1;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 9; i++) {
				System.out.printf("%3s: %s [%s, %s]%n", (i + 1) * k, DIGITS[j][i], j, i);
			}
			k *= 10;
		}
		System.out.println(kMul);
		String s = "999_999";
		System.out.printf("Maximum value (%s):%n", s);
		System.out.println(CyrillicConverter.convert(s));
	}
}
