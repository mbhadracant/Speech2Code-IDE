import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by mb on 07/12/2016.
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ui.fxml"));
        primaryStage.setTitle("Speech2Code");
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(this.getClass().getResource("/css/java_keywords.css").toExternalForm());
        deleteDir();
        File dir = new File("speech2code/src/main/java/temp");
        dir.mkdir();
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        deleteDir();
    }

    private void deleteDir() {
        File dir = new File("speech2code/src/main/java/temp");
        if(dir.listFiles() == null) return;
        for(File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
    }
}