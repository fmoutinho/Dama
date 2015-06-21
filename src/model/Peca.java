/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import JPlay.Sprite;
import java.awt.Point;
import util.Constantes;

/**
 *
 * @author VíviannMagaly
 */
public class Peca {

    private Sprite spritePeca;
    private boolean sentidoSubindo;
    private boolean dama;
    private int id;
    static private int contador;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Peca(double eixoXini, double eixoYini, boolean sentidoSubindo, String imagemFundo) {
        this.spritePeca = new Sprite(imagemFundo, 4);
        this.spritePeca.setPosition(eixoXini, eixoYini);
        this.sentidoSubindo = sentidoSubindo;
        this.spritePeca.setCurrFrame(0);
        Peca.contador += 1;
        this.id = Peca.contador;
    }

    public void draw() {
        this.spritePeca.draw();
    }

    public void movimentar(Casa casaClicada) {
        Point p = casaClicada.getPoint();
        this.spritePeca.x = p.x;
        this.spritePeca.y = p.y;
        this.deselecionaPeca();

        if (!this.dama && this.sentidoSubindo) {
            if (this.spritePeca.y == Constantes.Y_INICIAL_DESCENDO) { //Casa que ele vira dama
                viraDama();
            }
        } else if (!this.dama && !this.sentidoSubindo) {
            if (this.spritePeca.y == Constantes.Y_INICIAL_SUBINDO) {
                viraDama();
            }
        }
    }

    public void movimentar(Point p, int DESLOCAMENTO) {
        boolean sentido = !dama ? this.sentidoSubindo : p.y < this.spritePeca.y;
        if (!sentido) {
            double y = this.spritePeca.y + DESLOCAMENTO;
            double x;
            if (p.x < spritePeca.x) {
                x = this.spritePeca.x - DESLOCAMENTO;
            } else {
                x = this.spritePeca.x + DESLOCAMENTO;
            }
            this.spritePeca.x = x;
            this.spritePeca.y = y;
            this.deselecionaPeca();

            if (!this.dama && this.spritePeca.y == Constantes.Y_INICIAL_SUBINDO) { //Casa que ele vira dama
                viraDama();
            }
        } else {
            double y = this.spritePeca.y - DESLOCAMENTO;
            double x;
            if (p.x < spritePeca.x) {
                x = this.spritePeca.x - DESLOCAMENTO;
            } else {
                x = this.spritePeca.x + DESLOCAMENTO;
            }
            this.spritePeca.x = x;
            this.spritePeca.y = y;
            this.deselecionaPeca();

            if (!this.dama && this.spritePeca.y == Constantes.Y_INICIAL_DESCENDO) {
                viraDama();
            }
        }

    }

    public void movimentar(Point p) {
        movimentar(p, Constantes.DESLOCAMENTO_ANDAR);
    }

    public void movimentarAoComer(Point p) {
        movimentar(p, Constantes.DESLOCAMENTO_COMER);
    }

    private void viraDama() {
        this.dama = true;
        this.spritePeca.setCurrFrame(1);
    }

    public JPlay.Point getPosition() {
        return this.spritePeca.getPosition();
    }

    public int getWidth() {
        return this.spritePeca.width;
    }

    public int getHeight() {
        return this.spritePeca.height;
    }

    public Sprite getSpritePeca() {
        return spritePeca;
    }

    public void setSpritePeca(Sprite spritePeca) {
        this.spritePeca = spritePeca;
    }

    public boolean isSentidoSubindo() {
        return sentidoSubindo;
    }

    public void setSentidoSubindo(boolean sentido) {
        this.sentidoSubindo = sentido;
    }

    public boolean isDama() {
        return dama;
    }

    public void setDama(boolean dama) {
        this.dama = dama;
    }

    public void selecionaPeca() {
        if (this.dama) {
            this.spritePeca.setCurrFrame(3);
        } else {
            this.spritePeca.setCurrFrame(2);
        }
    }

    public void deselecionaPeca() {
        if (this.dama) {
            this.spritePeca.setCurrFrame(1);
        } else {
            this.spritePeca.setCurrFrame(0);
        }
    }

    @Override // Mais condições podem ser necessárias
    public boolean equals(Object obj) {
        Peca peca = (Peca) obj;
        if (this.getPosition() == peca.getPosition()) {
            return true;
        }
        return false;
    }

    public void comer(Peca pecaASerComida) {
        int x = (int) pecaASerComida.getPosition().x;
        int y = (int) pecaASerComida.getPosition().y;
        Point p = new Point(x, y);

        movimentarAoComer(p);
    }
    
}
