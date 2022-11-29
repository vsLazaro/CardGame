package backend;

import java.util.ArrayList;
import java.util.Map;

import backend.abstracts.ACarta;
import backend.abstracts.ACartaFeitico;
import backend.abstracts.ACartaTropa;

public class Jogo {
    private int rodadas;
    private String nomeJogador;
    private Arena arena;
    private int jogando;
    private String ganhador;

    public Jogo(String nomeJogador, String nomeJogador2) {
        rodadas = 0;
        arena = new Arena(nomeJogador, nomeJogador2);
        jogando = 0;
    }

    public void comecar() {
        jogando = 1;
        nomeJogador = arena.getJogador1().getNome();
        rodadas++;
    }

    public void proximoRound() {
        jogando = (jogando == 1) ? 2 : 1;
        nomeJogador = (jogando == 1) ? arena.getJogador1().getNome() : arena.getJogador2().getNome();
        rodadas++;
    }

    public int getRodadas() {
        return rodadas;
    }

    public ArrayList<ACartaTropa> getCampoJogador() {
        if(jogando == 1) {
            return this.arena.getJogador1().getCartasCampo();
        }
        return this.arena.getJogador1().getCartasCampo();
    }

    public ArrayList<ACarta> getMaoJogador() {
        if(jogando == 1) {
            return this.arena.getJogador1().getCartaMao();
        }
        return this.arena.getJogador2().getCartaMao();
    }

    public String getGanhador() {
        return ganhador;
    }

    public String getNomeJogador() {
        return this.nomeJogador;
    }

    public boolean acabouJogo() {
        boolean jogador1 = arena.getJogador1().acabouTurno();
        boolean jogador2 = arena.getJogador2().acabouTurno();

        if(!jogador1) {
            ganhador = arena.getJogador2().getNome();
            return true; 
        }

        if(!jogador2) {
            ganhador = arena.getJogador1().getNome();
            return true;
        }
        return false;   
    }

    public ArrayList<ACartaTropa> jogarCarta(ACarta carta) {
        if(jogando == 1) {
            if(carta instanceof ACartaTropa) {
                arena.inserirCarta(jogando, (ACartaTropa)carta);
                return arena.getJogador1().getCartasCampo();
            }
            arena.jogarFeitico(jogando, (ACartaFeitico)carta);
            return arena.getJogador2().getCartasCampo();
        }
        if(carta instanceof ACartaTropa) {
            arena.inserirCarta(jogando, (ACartaTropa)carta);
            return arena.getJogador2().getCartasCampo();
        }
        arena.jogarFeitico(jogando, (ACartaFeitico)carta);
        return arena.getJogador2().getCartasCampo();
    }

    public ArrayList<ACartaTropa> atacar() {
        if(jogando == 1) {
            arena.atacar(jogando);
            return arena.getJogador2().getCartasCampo();
        }
        arena.atacar(jogando);
        return arena.getJogador1().getCartasCampo();
    }
}

