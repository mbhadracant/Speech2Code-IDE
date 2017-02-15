package utilities;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mb on 11/01/2017.
 */
public class NamingTest extends TestCase {

    @Test
    public void testStringToCamelCase() {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("there");
        words.add("nice");
        words.add("weather");
        String output = Naming.listToCamelCase(words);
        assertEquals("helloThereNiceWeather", output);
    }

    @Test
    public void testCapitiliseFirstLetter() {
        String word = "word";
        String output = Naming.capitiliseFirstLetter(word);
        assertEquals("Word", output);
    }

    @Test
    public void testCapitiliseFirstLetterInEachWord() {
        List<String> words = new ArrayList<>();
        words.add("main");
        words.add("class");
        String output = Naming.capitiliseFirstLetterInEachWord(words);
        assertEquals("MainClass", output);
    }
}
