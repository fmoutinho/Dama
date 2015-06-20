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
                && position.y <= pecaSelecionada.getPosition().y + 152)) //Caso a dama estiver subindo
                || ((pecaSelecionada.getPosition().x - 88 <= position.x
                && position.x <= pecaSelecionada.getPosition().x - 8
                && pecaSelecionada.getPosition().y - 88 <= position.y
                && position.y <= pecaSelecionada.getPosition().y - 8)
                || (pecaSelecionada.getPosition().y - 88 <= position.y
                && position.y <= pecaSelecionada.getPosition().y - 8
                && pecaSelecionada.getPosition().x + 72 <= position.x
                && position.x <= pecaSelecionada.getPosition().x + 152))) { //Caso a dama estiver descendo
            return true;
        }
        return false;
    }

    public static boolean podeComer(Peca pecaSelecionada, Peca pecaASerComida, ArrayList<Peca> pecasJogador, ArrayList<Peca> pecasAdversario) {
        //deve varrer os arrayLists e saber se há alguma peca no caminho da pecaSelecionada , em funcao da pecaAdversaria

        if (pecaSelecionada.getSentidoSubindo()) {
            if (pecaSelecionada.getPosition().y < pecaASerComida.getPosition().y) {

                System.out.println("1 ");
                return false;
            }

            if (pecaSelecionada.getPosition().x < pecaASerComida.getPosition().x) {

                for (Peca pecaVerificada : pecasJogador) {
                    if (!pecaSelecionada.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x + Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y - Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println(" 2 ");
                            return false;
                        }
                    }
                }

                for (Peca pecaVerificada : pecasAdversario) {
                    if (!pecaASerComida.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x + Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y - Constantes.DESLOCAMENTO_COMER)) {

                            System.out.println("3 ");
                            return false;
                        }
                    }
                }
            } else if (pecaSelecionada.getPosition().x > pecaASerComida.getPosition().x) {

                for (Peca pecaVerificada : pecasJogador) {
                    if (!pecaSelecionada.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x - Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y - Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println("4 ");
                            return false;
                        }
                    }
                }

                for (Peca pecaVerificada : pecasAdversario) {
                    if (!pecaASerComida.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x - Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y - Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println("5 ");
                            return false;
                        }
                    }
                }
            }
        } else {
            if (pecaSelecionada.getPosition().y > pecaASerComida.getPosition().y) {
                return false;
            }

            if (pecaSelecionada.getPosition().x < pecaASerComida.getPosition().x) {

                for (Peca pecaVerificada : pecasJogador) {
                    if (!pecaSelecionada.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x + Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y + Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println("6 ");
                            return false;
                        }
                    }
                }

                for (Peca pecaVerificada : pecasAdversario) {
                    if (!pecaASerComida.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x + Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y + Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println("7 ");
                            return false;
                        }
                    }
                }
            } else if (pecaSelecionada.getPosition().x > pecaASerComida.getPosition().x) {

                for (Peca pecaVerificada : pecasJogador) {
                    if (!pecaSelecionada.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x - Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y + Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println("8");
                            return false;
                        }
                    }
                }

                for (Peca pecaVerificada : pecasAdversario) {
                    if (!pecaASerComida.equals(pecaVerificada)) {
                        if ((pecaSelecionada.getPosition().x == pecaVerificada.getPosition().x - Constantes.DESLOCAMENTO_COMER)
                                && (pecaSelecionada.getPosition().y == pecaVerificada.getPosition().y + Constantes.DESLOCAMENTO_COMER)) {
                            System.out.println("9");
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println("10");
        return true;
    }

    public static boolean deveComer(ArrayList<Peca> pecasJogador, ArrayList<Peca> pecasAdversario) {
        //deve varrer os arrayLists e saber se há alguma peca que está em posiçao para comer uma adversaria ( a peça , com uma adversaria no caminho e um espaço em branco depois )
        boolean deveComer;
        for (Peca pecaJogador : pecasJogador) {

            deveComer = (deveComer(pecaJogador, pecasJogador, pecasAdversario));

            if (deveComer == true) {
                return deveComer;
            }

        }
        return false;
    }

    public static boolean deveComer(Peca pecaSelecionada, ArrayList<Peca> pecasJogador, ArrayList<Peca> pecasAdversario) {
        //To change body of generated methods, choose Tools | Templates.
        boolean deveComer;
        for (Peca pecaASerComida : pecasAdversario) {
            deveComer = podeComer(pecaSelecionada, pecaASerComida, pecasJogador, pecasAdversario);

            if (deveComer == true) {
                return deveComer;
            }
        }
        return false;
    }

}
