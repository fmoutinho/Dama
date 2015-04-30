package model;

import JPlay.GameImage;

public class Casa {
    GameImage imagem;
    public Casa (String imagem) {
        this.imagem = new GameImage (imagem);
    }
    private int numero;
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
}

