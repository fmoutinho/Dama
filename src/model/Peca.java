/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import JPlay.Sprite;
import java.awt.Point;

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

    public void movimentar(Point p, int DESLOCAMENTO) {
        if (!dama) {
            if (!sentidoSubindo) {
                this.spritePeca.y = this.spritePeca.y + DESLOCAMENTO;
                if (p.x < spritePeca.x) {
                    this.spritePeca.x = this.spritePeca.x - DESLOCAMENTO;
                    this.deselecionaPeca();
                } else {
                    this.spritePeca.x = this.spritePeca.x + DESLOCAMENTO;
                    this.deselecionaPeca();
                }
                if (this.spritePeca.y == 568) { //Casa que ele vira dama
                    viraDama();
                }
            } else {
                this.spritePeca.y = this.spritePeca.y - DESLOCAMENTO;
                if (p.x < spritePeca.x) {
                    this.spritePeca.x = this.spritePeca.x - DESLOCAMENTO;
                    this.deselecionaPeca();
                } else {
                    this.spritePeca.x = this.spritePeca.x + DESLOCAMENTO;
                    this.deselecionaPeca();
                }
                if (this.spritePeca.y == 88) {
                    viraDama();
                }
            }
        }
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

    public boolean getSentidoSubindo() {
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
}
