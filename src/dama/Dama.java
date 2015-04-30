/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dama;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Mouse;
import JPlay.Window;
import java.awt.Point;
import model.Peca;
import util.Regra;

public class Dama {

    Window window;
    Peca pecaTeste;
    GameImage imagemFundo;
    Mouse mouse;
    Keyboard keyboard;
    Peca pecaSelecionada;

    public Dama() {

        System.out.println("rodando");
        carregarObjetos();
        loop();
        descarregarObjetos();
    }

    private void carregarObjetos() {
        //A windows SEMPRE deve ser a primeira a ser CARREGADA
        window = new Window(640, 640);
        mouse = window.getMouse();
        pecaTeste = new Peca(88, 8 , "Descendo",false);
        keyboard = window.getKeyboard();
        imagemFundo = new GameImage("tabuleiro.jpg");
    }

    private void descarregarObjetos() {

        mouse = null;
        pecaTeste = null;
        keyboard = null;
        imagemFundo = null;

        //Fecha a janela de jogo
        window.exit();
    }

    private void loop() {
        desenha();
        boolean executando = true;

        while (executando) {

            if (mouse.isLeftButtonPressed() == true) {

                if (pecaSelecionada != null) {
                    if (Regra.podeAndar(pecaSelecionada , mouse.getPosition())) {
                        System.out.println("1");
                        pecaSelecionada.andar(mouse.getPosition());     
                        desenha();
                        pecaSelecionada = null;
                    }
                } else if (existePecaSobMouse(mouse.getPosition())) {
                    System.out.println("2");
                    pecaSelecionada = pecaTeste;
                }
            }

            if (keyboard.keyDown(Keyboard.ESCAPE_KEY) == true) {
                executando = false;
            }
        }
    }

    private boolean existePecaSobMouse(Point position) {

        if ((position.getX() >= pecaTeste.getPosition().x)
                && (position.getX() <= pecaTeste.getPosition().x + pecaTeste.getWidth())
                && (position.getY() >= pecaTeste.getPosition().y)
                && (position.getY() <= pecaTeste.getPosition().y + pecaTeste.getHeight())) {
            System.out.println("Tem peça");
            return true;

        }

        System.out.println("Nao tem peça");
        return false;

    }

    private void desenha() {

        imagemFundo.draw();
        pecaTeste.draw();
        window.display();
    }

}
