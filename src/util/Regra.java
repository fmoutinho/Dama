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

/**
 *
 * @author Fernando
 */
public class Regra {

    public static boolean podeAndar(Casa casaPeca, Casa casaClicada) {

        if (casaPeca.getPeca().isDama()) {
            //return damaPodeAndar(pecaSelecionada, position);
            return false;
        } else {
            if(casaPeca.getPeca().getSentidoSubindo()) {
                return casaPeca.getPosicaoI() == casaClicada.getPosicaoI() - 1
                        && Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 1;
            } else {
                return casaPeca.getPosicaoI() == casaClicada.getPosicaoI() + 1
                        && Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 1;
            }
        }
    }

    private static boolean damaPodeAndar(Casa casaPeca, Casa casaClicada) {
        return Math.abs(casaPeca.getPosicaoI() - casaClicada.getPosicaoI()) == 1
                && Math.abs(casaPeca.getPosicaoJ() - casaClicada.getPosicaoJ()) == 1;
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
