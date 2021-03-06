package utilities;

import controllers.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by mb on 18/04/2017.
 */
public class HelpData {
    public final static ObservableList<Controller.HelpField> data =
            FXCollections.observableArrayList(
                    new Controller.HelpField("new file", "Opens a blank file on a new tab",
                            "", ""),
                    new Controller.HelpField("next file", "Opens the next file from the selected file",
                            "", ""),
                    new Controller.HelpField("previous file", "Opens the previous file from the selected file",
                            "", ""),
                    new Controller.HelpField("open file", "Opens a file from local disk",
                            "", ""),
                    new Controller.HelpField("save file", "Saves current file onto local disk",
                            "", ""),
                    new Controller.HelpField("undo", "Reverts the editor to the previous state of when a text was inserted",
                            "", ""),
                    new Controller.HelpField("run program", "Runs the java program on the current file.\n" +
                            "Only runs if there is a main method", "", ""),
                    new Controller.HelpField("close file", "Closes the current file",
                            "", ""),
                    new Controller.HelpField("next", "Moves the pointer by 1 to the right",
                            "int x = |5;", "int x = 5|;"),
                    new Controller.HelpField("back", "Moves the pointer by 1 to the left",
                            "int x = |5;", "int x =| 5;"),
                    new Controller.HelpField("back space", "removes the character to the left of the pointer",
                            "int x = 5|;", "int x = |;"),
                    new Controller.HelpField("next line", "Moves the pointer to the next line",
                            "public static void main(String[] args {\n\t|\n}",
                            "public static void main(String[] args {\n\t\n}|"),
                    new Controller.HelpField("previous line", "Moves the pointer to the previous line",
                            "public static void main(String[] args {\n\t|\n}",
                            "public static void main(String[] args {|\n\t\n}"),
                    new Controller.HelpField("new line", "Enters a new line at the current position",
                            "int x = 5;|", "int x = 5;\n|"),
                    new Controller.HelpField("create class", "Creates a template for creating a class. " +
                            "\nThe pointer is set to after the action to enter the class name",
                            "|", "public class | {\n\n}"),
                    new Controller.HelpField("create main method", "Creates a main method.",
                            "|", "public static void main(String[] args | {\n\t|\n}"),
                    new Controller.HelpField("for loop", "Creates a template of a for loop",
                            "|", "for(|){\n\n}"),
                    new Controller.HelpField("while loop", "Creates a template of a while loop",
                            "|", "while(|){\n\n}"),
                    new Controller.HelpField("call [method name] ~ call to string", "Creates code for a method call.\n " +
                            "The methods it can call are the methods on the following classes in the Java library:\n" +
                            "Map, HashMap, Character, HashSet, LinkedHashSet, Boolean, LinkedHashMap\n" +
                            "Double, Float, String, Integer, TreeSet, Set, LinkedList, TreeMap\n" +
                            "ArrayList, Long, Arrays, List, Collections",
                            "Integer|", "Integer.toString(|)"),
                    new Controller.HelpField("print", "outputs a print statement",
                            "|", "System.out.print(|);"),
                    new Controller.HelpField("print line", "outputs a print line statement",
                            "|", "System.out.println(|);"),
                    new Controller.HelpField("create string", "outputs a double quotation for a string value",
                            "String s = |", "String s = \"|\""),
                    new Controller.HelpField("create character", "outputs a single quotation for a char value",
                            "char c = |", "char c = '|'"),
                    new Controller.HelpField("zero", "outputs a '0'",
                            "|", "0|"),
                    new Controller.HelpField("one", "outputs '1'",
                            "|", "2|"),
                    new Controller.HelpField("two", "outputs '2'",
                            "|", "3|"),
                    new Controller.HelpField("three", "outputs '3'",
                            "|", "3|"),
                    new Controller.HelpField("four", "outputs '4'",
                            "|", "4|"),
                    new Controller.HelpField("five", "outputs '5'",
                            "|", "5|"),
                    new Controller.HelpField("six", "outputs '6'",
                            "|", "6|"),
                    new Controller.HelpField("seven", "outputs'7'",
                            "|", "7|"),
                    new Controller.HelpField("eight", "outputs '8'",
                            "|", "8|"),
                    new Controller.HelpField("nine", "outputs '9'",
                            "|", "9|"),
                    new Controller.HelpField("space", "outputs a space (' ')",
                            "(|)", "( |)"),
                    new Controller.HelpField("true", "outputs 'true'",
                            "|", "true|"),
                    new Controller.HelpField("false", "outputs 'false'",
                            "|", "false|"),
                    new Controller.HelpField("and", "outputs '&&'",
                            "|", "false|"),
                    new Controller.HelpField("or", "outputs '||'",
                            "", "||"),
                    new Controller.HelpField("not", "outputs '!'",
                            "|false", "!|false"),
                    new Controller.HelpField("equals equals", "outputs '=='",
                            "x |", "x ==|"),
                    new Controller.HelpField("equals", "outputs '='",
                            "x |", "x =|"),
                    new Controller.HelpField("not equals", "outputs '!='",
                            "x |", "x !=|"),
                    new Controller.HelpField("more than", "outputs '>'",
                            "x|", "x>|"),
                    new Controller.HelpField("less than", "outputs '<'",
                            "x|", "x<|"),
                    new Controller.HelpField("more or equals", "outputs '>='",
                            "x|", "x >=|"),
                    new Controller.HelpField("less or equals", "outputs '<='",
                            "x |", "x <=|"),
                    new Controller.HelpField("plus", "outputs '+'",
                            "5 | 5", "5 +| 5"),
                    new Controller.HelpField("plus plus", "outputs '++'",
                            "x|", "x++|"),
                    new Controller.HelpField("minus", "outputs '-'",
                            "5 | 5", "5 -|5"),
                    new Controller.HelpField("minus minus", "outputs '--'",
                            "x|", "x--|"),
                    new Controller.HelpField("primitive integer", "outputs 'int'",
                            "|", "int |"),
                    new Controller.HelpField("primitive boolean", "outputs 'boolean'",
                            "|", "boolean |"),
                    new Controller.HelpField("primitive character", "outputs 'char'",
                            "|", "char |"),
                    new Controller.HelpField("primitive double", "outputs 'double'",
                            "|", "double |"),
                    new Controller.HelpField("new", "outputs 'new'",
                            "ArrayList l = |", "ArrayList l = new|"),
                    new Controller.HelpField("dot", "outputs '.'",
                            "arr", "arr.|"),
                    new Controller.HelpField("semi colon", "outputs ';'",
                            "int x = 5|", "int x = 5;|"),
                    new Controller.HelpField("colon", "outputs ':'",
                            "for(int x |)", "for(int x :|)"),
                    new Controller.HelpField("comma mark", "outputs ','",
                            "(str1|)", "(str1,|)"),
                    new Controller.HelpField("length", "outputs 'length'",
                            "array.|", "array.length|"),
                    new Controller.HelpField("ant", "outputs 'a'",
                            "|", "a|"),
                    new Controller.HelpField("bravo", "outputs 'b'",
                            "|", "b|"),
                    new Controller.HelpField("charlie", "outputs 'c'",
                            "|", "c|"),
                    new Controller.HelpField("delta", "outputs 'd'",
                            "|", "d|"),
                    new Controller.HelpField("echo", "outputs 'e'",
                            "|", "e|"),
                    new Controller.HelpField("fox", "outputs 'f'",
                            "|", "f|"),
                    new Controller.HelpField("golf", "outputs 'g'",
                            "|", "g|"),
                    new Controller.HelpField("hotel", "outputs 'h'",
                            "|", "h|"),
                    new Controller.HelpField("insect", "outputs 'i'",
                            "|", "i|"),
                    new Controller.HelpField("juliet", "outputs 'j'",
                            "|", "j|"),
                    new Controller.HelpField("kilo", "outputs 'k'",
                            "|", "k|"),
                    new Controller.HelpField("lima", "outputs 'l'",
                            "|", "l|"),
                    new Controller.HelpField("mike", "outputs 'm'",
                            "|", "m|"),
                    new Controller.HelpField("november", "outputs 'n'",
                            "|", "n|"),
                    new Controller.HelpField("oscar", "outputs 'o'",
                            "|", "o|"),
                    new Controller.HelpField("papa", "outputs 'p'",
                            "|", "p|"),
                    new Controller.HelpField("quail", "outputs 'q'",
                            "|", "q|"),
                    new Controller.HelpField("romeo", "outputs 'r'",
                            "|", "r|"),
                    new Controller.HelpField("seal", "outputs 's'",
                            "|", "s|"),
                    new Controller.HelpField("tango", "outputs 't'",
                            "|", "t|"),
                    new Controller.HelpField("uniform", "outputs 'u'",
                            "|", "u|"),
                    new Controller.HelpField("victor", "outputs 'v'",
                            "|", "v|"),
                    new Controller.HelpField("whiskey", "outputs 'w'",
                            "|", "w|"),
                    new Controller.HelpField("x-ray", "outputs 'x'",
                            "|", "x|"),
                    new Controller.HelpField("yankee", "outputs 'y'",
                            "|", "y|"),
                    new Controller.HelpField("zulu", "outputs 'z'",
                            "|", "z|"),
                    new Controller.HelpField("capital [alphabet phrase] ~ capital ant", "outputs the capital of a alphabet phrase",
                            "|", "A|"),
                    new Controller.HelpField("curly brackets", "outputs '{}'",
                            "|", "{|}"),
                    new Controller.HelpField("round brackets", "outputs '()'",
                            "|", "(|)"),
                    new Controller.HelpField("square brackets", "outputs '[]'",
                            "|", "[|]"),
                    new Controller.HelpField("angle brackets", "outputs '<>'",
                            "|", "<|>")
            );
}
