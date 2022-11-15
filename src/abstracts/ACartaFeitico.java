public abstract class ACartaFeitico extends ACarta {
    private String efeito;
    private int valorEfeito;

    public ACartaFeitico(String nome, String tipo, String efeito, int valorEfeito) {
        super(nome, tipo);
        this.efeito = efeito;
        this.valorEfeito = valorEfeito;
    }

    public String getEfeito() {
        return this.efeito;
    }

    public int getValorEfeito() {
        return this.valorEfeito;
    }
}
