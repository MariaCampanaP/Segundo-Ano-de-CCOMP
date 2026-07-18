
package unespar.com.br.trabalho.equipe_6;

public class CelulaForte extends Celula {

    public CelulaForte(int vida) {
        super(vida);
    }
    
        @Override
    public Celula celulaProximaIteracao(int vizinhos) {
        int vida=this.vida;
        if (this.vida==0){//morta
            if(vizinhos>4){
                vida = 1;
            }
        }else{//viva
            if(vizinhos<2){
                vida = 0;
            }
        }
        Celula ProximaCelula = new CelulaForte(vida);
        return ProximaCelula;
    }
    
     @Override
    public String simbolo() {
        return "@";
    }
    
}
