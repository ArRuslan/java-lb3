package ua.nure.jfm.task3.converter;

import static ua.nure.jfm.task3.Utils.parseInt;

// Maya numerals.
// You can use Google Font: "Noto Sans Mayan Numerals" to see the maya numbers.
public class MayaConverter {

	private static final String[] AR = new String[20];

	static {
		int mayaZeroHighSurrogate = 0xDEE0;
		for (int j = 0; j < 20; j++) {
			AR[j] = "\uD834" + (char) (mayaZeroHighSurrogate + j);
		}
	}

	public static String convert(String from) { // TODO: fix 100_000 and 3_999
		int number = parseInt(from);
		if (number == 0) {
			return AR[0];
		}

		StringBuilder result = new StringBuilder();
		while (number > 0) {
			int remainder = number % 20;
			result.insert(0, AR[remainder]);
			number /= 20;
		}

		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println("All the digits:");
		for (int j = 0; j < AR.length; j++) {
			System.out.printf("%2s: %s%n", j, AR[j]);
		}
		System.out.println("~~~");
		System.out.println(429);
		System.out.println(MayaConverter.convert("429"));
		System.out.println("~~~");
		System.out.println("100_000");
		System.out.println(MayaConverter.convert("100_000"));
	}

}
