/* a) Implemente a subclasse Aquatico, com o atributo profundidadeMinima, do tipo inteiro*/

package br.edu.unespar.heranca;

public class Aquatico extends Transporte {

    public Aquatico(int capacidade) {
        super(capacidade);
    }
    
    protected int profundidadeMinima;
    
}
