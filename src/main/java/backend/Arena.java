package backend;

import java.util.ArrayList;

import backend.abstracts.ACarta;
import backend.abstracts.ACartaFeitico;
import backend.abstracts.ACartaTropa;

public class Arena {
    private Jogador jogador1;
    private Jogador jogador2;
    private int rodada = 0;

    public Arena(String nomeJogador1, String nomeJogador2)
    {
        GerenciadorDeJogadores gerenciadorDeJogadores = new GerenciadorDeJogadores(nomeJogador1, nomeJogador2);
        this.jogador1 = gerenciadorDeJogadores.getJogador1();
        this.jogador2 = gerenciadorDeJogadores.getJogador2();
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public ArrayList<ACarta> cartasNaMaoJogadores(int jogador) {
        if(jogador == 1) {
            return this.jogador1.getCartaMao();
        }
        return this.jogador2.getCartaMao();
    }

    public boolean comprarCartasJogadores(int jogador) {
        if(jogador == 1) {
            return this.jogador1.comprarCarta();
        }
        return this.jogador2.comprarCarta();
    }

    public ArrayList<ACartaTropa> cartasNoCampoJogadores(int jogador) {
        if(jogador == 1) {
            return this.jogador1.getCartasCampo();
        }
        return this.jogador2.getCartasCampo();
    }

    public boolean atacar(int jogador)
    {
        if(jogador == 1) {
            int dano = this.jogador1.getCartasCampo().get(0).getDano();
            ACartaTropa sofreAtaque = this.jogador2.getCartasCampo().get(0);
            return this.jogador2.getCampo().sofreuAtaque(dano, sofreAtaque);
        }
        int dano = this.jogador2.getCartasCampo().get(0).getDano();
        ACartaTropa sofreAtaque = this.jogador1.getCartasCampo().get(0);
        return this.jogador1.getCampo().sofreuAtaque(dano, sofreAtaque);
    }

    public boolean jogarFeitico(int jogador, ACartaFeitico feitico) {
        int valorEfeito = feitico.getValorEfeito();
        if(jogador == 1) {
            if(feitico.getEfeito().equals("cura")) {
                jogador1.matarFeitico(feitico);
                for(ACartaTropa carta : this.jogador1.getCartasCampo()) {
                    jogador1.utilizarFeiticoCura(valorEfeito, carta);
                }
                return true;
            }
            if(feitico.getEfeito().equals("bolzificar")) {
                for(ACartaTropa carta : this.jogador1.getCartasCampo()) {
                    jogador1.getCampo().bolzificar(valorEfeito, carta);
                }
                return true;
            }
            jogador1.matarFeitico(feitico);
            for(ACartaTropa carta : this.jogador2.getCartasCampo()) {
                jogador2.getCampo().sofreuAtaque(valorEfeito, carta);
            }
            return true;
        }
        if(feitico.getEfeito().equals("cura")) {
            jogador2.matarFeitico(feitico);
            for(ACartaTropa carta : this.jogador2.getCartasCampo()) {
                jogador2.utilizarFeiticoCura(valorEfeito, carta);
            }
            return true;
        }
        if(feitico.getEfeito().equals("bolzificar")) {
            for(ACartaTropa carta : this.jogador2.getCartasCampo()) {
                jogador2.getCampo().bolzificar(valorEfeito, carta);
            }
            return true;
        }
        jogador2.matarFeitico(feitico);
        for(ACartaTropa carta : this.jogador1.getCartasCampo()) {
            jogador1.getCampo().sofreuAtaque(valorEfeito, carta);
        }
        return true;
    }

    public boolean inserirCarta(int jogador, ACartaTropa carta) {
        if(jogador == 1) {
            return jogador1.jogarCarta(carta);
        }
        return jogador2.jogarCarta(carta);
    }
}
