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
        FXMLLoader root = new FXMLLoader(getClass().getResource("initialScreen.fxml"));
        root.setController(new SceneController(null));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
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

    public void jogo() throws IOException {
        System.out.println("Rodada: " + jogo.getRodadas());
        if (jogo.acabouJogo()) {
            if (jogo.temGanhador()) {
                /*
                 * Para decidir quem ganhou é somada a vida de todas as cartas que permaneceram
                 * no campo do jogado
                 * quem tiver maior vida no somatorio das vidas das cartas, ganha
                 */
                

                FXMLLoader root = new FXMLLoader(getClass().getResource("nomeGanhador.fxml"));
                root.setController(new SceneController(jogo));
                parent = (Parent) root.load();
                Text nomeGanhador = (Text) parent.lookup("#nomeGanhador");
                nomeGanhador.setText(jogo.getGanhador()+" ganhou!");
                scene = new Scene(parent);
                stage.setScene(scene);
            } else {
                System.out.println("Empatee");
                FXMLLoader root = new FXMLLoader(getClass().getResource("empate.fxml"));
                root.setController(new SceneController(jogo));
                parent = (Parent) root.load();
                scene = new Scene(parent);
                stage.setScene(scene);
            }
        }

        int initialY = 320;
        int initialX = 350;
        /*
         * TODO:
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

        int initialImgMaoY1 = 445;
        int initialImgMaoX1 = 277;
        int initialTextMaoX1 = 280;
        int cardVidaY1 = 552;
        int cardDanoY1 = 569;
        int playerTimeX1 = 40;
        int playerTimeY1 = 565;

        int somar = 185;

        int initialImgMaoY2 = 43;
        int initialImgMaoX2 = 277;
        int initialTextMaoX2 = 280;
        int cardVidaY2 = 149;
        int cardDanoY2 = 166;
        int playerTimeX2 = 758;
        int playerTimeY2 = 52;
        if (jogo.getRodadas() % 2 != 0) {
            ArrayList<ACarta> cartasBrancas = jogo.getMaoJogador2();
            for (int i = 0; i < cartasBrancas.size(); i++) {
                ImageView img = new ImageView("/scenes/assets/carta-virada.jpg");
                img.setX(initialImgMaoX2);
                img.setY(initialImgMaoY2);
                img.setFitHeight(96);
                img.setFitWidth(77);
                initialImgMaoX2 = initialImgMaoX2 + somar;
                listaImagensMesa.add(img);
                arenaWrapper.getChildren().add(img);
            }

            ArrayList<ACarta> listaCartas = jogo.getMaoJogador();
            for (int i = 0; i < listaCartas.size(); i++) {
                ImageView img = new ImageView(listaCartas.get(i).getDirImage());
                img.setX(initialImgMaoX1);
                img.setY(initialImgMaoY1);
                img.setFitHeight(96);
                img.setFitWidth(77);

                initialImgMaoX1 = initialImgMaoX1 + somar;

                Text cardVida = new Text();
                cardVida.setX(initialTextMaoX1);
                cardVida.setY(cardVidaY1);

                Text cardDano = new Text();
                cardDano.setX(initialTextMaoX1);
                cardDano.setY(cardDanoY1);

                Text playerTime = new Text();
                playerTime.setX(playerTimeX1);
                playerTime.setY(playerTimeY1);

                initialTextMaoX1 = initialTextMaoX1 + somar;

                if (listaCartas.get(i) instanceof ACartaTropa) {
                    ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                    img.setImage(new Image(cartaTropa.getDirImage()));
                    img.setOnMouseClicked(mouseevent -> {
                        if (jogo.jogarCarta(cartaTropa)) {
                            jogo.atacar();
                            jogo.proximoRound();
                        }
                        limpaImagesList();
                        try {
                            this.jogo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
                        try {
                            this.jogo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
        } else {
            ArrayList<ACarta> cartasBrancas = jogo.getMaoJogador1();
            for (int i = 0; i < cartasBrancas.size(); i++) {
                ImageView img = new ImageView("/scenes/assets/carta-virada.jpg");
                img.setX(initialImgMaoX1);
                img.setY(initialImgMaoY1);
                img.setFitHeight(96);
                img.setFitWidth(77);
                initialImgMaoX1 = initialImgMaoX1 + somar;
                listaImagensMesa.add(img);
                arenaWrapper.getChildren().add(img);
            }

            ArrayList<ACarta> listaCartas = jogo.getMaoJogador();
            for (int i = 0; i < listaCartas.size(); i++) {
                ImageView img = new ImageView(listaCartas.get(i).getDirImage());
                img.setX(initialImgMaoX2);
                img.setY(initialImgMaoY2);
                img.setFitHeight(96);
                img.setFitWidth(77);

                initialImgMaoX2 = initialImgMaoX2 + somar;

                Text cardVida = new Text();
                cardVida.setX(initialTextMaoX2);
                cardVida.setY(cardVidaY2);

                Text cardDano = new Text();
                cardDano.setX(initialTextMaoX2);
                cardDano.setY(cardDanoY2);

                Text playerTime = new Text();
                playerTime.setX(playerTimeX2);
                playerTime.setY(playerTimeY2);

                initialTextMaoX2 = initialTextMaoX2 + somar;

                if (listaCartas.get(i) instanceof ACartaTropa) {
                    ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                    img.setImage(new Image(cartaTropa.getDirImage()));
                    img.setOnMouseClicked(mouseevent -> {
                        if (jogo.jogarCarta(cartaTropa)) {
                            jogo.atacar();
                            jogo.proximoRound();
                        }
                        limpaImagesList();
                        try {
                            this.jogo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
                        try {
                            this.jogo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
