package ua.nure.jfm.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part3 {
	private static final Pattern PATTERN = Pattern.compile("\\b([\\w\\p{IsCyrillic}]+)\\b");
	//private static final Pattern PATTERN = Pattern.compile("\\b(\\w+)\\b(?=[\\s\\S]+?\\b(\\1)\\b)", Pattern.CASE_INSENSITIVE);
	private static final String PATH = "part3.txt";

	public static void main(String[] args) {
		String lines = getContent(PATH);

		System.out.println(convert(lines));
	}

	private static String swapCase(String input) {
		StringBuilder result = new StringBuilder();
		for(char ch : input.toCharArray()) {
			if((ch >= 'a' && ch <= 'z') || (ch >= 'а' && ch <= 'я')) {
				result.append((char)(ch - 32));
			} else if((ch >= 'A' && ch <= 'Z') || (ch >= 'А' && ch <= 'Я')) {
				result.append((char) (ch + 32));
			} else if(ch == 'і') {
				result.append('І');
			} else if(ch == 'І') {
				result.append('і');
			} else {
				//System.out.println("Unknown symbol: "+ch);
				result.append(ch);
			}
		}

		return result.toString();
	}

	public static String convert(String input) {
		StringBuilder result = new StringBuilder();
		Matcher matcher = PATTERN.matcher(input);

		/*int start = 0;
		while (matcher.find(start)) {
			start = matcher.end();
			String word = matcher.group(1).toLowerCase();
			System.out.println("Match: " + matcher.group(1));
			System.out.println("  " + matcher.group(1) + ", pos: " + matcher.start(1));
			System.out.println("  " + matcher.group(2) + ", pos: " + matcher.start(2));
			while(matcher.find()) {
				if(!matcher.group(1).toLowerCase().equals(word)) {
					continue;
				}
				System.out.println("  " + matcher.group(2) + ", pos: " + matcher.start(2));
			}
		}*/

		while (matcher.find()) {
			String word = matcher.group(1);
			Matcher prevMatcher = Pattern.compile("\\b("+word+")\\b", Pattern.CASE_INSENSITIVE).matcher(input.substring(0, matcher.start(1)));
			int prevStart = -1;
			int prevEnd = -1;
			while (prevMatcher.find()) {
				prevStart = prevMatcher.start();
				prevEnd = prevMatcher.end();
			}

			if(prevStart < 0 || prevEnd < 0) {
				matcher.appendReplacement(result, word);
				continue;
			}

			if(result.substring(prevStart, prevEnd).equals(input.substring(prevStart, prevEnd))) {
				word = swapCase(word);
			}

			matcher.appendReplacement(result, word);
		}

		matcher.appendTail(result);
		return result.toString();
	}
}
