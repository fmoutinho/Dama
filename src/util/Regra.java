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
                return (casaPeca.getPosicaoI() == casaClicada.getPosicaoI() + 1
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

    private static boolean damaPodeComer(Casa casaPeca, Casa casaClicada, Tabuleiro tab) {
        if (Math.abs(casaPeca.getPosicaoI() - casaClicada.getPosicaoI()) == 2
                && Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 2) {

            Casa casaComida = Regra.getCasaComida(casaPeca, casaClicada, tab);

            if (!casaComida.temPeca()) {
                return false;
            } else {
                return !casaPeca.temMesmoSentido(casaComida);
            }
        }
        return false;
    }

    public static boolean podeComer(Casa casaPeca, Casa casaClicada, Tabuleiro tab) {
        if (casaPeca.getPeca().isDama()) {
            return damaPodeComer(casaPeca, casaClicada, tab);
        } else {
            if (casaPeca.getPeca().isSentidoSubindo()) {
                if ((casaPeca.getPosicaoI() == casaClicada.getPosicaoI() + 2)
                        && (Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 2)) {

                    Casa casaPecaComida = Regra.getCasaComida(casaPeca, casaClicada, tab);

                    if (!casaPecaComida.temPeca()) {
                        return false;
                    } else {
                        return !casaPeca.temMesmoSentido(casaPecaComida);
                    }
                }
                return false;

            } else {
                if ((casaPeca.getPosicaoI() == casaClicada.getPosicaoI() - 2)
                        && (Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 2)) {

                    Casa casaPecaComida = Regra.getCasaComida(casaPeca, casaClicada, tab);

                    if (!casaPecaComida.temPeca()) {
                        return false;
                    } else {
                        return !casaPeca.temMesmoSentido(casaPecaComida);
                    }
                }
                return false;
            }
        }
    }

    public static Casa getCasaComida(Casa casaPeca, Casa casaClicada, Tabuleiro tab) {
        int i;
        if (casaPeca.getPeca().isDama()) {
            i = casaClicada.getPosicaoI() > casaPeca.getPosicaoI()
                    ? casaPeca.getPosicaoI() + 1 : casaPeca.getPosicaoI() - 1;
        } else {
            if (casaPeca.getPeca().isSentidoSubindo()) {
                i = casaPeca.getPosicaoI() - 1;
            } else {
                i = casaPeca.getPosicaoI() + 1;
            }
        }

        int j = casaClicada.getPosicaoJ() > casaPeca.getPosicaoJ()
                ? casaPeca.getPosicaoJ() + 1 : casaPeca.getPosicaoJ() - 1;

        return tab.getCasaTabuleiro(i, j);
    }

    public static boolean deveComer(ArrayList<Peca> pecas, Tabuleiro tab) {
        //deve varrer os arrayLists e saber se há alguma peca que está em posiçao para comer uma adversaria ( a peça , com uma adversaria no caminho e um espaço em branco depois )
        for (Peca p : pecas) {

            Point point = new Point((int) p.getPosition().x, (int) p.getPosition().y);
            Casa casaPeca = tab.getCasaTabuleiro(point);

            boolean deveComer = aindaDeveComer(casaPeca, tab);

            if (deveComer) {
                return true;
            }
        }
        return false;
    }

    public static boolean aindaDeveComer(Casa casa, Tabuleiro tab) {

        if (casa.getPeca().isDama()) {
            return aindaDeveComerSubindo(casa, tab) || aindaDeveComerDescendo(casa, tab);
        } else if (casa.getPeca().isSentidoSubindo()) {
            return aindaDeveComerSubindo(casa, tab);
        } else {
            return aindaDeveComerDescendo(casa, tab);
        }
    }

    private static boolean aindaDeveComerDescendo(Casa casa, Tabuleiro tab) {
        int i = casa.getPosicaoI() + 1;

        int j1 = casa.getPosicaoJ() + 1;
        int j2 = casa.getPosicaoJ() - 1;

        Casa casaAdjacenteij1 = tab.getCasaTabuleiro(i, j1);
        Casa casaAdjacenteij2 = tab.getCasaTabuleiro(i, j2);

        Casa c;

        if (casaAdjacenteij1 != null
                && casaAdjacenteij1.temPeca()
                && !casa.temMesmoSentido(casaAdjacenteij1)) {

            c = tab.getCasaTabuleiro(i + 1, j1 + 1);
            return c != null && !c.temPeca();
        } else if (casaAdjacenteij2 != null
                && casaAdjacenteij2.temPeca()
                && !casa.temMesmoSentido(casaAdjacenteij2)) {

            c = tab.getCasaTabuleiro(i + 1, j2 - 1);
            return c != null && !c.temPeca();
        }
        return false;
    }

    private static boolean aindaDeveComerSubindo(Casa casa, Tabuleiro tab) {
        int i = casa.getPosicaoI() - 1;

        int j1 = casa.getPosicaoJ() + 1;
        int j2 = casa.getPosicaoJ() - 1;

        Casa casaAdjacenteij1 = tab.getCasaTabuleiro(i, j1);
        Casa casaAdjacenteij2 = tab.getCasaTabuleiro(i, j2);

        Casa c;

        if (casaAdjacenteij1 != null
                && casaAdjacenteij1.temPeca()
                && !casa.temMesmoSentido(casaAdjacenteij1)) {

            c = tab.getCasaTabuleiro(i - 1, j1 + 1);
            return c != null && !c.temPeca();
        } else if (casaAdjacenteij2 != null
                && casaAdjacenteij2.temPeca()
                && !casa.temMesmoSentido(casaAdjacenteij2)) {

            c = tab.getCasaTabuleiro(i - 1, j2 - 1);
            return c != null && !c.temPeca();
        }
        return false;
    }
}
