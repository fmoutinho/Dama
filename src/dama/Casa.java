/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import JPlay.GameImage;

/**
 *
 * @author fernando
 */
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

