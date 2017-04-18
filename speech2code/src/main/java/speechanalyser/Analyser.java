package speechanalyser;

import codeoperations.CodeAction;
import codeoperations.Lookup;
import codeoperations.Statement;
import codeoperations.create.actions.CreateClassNameAction;
import codeoperations.create.actions.CreateMethodNameAction;
import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by mb on 11/01/2017.
 */
public class Analyser {

    private Trie t = new Trie();

    private HashSet<String> classNameSet = new HashSet();

    public Analyser() throws IOException {

        InputStream is = this.getClass().getResourceAsStream("/analyser/map.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        while((line = br.readLine()) != null) {
            String[] split = line.split("~");
            String key = split[0];
            String statement = split[1];

            t.addNode(key,statement);
        }

        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("java.util"))));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
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
        allowedClasses.add("Collections");

        for (Class c : classes) {
            if (allowedClasses.contains(c.getSimpleName()) && StringUtils.countMatches(c.getCanonicalName(), ".") == 2) {

                String[] words = StringUtils.splitByCharacterTypeCamelCase(c.getSimpleName());
                String joined = String.join(" ", words).toLowerCase();
                classNameSet.add(joined);
                CreateClassNameAction action = (CreateClassNameAction) Lookup.getAction(Statement.CLASS_NAME);
                action.classMap.put(joined, c);

                Method[] methods = c.getMethods();

                for (Method m : methods) {
                    String mName = m.getName();
                    words = StringUtils.splitByCharacterTypeCamelCase(mName);
                    String methodName = String.join(" ", words).toLowerCase();
                    CreateMethodNameAction methodNameAction = (CreateMethodNameAction) Lookup.getAction(Statement.METHOD_NAME);

                    if(methodNameAction.classMethodsMap.containsKey(joined)) {
                        methodNameAction.classMethodsMap.get(joined).add(methodName);
                    } else {
                        HashSet<String> methodNames = new HashSet();
                        methodNames.add(methodName);
                        methodNameAction.classMethodsMap.put(joined,methodNames);
                    }
                }
            }


        }


    }

    public CodeAction analyse(String input) {
        /*
        CodeAction action = null;
        String split[] = inputStr.split(" ");
            if(codeActionMap.get(inputStr) == null) {
                if(split[0].equals("create")) {
                    if(split[1].equals("static")) {
                        action = codeActionMap.get("create static method");
                    }
                    if(split[1].equals("method")) {
                        action = codeActionMap.get("create method");
                    }
                } else if(split[0].equals("call")) {
                    action = codeActionMap.get("method name");
                } else if(classNameSet.contains(inputStr)) {
                    action = codeActionMap.get("class name");
                } else {

                    if(CreateValueAction.expressionValueMap.get(String.join(" ",split)) != null) {
                        action = codeActionMap.get("value");
                    }
                }
                action.set(inputStr);
            } else {
                action = codeActionMap.get(inputStr);
            }


        if(action != null) action.init();
        return action;

        */

        Statement statement = t.getStatement(input);
        if(statement == null) return null;
        CodeAction action = Lookup.getAction(statement);
        action.set(input);
        return action;
    }
}
