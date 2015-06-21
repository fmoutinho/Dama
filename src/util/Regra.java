/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Point;
import java.util.ArrayList;
import model.Casa;

import model.Peca;
import model.Tabuleiro;

/**
 *
 * @author Fernando
 */
public class Regra {

    public static boolean podeAndar(Casa casaPeca, Casa casaClicada) {

        if (casaPeca.getPeca().isDama()) {
            return damaPodeAndar(casaPeca, casaClicada);
        } else {
            if (casaPeca.getPeca().isSentidoSubindo()) {
                System.out.println("Peca selecionada (" + casaPeca.getPosicaoI() + ", " + casaPeca.getPosicaoJ() + ")");
                System.out.println("casa clicada (" + casaPeca.getPosicaoI() + ", " + casaPeca.getPosicaoJ() + ")");
                System.out.println("Subindo direita");

                return (casaPeca.getPosicaoI() == casaClicada.getPosicaoI() + 1
                        && casaClicada.getPosicaoJ() - casaPeca.getPosicaoJ() == 1)
                        || (casaPeca.getPosicaoI() == casaClicada.getPosicaoI() + 1
                        && Math.abs(casaClicada.getPosicaoJ() - casaPeca.getPosicaoJ()) == 1);
            } else {
                return casaPeca.getPosicaoI() == casaClicada.getPosicaoI() - 1
                        && Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 1;
            }
        }
    }

    private static boolean damaPodeAndar(Casa casaPeca, Casa casaClicada) {
        return Math.abs(casaPeca.getPosicaoI() - casaClicada.getPosicaoI()) == 1
                && Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 1;
    }

    private static boolean damaPodeComer(Casa casaPeca, Casa casaClicada) {
        return false;
    }

    public static boolean podeComer(Casa casaPeca, Casa casaClicada, Tabuleiro tab) {
        if (casaPeca.getPeca().isDama()) {
            return damaPodeComer(casaPeca, casaClicada);
        } else {
            if (casaPeca.getPeca().isSentidoSubindo()) {
                if ((casaPeca.getPosicaoI() == casaClicada.getPosicaoI() + 2)
                        && (Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 2)) {

                    Casa casaPecaComida = Regra.getCasaComida(casaPeca, casaClicada, tab);

                    if (casaPecaComida.getPeca() == null) {
                        return false;
                    } else {
                        return casaPecaComida.getPeca().isSentidoSubindo() != casaPeca.getPeca().isSentidoSubindo();
                    }
                }
                return false;

            } else {
                if ((casaPeca.getPosicaoI() == casaClicada.getPosicaoI() - 2)
                        && (Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 2)) {

                    Casa casaPecaComida = Regra.getCasaComida(casaPeca, casaClicada, tab);

                    if (casaPecaComida.getPeca() == null) {
                        return false;
                    } else {
                        return casaPecaComida.getPeca().isSentidoSubindo() != casaPeca.getPeca().isSentidoSubindo();
                    }
                }
                return false;
            }
        }
    }

    public static Casa getCasaComida(Casa casaPeca, Casa casaClicada, Tabuleiro tab) {
        if (casaPeca.getPeca().isDama()) {
            return null;
        } else {
            if (casaPeca.getPeca().isSentidoSubindo()) {
                int i = casaPeca.getPosicaoI() - 1;
                int j = casaClicada.getPosicaoJ() > casaPeca.getPosicaoJ()
                        ? casaPeca.getPosicaoJ() + 1 : casaPeca.getPosicaoJ() - 1;

                return tab.getCasaTabuleiro(i, j);
            } else {
                int i = casaPeca.getPosicaoI() + 1;
                int j = casaClicada.getPosicaoJ() > casaPeca.getPosicaoJ()
                        ? casaPeca.getPosicaoJ() + 1 : casaPeca.getPosicaoJ() - 1;

                return tab.getCasaTabuleiro(i, j);
            }
        }
    }

    public static boolean deveComer(ArrayList<Peca> pecas, ArrayList<Peca> pecas0) {
        //deve varrer os arrayLists e saber se há alguma peca que está em posiçao para comer uma adversaria ( a peça , com uma adversaria no caminho e um espaço em branco depois )
        return false;
    }

}
