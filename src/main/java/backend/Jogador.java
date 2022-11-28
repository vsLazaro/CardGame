package backend;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import backend.abstracts.ACarta;

public class Jogador {
    private ArrayList<ACarta> mao = new ArrayList<>();
    private int vida;
    private Queue<ACarta> baralho = new LinkedList<>();
    private CampoJogador campo;

    public Jogador(ArrayList<ACarta> listaCartas) { 
        popularBaralho(listaCartas);
        this.vida = baralho.size();
        popularMao();
        this.campo = new CampoJogador();
    }

    private void popularBaralho (ArrayList<ACarta> listaCartas) {
        for (ACarta carta : listaCartas) {
            this.baralho.add(carta);
        }
    }

    private void popularMao() {
        for(int i = 0; i < 2 ; i++) {
            this.mao.add(this.baralho.poll());
        }
    }
}
