
package unespar.com.br.trabalho.equipe_6;
import java.util.Scanner;


public class Trabalho1Equipe_6 {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); //criação do scanner
        
        System.out.println("Insira as dimensoes do tabuleiro");
        
        int m = scanner.nextInt(); //escanea as dimensões da matriz
        int n = scanner.nextInt();
        int i, j;
        
        int tamanhoLinha = m+2;
        int tamanhoColuna = n+2;
        
        String[][] celulasBase = new String[tamanhoLinha][tamanhoColuna]; //cria o tabuleiro base das celulas
        int[][] vida = new int[tamanhoLinha][tamanhoColuna]; //cria o tabuleiro da vida das células
        
        System.out.println("Insira a o tabuleiro das celulas");
        
        for (i=1; i<tamanhoLinha-1; i++){
            for (j=1; j<tamanhoColuna-1; j++){
                celulasBase [i][j] = scanner.next(); //preenche a matriz das células
        
        }}
        System.out.println("Insira a o tabuleiro da vida");
               
        for (i=1; i<tamanhoLinha-1; i++){
            for (j=1; j<tamanhoColuna-1; j++){
                vida[i][j] = scanner.nextInt(); //preenche a matriz da vida
        
        }}
        
        Tabuleiro tabuleiro = new Tabuleiro(CriarTabuleiro(celulasBase, vida));//Cria um tabuleiro de céclulas
        
        System.out.println("Insira o numero de iteracoes");
        
        int numIteracoes = scanner.nextInt(); //escanea o número de iterações
        
        System.out.println(tabuleiro.toString());//realiza a impressão do tabuleiro base
        
        for(i=0;i<numIteracoes;i++){

            Tabuleiro ProximaIteracao = new Tabuleiro(tabuleiro.ProximaIteracao());//cria um tabuleiro novo, contendo a próxima iteração das células
            System.out.println(ProximaIteracao.toString());//imprime o tabuleiro novo
        }
               
    }
    
    private static Celula[][] CriarTabuleiro(String[][] TabuleiroCelulas, int[][]TabuleiroVida){//cria uma matriz de classe Célula a partir da entrada do usuário
        Celula[][] Tabuleiro = new Celula[TabuleiroVida.length][TabuleiroVida[0].length];
            for(int i=0;i<Tabuleiro.length;i++){
                for(int j=0;j<Tabuleiro[i].length;j++){
                    switch (TabuleiroCelulas[i][j]){
                        case "+":
                            Tabuleiro[i][j] = new CelulaClassica(TabuleiroVida[i][j]);
                            break;
                        case "@":
                            Tabuleiro[i][j] = new CelulaForte(TabuleiroVida[i][j]);
                            break;
                        case "&":
                            Tabuleiro[i][j] = new CelulaTimida(TabuleiroVida[i][j]);
                            break;
                        case "#":
                            Tabuleiro[i][j] = new CelulaMatematica(TabuleiroVida[i][j]);
                            break;
                        case null:
                            Tabuleiro[i][j] = new CelulaBorda(0);
                            break;
                        default:
                            Tabuleiro[i][j] = new CelulaBorda(0);
                            break;
                    }
                }
            }
        return Tabuleiro;
    }
            
}