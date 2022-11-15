package abstracts;
import interfaces.*;

public abstract class ACarta implements ICarta {
    private String nome;
    private String tipo;
    private String dirImage;

    public ACarta(String nome, String tipo, String dirImage) {
        this.nome = nome;
        this.tipo = tipo;
        this.dirImage = dirImage;
    }
    
    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getDirImage() {
        return this.dirImage;
    }
}