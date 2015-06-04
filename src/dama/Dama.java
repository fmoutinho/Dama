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
import model.Partida;
import model.Peca;
import util.Regra;

public class Dama {

    Window window;
    Partida partida;
    GameImage imagemFundo;
    Mouse mouse;
    Keyboard keyboard;
    Peca pecaSelecionada;
    final int DESLOCAMENTO_ANDAR = 80;
    final int DESLOCAMENTO_COMER = 160;
    
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
        partida = new Partida();
        keyboard = window.getKeyboard();
        imagemFundo = new GameImage("tabuleiro.jpg");
    }

    private void descarregarObjetos() {

        mouse = null;
        partida = null;
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
                System.out.println("Primeiro Clique");

                //Seleciona a peça que o jogador clicou
                if (partida.jogadorDaVez().existePecaSobMouse(mouse.getPosition())) {
                    pecaSelecionada = partida.jogadorDaVez().getUltimaPecaClicada();
                    desenha();
                    
                }

                //Se houver peça selecionada, espera o movimento
                if (pecaSelecionada != null) {
                    System.out.println("Fazer o movimento de andar");

                    while (esperandoMovimento) {

                        if (mouse.isLeftButtonPressed() == true) {
                            
                            //Se, em vez de fazer o movimento, decidiu escolher outra peça
                            
                            if (partida.jogadorDaVez().existePecaSobMouse(mouse.getPosition())) {
                                pecaSelecionada.deselecionaPeca();
                                desenha();
                                pecaSelecionada = partida.jogadorDaVez().getUltimaPecaClicada();
                                
                                
                            } else //Se não selecionou outra peça, então verifica se pode andar
                                if (Regra.podeAndar(pecaSelecionada, mouse.getPosition(), partida.getJogadorVermelho().getPecas())) {
                                    System.out.println("A peca " + pecaSelecionada.getId() + " andou");
                                    pecaSelecionada.movimentar(mouse.getPosition(), DESLOCAMENTO_ANDAR);
                                    pecaSelecionada.deselecionaPeca();
                                    desenha();
                                    pecaSelecionada = null;
                                    partida.trocaDeTurno();
                                    System.out.println("Vez do jogador " + partida.jogadorDaVez().getCor());
                                    
                                } else {
                                System.out.println("A peca " + pecaSelecionada.getId() + " nao pode andar");
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
        partida.desenhaPecasJogadores();
        window.display();
    }

}
