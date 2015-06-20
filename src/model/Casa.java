package model;

import java.awt.Point;

public class Casa {

    public Casa(Point point) {
        this.point = point;
    }

    private int posicaoI;
    private int posicaoJ;
    private Point point;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getPosicaoI() {
        return posicaoI;
    }

    public void setPosicaoI(int posicaoI) {
        this.posicaoI = posicaoI;
    }

    public int getPosicaoJ() {
        return posicaoJ;
    }

    public void setPosicaoJ(int posicaoJ) {
        this.posicaoJ = posicaoJ;
    }
}
