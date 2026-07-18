
package unespar.com.br.trabalho.equipe_6;
public class CelulaTimida extends Celula {

    public CelulaTimida(int vida) {
        super(vida);
    }
    
    @Override
    public Celula celulaProximaIteracao(int vizinhos) {
        int vida=this.vida;
        if (this.vida==0){//morta
            if(vizinhos<1){
                vida = 1;
            }
        }else{//viva
            if(vizinhos>0){
                vida = 0;
            }
        }
        Celula ProximaCelula = new CelulaTimida(vida);
        return ProximaCelula;
    }
    
    @Override
    public String simbolo() {
        return "&";
    }
    
}
