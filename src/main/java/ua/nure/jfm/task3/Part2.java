package ua.nure.jfm.task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part2 {
    private static final String PATTERN = "(?<=[^\\S]|^)\\S+(?=[^\\S]|$)";
    private static final String PATH = "part2.txt";

    public static void main(String[] args) {
        String lines = getContent(PATH);

        for (int j = 1; j < 10; j++) {
            System.out.println(convert(lines, j));
        }
    }

    public static String convert(String input, int k) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder(k+":");

        /*while (matcher.find()) {
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

        return result.toString();*/

        HashMap<Integer, LinkedHashSet<String>> words = new HashMap<>();

        while (matcher.find()) {
            LinkedHashSet<String> current;
            String word = matcher.group();
            int length = word.length();

            if (!words.containsKey(length)) {
                current = new LinkedHashSet<>();
                words.put(length, current);
            } else {
                current = words.get(length);
            }

            current.add(word);
        }

        if(k > words.size()) {
            return "";
        }

        int needLen = words.keySet().stream().sorted().toList().get(k-1);
        for(String word : words.get(needLen)) {
            result.append(" ").append(word);
        }

        return result.toString();
    }

}