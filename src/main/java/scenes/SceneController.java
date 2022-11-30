package scenes;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import backend.Aereo;
import backend.Arena;
import backend.Jogo;
import backend.Tanque;
import backend.Terrestre;
import backend.abstracts.ACarta;
import backend.abstracts.ACartaFeitico;
import backend.abstracts.ACartaTropa;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

    Jogo jogo;

    Parent parent;

    AnchorPane arenaWrapper;

    ArrayList<ImageView> listaImagensMesa = new ArrayList<>();
    ArrayList<Text> listaTextos = new ArrayList<>();

    public SceneController(Jogo jogo) {
        this.jogo = jogo;
    }

    private void limpaImagesList() {
        System.out.println("limpei");
        for (ImageView img : listaImagensMesa) {
            arenaWrapper.getChildren().remove(img);
        }

        for (Text texto : listaTextos) {
            arenaWrapper.getChildren().remove(texto);
        }
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("initialScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void playButton(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("nomeJogadores.fxml"));
        root.setController(new SceneController(jogo));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();

    }

    public void enterGame(ActionEvent event) throws IOException {
        String namePlayer1Value = namePlayer1.getText();
        String namePlayer2Value = namePlayer2.getText();
        this.jogo = new Jogo(namePlayer1Value, namePlayer2Value);
        jogo.comecar();

        FXMLLoader root = new FXMLLoader(getClass().getResource("arena.fxml"));
        root.setController(new SceneController(jogo));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());

        stage.setScene(scene);
        stage.show();
    }

    public void jogo() {
        System.out.println("Rodada: " + jogo.getRodadas());
        if (jogo.acabouJogo()) {
            if(jogo.temGanhador()) {
                /* Para decidir quem ganhou é somada a vida de todas as cartas que permaneceram no campo do jogado
                 * quem tiver maior vida no somatorio das vidas das cartas, ganha
                 */
                /* TODO: Método de tela de ganhador, que irá mostrar o nome do vencedor */
                System.out.println("Ganhador: " + jogo.getGanhador());
            } else {
                /* TODO: Método de empate, onde irá mostra que deu empate */
                System.out.println("Empatee");
            }
        }

        int initialY = 320;
        int initialX = 350;
        /*TODO:
         * Mostrar a vida e o dano das cartas que estão no campo
          */
        ArrayList<ACartaTropa> mesaJogador1 = jogo.getCampoJogador1();
        for (int i = 0; i < mesaJogador1.size(); i++) {
            ImageView img = new ImageView(mesaJogador1.get(i).getDirImage());
            img.setX(initialX);
            img.setY(initialY);
            img.setFitHeight(96);
            img.setFitWidth(77);
            arenaWrapper.getChildren().add(img);
            listaImagensMesa.add(img);
            initialX = initialX + 90;
        }

        int initialY2 = 200;
        int initialX2 = 350;
        ArrayList<ACartaTropa> mesaJogador2 = jogo.getCampoJogador2();
        for (int i = 0; i < mesaJogador2.size(); i++) {
            ImageView img = new ImageView(mesaJogador2.get(i).getDirImage());
            img.setX(initialX2);
            img.setY(initialY2);
            img.setFitHeight(96);
            img.setFitWidth(77);

            arenaWrapper.getChildren().add(img);
            listaImagensMesa.add(img);
            initialX2 = initialX2 + 90;
        }
        int initialImgMaoY;
        int initialImgMaoX;
        int initialTextMaoX;
        int somar = 185;
        int cardVidaY;
        int cardDanoY;
        int playerTimeX;
        int playerTimeY;

        if (jogo.getRodadas() % 2 != 0) {
            initialImgMaoY = 445;
            initialImgMaoX = 277;
            initialTextMaoX = 280;
            cardVidaY = 552;
            cardDanoY = 569;
            playerTimeX = 40;
            playerTimeY = 565;
        } else {
            initialImgMaoY = 43;
            initialImgMaoX = 277;
            initialTextMaoX = 280;
            cardVidaY = 149;
            cardDanoY = 166;
            playerTimeX = 758;
            playerTimeY = 52;
        }
        ArrayList<ACarta> listaCartas = jogo.getMaoJogador();
        for (int i = 0; i < listaCartas.size(); i++) {
            ImageView img = new ImageView(listaCartas.get(i).getDirImage());
            img.setX(initialImgMaoX);
            img.setY(initialImgMaoY);
            img.setFitHeight(96);
            img.setFitWidth(77);

            
            initialImgMaoX = initialImgMaoX + somar;

            Text cardVida = new Text();
            cardVida.setX(initialTextMaoX);
            cardVida.setY(cardVidaY);

            Text cardDano = new Text();
            cardDano.setX(initialTextMaoX);
            cardDano.setY(cardDanoY);

            Text playerTime = new Text();
            playerTime.setX(playerTimeX);
            playerTime.setY(playerTimeY);

            initialTextMaoX = initialTextMaoX + somar;

            
            if (listaCartas.get(i) instanceof ACartaTropa) {
                ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                img.setImage(new Image(cartaTropa.getDirImage()));
                img.setOnMouseClicked(mouseevent -> {
                    if(jogo.jogarCarta(cartaTropa)) {
                        jogo.atacar();
                        jogo.proximoRound();
                    }
                    limpaImagesList();
                    this.jogo();
                });
                cardVida.setText("Vida: " + String.valueOf(cartaTropa.getVida()));
                cardDano.setText("Dano: " + String.valueOf(cartaTropa.getDano()));

            } else {
                ACartaFeitico cartaFeitico = (ACartaFeitico) listaCartas.get(i);
                img.setImage(new Image(cartaFeitico.getDirImage()));
                cardVida.setText("Efeito: " + cartaFeitico.getEfeito());
                cardDano.setText("Valor Efeito: " + String.valueOf(cartaFeitico.getValorEfeito()));
                img.setOnMouseClicked(mouseevent -> {
                    System.out.println("feitiço");
            
                    jogo.jogarCarta(cartaFeitico);
                    jogo.proximoRound();
                    limpaImagesList();
                    this.jogo();
                });
            }

            cardVida.setFill(Color.WHITE);
            cardDano.setFill(Color.WHITE);
            playerTime.setFill(Color.WHITE);
            playerTime.setFont(Font.font(24));
            playerTime.setText("Sua vez: " + jogo.getNomeJogador());

            listaTextos.add(cardVida);
            listaTextos.add(cardDano);
            listaTextos.add(playerTime);
            listaImagensMesa.add(img);
            
            arenaWrapper.getChildren().add(img);
            arenaWrapper.getChildren().add(cardVida);
            arenaWrapper.getChildren().add(cardDano);
            arenaWrapper.getChildren().add(playerTime);
        }

        stage.show();

    }

    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("arena.fxml"));
        root.setController(new SceneController(jogo));
        parent = (Parent) root.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        arenaWrapper = (AnchorPane) parent.lookup("#arenaWrapper");
        Button botaoCentral = (Button) parent.lookup("#botaoCentral");
        arenaWrapper.getChildren().remove(botaoCentral);
        this.jogo();
    }
}
