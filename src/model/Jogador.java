/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;




import java.util.ArrayList;
import util.Constantes;


/**
 *
 * @author VíviannMagaly
 */
public class Jogador {


    private ArrayList<Peca> pecas = new ArrayList<>(12);

    private boolean sentidoSubindo;
    private String cor;
    private boolean jogadorDaVez;

    public Jogador(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
        if (sentidoSubindo) {
            this.cor = "Vermelho";
            this.jogadorDaVez = sentidoSubindo;
        } else {
            this.cor = "Azul";
            this.jogadorDaVez = sentidoSubindo;

        }
        InstanciaPecas();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isSentidoSubindo() {
        return sentidoSubindo;
    }

    public void setSentidoSubindo(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    private void InstanciaPecas() {
        int x;
        int y;
        String imagemPeca;
        int lenght = 0;

        if (this.sentidoSubindo) {
            y = Constantes.Y_INICIAL_SUBINDO;

            //roda apenas 3 vezes para preencher o x
            for (int i = 1; i <= 3; i++) {
                x = Constantes.X_INICIAL_SUBINDO;

                if (i == 2) {
                    x += 80;
                }
                //roda apenas 4 vezes para preencher o y
                for (int j = 1; j <= 4; j++) {

                    imagemPeca = "peca_vermelha.png";
                    Peca novaPeca = new Peca(x, y, this.sentidoSubindo, imagemPeca);
                    this.pecas.add(lenght, novaPeca);
                    lenght++;

                    x += 2 * Constantes.DESLOCAMENTO_ANDAR;
                }
                y -= Constantes.DESLOCAMENTO_ANDAR;
            }
        } else {
            y = Constantes.Y_INICIAL_DESCENDO;

            //roda apenas 3 vezes para preencher o x
            for (int i = 1; i <= 3; i++) {
                x = Constantes.X_INICIAL_DESCENDO;

                if (i == 1 || i == 3) {
                    x += Constantes.DESLOCAMENTO_ANDAR;
                }

                //roda apenas 4 vezes para preencher o y
                for (int j = 1; j <= 4; j++) {
                    imagemPeca = "peca_azul.png";
                    Peca novaPeca = new Peca(x, y, this.sentidoSubindo, imagemPeca);
                    this.pecas.add(lenght, novaPeca);
                    lenght++;

                    x += 2 * Constantes.DESLOCAMENTO_ANDAR;
                }
                y += Constantes.DESLOCAMENTO_ANDAR;
            }
        }
    }



    public void desenhaPecas() {
        for (Peca p : pecas) {
            p.draw();
        }
    }

    public boolean isJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(boolean jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

}
