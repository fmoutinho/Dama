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

    public static boolean podeAndar(Peca peca) {
        return true;
    }

   public static boolean podeAndar(Peca pecaSelecionada, Point position, Peca[] pecasAdversario) {
        if (pecaSelecionada.isDama()) {
            return damaPodeAndar(pecaSelecionada, position);

        } else {
            if (!pecaSelecionada.getSentidoSubindo()) { //Descendo a direita || Descendo a esquerda
                if ((pecaSelecionada.getPosition().x + 72 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x + 152
                        && pecaSelecionada.getPosition().y + 72 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y + 152)
                        || (pecaSelecionada.getPosition().x - 88 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x - 8
                        && pecaSelecionada.getPosition().y + 72 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y + 152))
                    {
                    return true;
                }
            } else {//Subindo a esquerda || Subindo a direita
                if ((pecaSelecionada.getPosition().x - 88 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x - 8
                        && pecaSelecionada.getPosition().y - 88 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y - 8)
                        || (pecaSelecionada.getPosition().y - 88 <= position.y
                        && position.y <= pecaSelecionada.getPosition().y - 8
                        && pecaSelecionada.getPosition().x + 72 <= position.x
                        && position.x <= pecaSelecionada.getPosition().x + 152)) {
                    return true;

                }
            }
            return false;
        }
    }
   
    public static boolean naoHaPecaAdversariaNaFrente(Point mousePosition, Peca[] pecasAdversario) {
        
        for (Peca pecaAdversaria : pecasAdversario) {
            if ((mousePosition.getX() == pecaAdversaria.getPosition().x)
                    && (mousePosition.getX() == pecaAdversaria.getPosition().x + pecaAdversaria.getWidth())
                    && (mousePosition.getY() == pecaAdversaria.getPosition().y)
                    && (mousePosition.getY() == pecaAdversaria.getPosition().y + pecaAdversaria.getHeight())) {
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

}
