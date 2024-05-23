package ua.nure.jfm.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part2 {
    private static final String PATTERN_FORMAT = "(?<=[^\\S]|^)\\S{%s}(?=[^\\S]|$)";
    private static final String PATH = "part2.txt";

    public static void main(String[] args) {
        String lines = getContent(PATH);

        for (int j = 1; j < 10; j++) {
            System.out.println(convert(lines, j));
        }
    }

    public static String convert(String input, int k) {
        StringBuilder result = new StringBuilder(k+":");
        int lastLength = 0;

        for(int i = 1; i <= k; i++) {
            int nextLength = 0;
            Pattern pattern = Pattern.compile(String.format(PATTERN_FORMAT, (lastLength + 1)+","));
            Matcher matcher = pattern.matcher(input);
            if(!matcher.find()) {
                return "";
            }
            matcher.reset();

            while (matcher.find()) {
                String word = matcher.group();
                int length = word.length();

                if(nextLength == 0 || nextLength > length) {
                    nextLength = length;
                }
            }

            lastLength = nextLength;
        }

        Pattern pattern = Pattern.compile(String.format(PATTERN_FORMAT, lastLength));
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String word = matcher.group();

            if(result.indexOf(word) != -1) {
                continue;
            }

            result.append(" ").append(word);
        }

        return result.toString();
    }

}