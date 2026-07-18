/* c) Implemente duas novas subclasses de Aquatico: BarcoAVela e Iate. Em cada classe, adicione dois atributos novos que são exclusivos para cada tipo de veículo aquático*/

package br.edu.unespar.heranca;

public class BarcoAVela extends Aquatico {

    public BarcoAVela(int capacidade) {
        super(capacidade);
    }
    
    protected int Quantidademastro;
    protected boolean leme;

    public BarcoAVela(int Quantidademastro, boolean leme, int capacidade) {
        super(capacidade);
        this.Quantidademastro = Quantidademastro;
        this.leme = leme;
    }
  } 
