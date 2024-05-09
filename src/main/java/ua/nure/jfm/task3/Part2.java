package ua.nure.jfm.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part2 {
    private static final String PATTERN_FORMAT = "(?<=[^\\S]|^)\\S{%d}(?=[^\\S]|$)";
    private static final String PATH = "part2.txt";

    public static void main(String[] args) {
        String lines = getContent(PATH);

        for (int j = 1; j < 10; j++) {
            System.out.println(convert(lines, j));
        }
    }

    public static String convert(String input, int k) {
        Pattern pattern = Pattern.compile(String.format(PATTERN_FORMAT, k));
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder(k+":");

        while (matcher.find()) {
            String word = matcher.group();

            int length = word.length();
            if (length != k || result.indexOf(word) != -1) {
                continue;
            }

            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(word);
        }

        return result.toString();
    }

}