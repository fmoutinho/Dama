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
import model.Casa;
import model.Partida;
import model.Peca;
import model.Tabuleiro;
import util.Regra;
import util.Constantes;

public class Dama {

    Window window;
    Partida partida;
    GameImage imagemFundo;
    Mouse mouse;
    Keyboard keyboard;
    Tabuleiro tabuleiro;

    Peca pecaSelecionada;
    Peca pecaASerComida;
    Casa casaSelecionada;

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
        tabuleiro = new Tabuleiro();
        partida = new Partida(tabuleiro);
        keyboard = window.getKeyboard();
        imagemFundo = new GameImage("tabuleiro.jpg");
    }

    private void descarregarObjetos() {

        mouse = null;
        partida = null;
        keyboard = null;
        imagemFundo = null;
        tabuleiro = null;

        //Fecha a janela de jogo
        window.exit();
    }

    private void loop() {
        boolean executando = true;
        boolean esperandoMovimento;
        while (executando) {
            desenha();

            boolean deveComer = Regra.deveComer(partida.getJogadorDaVez().getPecas(), tabuleiro);
            if (deveComer) {
                System.out.println("Deve Comer!");
                loopComer(deveComer);
            } else {
                System.out.println("Não deve Comer!");
                loopAndar();
            }

            if (keyboard.keyDown(Keyboard.ESCAPE_KEY)
                    == true) {
                executando = false;
            }
        }
    }

    private void loopComer(boolean deveComer) {
        boolean esperandoMovimento = false;

        while (deveComer) {
            if (mouse.isLeftButtonPressed() == true) {
                casaSelecionada = tabuleiro.getCasaTabuleiro(mouse.getPosition());

                if (casaSelecionada != null && casaSelecionada.temPeca()) {

                    if (partida.getJogadorDaVez().isSentidoSubindo() == casaSelecionada.getPeca().isSentidoSubindo()) {
                        casaSelecionada.getPeca().selecionaPeca();
                        esperandoMovimento = true;
                        desenha();
                    }

                    while (esperandoMovimento) {

                        if (mouse.isLeftButtonPressed() == true) {

                            Casa casaClicada = tabuleiro.getCasaTabuleiro(mouse.getPosition());

                            if (casaClicada != null && casaClicada.temPeca()) {
                                System.out.println(casaClicada.getPeca());
                                if (partida.getJogadorDaVez().isSentidoSubindo() == casaClicada.getPeca().isSentidoSubindo()) {
                                    casaSelecionada.getPeca().deselecionaPeca();
                                    casaClicada.getPeca().selecionaPeca();
                                    casaSelecionada = casaClicada;
                                    desenha();
                                }
                            } else if (Regra.podeComer(casaSelecionada, casaClicada, tabuleiro)) {
                                if (Regra.podeComer(casaSelecionada, casaClicada, tabuleiro)) {
                                    System.out.println("Pode comer");
                                    Casa casaPecaComida = Regra.getCasaComida(casaSelecionada, casaClicada, tabuleiro);
                                    partida.getJogadorAdversario().mataPeca(casaPecaComida.getPeca(), partida.getJogadorAdversario().getPecas());
                                    casaSelecionada.getPeca().movimentar(casaClicada);
                                    casaPecaComida.setPeca(null);
                                    casaClicada.setPeca(casaSelecionada.getPeca());
                                    casaSelecionada.setPeca(null);

                                    desenha();
                                    esperandoMovimento = false;
                                    deveComer = Regra.aindaDeveComer(casaClicada, tabuleiro);
                                }

                            }
                        }

                    }
                }
            }
        }
        trocaDeTurno();
    }

    private void loopAndar() {
        boolean esperandoMovimento;
        boolean naoFezMovimento = true;

        while (naoFezMovimento) {

            if (mouse.isLeftButtonPressed() == true) {
                System.out.println("Primeiro Clique");

                casaSelecionada = tabuleiro.getCasaTabuleiro(mouse.getPosition());

                if (casaSelecionada != null && casaSelecionada.temPeca()) {

                    if (partida.getJogadorDaVez().isSentidoSubindo() == casaSelecionada.getPeca().isSentidoSubindo()) {
                        casaSelecionada.getPeca().selecionaPeca();
                        esperandoMovimento = true;
                        desenha();

                        System.out.println("Fazer o movimento de andar");

                        while (esperandoMovimento) {

                            if (mouse.isLeftButtonPressed() == true) {

                                Casa casaClicada = tabuleiro.getCasaTabuleiro(mouse.getPosition());

                                if (casaClicada != null && casaClicada.temPeca()) {
                                    System.out.println(casaClicada.getPeca());
                                    if (partida.getJogadorDaVez().isSentidoSubindo() == casaClicada.getPeca().isSentidoSubindo()) {
                                        casaSelecionada.getPeca().deselecionaPeca();
                                        casaClicada.getPeca().selecionaPeca();
                                        casaSelecionada = casaClicada;
                                        desenha();
                                    }

                                } else if (Regra.podeAndar(casaSelecionada, casaClicada)) {
                                    System.out.println("Pode andar");
                                    casaSelecionada.getPeca().movimentar(casaClicada);
                                    tabuleiro.trocaCasa(casaSelecionada, casaClicada);
                                    trocaDeTurno();
                                    esperandoMovimento = false;
                                    naoFezMovimento = false;
                                    desenha();
                                } else {
                                    System.out.println("A peca " + casaSelecionada.getPeca().getId() + " nao pode andar");
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private boolean existePecaSobMouse(Point position, boolean verificandoTodasAsPecas) {

        if (verificandoTodasAsPecas) {
            return existePecaDoJogadorSobMouse(position, (ArrayList<Peca>) partida.getJogadorDaVez().getPecas())
                    && existePecaDoJogadorSobMouse(position, (ArrayList<Peca>) partida.getJogadorAdversario().getPecas());
        } else {
            return existePecaDoJogadorSobMouse(position, (ArrayList<Peca>) partida.getJogadorAdversario().getPecas());
        }

    }

    private void desenha() {
        imagemFundo.draw();
        partida.desenhaPecasJogadores();
        window.display();
    }

    private void trocaDeTurno() {
        partida.trocaDeTurno();
        System.out.println("Vez do jogador " + partida.getJogadorDaVez().getCor());
        casaSelecionada = null;
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
