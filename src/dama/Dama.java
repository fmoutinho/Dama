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
import java.util.ArrayList;
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
                if (existePecaDoJogadorSobMouse(mouse.getPosition(), partida.getJogadorDaVez().getPecas())) {

                    selecionaPeca(retornaPecaSelecionada(mouse.getPosition(), partida.getJogadorDaVez().getPecas()));

                    desenha();
                }

                //Se houver peça selecionada, espera o movimento
                if (pecaSelecionada != null) {
                    System.out.println("Fazer o movimento de andar");

                    while (esperandoMovimento) {

                        if (mouse.isLeftButtonPressed() == true) {

                            //Se, em vez de fazer o movimento, decidiu escolher outra peça
                            if (existePecaDoJogadorSobMouse(mouse.getPosition(), partida.getJogadorDaVez().getPecas())) {

                                selecionaPeca(retornaPecaSelecionada(mouse.getPosition(), partida.getJogadorDaVez().getPecas()));
                                desenha();
                                //Se não selecionou outra peça, então verifica se pode andar                                    
                            } else if (pecaSelecionada != null) {
                                if (!Regra.deveComer(partida.getJogadorAdversario().getPecas(), partida.getJogadorDaVez().getPecas())
                                        && Regra.podeAndar(pecaSelecionada, mouse.getPosition())
                                        && !existePecaSobMouse(mouse.getPosition(), true)) {

                                    movimentar(mouse.getPosition());
                                    trocaDeTurno(esperandoMovimento);

                                } else if (existePecaDoJogadorSobMouse(mouse.getPosition(), partida.getJogadorAdversario().getPecas())) {
                                    pecaASerComida = retornaPecaSelecionada(mouse.getPosition(), partida.getJogadorAdversario().getPecas());

                                    if (Regra.podeComer(pecaSelecionada, pecaASerComida,
                                            partida.getJogadorAdversario().getPecas(), partida.getJogadorDaVez().getPecas())) {
                                        comer();
                                        if (!Regra.deveComer(partida.getJogadorAdversario().getPecas(), partida.getJogadorDaVez().getPecas())) {
                                            trocaDeTurno(esperandoMovimento);
                                        }
                                    }
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

    private boolean existePecaSobMouse(Point position, boolean verificandoTodasAsPecas) {

        if (verificandoTodasAsPecas) {
            return existePecaDoJogadorSobMouse(position, partida.getJogadorDaVez().getPecas())
                    && existePecaDoJogadorSobMouse(position, partida.getJogadorAdversario().getPecas());
        } else {
            return existePecaDoJogadorSobMouse(position, partida.getJogadorAdversario().getPecas());
        }

    }

    private void desenha() {
        imagemFundo.draw();
        partida.desenhaPecasJogadores();
        window.display();
    }

    private void trocaDeTurno(boolean esperandoMovimento) {
        partida.trocaDeTurno();
        System.out.println("Vez do jogador " + partida.getJogadorDaVez().getCor());
        esperandoMovimento = false;
        pecaSelecionada = null;
        pecaASerComida = null;

    }

    private void movimentar(Point position) {

        pecaSelecionada.movimentar(position);
        desenha();
        System.out.println("A peca " + pecaSelecionada.getId() + " andou");

    }

    private void comer() {
        pecaSelecionada.comer(pecaASerComida);
        partida.getJogadorAdversario().getPecas().remove(pecaASerComida);
        pecaASerComida = null;
    }

    //Mesmo método que já existia porém rodando para todas as pecas.
    public boolean existePecaDoJogadorSobMouse(Point mousePosition, ArrayList<Peca> pecas) {
        for (Peca peca : pecas) {

            if ((mousePosition.getX() >= peca.getPosition().x)
                    && (mousePosition.getX() <= peca.getPosition().x + peca.getWidth())
                    && (mousePosition.getY() >= peca.getPosition().y)
                    && (mousePosition.getY() <= peca.getPosition().y + peca.getHeight())) {
                System.out.println("Peca " + peca.getId() + " Selecionada");

                return true;
            }
        }
        return false;
    }

    public Peca retornaPecaSelecionada(Point mousePosition, ArrayList<Peca> pecas) {
        Peca pecaRetorno = null;
        for (Peca peca : pecas) {

            if ((mousePosition.getX() >= peca.getPosition().x)
                    && (mousePosition.getX() <= peca.getPosition().x + peca.getWidth())
                    && (mousePosition.getY() >= peca.getPosition().y)
                    && (mousePosition.getY() <= peca.getPosition().y + peca.getHeight())) {

                System.out.println("Peca " + peca.getId() + " Selecionada");
                pecaRetorno = peca;
                return pecaRetorno;
            }

        }
        return null;
    }

    private void selecionaPeca(Peca retornaPecaSelecionada) {
        if (pecaSelecionada == null) {
            pecaSelecionada = retornaPecaSelecionada;
            pecaSelecionada.selecionaPeca();
        } else {
            pecaSelecionada.deselecionaPeca();
            pecaSelecionada = retornaPecaSelecionada;
            pecaSelecionada.selecionaPeca();
        }
    }

}
