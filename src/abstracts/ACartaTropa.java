public abstract class ACartaTropa extends ACarta{ 
    private int vida;
    private int dano;
    private boolean furia;
    private int buffer;

    public ACartaTropa(String nome, String tipo, int vida, int dano) {
        super(nome, tipo);
        this.vida = vida;
        this.dano = dano;
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

    public void cura(int cura) {
        this.vida = this.vida + cura;
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
}