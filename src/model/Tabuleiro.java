package model;

import java.awt.Point;
import util.Constantes;

public class Tabuleiro {

    Casa[][] mapa = new Casa[Constantes.DIMENSAO_TABULEIRO][Constantes.DIMENSAO_TABULEIRO];

    public Tabuleiro() {
        int y = Constantes.Y_INICIAL_DESCENDO;
        int x;

        for (int i = 0; i < Constantes.DIMENSAO_TABULEIRO; i++) {
            x = Constantes.X_INICIAL_DESCENDO;
            for (int j = 0; j < Constantes.DIMENSAO_TABULEIRO; j++) {
                Point p = new Point(x, y);
                Casa casa = new Casa(p);
                casa.setPosicaoI(i);
                casa.setPosicaoJ(j);

                this.mapa[i][j] = casa;
                x += Constantes.DESLOCAMENTO_ANDAR;
            }
            y += Constantes.DESLOCAMENTO_ANDAR;
        }
    }

    public Casa getCasaTabuleiro(int i, int j) {
        return this.mapa[i][j];
    }
    
    public Casa getCasaTabuleiro(Point p) {
        for(int i = 0; 1 < Constantes.DIMENSAO_TABULEIRO; i++) {
            for(int j = 0; j < Constantes.DIMENSAO_TABULEIRO; j++) {
                
            }
        }
    }
}
