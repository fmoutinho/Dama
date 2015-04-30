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

    private Sprite spritePeca = new Sprite("peca_vermelha.png");
    private String sentido;
    private boolean dama;

    public Peca(double eixoXini, double eixoYini, String sentido, boolean dama) {
        this.spritePeca.setPosition(eixoXini, eixoYini);
        this.sentido = sentido;
        this.dama = dama;

    }

    public void draw() {
        this.spritePeca.draw();
    }

    public void andar(Point p) {
        if (!dama) {
            if (sentido.equals("Descendo")) {
                this.spritePeca.y = this.spritePeca.y + 80;
                if (p.x < spritePeca.x) {
                    this.spritePeca.x = this.spritePeca.x - 80;
                } else {
                    this.spritePeca.x = this.spritePeca.x + 80;
                }
            } else {
                this.spritePeca.y = this.spritePeca.y - 80;
                if (p.x < spritePeca.x) {
                    this.spritePeca.x = this.spritePeca.x - 80;
                } else {
                    this.spritePeca.x = this.spritePeca.x + 80;

                }
            }
        }
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

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public boolean isDama() {
        return dama;
    }

    public void setDama(boolean dama) {
        this.dama = dama;
    }

}
