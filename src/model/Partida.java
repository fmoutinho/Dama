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

    private Jogador jogadorAzul;
    private Jogador jogadorVermelho;
    private Jogador jogadorAdversario;
    private boolean vezPlayer1 = true;

    public Partida(Tabuleiro tab) {
        this.jogadorVermelho = new Jogador(true, tab);
        this.jogadorAzul = new Jogador(false, tab);
        this.jogadorAdversario = jogadorAzul;
    }

    public Jogador getJogadorAzul() {
        return jogadorAzul;
    }

    public Jogador getJogadorVermelho() {
        return jogadorVermelho;
    }

    public Jogador getJogadorDaVez() {
        if (this.vezPlayer1) {
            System.out.println("Retornando jogador vermelho.");
            return this.jogadorVermelho;
        } else {
            System.out.println("Retornando jogador azul.");
            return this.jogadorAzul;
        }
    }

    public void trocaDeTurno() {
        if (jogadorVermelho.isJogadorDaVez()) {
            jogadorVermelho.setJogadorDaVez(false);
            jogadorAzul.setJogadorDaVez(true);
            jogadorAdversario = jogadorVermelho;
        }else{

            jogadorAzul.setJogadorDaVez(false);
            jogadorVermelho.setJogadorDaVez(true);
            jogadorAdversario = jogadorAzul;
        }

        this.vezPlayer1 = !this.vezPlayer1;
    }

    public void desenhaPecasJogadores() {
        this.jogadorAzul.desenhaPecas();
        this.jogadorVermelho.desenhaPecas();
    }

    public Jogador getJogadorAdversario() {
        return jogadorAdversario;
    }

    public void setJogadorAdversario(Jogador jogadorAdversario) {
        this.jogadorAdversario = jogadorAdversario;
    }
}
