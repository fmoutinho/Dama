/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;

/**
 *
 * @author VíviannMagaly
 */
public class Jogador {

    public Jogador(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
        if (sentidoSubindo) {
            this.cor = "Vermelho";
        } else {
            this.cor = "Azul";
        } 
        InstanciaPecas();
    }

    private Peca ultimaPecaClicada;
    private Peca[] pecas = new Peca[12];
    private boolean sentidoSubindo;
    private String cor;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public Peca getUltimaPecaClicada() {
        return ultimaPecaClicada;
    }
    
    private void setUltimaPecaClicada(Peca peca) {
        if(this.ultimaPecaClicada != null) {
            this.ultimaPecaClicada.deselecionaPeca();
        }
        this.ultimaPecaClicada = peca;
        this.ultimaPecaClicada.selecionaPeca();
    }
    
    public boolean isSentidoSubindo() {
        return sentidoSubindo;
    }

    public void setSentidoSubindo(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
    }

    public Peca[] getPecas() {
        return pecas;
    }

    public void setPecas(Peca[] pecas) {
        this.pecas = pecas;
    }

    private void InstanciaPecas() {
        int x;
        int y;
        String imagemPeca;
        int lenght = 0;

        if (this.sentidoSubindo) {
            y = 568;

            //roda apenas 3 vezes para preencher o x
            for (int i = 1; i <= 3; i++) {
                x = 8;

                if (i == 2) {
                    x += 80;
                }
                //roda apenas 4 vezes para preencher o y
                for (int j = 1; j <= 4; j++) {

                    imagemPeca = "peca_vermelha.png";
                    Peca novaPeca = new Peca(x, y, this.sentidoSubindo, imagemPeca);
                    this.pecas[lenght] = novaPeca;
                    lenght++;

                    x += 160;
                }
                y -= 80;
            }
        } else {
            y = 8;

            //roda apenas 3 vezes para preencher o x
            for (int i = 1; i <= 3; i++) {
                x = 8;

                if (i == 1 || i == 3) {
                    x += 80;
                }

                //roda apenas 4 vezes para preencher o y
                for (int j = 1; j <= 4; j++) {
                    imagemPeca = "peca_azul.png";
                    Peca novaPeca = new Peca(x, y, this.sentidoSubindo, imagemPeca);
                    this.pecas[lenght] = novaPeca;
                    lenght++;

                    x += 160;
                }
                y += 80;
            }
        }
    }

    //Mesmo método que já existia porém rodando para todas as pecas.
    public boolean existePecaSobMouse(Point mousePosition) {
        for (Peca p : pecas) {

            if ((mousePosition.getX() >= p.getPosition().x)
                    && (mousePosition.getX() <= p.getPosition().x + p.getWidth())
                    && (mousePosition.getY() >= p.getPosition().y)
                    && (mousePosition.getY() <= p.getPosition().y + p.getHeight())) {
                System.out.println("Peca " + p.getId() + " Selecionada");
                setUltimaPecaClicada(p);                
                return true;
            }
        }
        return false;
    }

    public void desenhaPecas() {
        for (Peca p : pecas) {
            p.draw();
        }
    }
}
