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
import model.Jogador;
import model.Peca;
import util.Regra;

public class Dama {

    Window window;
    Jogador jogador1;
    Jogador jogador2;
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
        jogador1 = new Jogador(true);
        jogador2 = new Jogador(false);
        keyboard = window.getKeyboard();
        imagemFundo = new GameImage("tabuleiro.jpg");
    }

    private void descarregarObjetos() {

        mouse = null;
        jogador1 = null;
        jogador2 = null;
        keyboard = null;
        imagemFundo = null;

        //Fecha a janela de jogo
        window.exit();
    }

    private void loop() {
        boolean executando = true;
        boolean esperandoMovimento;
        
        while (executando) {
            
            esperandoMovimento = true;
            desenha();
            
            if (mouse.isLeftButtonPressed() == true) {
                
                pecaSelecionada = jogador2.existePecaSobMouse(mouse.getPosition());
                
                if (pecaSelecionada != null) {
                    System.out.println("Fazer o movimento de andar");
                    while (esperandoMovimento) {
                        if (mouse.isLeftButtonPressed() == true) {
                            if (Regra.podeAndar(pecaSelecionada , mouse.getPosition())) {
                                System.out.println("A peca " + pecaSelecionada.getId() + " pode andar");
                                pecaSelecionada.andar(mouse.getPosition());     
                                desenha();
                                pecaSelecionada = null;
                            } else {
                                System.out.println("A peca " + pecaSelecionada.getId() + " nao pode andar" );
                               }    
                            esperandoMovimento = false;
                        }
                    }
                    
                } else {
                    System.out.println("Nenhuma peca selecionada");                    
                }                
            }

            if (keyboard.keyDown(Keyboard.ESCAPE_KEY) == true) {
                executando = false;
            }
        }   
    }
    
    /*private boolean existePecaSobMouse(Point position) {

        if ((position.getX() >= pecaTeste.getPosition().x)
                && (position.getX() <= pecaTeste.getPosition().x + pecaTeste.getWidth())
                && (position.getY() >= pecaTeste.getPosition().y)
                && (position.getY() <= pecaTeste.getPosition().y + pecaTeste.getHeight())) {
            System.out.println("Tem peça");
            return true;

        }

        System.out.println("Nao tem peça");
        return false;

    }*/

    private void desenha() {
        imagemFundo.draw();
        jogador1.desenhaPecas();
        jogador2.desenhaPecas();
        window.display();
    }

}
