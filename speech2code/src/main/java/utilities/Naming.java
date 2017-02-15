package utilities;

import java.util.List;

/**
 * Created by mb on 11/01/2017.
 */
public class Naming {
    public static String listToCamelCase(List<String> words) {
        StringBuilder result = new StringBuilder();

        result.append(words.get(0));

        for(int i = 1; i < words.size(); i++) {
            result.append(capitiliseFirstLetter(words.get(i)));
        }

        return result.toString();
    }

    public static String capitiliseFirstLetter(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
    }

    public static String capitiliseFirstLetterInEachWord(List<String> words) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < words.size(); i++) {
            result.append(capitiliseFirstLetter(words.get(i)));
        }

        return result.toString();
    }

}