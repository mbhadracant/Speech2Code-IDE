import edu.cmu.sphinx.linguist.g2p.G2PConverter;
import edu.cmu.sphinx.linguist.g2p.Path;
import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by mb on 22/01/2017.
 */


public class Playground {


    public static void main(String[] args) throws IOException {

        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("java.util"))));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("java.lang"))));

        classes.addAll(reflections.getSubTypesOf(Object.class));

        HashSet<String> allowedClasses = new HashSet();

        allowedClasses.add("Map");
        allowedClasses.add("TreeMap");
        allowedClasses.add("HashMap");
        allowedClasses.add("LinkedHashMap");
        allowedClasses.add("Set");
        allowedClasses.add("TreeSet");
        allowedClasses.add("HashSet");
        allowedClasses.add("LinkedHashSet");
        allowedClasses.add("List");
        allowedClasses.add("ArrayList");
        allowedClasses.add("LinkedList");
        allowedClasses.add("Arrays");
        allowedClasses.add("Long");
        allowedClasses.add("Integer");
        allowedClasses.add("Boolean");
        allowedClasses.add("String");
        allowedClasses.add("Character");
        allowedClasses.add("Float");
        allowedClasses.add("Double");

        File file = new File("speech2code/src/main/resources/sphinx/grammar/grammar.gram");
        File dict = new File("speech2code/src/main/resources/sphinx/dictionary/dictionary.dict");

        //File file = new File(ClassLoader.getSystemClassLoader().getResource("sphinx/grammar/grammar.gram").getFile());
        FileWriter writer = new FileWriter(file,true);
        FileWriter dictWriter = new FileWriter(dict, true);

        G2PConverter converter = new G2PConverter(ClassLoader.getSystemClassLoader().
                getResource("sphinx/g2p_model/model.fst.ser"));

        HashSet<String> wordSet = new HashSet();

        /*
        String result = "";

        // creates the file
        // creates a FileWriter Object

        // Writes the content to the file
        writer.append("public <util-methods> = (\n");
        HashSet<String> set = new HashSet();
        for(Class c : classes) {

            Method[] methods = c.getMethods();

            for(Method m : methods) {
                String mName = m.getName();
                String[] words = StringUtils.splitByCharacterTypeCamelCase(mName);
                for(String word : words) {
                    String low = word.toLowerCase();
                    String joined = String.join(" ", words);
                    if(set.contains(joined)) continue;
                    set.add(joined);
                    writer.append(joined + " |\n" );
                }
            }

        }

        */

        writer.append("\n");
        writer.append("public <classes> = (\n");

        for(Class c : classes) {
            if(allowedClasses.contains(c.getSimpleName()) && StringUtils.countMatches(c.getCanonicalName(), ".") == 2) {
                String className = c.getSimpleName();
                String[] words = StringUtils.splitByCharacterTypeCamelCase(className);
                System.out.println(c.getCanonicalName() + " - " + c.getSimpleName());

                String joined = String.join(" ", words).toLowerCase();
                writer.append(joined + " |\n" );
                for(String w : words) {
                    if(!wordSet.contains(w)) {
                        String word = w.toLowerCase();
                        wordSet.add(w);
                        ArrayList<Path> path = converter.phoneticize(word, 1);
                        try {
                            dictWriter.append(word + " " + path.get(0).toString().split("\\t")[1] + "\n");
                        } catch(ArrayIndexOutOfBoundsException e) {
                            dictWriter.append(word + " " + path.get(0).toString() +  "\n");
                        }
                    }
                }

            }
        }

        writer.write("test");
        writer.write(");\n");

        writer.append("\n");
        /*
        writer.append("public <methods> = (\n");
        HashSet<String> methodNames = new HashSet<>();
        for(Class c : classes) {
            if(allowedClasses.contains(c.getSimpleName()) && StringUtils.countMatches(c.getCanonicalName(), ".") == 2) {

                Method[] methods = c.getMethods();

                for(Method m : methods) {
                    String mName = m.getName();
                    String[] words = StringUtils.splitByCharacterTypeCamelCase(mName);
                    String joined = String.join(" ", words).toLowerCase();
                    if(!methodNames.contains(joined))
                        writer.append(joined + " |\n" );
                    methodNames.add(joined);
                    for(String w : words) {
                        if(!wordSet.contains(w)) {
                            String word = w.toLowerCase();
                            wordSet.add(w);
                            ArrayList<Path> path = converter.phoneticize(word, 1);
                            try {
                                dictWriter.append(word + " " + path.get(0).toString().split("\\t")[1] + "\n");
                            } catch(ArrayIndexOutOfBoundsException e) {
                                dictWriter.append(word + " " + path.get(0).toString() +  "\n");
                            }
                        }
                    }
                }




            }
        }

        writer.write("test");
        writer.write(");\n");


        */

        writer.close();
        dictWriter.close();



    }




}
