package scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.awt.event.MouseEvent;
import javafx.event.EventHandler;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Button enterButton;
    @FXML
    private TextField namePlayer2;

    @FXML
    private TextField namePlayer1;

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card2;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("initialScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("nomeJogadores.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void enterGame(ActionEvent event) throws IOException {
        String namePlayer2Value = namePlayer2.getText();
        String namePlayer1Value = namePlayer1.getText();
        Parent root = FXMLLoader.load(getClass().getResource("player1Turn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void chooseCard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("seeHand.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void selectCard1(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("card1");
    }

    public void selectCard2(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("card2");
    }
}
