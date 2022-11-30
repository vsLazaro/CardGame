package scenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("initialScreen.fxml"));
        fxmlLoader.setController(new SceneController(null));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        tela.getStylesheets().add(getClass().getResource("homeStyles.css").toExternalForm());
        primaryStage.setTitle("jogo");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}
