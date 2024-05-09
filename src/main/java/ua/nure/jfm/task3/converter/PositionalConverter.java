package ua.nure.jfm.task3.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.parseInt;

public class PositionalConverter {

	private static final Pattern PATTERN = Pattern.compile("(\\d{1,2}):([\\w\\d]+):(\\d{1,2})");

	public static String convert(String line) {
		Matcher matcher = PATTERN.matcher(line);
		if(!matcher.find()) {
			throw new IllegalArgumentException("Invalid input");
		}

		int from = parseInt(matcher.group(1));
		int to = parseInt(matcher.group(3));
		String input = matcher.group(2);

		if (from == to) {
			return input;
		}

		if (from < 2 || from > 36)
			throw new IllegalArgumentException("Invalid base " + from);
		if (to < 2 || to > 36)
			throw new IllegalArgumentException("Invalid new base " + to);

		int numberValue = 0;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '_') {
				continue;
			}

			int digit = from;
			if (ch >= '0' && ch <= '9') {
				digit = ch - '0';
			} else if (ch >= 'a') {
				digit = ch - 'a' + 10;
			} else if (ch >= 'A') {
				digit = ch - 'A' + 10;
			}

			if (digit >= from) {
				throw new IllegalArgumentException("Invalid character " + ch + " for base " + from);
			}

			numberValue = numberValue * from + digit;
		}

		StringBuilder sb = new StringBuilder();
		while (numberValue != 0) {
			int digit = numberValue % to;
			numberValue = numberValue / to;

			char ch = (char) (digit < 10 ? digit + '0' : digit - 10 + 'A');
			sb.insert(0, ch);
		}

		if (sb.isEmpty())
			return "0";
		else
			return sb.toString();
	}

	public static void main(String[] args) {
		String s;
		s = "10:15:16";
		System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
		s = "10:17:2";
		System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
		s = "10:171:36";
		System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
	}

}
