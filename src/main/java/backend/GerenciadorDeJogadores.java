package backend;

import java.util.ArrayList;

import backend.abstracts.ACarta;

public class GerenciadorDeJogadores {
    private Jogador jogador1;
    private Jogador jogador2;

    public GerenciadorDeJogadores(String nomeJogador1, String nomeJogador2)
    {
        GerenciadorDeBaralho gerenciador = new GerenciadorDeBaralho();
        ArrayList<ACarta> baralho = gerenciador.geraBaralho();
        this.jogador1 = new Jogador(nomeJogador1, baralho);
        baralho = gerenciador.geraBaralho();
        this.jogador2 = new Jogador(nomeJogador2, baralho);
    }

    public Jogador getJogador1() {
        return this.jogador1;
    }

    public Jogador getJogador2() {
        return this.jogador2;
    }
}
