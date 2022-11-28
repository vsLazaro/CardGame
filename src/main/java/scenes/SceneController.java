package scenes;

import java.io.IOException;

import backend.Aereo;
import backend.Tanque;
import backend.Terrestre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @FXML
    private ImageView card3;

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
        ImageView card1 = (ImageView) root.lookup("#card1");
        Text vidaCard1 = (Text) root.lookup("#vidaCard1");
        Text danoCard1 = (Text) root.lookup("#danoCard1");
        Text vidaCard2 = (Text) root.lookup("#vidaCard2");
        Text danoCard2 = (Text) root.lookup("#danoCard2");
        Text vidaCard3 = (Text) root.lookup("#vidaCard3");
        Text danoCard3 = (Text) root.lookup("#danoCard3");

        ImageView card2 = (ImageView) root.lookup("#card2");
        ImageView card3 = (ImageView) root.lookup("#card3");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
        String dirImg1 = "@file:/../assets/bebe-dragao.jpg";
        String dirImg2 = "@file:/../assets/esqueleto-gigante.jpg";
        String dirImg3 = "@file:/../assets/mosqueteira.jpg";
        Aereo cartaAereo = new Aereo("bebê dragão", "aereo", dirImg1, 100, 10);
        Tanque cartaTanque = new Tanque("Esqueleto", "terrestre", dirImg2, 100, 10);
        Terrestre cartaTerrestre = new Terrestre("mosqueteira", "terrestre", dirImg3, 100, 10);

        card1.setImage(new Image(cartaAereo.getDirImage()));
        vidaCard1.setText("Vida: " + String.valueOf(cartaAereo.getVida()));
        danoCard1.setText("Dano: X" /* + String.valueOf(cartaAereo.get()) */);
        card2.setImage(new Image(cartaTanque.getDirImage()));
        vidaCard2.setText("Vida: " + String.valueOf(cartaAereo.getVida()));
        danoCard2.setText("Dano: X" /* + String.valueOf(cartaAereo.get()) */);
        card3.setImage(new Image(cartaTerrestre.getDirImage()));
        vidaCard3.setText("Vida: " + String.valueOf(cartaAereo.getVida()));
        danoCard3.setText("Dano: X" /* + String.valueOf(cartaAereo.get()) */);

    }

    public void selectCard1(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println(card1);
        Parent root = FXMLLoader.load(getClass().getResource("switchTurn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void selectCard2(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("card2");
        Parent root = FXMLLoader.load(getClass().getResource("switchTurn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void selectCard3(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("card3");
        Parent root = FXMLLoader.load(getClass().getResource("switchTurn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTurn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("seeHand.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /*
     * To Do's
     * - Lógica de troca de turnos e gerenciar renderização de acordo
     * - Popular "mão" com cartas do backned
     * -
     * 
     */
}
