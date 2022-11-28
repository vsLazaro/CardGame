package backend;
import java.util.ArrayList;

import backend.abstracts.ACarta;

public class CampoJogador {
    private ArrayList<ACarta> cartasCampo;

    public CampoJogador() {
        this.cartasCampo = new ArrayList<>();
    }

    public void inserirCarta(ACarta carta) {
        cartasCampo.add(carta);
    }
}
