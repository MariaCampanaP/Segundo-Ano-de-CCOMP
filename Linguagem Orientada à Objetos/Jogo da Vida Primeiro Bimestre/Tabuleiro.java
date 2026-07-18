package unespar.com.br.trabalho.equipe_6;

public class Tabuleiro {
      
    public Celula[][] TabuleiroAtual;

    public Tabuleiro(Celula[][] TabuleiroAtual) {
        this.TabuleiroAtual = TabuleiroAtual;
    }
    
    public Celula[][] ProximaIteracao(){
        
        Celula[][]ProximaIteracao = new Celula[TabuleiroAtual.length][TabuleiroAtual[0].length];
        
        for(int i=0;i<TabuleiroAtual.length;i++){
            for(int j=0;j<TabuleiroAtual[i].length;j++){
                ProximaIteracao[i][j] = new CelulaBorda(0); //preenche o tabuleiro da proxima iteracao com celulas borda para não houver espaços nulos
            }
        }
        
        for(int i=1;i<TabuleiroAtual.length-1;i++){
            for(int j=1;j<TabuleiroAtual[i].length-1;j++){
                
                int vizinhos = (TabuleiroAtual[i-1][j-1].vida + TabuleiroAtual[i-1][j].vida + TabuleiroAtual[i-1][j+1].vida + TabuleiroAtual[i][j-1].vida + TabuleiroAtual[i][j+1].vida + TabuleiroAtual[i+1][j-1].vida + TabuleiroAtual[i+1][j].vida + TabuleiroAtual[i+1][j+1].vida);
                    
                ProximaIteracao[i][j] = TabuleiroAtual[i][j].celulaProximaIteracao(vizinhos);//Cria uma nova celula para o proximo tabuleiro
            }
        }
        
        for(int i=1;i<TabuleiroAtual.length-1;i++){
            for(int j=1;j<TabuleiroAtual[i].length-1;j++){
                        TabuleiroAtual[i][j] = ProximaIteracao[i][j];//Atualizacao do tabuleiro atual
            }
        }
        
        return ProximaIteracao;
    
    }

    @Override
    public String toString() {
        String retorno = "\n";
        
        for(int i=0;i<TabuleiroAtual.length;i++){
            for(int j=0;j<TabuleiroAtual[i].length;j++){
                retorno += TabuleiroAtual[i][j].toString() + ' ';
            }
            retorno += "\n";
        }
        return retorno;
    }
    
    
    
}
