package backend;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import backend.abstracts.ACarta;
import backend.abstracts.ACartaFeitico;
import backend.abstracts.ACartaTropa;

public class GerenciadorDeBaralho {
    private ArrayList<ACarta> todasAsCartas;

    public GerenciadorDeBaralho()
    {
        todasAsCartas = new ArrayList<ACarta>();
        this.populaObjCartas();
    }

    public ArrayList<ACarta> geraBaralho()
    {
        ArrayList<ACarta> copiaCartas = todasAsCartas;
        ArrayList<ACarta> baralho = new ArrayList<ACarta>();
        Random gerador = new Random();
        for(int i = 0; i < 6; i++) {
            int numero = copiaCartas.size();
            int numeroRandomico = gerador.nextInt(numero);
            baralho.add(copiaCartas.get(numeroRandomico));
            copiaCartas.remove(numeroRandomico);
        }
        return baralho;
    }
    
    private void populaObjCartas()
    {
        try {
            ArrayList<ACartaTropa> tropas = this.populaTropa();
            ArrayList<ACartaFeitico> feiticos = this.populaFeitico();
            this.todasAsCartas.addAll(tropas);
            this.todasAsCartas.addAll(feiticos);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public ArrayList<ACartaTropa> populaTropa()
    {
        ArrayList<ACartaTropa> tropas = new ArrayList<ACartaTropa>();
        ACartaTropa carta;
        carta = new Tanque("Lazarento", "tanque", "/scenes/assets/lazarento.jpg", 93, 10);
        tropas.add(carta);
        carta = new Tanque("Gigante", "tanque", "/scenes/assets/gigante.jpg", 77, 15);
        tropas.add(carta);
        carta = new Tanque("Esqueletasso", "tanque", "/scenes/assets/esqueleto-gigante.jpg", 86, 17);
        tropas.add(carta);
        carta = new Terrestre("Corredor", "terrestre", "/scenes/assets/esqueleto-gigante.jpg", 50, 22);
        tropas.add(carta);
        carta = new Terrestre("Cavaleiro", "terrestre", "/scenes/assets/cavaleiro.jpg", 57, 25);
        tropas.add(carta);
        carta = new Terrestre("Brendida", "terrestre", "/scenes/assets/brendida.jpg", 45, 31);
        tropas.add(carta);
        carta = new Aereo("Dragonoide", "aereo", "/scenes/assets/bebe-dragao.jpg", 52, 20);
        tropas.add(carta);
        carta = new Aereo("Balao", "aereo", "/scenes/assets/balao.jpg", 60, 15);
        tropas.add(carta);
        carta = new Aereo("Conservo", "aereo", "/scenes/assets/conservo.jpg", 50, 28);
        tropas.add(carta);
        carta = new AntiAereo("Mazzomago", "antiaereo", "/scenes/assets/mazzomago.jpeg", 35, 40);
        tropas.add(carta);
        carta = new AntiAereo("Arqueira", "antiaereo", "/scenes/assets/arqueira.jpg", 45, 35);
        tropas.add(carta);
        carta = new AntiAereo("Mosqueteira", "antiaereo", "/scenes/assets/mosqueteira.jpg", 43, 38);
        tropas.add(carta);
        return tropas;
    }

    public ArrayList<ACartaFeitico> populaFeitico()
    {
        ArrayList<ACartaFeitico> feitico = new ArrayList<ACartaFeitico>(); 
        ACartaFeitico carta;
        carta = new Feitico("Bola de fogo", "feitico", "/scenes/assets/bola-de-fogo.jpg", "dano", 20);
        feitico.add(carta);
        carta = new Feitico("Cura", "feitico", "/scenes/assets/cura.jpg", "cura", 20);
        feitico.add(carta);
        carta = new Feitico("Bolzificação", "feitico", "adefinir", "bolzifica", 10);
        feitico.add(carta);
        return feitico;
    }
}
