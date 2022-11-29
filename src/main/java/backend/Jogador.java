package backend;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import backend.abstracts.ACarta;
import backend.abstracts.ACartaFeitico;
import backend.abstracts.ACartaTropa;

public class Jogador {
    private ArrayList<ACarta> mao = new ArrayList<>();
    String nome;
    private Queue<ACarta> baralho = new LinkedList<>();
    private CampoJogador campo;
    private boolean comprarCarta;
    private boolean jogarCarta;
    private boolean atacar;

    public Jogador(String nome, ArrayList<ACarta> listaCartas) { 
        this.nome = nome;
        popularBaralho(listaCartas);
        popularMao();
        this.campo = new CampoJogador();
        this.comprarCarta = true;
        this.jogarCarta = true;
        this.atacar = true;
    }

    public String getNome() {
        return nome;
    }

    private void popularBaralho(ArrayList<ACarta> listaCartas) {
        for (ACarta carta : listaCartas) {
            this.baralho.add(carta);
        }
    }

    public void comprou() {
        this.comprarCarta = false;
    }

    public void jogou() {
        this.jogarCarta = false;
    }

    public void atacou() {
        this.atacar = false;
    }

    private void popularMao() {
        for(int i = 0; i <= 2 ; i++) {
            this.mao.add(this.baralho.poll());
        }
    }

    public boolean comprarCarta() {
        if(!this.comprarCarta) {
            return false;
        }
        if(this.baralho.size() <= 0) {
            return false;
        }
        this.mao.add(this.baralho.poll());
        this.comprou();
        return true;
    }

    public boolean jogarCarta(ACartaTropa carta) {
       /*  if(!this.jogarCarta) {
            return false;
        } */
        if(this.mao.contains(carta)) {
            this.campo.inserirCarta(carta);
            this.mao.remove(carta);
            this.jogou();
            return true;
        }
        return false;
    }

    public boolean utilizarFeiticoCura(int cura, ACartaTropa carta) {
        if(!this.jogarCarta) {
            return false;
        }
        this.jogou();
        return this.campo.curar(cura, carta);
    }

    public boolean matarFeitico(ACartaFeitico carta) {
        if(this.mao.contains(carta)) {
            this.mao.remove(carta);
            return true;
        }
        return false;
    }

    /** Caso retorne false, o jogador perdeu o jogo
     *  Caso retorne true, segue o jogo para esse jogador
     */
    public boolean acabouTurno() {
        if(this.baralho.size() <= 0 && this.mao.size() <= 0 && this.campo.getCartasCampo().size() <= 0) {
            return false;
        }

        if(this.baralho.size() <= 0) {
            if(this.mao.size() > 0 && this.campo.getCartasCampo().size() <= 0) {
                int cartasFeitico = 0;
                int cartasNaMao = this.mao.size();
                for (ACarta aCarta : this.mao) {
                    if(aCarta instanceof ACartaFeitico) {
                        cartasFeitico++;
                    }
                }
                if(cartasFeitico == cartasNaMao) {
                    return false;
                }
            }
        }

        if(this.mao.size() <= 0 && this.campo.getCartasCampo().size() <= 0) {
            int cartasFeitico = 0;
            int cartasNoBaralho = this.baralho.size();
            for (ACarta aCarta : this.baralho) {
                if(aCarta instanceof ACartaFeitico) {
                    cartasFeitico++;
                }
            }
            if(cartasFeitico == cartasNoBaralho) {
                return false;
            }
        }
        this.recomecarTurno();
        this.campo.passouPrimeiroRound();
        return true;
    }

    private void recomecarTurno() {
        this.jogarCarta = true;
        this.atacar = true;
        this.comprarCarta = true;
    }

    public ArrayList<ACarta> getCartaMao(){
        return this.mao;
    }

    public ArrayList<ACartaTropa> getCartasCampo(){
        return this.campo.getCartasCampo();
    }

    public CampoJogador getCampo() {
        return this.campo;
    }
}
