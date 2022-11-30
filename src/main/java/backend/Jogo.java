package backend;

import java.util.ArrayList;

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
        if (jogando == 1) {
            return this.arena.getJogador1().getCartasCampo();
        }
        return this.arena.getJogador2().getCartasCampo();
    }

    public ArrayList<ACartaTropa> getCampoJogador1() {
        return this.arena.getJogador1().getCartasCampo();
    }

    public ArrayList<ACartaTropa> getCampoJogador2() {
        return this.arena.getJogador2().getCartasCampo();
    }

    public ArrayList<ACarta> getMaoJogador() {
        if (jogando == 1) {
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
        boolean acabou = arena.verificaAcabou();
        return acabou;        
    }

    public boolean jogarCarta(ACarta carta) {
        if (jogando == 1) {
            if (carta instanceof ACartaTropa) {
                return arena.inserirCarta(jogando, (ACartaTropa) carta);
            }
            return arena.jogarFeitico(jogando, (ACartaFeitico) carta);
        }
        if (carta instanceof ACartaTropa) {
            return arena.inserirCarta(jogando, (ACartaTropa) carta);
        }
        return arena.jogarFeitico(jogando, (ACartaFeitico) carta);
    }

    public boolean atacar() {
        if (jogando == 1) {
            return arena.atacar(jogando);
        }
        return arena.atacar(jogando);
    }

    public boolean temGanhador() {
        int vidaJogador1 = 0;
        int vidaJogador2 = 0;

        for(ACartaTropa carta : this.arena.getJogador1().getCartasCampo()) {
            vidaJogador1 += carta.getVida();
        }
        for(ACartaTropa carta : this.arena.getJogador2().getCartasCampo()) {
            vidaJogador2 += carta.getVida();
        }

        if(vidaJogador1 == vidaJogador2) {
            return false;
        }

        this.ganhador = (vidaJogador1 > vidaJogador2) ? this.arena.getJogador1().getNome() : this.arena.getJogador2().getNome();
        return true;
    }
}
