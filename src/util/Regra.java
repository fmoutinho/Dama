/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Point;

import model.Peca;

/**
 *
 * @author Fernando
 */
public class Regra {

    public static boolean podeAndar(Peca pecaSelecionada, Point position, Peca[] pecasAdversario, Peca[] pecasJogador) {
        if (deveComer(pecaSelecionada, position, pecasAdversario)) {
            return false;
        } else {
            if (pecaSelecionada.isDama()) {
                return damaPodeAndar(pecaSelecionada, position);

            } else {
                if (!pecaSelecionada.getSentidoSubindo()) { //Descendo a direita || Descendo a esquerda
                    if (((pecaSelecionada.getPosition().x + 72 <= position.x
                            && position.x <= pecaSelecionada.getPosition().x + 152
                            && pecaSelecionada.getPosition().y + 72 <= position.y
                            && position.y <= pecaSelecionada.getPosition().y + 152)
                            || (pecaSelecionada.getPosition().x - 88 <= position.x
                            && position.x <= pecaSelecionada.getPosition().x - 8
                            && pecaSelecionada.getPosition().y + 72 <= position.y
                            && position.y <= pecaSelecionada.getPosition().y + 152)) ) {
                        return true;

                    }
                } else {//Subindo a esquerda || Subindo a direita
                    if (((pecaSelecionada.getPosition().x - 88 <= position.x
                            && position.x <= pecaSelecionada.getPosition().x - 8
                            && pecaSelecionada.getPosition().y - 88 <= position.y
                            && position.y <= pecaSelecionada.getPosition().y - 8)
                            || (pecaSelecionada.getPosition().y - 88 <= position.y
                            && position.y <= pecaSelecionada.getPosition().y - 8
                            && pecaSelecionada.getPosition().x + 72 <= position.x
                            && position.x <= pecaSelecionada.getPosition().x + 152)) ) {

                        return true;
                    }
                }
                return false;
            }
        }
    }

    public static boolean naoHaPecaAdversariaNaFrente(Point mousePosition, Peca[] pecasAdversario) {

        for (Peca pecaAdversaria : pecasAdversario) {
            if ((mousePosition.getX() >= pecaAdversaria.getPosition().x)
                    && (mousePosition.getX() <= pecaAdversaria.getPosition().x + pecaAdversaria.getWidth())
                    && (mousePosition.getY() >= pecaAdversaria.getPosition().y)
                    && (mousePosition.getY() <= pecaAdversaria.getPosition().y + pecaAdversaria.getHeight())) {
                return false;
            }
        }
        return true;
    }
    /*public static boolean podeComer(Peca pecaSelecionada, Point position, Peca[] pecasAdversario) {
     for (for (Peca p : pecas)) {
     return true;
     }
     }*/

    private static boolean damaPodeAndar(Peca pecaSelecionada, Point position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean deveComer(Peca pecaSelecionada, Point position, Peca[] pecasAdversario) {
        return false;
    }

    public static boolean existePecaNaFrente(Peca pecaSelecionada, Point mousePosition, Peca[] pecasAdversario, Peca[] pecasJogador) {

        return existePecaPropriaNaFrente(pecaSelecionada, pecasJogador) || existePecaAdversariaNaFrente(pecaSelecionada, pecasAdversario);

    }

    public static boolean existePecaAdversariaNaFrente(Peca pecaSelecionada, Peca[] pecasAdversario) {

        for (Peca pecaAdversaria : pecasAdversario) {
            if (!pecaSelecionada.getSentidoSubindo()) { //Descendo a direita || Descendo a esquerda
                if ((pecaSelecionada.getPosition().x + 72 <= pecaAdversaria.getPosition().x
                        && pecaAdversaria.getPosition().x <= pecaSelecionada.getPosition().x + 152
                        && pecaSelecionada.getPosition().y + 72 <= pecaAdversaria.getPosition().y
                        && pecaAdversaria.getPosition().y <= pecaSelecionada.getPosition().y + 152)
                        && (pecaSelecionada.getPosition().x - 88 <= pecaAdversaria.getPosition().x
                        && pecaAdversaria.getPosition().x <= pecaSelecionada.getPosition().x - 8
                        && pecaSelecionada.getPosition().y + 72 <= pecaAdversaria.getPosition().y
                        && pecaAdversaria.getPosition().y <= pecaSelecionada.getPosition().y + 152)) {
                    return true;

                }
            } else {//Subindo a esquerda || Subindo a direita
                if ((pecaSelecionada.getPosition().x - 88 <= pecaAdversaria.getPosition().x
                        && pecaAdversaria.getPosition().x <= pecaSelecionada.getPosition().x - 8
                        && pecaSelecionada.getPosition().y - 88 <= pecaAdversaria.getPosition().y
                        && pecaAdversaria.getPosition().y <= pecaSelecionada.getPosition().y - 8)
                        && (pecaSelecionada.getPosition().y - 88 <= pecaAdversaria.getPosition().y
                        && pecaAdversaria.getPosition().y <= pecaSelecionada.getPosition().y - 8
                        && pecaSelecionada.getPosition().x + 72 <= pecaAdversaria.getPosition().x
                        && pecaAdversaria.getPosition().x <= pecaSelecionada.getPosition().x + 152)) {

                    return true;
                }
            }

        }
        return false;

    }

    public static boolean existePecaPropriaNaFrente(Peca pecaSelecionada, Peca[] pecasJogador) {

        for (Peca pecaJogador : pecasJogador) {
            if (!pecaJogador.equals(pecaSelecionada)) {
                if (!pecaSelecionada.getSentidoSubindo()) { //Descendo a direita || Descendo a esquerda
                    if ((pecaSelecionada.getPosition().x + 72 <= pecaJogador.getPosition().x
                            && pecaJogador.getPosition().x <= pecaSelecionada.getPosition().x + 152
                            && pecaSelecionada.getPosition().y + 72 <= pecaJogador.getPosition().y
                            && pecaJogador.getPosition().y <= pecaSelecionada.getPosition().y + 152)
                            || (pecaSelecionada.getPosition().x - 88 <= pecaJogador.getPosition().x
                            && pecaJogador.getPosition().x <= pecaSelecionada.getPosition().x - 8
                            && pecaSelecionada.getPosition().y + 72 <= pecaJogador.getPosition().y
                            && pecaJogador.getPosition().y <= pecaSelecionada.getPosition().y + 152)) {
                        return true;

                    }
                } else {//Subindo a esquerda || Subindo a direita
                    if ((pecaSelecionada.getPosition().x - 88 <= pecaJogador.getPosition().x
                            && pecaJogador.getPosition().x <= pecaSelecionada.getPosition().x - 8
                            && pecaSelecionada.getPosition().y - 88 <= pecaJogador.getPosition().y
                            && pecaJogador.getPosition().y <= pecaSelecionada.getPosition().y - 8)
                            || (pecaSelecionada.getPosition().y - 88 <= pecaJogador.getPosition().y
                            && pecaJogador.getPosition().y <= pecaSelecionada.getPosition().y - 8
                            && pecaSelecionada.getPosition().x + 72 <= pecaJogador.getPosition().x
                            && pecaJogador.getPosition().x <= pecaSelecionada.getPosition().x + 152)) {

                        return true;
                    }
                }
            }
        }
        return false;

    }

}
