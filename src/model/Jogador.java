/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.util.ArrayList;
import util.Constantes;

/**
 *
 * @author VíviannMagaly
 */
public class Jogador {

    private ArrayList<Peca> pecas = new ArrayList<>(12);

    private boolean sentidoSubindo;
    private String cor;
    private boolean jogadorDaVez;

    public Jogador(boolean sentidoSubindo, Tabuleiro tab) {
        this.sentidoSubindo = sentidoSubindo;
        if (sentidoSubindo) {
            this.cor = "Vermelho";
            this.jogadorDaVez = sentidoSubindo;
        } else {
            this.cor = "Azul";
            this.jogadorDaVez = sentidoSubindo;

        }
        InstanciaPecas(tab);
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isSentidoSubindo() {
        return sentidoSubindo;
    }

    public void setSentidoSubindo(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    private void InstanciaPecas(Tabuleiro tab) {
        String imagemPeca;
        int lenght = 0;
        int jInicial;

        if (!this.sentidoSubindo) {
            //roda para preencher o i
            for (int i = 0; i < 3; i++) {

                jInicial = (i == 0) || (i == 2) ? 1 : 0;

                //roda para preencher o j
                for (int j = jInicial; j < Constantes.DIMENSAO_TABULEIRO; j += 2) {
                    imagemPeca = "peca_azul.png";
                    Point p = tab.getMapa()[i][j].getPoint();
                    Peca novaPeca = new Peca(p.x, p.y, !this.sentidoSubindo, imagemPeca);
                    this.pecas.add(lenght, novaPeca);
                    lenght++;

                    tab.getMapa()[i][j].setPeca(novaPeca);
                }
            }
        } else {
            //roda para preencher o i
            for (int i = Constantes.DIMENSAO_TABULEIRO - 1; i >= Constantes.DIMENSAO_TABULEIRO - 3; i--) {

                jInicial = (i % 2) == 0 ? 1 : 0;

                //roda pra preencher o j
                for (int j = jInicial; j < Constantes.DIMENSAO_TABULEIRO; j = j + 2) {
                    imagemPeca = "peca_vermelha.png";
                    Point p = tab.getMapa()[i][j].getPoint();
                    Peca novaPeca = new Peca(p.x, p.y, this.sentidoSubindo, imagemPeca);
                    this.pecas.add(lenght, novaPeca);
                    lenght++;

                    tab.getMapa()[i][j].setPeca(novaPeca);
                }
            }
        }
    }

    public void desenhaPecas() {
        for (Peca p : pecas) {
            p.draw();
        }
    }

    public boolean isJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(boolean jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

}
