/* d) Implemente duas novas subclasses de Aereo: AviaoComercial e AviaoComHelice. Em cada classe, adicione dois atributos novos que são exclusivos de cada tipo de avião*/

package br.edu.unespar.heranca;

public class AviaoComHelice extends Aereo {
    
    public AviaoComHelice(int capacidade){
        super(capacidade);
    }
    
    protected int Quantidadehelice;
    protected boolean Motor;

    public AviaoComHelice(int Quantidadehelice, boolean Motor, int capacidade) {
        super(capacidade);
        this.Quantidadehelice = Quantidadehelice;
        this.Motor = Motor;
    } 
}
