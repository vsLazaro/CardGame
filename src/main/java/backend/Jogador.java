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

    public Jogador(String nome, ArrayList<ACarta> listaCartas) { 
        this.nome = nome;
        popularBaralho(listaCartas);
        popularMao();
        this.campo = new CampoJogador();
    }

    public String getNome() {
        return nome;
    }

    private void popularBaralho(ArrayList<ACarta> listaCartas) {
        for (ACarta carta : listaCartas) {
            this.baralho.add(carta);
        }
    }

    private void popularMao() {
        for(int i = 0; i <= 2 ; i++) {
            this.mao.add(this.baralho.poll());
        }
    }

    public boolean comprarCarta() {
        if(this.baralho.size() <= 0) {
            return false;
        }
        this.mao.add(this.baralho.poll());
        return true;
    }

    public boolean jogarCarta(ACartaTropa carta) {
        if(this.mao.contains(carta)) {
            this.campo.inserirCarta(carta);
            this.mao.remove(carta);
            this.comprarCarta();
            return true;
        }
        return false;
    }

    public boolean utilizarFeiticoCura(int cura) {
        return this.campo.curar(cura);
    }

    public boolean bolzificar(int danoAumentado) {
        if(this.mao.size()>=1) {
            System.out.println("bolzificar() Jogador");
            for (ACarta aCarta : this.mao) {
                if(aCarta instanceof ACartaTropa) {
                    ((ACartaTropa) aCarta).setFuria(danoAumentado);
                }
            }
            return true;
        }
        return false;
    }

    public boolean matarFeitico(ACartaFeitico carta) {
        if(this.mao.contains(carta)) {
            this.mao.remove(carta);
            this.comprarCarta();
            return true;
        }
        return false;
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
