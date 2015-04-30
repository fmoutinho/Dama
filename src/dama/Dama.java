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

public class Dama {

    public static void main(String[] args) {

        Window janela = new Window(640, 640);
        Peca peca1 = new Peca(88, 8);

        GameImage imagemFundo = new GameImage("tabuleiro.jpg");
        Mouse mouse = janela.getMouse();
        Keyboard teclado = janela.getKeyboard();

        boolean executando = true;

        while (executando) {

            imagemFundo.draw();
            peca1.draw();
            janela.display();

            if (mouse.isLeftButtonPressed() == true) {
                peca1.andar(mouse.getPosition());
            }

            if (teclado.keyDown(Keyboard.ESCAPE_KEY) == true) {
                executando = false;
            }
        }
        janela.exit();
    }

}
