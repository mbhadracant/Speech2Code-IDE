package speech_recognition;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import fxelements.JavaCodeArea;
import javafx.application.Platform;

import java.io.IOException;

/**
 * Created by mb on 09/12/2016.
 */
public class SpeechRecognizer implements Runnable{
    private Configuration configuration;
    private LiveSpeechRecognizer recognizer;
    private SpeechResult result;
    private JavaCodeArea editor;

    public SpeechRecognizer(JavaCodeArea editor) throws IOException {
        this.editor = editor;
        configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");


        configuration.setGrammarPath("resource:/sphinx/grammar/");
        configuration.setGrammarName("commands");
        configuration.setUseGrammar(true);

        recognizer = new LiveSpeechRecognizer(configuration);

        recognizer.startRecognition(true);
    }


    @Override
    public void run() {
        while ((result = recognizer.getResult()) != null) {
            System.out.println(result.getHypothesis());
            Platform.runLater(() -> editor.insertText(editor.getCaretPosition(), result.getHypothesis()));
        }

    }
}
