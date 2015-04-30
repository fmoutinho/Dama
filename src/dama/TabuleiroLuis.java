package dama;

public class TabuleiroLuis {
    public TabuleiroLuis() {
        Casa[][] casa = new Casa[8][8];
        int contador = 1;
        for(int x = 0; x < 8; x++ ) {
           for (int y = 0; y < 8; y++) {
               if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
                   casa[x][y] = new Casa("quadrado_branco.png");
               } else {
                   casa[x][y] = new Casa("quadrado_preto.png");
               }

               casa[x][y].imagem.setPosition(x * 80, y * 80);
               casa[x][y].imagem.draw();
               casa[x][y].setNumero(contador);

               contador++;
            }
        }
    }
}
