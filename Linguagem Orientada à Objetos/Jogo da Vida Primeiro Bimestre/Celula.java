package unespar.com.br.trabalho.equipe_6;

public abstract class Celula implements Cloneable {
    
    protected int vida;
    protected abstract String simbolo();

    public Celula(int vida) {
        this.vida = vida;
    }

    public abstract Celula celulaProximaIteracao(int vizinhos);

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        if (vida == 0){
        return ".";
        }else{
            return simbolo();
        }
    }
    
    
    }
   
   


