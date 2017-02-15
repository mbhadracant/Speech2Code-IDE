package coderunner;

import coderunner.dynamicloader.DynamicClassLoader;
import javafx.scene.control.TextArea;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeRunner {

    public String className;
    public String code;
    static final String DIR = "speech2code/src/main/java/temp/";
    TextArea console;

    public CodeRunner() {

    }
    public CodeRunner(TextArea console) {
        this.console = console;
    }

    public void set(String code) {
        try {
            this.className = code.substring(code.indexOf(" class ") + 6, code.indexOf(" {")).trim();
        } catch(StringIndexOutOfBoundsException e) {

        }
        this.code = code;
    }

    public void createJavaFile() {

        try {
            PrintWriter pw = new PrintWriter(DIR + className + ".java");
            pw.close();
            FileWriter writer = new FileWriter(DIR + className + ".java", true);
            writer.write(code);
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public boolean compile() {
        File file = new File(DIR + className + ".java");
        File classFile = new File(DIR + className + ".class");
        classFile.delete();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        boolean compileSuccess = (compiler.run(null, null, null, file.getPath()) == 0);
        if(!compileSuccess) {
            file.delete();
        }
        return compileSuccess;
    }

    public void run() {


        Class<?> cls = null;
        try {
            cls = new DynamicClassLoader(DIR).loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method meth = null;
        try {
            if(cls == null) return;
            meth = cls.getDeclaredMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            return;
        }
        String[] params = null;
        try {
            if(meth == null) return;
            PrintStream old = null;
            ByteArrayOutputStream baos = null;
            if(console != null) {
                baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                old = System.out;
                System.setOut(ps);
            }

            meth.invoke(null, (Object) params);
            if(console != null) {
                System.out.flush();
                System.setOut(old);
                console.appendText(baos.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

