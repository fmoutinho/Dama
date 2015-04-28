/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import JPlay.Sprite;
import java.awt.Point;
/**
 *
 * @author VíviannMagaly
 */
public class Peca {
    
   private Sprite pecaJogo = new Sprite("04-damas.png");
   
   public Peca(double eixoXini, double eixoYini) {
       this.pecaJogo.setPosition(eixoXini, eixoYini);
   }
   
   public void draw() {
       this.pecaJogo.draw();
   }
   
   public void andar(Point p) {
       this.pecaJogo.setPosition(p);
   }
}
