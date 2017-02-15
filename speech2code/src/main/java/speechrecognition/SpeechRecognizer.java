package speechrecognition;

import codeoperations.CodeAction;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import fxelements.JavaCodeArea;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import speechanalyser.Analyser;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by mb on 09/12/2016.
 */
public class SpeechRecognizer implements Runnable{
    private Configuration configuration;
    private LiveSpeechRecognizer recognizer;
    private SpeechResult result;
    private JavaCodeArea editor;
    private TextField commandText;
    private Analyser analyser;
    private CheckBox inputCheckBox;

    public SpeechRecognizer(JavaCodeArea editor, TextField commandText, TextField inputText, Button inputButton, CheckBox inputCheckBox) {
        analyser = new Analyser();
        this.editor = editor;
        this.commandText = commandText;
        this.inputCheckBox = inputCheckBox;
        inputButton.setOnAction(e -> {
            CodeAction action = analyser.analyse(inputText.getText());
            if(action != null) action.execute(editor);
        });

        inputText.setDisable(true);
        inputButton.setDisable(true);

        inputCheckBox.setOnAction(e -> {
            if(!inputCheckBox.isSelected()) {
                inputText.setDisable(true);
                inputButton.setDisable(true);
            } else {
                inputText.setDisable(false);
                inputButton.setDisable(false);
            }
        });

        configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/sphinx/dictionary/words.dict");

        configuration.setGrammarPath("resource:/sphinx/grammar/");
        configuration.setGrammarName("commands");
        configuration.setUseGrammar(true);

        try {
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch(IOException e) {
            throw new IOError(new Throwable("Configuration file not found!"));
        }

        recognizer.startRecognition(true);
    }

    @Override
    public void run() {
        while ((result = recognizer.getResult()) != null && !inputCheckBox.isSelected()) {
            commandText.setText(result.getHypothesis());
            CodeAction action = analyser.analyse(result.getHypothesis());
            if(action != null) action.execute(editor);
        }
    }
}
