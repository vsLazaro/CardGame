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

    private void limpaImagesList() {
        for (ImageView img : listaImagensMesa) {
            arenaWrapper.getChildren().remove(img);
        }

        for (Text texto : listaTextos) {
            arenaWrapper.getChildren().remove(texto);
        }
    }

    private void setCurrentPlayer(int rodada) {
        playerId = (rodada % 2) + 1;
    }

    private int getCurrentPlayer() {
        return playerId;
    }

    public SceneController(Jogo jogo) {
        this.jogo = jogo;
    }

    private Jogo getJogo() {
        return jogo;
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

    public void atualizarMesa(AnchorPane arenaWrapper, ArrayList<ACartaTropa> lista) {
        System.out.println("chMOU ATULIZA");

    }

    public void jogo() {

        if (jogo.acabouJogo()) {

        }

        int initialY = 320;
        int initialX = 350;

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
        
        if (jogo.getRodadas() % 2 != 0) {
            initialImgMaoY = 445;
            initialImgMaoX = 277;
            initialTextMaoX = 280;
            cardVidaY = 552;
            cardDanoY = 569;
        } else {
            initialImgMaoY = 43;
            initialImgMaoX = 277;
            initialTextMaoX = 280;
            cardVidaY = 149;
            cardDanoY = 166;
        }

        ArrayList<ACarta> listaCartas = jogo.getMaoJogador();
        for (int i = 0; i < listaCartas.size(); i++) {
            ImageView img = new ImageView(listaCartas.get(i).getDirImage());
            img.setX(initialImgMaoX);
            img.setY(initialImgMaoY);
            img.setFitHeight(96);
            img.setFitWidth(77);

            arenaWrapper.getChildren().add(img);
            listaImagensMesa.add(img);
            initialImgMaoX = initialImgMaoX + somar;

            Text cardVida = new Text();
            cardVida.setX(initialTextMaoX);
            cardVida.setY(cardVidaY);

            Text cardDano = new Text();
            cardDano.setX(initialTextMaoX);
            cardDano.setY(cardDanoY);

            initialTextMaoX = initialTextMaoX + somar;

            if (listaCartas.get(i) instanceof ACartaTropa) {
                ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                img.setImage(new Image(cartaTropa.getDirImage()));
                img.setOnMouseClicked(mouseevent -> {
                    jogo.jogarCarta(cartaTropa);
                    jogo.atacar();
                    jogo.proximoRound();
                    System.out.println();
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
                    jogo.jogarCarta(cartaFeitico);
                });
            }

            cardVida.setFill(Color.WHITE);
            cardDano.setFill(Color.WHITE);
            listaTextos.add(cardVida);
            listaTextos.add(cardDano);
            arenaWrapper.getChildren().add(cardVida);
            arenaWrapper.getChildren().add(cardDano);
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
        botaoCentral.setOnAction(eventButton -> {
            this.jogo();
        });

    }

    /*
     * FXMLLoader root = new FXMLLoader(getClass().getResource("arena.fxml"));
     * root.setController(new SceneController(jogo));
     * stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     * scene = new Scene(root.load());
     * stage.setScene(scene);
     * stage.show();
     */

    public void chooseCard(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("seeHand.fxml"));
        root.setController(new SceneController(jogo));
        Parent parent = (Parent) root.load();
        ImageView card1 = (ImageView) parent.lookup("#card1");
        Text vidaCard1 = (Text) parent.lookup("#vidaCard1");
        Text danoCard1 = (Text) parent.lookup("#danoCard1");
        ImageView card2 = (ImageView) parent.lookup("#card2");
        Text vidaCard2 = (Text) parent.lookup("#vidaCard2");
        Text danoCard2 = (Text) parent.lookup("#danoCard2");

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();
        System.out.println(this.jogo);
        int numRodadas = 0;

    }

    public void selectCard1(javafx.scene.input.MouseEvent event) throws IOException {
        /*
         * System.out.println(this.arena);
         * System.out.println("Carta: " + getCarta1());
         * Arena arena = getArena();
         * ArrayList<ACarta> mao = arena.cartasNaMaoJogadores(1);
         * if(getCarta1() instanceof ACartaTropa) {
         * boolean retorno = arena.inserirCarta(1, (ACartaTropa)getCarta1());
         * } else {
         * //todo
         * }
         * ArrayList<ACartaTropa> mesa = arena.cartasNoCampoJogadores(1);
         */
        /*
         * System.out.println(mesa);
         * System.out.println("mao: " + mao);
         * System.out.println(retorno);
         */
        Parent root = FXMLLoader.load(getClass().getResource("attack2.fxml"));
        AnchorPane arenaWrapper = (AnchorPane) root.lookup("#arenaWrapper");
        // arenaWrapper.getChildren().add(img);
        int initialY = 250;
        int initialX = 200;
        /*
         * for (int i = 0; i < mesa.size(); i++) {
         * ImageView img = new ImageView(mesa.get(i).getDirImage());
         * img.setX(initialX);
         * img.setY(initialY);
         * img.setFitHeight(96);
         * img.setFitWidth(77);
         * arenaWrapper.getChildren().add(img);
         * initialX = initialX + 90;
         * }
         */

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void selectCard2(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println(card1);
        Parent root = FXMLLoader.load(getClass().getResource("switchTurn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * public void selectCard2(javafx.scene.input.MouseEvent event) throws
     * IOException {
     * System.out.println("card2");
     * Parent root = FXMLLoader.load(getClass().getResource("switchTurn.fxml"));
     * stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     * scene = new Scene(root);
     * stage.setScene(scene);
     * stage.show();
     * }
     * 
     * public void selectCard3(javafx.scene.input.MouseEvent event) throws
     * IOException {
     * System.out.println("card3");
     * Parent root = FXMLLoader.load(getClass().getResource("switchTurn.fxml"));
     * stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     * scene = new Scene(root);
     * stage.setScene(scene);
     * stage.show();
     * }
     */
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
