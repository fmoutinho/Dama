/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import model.Peca;
import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Mouse;
import JPlay.Window;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dama {
    
    public static void main(String[] args) {
        // TODO code application logic here/* blablabla*/
        Window janela = new Window(1080, 768);     
        Peca peca1 = new Peca(300, 300);             
        
        TabuleiroLuis tabuleiro = new TabuleiroLuis();
        
        Mouse mouse = janela.getMouse();
        Keyboard teclado = janela.getKeyboard();
        
        boolean executando = true;
        
        while (executando) {
           
            peca1.draw();
            janela.display();
            
            if (mouse.isLeftButtonPressed() == true)
               peca1.andar(mouse.getPosition());    
            
            if(teclado.keyDown(Keyboard.ESCAPE_KEY) == true)
                executando = false;
        }
        janela.exit();
    }
 
} 
