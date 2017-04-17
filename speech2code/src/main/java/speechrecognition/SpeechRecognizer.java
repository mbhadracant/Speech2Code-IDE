package speechrecognition;

import codeoperations.CodeAction;
import coderunner.CodeRunner;
import controllers.Controller;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import fxelements.JavaCodeArea;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import speechanalyser.Analyser;

import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Controller.UI, Node> uiMap;

    public SpeechRecognizer(Map<Controller.UI, Node> uiMap) throws IOException {
        analyser = new Analyser();
        this.uiMap = uiMap;
        this.commandText = (TextField) uiMap.get(Controller.UI.SPEECH);

        configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/sphinx/dictionary/dictionary.dict");

        configuration.setGrammarPath("resource:/sphinx/grammar/");
        configuration.setGrammarName("grammar");
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
        while ((result = recognizer.getResult()) != null) {
            commandText.setText(result.getHypothesis());
            try {
                CodeAction action = analyser.analyse(result.getHypothesis());
                if (action != null) action.execute(uiMap);
            } catch (NullPointerException e) {
                System.out.println("Error: Unknown Phrase!");
            }
        }
    }

    public void setEditor(JavaCodeArea editor) {
        this.editor = editor;
    }
}
