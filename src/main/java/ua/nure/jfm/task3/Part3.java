package ua.nure.jfm.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part3 {
	//private static final Pattern PATTERN = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
	private static final Pattern PATTERN = Pattern.compile("\\b(\\w+)\\b(?=.*\\b(\\1)\\b)*");
	private static final String PATH = "part3.txt";

	public static void main(String[] args) {
		String lines = getContent(PATH);

		System.out.println(convert(lines));
	}

	public static String convert(String input) { // TODO: implement
		Matcher matcher = PATTERN.matcher(input);
		String result = input;

		while (matcher.find()) {
			System.out.println(matcher.group(1) + " - " + matcher.groupCount() + ":");
			for(int i = 1; i <= matcher.groupCount(); i++) {
				System.out.println("  " + matcher.group(i));
			}
			/*int wordLen = (matcher.end() - matcher.start() - 1) / 2;
			String word = input.substring(matcher.start(), matcher.start() + wordLen);
			result = result.substring(0, matcher.start() + wordLen + 1) + word.toUpperCase() + result.substring(matcher.end());*/
		}

		return result;
	}

}
