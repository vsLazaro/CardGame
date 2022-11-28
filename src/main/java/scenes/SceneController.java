package scenes;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import backend.Aereo;
import backend.Arena;
import backend.Tanque;
import backend.Terrestre;
import backend.abstracts.ACarta;
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

    int playerId = 0;
    Arena arena = new Arena("test1", "test2");

    private void setCurrentPlayer(int rodada) {
        playerId = (rodada % 2) + 1;
    }

    private int getCurrentPlayer() {
        return playerId;
    }

    private void createArena(String namePlayer1, String namePlayer2) {
        arena = new Arena(namePlayer1, namePlayer2);
    }

    private Arena getArena() {
        return arena;
    }

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
        String namePlayer1Value = namePlayer1.getText();
        String namePlayer2Value = namePlayer2.getText();
        createArena(namePlayer1Value, namePlayer2Value);

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

        ImageView card2 = (ImageView) root.lookup("#card2");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
      
        int numRodadas = 0;
        if (numRodadas < 10) {
            setCurrentPlayer(numRodadas);
            
            Arena arena = getArena();
            ArrayList<ACarta> mao = arena.cartasNaMaoJogadores(getCurrentPlayer());

            for(int i = 0; i<mao.size();i++){
                switch (i) {
                    case 0:
                        card1.setImage(new Image(mao.get(i).getDirImage()));
                        vidaCard1.setText("Vida: X" /* + String.valueOf(mao.get(i).getVida() )*/);
                        danoCard1.setText("Dano: X" /* + String.valueOf(cartaAereo.get()) */);
                        break;
                
                    case 1:
                        card2.setImage(new Image(mao.get(i).getDirImage()));
                        vidaCard2.setText("Vida: X" /* + String.valueOf(mao.get(i).getVida()) */);
                        danoCard2.setText("Dano: X" /* + String.valueOf(mao.get(i)) */);
                        break;
    
                    default:
                        break;
                }
            }


            numRodadas++;
        } else {
            System.out.println("jogo acabou");
        }

    }

    // receber array de cartas na mesa
    // se array length = 1 renderizar swithTurn e substituir img
    // se array length = 2 renderizar outra tela e substituir imgs
    // se array length = 3 renderizar outra tela e substituir imgs
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
