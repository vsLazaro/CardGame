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
        System.out.println("Passei");

        FXMLLoader root = new FXMLLoader(getClass().getResource("arena.fxml"));
        root.setController(new SceneController(jogo));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());

        stage.setScene(scene);
        stage.show();
    }

    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("arena.fxml"));
        root.setController(new SceneController(jogo));
        Parent parent = (Parent) root.load();
        System.out.println("pegar baralho");

        ArrayList<ACarta> listaCartas = jogo.getMaoJogador();
        ImageView card1 = (ImageView) parent.lookup("#play1Card1");
        ImageView card2 = (ImageView) parent.lookup("#play1Card2");
        ImageView card3 = (ImageView) parent.lookup("#play1Card3");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();
        Text card1Vida = new Text("Vida: ");
        Text card2Vida = new Text("Vida: ");
        Text card3Vida = new Text("Vida: ");
        Text card1Dano = new Text("Dano: ");
        Text card2Dano = new Text("Dano: ");
        Text card3Dano = new Text("Dano: ");

        for (int i = 0; i < listaCartas.size(); i++) {
            switch (i) {
                case 0:
                    if (listaCartas.get(i) instanceof ACartaTropa) {
                        ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                        System.out.println(cartaTropa);
                        card1.setImage(new Image(cartaTropa.getDirImage()));
                        card1Vida.setText("Vida: " + String.valueOf(cartaTropa.getVida()));
                        card1Dano.setText("Dano: " + String.valueOf(cartaTropa.getDano()));
                    } else {
                        ACartaFeitico cartaFeitico = (ACartaFeitico) listaCartas.get(i);
                        card1.setImage(new Image(cartaFeitico.getDirImage()));
                        card1Vida.setText("Efeito: " + cartaFeitico.getEfeito());
                        card1Dano.setText("Valor Efeito: " + String.valueOf(cartaFeitico.getValorEfeito()));
                    }
                    break;
                case 1:
                    if (listaCartas.get(i) instanceof ACartaTropa) {
                        ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                        System.out.println(cartaTropa);
                        card2.setImage(new Image(cartaTropa.getDirImage()));
                        card2Vida.setText("Vida: X" + String.valueOf(cartaTropa.getVida()));
                        card2Dano.setText("Dano: X" + String.valueOf(cartaTropa.getDano()));
                    } else {
                        ACartaFeitico cartaFeitico = (ACartaFeitico) listaCartas.get(i);
                        card2.setImage(new Image(cartaFeitico.getDirImage()));
                        card2Vida.setText("Efeito: " + cartaFeitico.getEfeito());
                        card2Dano.setText("Valor Efeito: " + String.valueOf(cartaFeitico.getValorEfeito()));
                    }
                    break;
                case 2:
                    if (listaCartas.get(i) instanceof ACartaTropa) {
                        ACartaTropa cartaTropa = (ACartaTropa) listaCartas.get(i);
                        System.out.println(cartaTropa);
                        card3.setImage(new Image(cartaTropa.getDirImage()));
                        card3Vida.setText("Vida: X" + String.valueOf(cartaTropa.getVida()));
                        card3Dano.setText("Dano: X" + String.valueOf(cartaTropa.getDano()));
                    } else {
                        ACartaFeitico cartaFeitico = (ACartaFeitico) listaCartas.get(i);
                        card3.setImage(new Image(cartaFeitico.getDirImage()));
                        card3Vida.setText("Efeito: " + cartaFeitico.getEfeito());
                        card3Dano.setText("Valor Efeito: " + String.valueOf(cartaFeitico.getValorEfeito()));
                    }
                    break;
                default:
                    break;
            }
        }

        AnchorPane arenaWrapper = (AnchorPane) parent.lookup("#arenaWrapper");
        arenaWrapper.getChildren().add(card1Vida);
        arenaWrapper.getChildren().add(card2Vida);
        arenaWrapper.getChildren().add(card3Vida);
        arenaWrapper.getChildren().add(card1Dano);
        arenaWrapper.getChildren().add(card2Dano);
        arenaWrapper.getChildren().add(card3Dano);
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
        /*
         * if (numRodadas < 10) {
         * setCurrentPlayer(numRodadas);
         * 
         * ArrayList<ACarta> mao = jogo.cartasNaMaoJogadores(getCurrentPlayer());
         * 
         * for (int i = 0; i < mao.size(); i++) {
         * switch (i) {
         * case 0:
         * if (mao.get(i) instanceof ACartaTropa) {
         * ACartaTropa cartaTropa = (ACartaTropa) mao.get(i);
         * System.out.println(cartaTropa);
         * card1.setImage(new Image(cartaTropa.getDirImage()));
         * vidaCard1.setText("Vida: X" + String.valueOf(cartaTropa.getVida()));
         * danoCard1.setText("Dano: X" /* + String.valueOf(cartaAereo.get()));
         * setCarta1(cartaTropa);
         * System.out.println(getCarta1());
         * } else {
         * ACartaFeitico cartaFeitico = (ACartaFeitico) mao.get(i);
         * card1.setImage(new Image(cartaFeitico.getDirImage()));
         * vidaCard1.setText("Efeito: " + cartaFeitico.getEfeito());
         * danoCard1.setText("Valor Efeito: " +
         * String.valueOf(cartaFeitico.getValorEfeito()));
         * setCarta1(cartaFeitico);
         * }
         * break;
         * 
         * case 1:
         * if (mao.get(i) instanceof ACartaTropa) {
         * ACartaTropa cartaTropa = (ACartaTropa) mao.get(i);
         * card2.setImage(new Image(cartaTropa.getDirImage()));
         * vidaCard2.setText("Vida: X" + String.valueOf(cartaTropa.getVida()));
         * danoCard2.setText("Dano: X" /* + String.valueOf(cartaAereo.get()));
         * setCarta2(cartaTropa);
         * } else {
         * ACartaFeitico cartaFeitico = (ACartaFeitico) mao.get(i);
         * card2.setImage(new Image(cartaFeitico.getDirImage()));
         * vidaCard2.setText("Efeito: " + cartaFeitico.getEfeito());
         * danoCard2.setText("Valor Efeito: " +
         * String.valueOf(cartaFeitico.getValorEfeito()));
         * setCarta2(cartaFeitico);
         * }
         * break;
         * 
         * default:
         * break;
         * }
         * }
         * 
         * numRodadas++;
         * } else {
         * System.out.println("jogo acabou");
         * }
         **/
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
