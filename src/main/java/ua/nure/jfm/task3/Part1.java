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

    public static String convert3(String input) {
		Matcher matcher = PATTERN.matcher(input);
		int lastNameMaxLen = 0;
		int firstNameMaxLen = 0;
		while (matcher.find()) {
			int lastNameLen = matcher.group(4).length();
			int firstNameLen = matcher.group(2).length();
			int midNameLen = matcher.group(3) == null ? 0 : matcher.group(3).length()+1;

			if (lastNameLen > lastNameMaxLen) {
				lastNameMaxLen = lastNameLen;
			}
			if (firstNameLen + midNameLen > firstNameMaxLen) {
				firstNameMaxLen = firstNameLen + midNameLen;
			}
		}

		lastNameMaxLen++;
		firstNameMaxLen++;

		matcher = PATTERN.matcher(input);
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			if(!result.isEmpty()) {
				result.append("\n");
			}

			String lastName = matcher.group(4);
			String firstName = matcher.group(2);
			String midName = matcher.group(3) == null ? "" : (" "+matcher.group(3));

			result.append(lastName)
					.append(" ".repeat(lastNameMaxLen - lastName.length()))
					.append(firstName)
					.append(midName)
					.append(" ".repeat(firstNameMaxLen - (firstName.length() + midName.length())))
					.append(matcher.group(1));
		}

		return result.toString().trim();
    }

}
