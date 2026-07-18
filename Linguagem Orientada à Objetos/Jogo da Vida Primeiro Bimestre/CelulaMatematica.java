package unespar.com.br.trabalho.equipe_6;

public class CelulaMatematica extends Celula{

    public CelulaMatematica(int vida) {
        super(vida);
    }
    
    @Override
    public Celula celulaProximaIteracao(int vizinhos) {
         int vida=this.vida;
        if (this.vida==0){//morta
            if(vizinhos%2==0){
                vida = 1;
            }
        }else{//viva
            if(vizinhos%2!=1){
                vida = 0;
            }
        }
        Celula ProximaCelula = new CelulaMatematica(vida);
        return ProximaCelula;
    }
    
    @Override
    public String simbolo() {
        return "#";
    }
    
}
