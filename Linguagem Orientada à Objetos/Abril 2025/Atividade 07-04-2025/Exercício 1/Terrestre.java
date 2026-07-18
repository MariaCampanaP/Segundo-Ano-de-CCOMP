package br.edu.unespar.heranca;

public class Terrestre extends Transporte {

    protected int numRodas;

    public Terrestre(int numRodas, int capacidade) {
        super(capacidade);
        this.numRodas = numRodas;
    }

    public int getNumRodas() {
        return numRodas;
    }

    public void setNumRodas(int numRodas) {
        this.numRodas = numRodas;
    }

}
