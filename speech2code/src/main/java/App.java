import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}