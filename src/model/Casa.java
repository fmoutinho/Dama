package model;

import java.awt.Point;

public class Casa {

    public Casa(Point point) {
        this.point = point;
    }

    private int posicaoI;
    private int posicaoJ;
    private Point point;
    private Peca peca;

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

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
    
    public boolean temPeca() {
        if(this.peca != null) {
            return true;
        }
        return false;
    }
    
    public boolean temMesmoSentido(Casa casa) {
        if(casa.temPeca() && this.temPeca()) {
            return casa.getPeca().isSentidoSubindo() == this.getPeca().isSentidoSubindo();
        }
        return false;
    }
}
