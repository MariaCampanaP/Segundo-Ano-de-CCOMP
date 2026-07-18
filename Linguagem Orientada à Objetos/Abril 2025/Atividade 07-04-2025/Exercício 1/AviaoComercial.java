/* d) Implemente duas novas subclasses de Aereo: AviaoComercial e AviaoComHelice. Em cada classe, adicione dois atributos novos que são exclusivos de cada tipo de avião*/
package br.edu.unespar.heranca;

public class AviaoComercial extends Aereo {
    
    public AviaoComercial(int capacidade){
        super(capacidade);
    }
    
    protected int QuantidamascarasDeoxigenio;
    protected int QuantidadeAssentos;

    public AviaoComercial(int QuantidamascarasDeoxigenio, int QuantidadeAssentos, int capacidade) {
        super(capacidade);
        this.QuantidamascarasDeoxigenio = QuantidamascarasDeoxigenio;
        this.QuantidadeAssentos = QuantidadeAssentos;
    }
}
