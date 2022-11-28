package backend;
import java.lang.reflect.Array;
import java.util.ArrayList;

import abstracts.ACarta;

public class CampoJogador {
    private ArrayList<ACarta> cartasCampo;

    public CampoJogador() {
        this.cartasCampo = new ArrayList<>();
    }

    public void inserirCarta(ACarta carta) {
        cartasCampo.add(carta);
    }
}
