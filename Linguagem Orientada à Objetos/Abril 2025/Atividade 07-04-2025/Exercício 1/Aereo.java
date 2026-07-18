/* b) Implemente a subclasse Aereo, com o atributo alturaDeVoo do tipo inteiro*/
package br.edu.unespar.heranca;

public class Aereo extends Transporte {

    public Aereo(int capacidade) {
        super(capacidade);
    }
    
    protected int alturaDeVoo;
    
}
