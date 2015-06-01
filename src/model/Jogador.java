/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author VíviannMagaly
 */
public class Jogador {
    
    public Jogador(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
        
        InstanciaPecas();
    }
         
    private Peca[] pecas = new Peca[12];
    private boolean sentidoSubindo;

    public boolean isSentidoSubindo() {
        return sentidoSubindo;
    }

    public void setSentidoSubindo(boolean sentidoSubindo) {
        this.sentidoSubindo = sentidoSubindo;
    }

    public Peca[] getPecas() {
        return pecas;
    }

    public void setPecas(Peca[] pecas) {
        this.pecas = pecas;
    }

    private void InstanciaPecas() {
        int x;
        int y; 
        String imagemPeca;
 
        if(this.sentidoSubindo) {
            y = 488;
            
            //roda apenas 3 vezes para preencher o x
            for(int i = 1; i <= 3; i++) {   
                x = 8;  
                //roda apenas 4 vezes para preencher o y
                for(int j = 1; j <= 4; j++) {
                    if(i == 2) {
                        x += 80;
                    }
                    
                    imagemPeca = "peca_vermelha.png";
                    Peca novaPeca = new Peca(x, y, this.sentidoSubindo, imagemPeca);
                    novaPeca.draw();
                    this.pecas[this.pecas.length] = novaPeca;
                    
                    x += 160;
                }
                y -= 80;
            }
        } else {
            y = 8;
            
            //roda apenas 3 vezes para preencher o x
            for(int i = 1; i <= 3; i++) {   
                x = 8;  
                //roda apenas 4 vezes para preencher o y
                for(int j = 1; j <= 4; j++) {
                    if((i % 2) != 0) {
                        x += 80;
                    }
                    
                    imagemPeca = "peca_azul.png";
                    Peca novaPeca = new Peca(x, y, this.sentidoSubindo, imagemPeca);
                    novaPeca.draw();
                    this.pecas[this.pecas.length] = novaPeca;
                    
                    x += 160;
                }
                y += 80;
            }
        }
    }    
}
