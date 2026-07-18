/* c) Implemente duas novas subclasses de Aquatico: BarcoAVela e Iate. Em cada classe, adicione dois atributos novos que são exclusivos para cata tipo de veículo aquático*/
 
package br.edu.unespar.heranca;

public class Iate extends Aquatico {
    
    public Iate(int capacidade){
        super(capacidade);
    }
    
    protected int numeroCabines;
    protected int numeroMotores;

    public Iate(int numeroCabines, int numeroMotores, int capacidade) {
        super(capacidade);
        this.numeroCabines = numeroCabines;
        this.numeroMotores = numeroMotores;
    } 
}
