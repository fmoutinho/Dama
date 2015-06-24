/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ia;

/**
 *
 * @author Fernando
 */
import java.util.ArrayList;


public class Minimax {

        private int profundidade;
        
        private ArrayList<Node>[] listaNohsPorNivel;
        
        public Minimax(int profundidade){
                
                // Cria��o do array de listas de n�s
                // �ndice do array = profundidade da lista
                
                this.profundidade = profundidade;
                this.listaNohsPorNivel = new ArrayList[profundidade+1]; 
                for(int i = 0; i < profundidade+1; i++){
                        listaNohsPorNivel[i] = new ArrayList<Node>();
                }
        }
        
        public void arvore(Tabuleiro tab, int n){
                arvore(tab, n, null);
        }

        public void arvore(Tabuleiro tab, Integer n, Node pai) {

                if(n >= 0){
                        Node nodetemp = new Node(profundidade - n);
                        nodetemp.setTab(tab);
                        nodetemp.setPai(pai);
                        listaNohsPorNivel[profundidade - n].add(nodetemp);
                        
                        if(pai != null) 
                                pai.getFilhos().add(nodetemp);
                        
                        if((profundidade - n) % 2 == 1)
                                tab = Tabuleiro.inverter(tab);
                        
                        ArrayList<Tabuleiro> tablist = tab.listarPossiveisJogadas();
                        for (int i = 0; i < tablist.size(); i++) {
                                if((profundidade - n) % 2 == 1)
                                        tablist.set(i, Tabuleiro.inverter(tablist.get(i)));
                                
                                arvore(tablist.get(i), n - 1, nodetemp);
                        }
                }
        }

        public void atribuirMinMax(Node noh) {

                if (noh.getFilhos().size() == 0) {
                        noh.setMinimax(Utilidade.getCusto(noh.getTab()));
                } else {
                        if (noh.getDepth() % 2 == 0) {
                                int max = -Integer.MAX_VALUE;
                                for (Node d : noh.getFilhos()) {
                                        if (d.getMinimax() ==-Integer.MAX_VALUE) {
                                                atribuirMinMax(d);
                                        }
                                        if (d.getMinimax() > max)
                                                max = d.getMinimax();
                                }
                                noh.setMinimax(max);
                        } else {
                                int min = Integer.MAX_VALUE;
                                for (Node d : noh.getFilhos()) {
                                        if (d.getMinimax() ==Integer.MAX_VALUE) {
                                                atribuirMinMax(d);
                                        }
                                        if (d.getMinimax() < min)
                                                min = d.getMinimax();
                                }
                                noh.setMinimax(min);
                        }
                }

        }

        public Integer mtdf(Tabuleiro root, Integer f, Integer d) {
                int g = f;
                int upperbound = +66666;
                int lowerbound = -66666;
                int beta;

                // repeat
                do {
                        if (g == lowerbound) {
                                beta = g + 1;
                        } else
                                beta = g;

                        // g = alphaBetaWithMemory(root, beta - 1, beta, d);
                        if (g < beta) {
                                upperbound = g;
                        } else
                                lowerbound = g;
                } while (lowerbound < upperbound);
                return g;
        }
        
        public Tabuleiro melhorJogada() {
                atribuirMinMax(listaNohsPorNivel[0].get(0)); //pega tabuleiro atual
                int valor = listaNohsPorNivel[0].get(0).getMinimax();
                int i;
                Tabuleiro tabuleiro = new Tabuleiro();
                
                for (i=0; i<listaNohsPorNivel[1].size(); i++) {
                        if (listaNohsPorNivel[1].get(i).getMinimax()==valor) {
                                tabuleiro = listaNohsPorNivel[1].get(i).getTab();
                                i = listaNohsPorNivel[1].size();
                        }
                }
                
                return tabuleiro;
                
        }
