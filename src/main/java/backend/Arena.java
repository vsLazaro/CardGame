package backend;

import java.util.ArrayList;

import backend.abstracts.ACarta;
import backend.abstracts.ACartaFeitico;
import backend.abstracts.ACartaTropa;

public class Arena {
    private Jogador jogador1;
    private Jogador jogador2;

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
            if(this.jogador2.getCartasCampo().isEmpty()) {
                return false;
            }
            int dano = this.jogador1.getCartasCampo().get(this.jogador1.getCartasCampo().size()-1).getDano();
            ACartaTropa sofreAtaque = this.jogador2.getCartasCampo().get(0);
            return this.jogador2.getCampo().sofreuAtaque(dano, sofreAtaque);
        }
        if(this.jogador1.getCartasCampo().isEmpty()) {
            return false;
        }
        int dano = this.jogador2.getCartasCampo().get(this.jogador2.getCartasCampo().size()-1).getDano();
        ACartaTropa sofreAtaque = this.jogador1.getCartasCampo().get(0);
        return this.jogador1.getCampo().sofreuAtaque(dano, sofreAtaque);
    }

    public boolean jogarFeitico(int jogador, ACartaFeitico feitico) {
        int valorEfeito = feitico.getValorEfeito();
        boolean retorno = false;
        System.out.println("JogarFeitico() -> Arena");
        if(jogador == 1) {
            if(feitico.getEfeito().equals("cura")) {
                jogador1.matarFeitico(feitico);
                retorno = jogador1.utilizarFeiticoCura(valorEfeito);
            }
            if(feitico.getEfeito().equals("bolzificar")) {
                retorno = jogador1.bolzificar(valorEfeito);
            }

            if(feitico.getEfeito().equals("dano")) {
                retorno = jogador2.getCampo().sofreuAtaqueFeitico(valorEfeito);
            }
            jogador1.matarFeitico(feitico);
            return retorno;
        }
        if(feitico.getEfeito().equals("cura")) {
            retorno = jogador2.utilizarFeiticoCura(valorEfeito);
        }
        if(feitico.getEfeito().equals("bolzificar")) {
            retorno = jogador2.bolzificar(valorEfeito);
        }
        if(feitico.getEfeito().equals("dano")) {
            retorno = jogador1.getCampo().sofreuAtaqueFeitico(valorEfeito);
        }
        
        jogador2.matarFeitico(feitico);
        return retorno;
    }

    public boolean inserirCarta(int jogador, ACartaTropa carta) {
        if(jogador == 1) {
            return jogador1.jogarCarta(carta);
        }
        return jogador2.jogarCarta(carta);
    }

    public boolean verificaAcabou() {
        if(jogador1.getCartaMao().isEmpty() && jogador2.getCartaMao().isEmpty()) {
            return true;
        }
        return false;
    }
}
