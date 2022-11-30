package backend;
import java.util.ArrayList;

import backend.abstracts.ACartaTropa;

public class CampoJogador {
    private ArrayList<ACartaTropa> cartasCampo;

    public CampoJogador() {
        this.cartasCampo = new ArrayList<>();
    }

    public void inserirCarta(ACartaTropa carta) {
        cartasCampo.add(carta);
    }

    public ArrayList<ACartaTropa> getCartasCampo() {
        return this.cartasCampo;
    }

    public boolean cartaMorreu(ACartaTropa carta) {
        if(this.cartasCampo.remove(carta)) {
            return true;
        }
        return false;
    }

    public boolean curar(int cura) {
        if(this.cartasCampo.size() <= 0) {
           return false; 
        } 
        this.cartasCampo.get(0).cura(cura);
        return true;
    }

    public boolean sofreuAtaque(int dano, ACartaTropa carta) {
        if(this.cartasCampo.contains(carta)) {
            for (ACartaTropa aCarta : cartasCampo) {
                if(aCarta.getNome().equals(carta.getNome())) {
                    aCarta.danoSofrido(dano);
                    if(aCarta.getVida() <= 0) {
                        this.cartaMorreu(aCarta);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean sofreuAtaqueFeitico(int dano) {
        if(this.cartasCampo.size()<=0) {
            return false;
        }
        this.cartasCampo.get(0).danoSofrido(dano);
        if(this.cartasCampo.get(0).getVida()<=0) {
            this.cartaMorreu(this.cartasCampo.get(0));
        }
        return true;
    }
}
