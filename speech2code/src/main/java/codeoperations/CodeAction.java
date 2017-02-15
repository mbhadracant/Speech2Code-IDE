package codeoperations;

import fxelements.JavaCodeArea;

/**
 * Created by mb on 11/01/2017.
 */
public abstract class CodeAction {
    public abstract void init();
    public abstract void set(String parameter);
    public abstract void execute(JavaCodeArea editor);
}
