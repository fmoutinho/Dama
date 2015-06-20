/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Point;
import java.util.ArrayList;

import model.Peca;

/**
 *
 * @author Fernando
 */
public class Regra {

    public static boolean podeAndar(Peca pecaSelecionada, Point position) {

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
                        && position.y <= pecaSelecionada.getPosition().y + 152))) {
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
                        && position.x <= pecaSelecionada.getPosition().x + 152))) {

                    return true;
                }
            }
            return false;
        }
    }

    private static boolean damaPodeAndar(Peca pecaSelecionada, Point position) {
        if (((pecaSelecionada.getPosition().x + 72 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x + 152
                        && pecaSelecionada.getPosition().y + 72 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y + 152)
                        || (pecaSelecionada.getPosition().x - 88 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x - 8
                        && pecaSelecionada.getPosition().y + 72 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y + 152))
                ||
                ((pecaSelecionada.getPosition().x - 88 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x - 8
                        && pecaSelecionada.getPosition().y - 88 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y - 8)
                        || (pecaSelecionada.getPosition().y - 88 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y - 8
                        && pecaSelecionada.getPosition().x + 72 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x + 152))) {
            return true;
        }
        return false;
    }

    public static boolean podeComer(Peca pecaSelecionada, Peca pecaASerComida, ArrayList<Peca> pecas, ArrayList<Peca> pecas0) {
        //deve varrer os arrayLists e saber se há alguma peca no caminho da pecaSelecionada , em funcao da pecaAdversaria
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean deveComer(ArrayList<Peca> pecas, ArrayList<Peca> pecas0) {
        //deve varrer os arrayLists e saber se há alguma peca que está em posiçao para comer uma adversaria ( a peça , com uma adversaria no caminho e um espaço em branco depois )
        return false;
    }

}
