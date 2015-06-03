/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author VíviannMagaly
 */
public class Partida {

    public Partida() {
        this.jogadorVermelho = new Jogador(true);
        this.jogadorAzul = new Jogador(false);
    }
    
    private Jogador jogadorAzul;
    private Jogador jogadorVermelho;
    private boolean vezPlayer1 = true;

    public Jogador getJogadorAzul() {
        return jogadorAzul;
    }

    public Jogador getJogadorVermelho() {
        return jogadorVermelho;
    } 
    
    public Jogador jogadorDaVez() {
        if(this.vezPlayer1) {
            return this.jogadorVermelho;
        } else {
            return this.jogadorAzul;
        }
    }
    
    public void trocaDeTurno() {
        this.vezPlayer1 = !this.vezPlayer1;
    }
    
    public void desenhaPecasJogadores() {
        this.jogadorAzul.desenhaPecas();
        this.jogadorVermelho.desenhaPecas();
    }
}
