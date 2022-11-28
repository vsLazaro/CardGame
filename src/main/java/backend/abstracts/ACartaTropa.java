package backend.abstracts;

public abstract class ACartaTropa extends ACarta{ 
    private int vida;
    private int dano;
    private boolean furia;
    private int buffer;
    private boolean primeiroRound;

    public ACartaTropa(String nome, String tipo, String dirImage, int vida, int dano) {
        super(nome, tipo, dirImage);
        this.vida = vida;
        this.dano = dano;
        this.primeiroRound = true;
    }
    
    public boolean getPrimeiroRound() {
        return this.primeiroRound;
    }
    public int getDano() {
        return this.dano;
    }

    public int getVida() {
        return this.vida;
    }

    private void setVida(int vida) {
        this.vida = vida;
    }

    public void danoSofrido(int dano) {
        this.vida = this.vida - dano;
    }

    public boolean cura(int cura) {
        this.vida = this.vida + cura;
        return true;
    }
    
    public boolean getFuria() {
        return this.furia;
    }

    public void setFuria(boolean furia, int buff) {
        if(furia) {
            this.furia = true;
            this.buffer = buff;
        } else {
            this.furia = false;
            this.buffer = 0;
        }
    }

    public void passouPrimeiroRound() {
        this.primeiroRound = false;
    }
}