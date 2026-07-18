package unespar.com.br.trabalho.equipe_6;

public class CelulaClassica extends Celula {

    public CelulaClassica(int vida) {
        super(vida);
    }

    @Override
    public Celula celulaProximaIteracao(int vizinhos) {
        int vida=this.vida;
        if (this.vida==0){//morta
            if(vizinhos==3){
                vida = 1;
            }
        }else{//viva
            if(vizinhos<2||vizinhos>3){
                vida = 0;
            }
        }
        Celula ProximaCelula = new CelulaClassica(vida);
        return ProximaCelula;
    }
     
    @Override
    public String simbolo() {
        return "+";
    }
    
}
