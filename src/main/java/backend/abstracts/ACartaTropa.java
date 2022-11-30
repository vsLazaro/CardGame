package backend.abstracts;

public abstract class ACartaTropa extends ACarta{ 
    private int vida;
    private int dano;
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

    public void danoSofrido(int dano) {
        this.vida = this.vida - dano;
    }

    public boolean cura(int cura) {
        this.vida = this.vida + cura;
        return true;
    }

    public void setFuria(int buff) {
        this.dano += buff;
    }
}