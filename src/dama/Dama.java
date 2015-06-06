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
import model.Partida;
import model.Peca;
import util.Regra;
import util.Constantes;

public class Dama {

    Window window;
    Partida partida;
    GameImage imagemFundo;
    Mouse mouse;
    Keyboard keyboard;
    Peca pecaSelecionada;
    Peca pecaASerComida;

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
                if (partida.jogadorDaVez().existePecaDoJogadorSobMouse(mouse.getPosition())) {
                    pecaSelecionada = partida.jogadorDaVez().getUltimaPecaClicada();
                    desenha();
                }

                //Se houver peça selecionada, espera o movimento
                if (pecaSelecionada != null) {
                    System.out.println("Fazer o movimento de andar");

                    while (esperandoMovimento) {

                        if (mouse.isLeftButtonPressed() == true) {

                            //Se, em vez de fazer o movimento, decidiu escolher outra peça
                            if (partida.jogadorDaVez().existePecaDoJogadorSobMouse(mouse.getPosition())) {
                                pecaSelecionada = partida.jogadorDaVez().getUltimaPecaClicada();
                                desenha();
                                //Se não selecionou outra peça, então verifica se pode andar                                    
                            } else if (pecaSelecionada != null) {
                                if (Regra.podeAndar(pecaSelecionada, mouse.getPosition(),
                                        partida.getJogadorAdversario().getPecas(), partida.jogadorDaVez().getPecas()) && !existePecaSobMouse(mouse.getPosition())) {

                                    movimentar(mouse.getPosition(), Constantes.DESLOCAMENTO_ANDAR, esperandoMovimento);

                                    trocaDeTurno();

                                } else {
                                    System.out.println("A peca " + pecaSelecionada.getId() + " nao pode andar");
                                }
                            }
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

    private boolean existePecaSobMouse(Point position) {

        for (Peca p : partida.getJogadorAzul().getPecas()) {

            if ((position.getX() >= p.getPosition().x)
                    && (position.getX() <= p.getPosition().x + p.getWidth())
                    && (position.getY() >= p.getPosition().y)
                    && (position.getY() <= p.getPosition().y + p.getHeight())) {
                System.out.println("Peca " + p.getId() + " Selecionada");

                return true;
            }
        }

        for (Peca p : partida.getJogadorVermelho().getPecas()) {

            if ((position.getX() >= p.getPosition().x)
                    && (position.getX() <= p.getPosition().x + p.getWidth())
                    && (position.getY() >= p.getPosition().y)
                    && (position.getY() <= p.getPosition().y + p.getHeight())) {
                System.out.println("Peca " + p.getId() + " Selecionada");

                return true;
            }
        }
        return false;

    }

    private void desenha() {
        imagemFundo.draw();
        partida.desenhaPecasJogadores();
        window.display();
    }

    private void trocaDeTurno() {
        partida.trocaDeTurno();
        System.out.println("Vez do jogador " + partida.jogadorDaVez().getCor());

    }

    private void movimentar(Point position, int DESLOCAMENTO_ANDAR, boolean esperandoMovimento) {

        pecaSelecionada.movimentar(position, DESLOCAMENTO_ANDAR);
        desenha();
        System.out.println("A peca " + pecaSelecionada.getId() + " andou");
        pecaSelecionada = null;
        esperandoMovimento = false;

    }

}
