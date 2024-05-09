package ua.nure.jfm.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part1 {
	
    private static final String PATH = "part1.txt";
	private static final Pattern PATTERN = Pattern.compile("^(\\w+);([\\w\\p{IsCyrillic}-]+) (?:([\\w\\p{IsCyrillic}]+) )?([\\w\\p{IsCyrillic}]+);(\\w+@\\w{1,256}\\.\\w+)", Pattern.MULTILINE);

	public static void main(String[] args) {
		System.setProperty("file.encoding", "windows-1251");

		String lines = getContent(PATH);

		System.out.println(convert1(lines));
		System.out.println();
		System.out.println(convert2(lines));
		System.out.println();
		System.out.println(convert3(lines));
	}

    public static String convert1(String input) {
		Matcher matcher = PATTERN.matcher(input);

		StringBuilder result = new StringBuilder("LastName;Email");
		while (matcher.find()) {
			if(!result.isEmpty()) {
				result.append("\n");
			}

			result.append(matcher.group(4))
					.append(";")
					.append(matcher.group(5));
		}

		return result.toString();
    }
    
    public static String convert2(String input) {
		Matcher matcher = PATTERN.matcher(input);

		StringBuilder result = new StringBuilder("LastName;MiddleName;FirstName");
		while (matcher.find()) {
			if(!result.isEmpty()) {
				result.append("\n");
			}

			result.append(matcher.group(4))
					.append(";")
					.append(matcher.group(3) == null ? "" : matcher.group(3))
					.append(";")
					.append(matcher.group(2));
		}

		return result.toString();
    }

    public static String convert3(String input) { // TODO: fix tabs
		Matcher matcher = PATTERN.matcher(input);

		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			if(!result.isEmpty()) {
				result.append("\n");
			}

			result.append(matcher.group(4))
					.append("\t")
					.append(matcher.group(2))
					.append(matcher.group(3) == null ? "" : " " + matcher.group(3))
					.append("\t")
					.append(matcher.group(1));
		}

		return result.toString().trim();
    }

}
