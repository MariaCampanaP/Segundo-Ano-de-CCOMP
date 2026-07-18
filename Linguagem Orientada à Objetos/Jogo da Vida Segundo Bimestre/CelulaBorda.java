
package unespar.com.br.trabalho.equipe_6;

public class CelulaBorda extends Celula {

    public CelulaBorda(int vida) {
        super(vida);
    }

    @Override
    public Celula celulaProximaIteracao(int vizinhos) {
        Celula ProximaCelula = new CelulaBorda(vida);
        return ProximaCelula;
    }
    
    @Override
    public String simbolo() {
        return ".";
    }
    
}
    

