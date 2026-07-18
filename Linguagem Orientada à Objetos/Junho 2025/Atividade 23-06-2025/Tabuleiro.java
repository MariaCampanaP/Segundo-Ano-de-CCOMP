package unespar.com.br.trabalho.equipe_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Tabuleiro {
      
    public Celula[][] tabuleiroAtual;

    public Tabuleiro(Celula[][] tabuleiroAtual) {
        this.tabuleiroAtual = tabuleiroAtual;
    }
    
    public Celula[][] proximaIteracao(){
        
        Celula[][]proximaIteracao = new Celula[tabuleiroAtual.length][tabuleiroAtual[0].length];
        
        for(int i=0;i<tabuleiroAtual.length;i++){
            for(int j=0;j<tabuleiroAtual[i].length;j++){
                proximaIteracao[i][j] = new CelulaBorda(0); //preenche o tabuleiro da proxima iteracao com celulas borda para não houver espaços nulos
            }
        }
        
        for(int i=1;i<tabuleiroAtual.length-1;i++){
            for(int j=1;j<tabuleiroAtual[i].length-1;j++){
                
                int vizinhos = (tabuleiroAtual[i-1][j-1].vida + tabuleiroAtual[i-1][j].vida + tabuleiroAtual[i-1][j+1].vida + tabuleiroAtual[i][j-1].vida + tabuleiroAtual[i][j+1].vida + tabuleiroAtual[i+1][j-1].vida + tabuleiroAtual[i+1][j].vida + tabuleiroAtual[i+1][j+1].vida);
                    
                proximaIteracao[i][j] = tabuleiroAtual[i][j].celulaProximaIteracao(vizinhos);//Cria uma nova celula para o proximo tabuleiro
            }
        }
        
        for(int i=1;i<tabuleiroAtual.length-1;i++){
            for(int j=1;j<tabuleiroAtual[i].length-1;j++){
                        tabuleiroAtual[i][j] = proximaIteracao[i][j];//Atualizacao do tabuleiro atual
            }
        }
        
        return proximaIteracao;
    
    }

    public int getLinhas(){
        return tabuleiroAtual.length;
    }
    public int getColunas(){
        return tabuleiroAtual[0].length;
    }

    public String getTabuleiroCelulasBase(){
        String retorno = new String();

        for(int i = 1; i < tabuleiroAtual.length-1; i++){
            for(int j=1; j < tabuleiroAtual[0].length-1; j++){
            retorno += tabuleiroAtual[i][j].getSimbolo() + " ";
            }
            retorno += "\n";
        }
        return retorno;
    }

    public String getTabuleiroVida(){
        String retorno = new String();

        for(int i = 1; i < tabuleiroAtual.length-1; i++){
            for(int j=1; j < tabuleiroAtual[0].length-1; j++){
            retorno += tabuleiroAtual[i][j].getVida() + " ";
            }
            retorno += "\n";
        }
        return retorno;
    }

    @Override
    public String toString() {
        String retorno = "\n";
        
        for(int i=0;i<tabuleiroAtual.length;i++){
            for(int j=0;j<tabuleiroAtual[i].length;j++){
                retorno += tabuleiroAtual[i][j].toString() + ' ';
            }
            retorno += "\n";
        }
        return retorno;
    }
    
    public Tabuleiro(File arquivoTabuleiro) throws FileNotFoundException{
        Scanner scanner = new Scanner(arquivoTabuleiro);
        try{
        int m = scanner.nextInt()+2;
        int n = scanner.nextInt()+2;

        String[][] celulasBase = new String[m][n]; //cria o tabuleiro base das celulas
        int[][] vida = new int[m][n]; //cria o tabuleiro da vida das células
        
        for (int i=1; i<m-1; i++){
            for (int j=1; j<n-1; j++){
                celulasBase [i][j] = scanner.next(); //preenche a matriz das células
        
        }
    }

    for (int i=1; i<m-1; i++){
            for (int j=1; j<n-1; j++){
                vida[i][j] = scanner.nextInt(); //preenche a matriz da vida
        
        }
    }

    scanner.close();

    tabuleiroAtual = CriarTabuleiro(celulasBase, vida);//Cria um tabuleiro de células

        }catch (InputMismatchException erroInput){
            JOptionPane.showMessageDialog(null, "Erro na leitura do Arquivo!\nVerifique se o arquivo está escrito de acordo com a norma", "ERRO", JOptionPane.ERROR_MESSAGE);
        }catch (IndexOutOfBoundsException erroIndice){
            JOptionPane.showMessageDialog(null, "Erro na leitura do Arquivo!\nTamanho da string deve ser positivo!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException erroNulo){
            JOptionPane.showMessageDialog(null, "Erro na leitura do Arquivo!\nElemento nulo detectado, verifique se os números das linhas e colunas coincidem com o tamanho das matrizes", "ERRO", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro desconhecido na leitura do arquivo", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }

    private Celula[][] CriarTabuleiro(String[][] tabuleiroCelulas, int[][]tabuleiroVida){//cria uma matriz de classe Célula a partir da entrada do usuário
        
        Celula[][] tabuleiro = new Celula[tabuleiroVida.length][tabuleiroVida[0].length];
            for(int i=0;i<tabuleiro.length;i++){
                for(int j=0;j<tabuleiro[i].length;j++){
                    switch (tabuleiroCelulas[i][j]){
                        case "+":
                            tabuleiro[i][j] = new CelulaClassica(tabuleiroVida[i][j]);
                            break;
                        case "@":
                            tabuleiro[i][j] = new CelulaForte(tabuleiroVida[i][j]);
                            break;
                        case "&":
                            tabuleiro[i][j] = new CelulaTimida(tabuleiroVida[i][j]);
                            break;
                        case "#":
                            tabuleiro[i][j] = new CelulaMatematica(tabuleiroVida[i][j]);
                            break;
                        case null:
                            tabuleiro[i][j] = new CelulaBorda(0);
                            break;
                        default:
                            tabuleiro[i][j] = new CelulaBorda(0);
                            break;
                    }
                }
            }
        return tabuleiro;
    }

    
}
