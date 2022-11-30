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
        carta = new Tanque("Lazarento", "tanque", "/scenes/assets/lazarento.jpg", 9, 1);
        tropas.add(carta);
        carta = new Tanque("Gigante", "tanque", "/scenes/assets/gigante.jpg", 8, 1);
        tropas.add(carta);
        carta = new Tanque("Esqueletasso", "tanque", "/scenes/assets/esqueleto-gigante.jpg", 8, 1);
        tropas.add(carta);
        carta = new Terrestre("Corredor", "terrestre", "/scenes/assets/esqueleto-gigante.jpg", 4, 4);
        tropas.add(carta);
        carta = new Terrestre("Cavaleiro", "terrestre", "/scenes/assets/cavaleiro.jpg", 4, 4);
        tropas.add(carta);
        carta = new Terrestre("Brendida", "terrestre", "/scenes/assets/brendida.jpg", 4, 8);
        tropas.add(carta);
        carta = new Aereo("Dragonoide", "aereo", "/scenes/assets/bebe-dragao.jpg", 4, 4);
        tropas.add(carta);
        carta = new Aereo("Balao", "aereo", "/scenes/assets/balao.jpg", 4, 4);
        tropas.add(carta);
        carta = new Aereo("Conservo", "aereo", "/scenes/assets/conservo.jpg", 4, 8);
        tropas.add(carta);
        carta = new AntiAereo("Mazzomago", "antiaereo", "/scenes/assets/mazzomago.jpeg", 4, 8);
        tropas.add(carta);
        carta = new AntiAereo("Arqueira", "antiaereo", "/scenes/assets/arqueira.jpg", 4, 4);
        tropas.add(carta);
        carta = new AntiAereo("Mosqueteira", "antiaereo", "/scenes/assets/mosqueteira.jpg", 4, 4);
        tropas.add(carta);
        return tropas;
    }

    public ArrayList<ACartaFeitico> populaFeitico()
    {
        ArrayList<ACartaFeitico> feitico = new ArrayList<ACartaFeitico>(); 
        ACartaFeitico carta;
        carta = new Feitico("Bola de fogo", "feitico", "/scenes/assets/bola-de-fogo.jpg", "dano", 10);
        feitico.add(carta);
        carta = new Feitico("Cura", "feitico", "/scenes/assets/cura.jpg", "cura", 1);
        feitico.add(carta);
        carta = new Feitico("Bolzificação", "feitico", "/scenes/assets/bolzificacao.jpg", "bolzifica", 2);
        feitico.add(carta);
        return feitico;
    }
}
