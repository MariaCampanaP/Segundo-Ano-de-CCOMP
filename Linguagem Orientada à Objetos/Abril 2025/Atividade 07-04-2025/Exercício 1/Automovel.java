package br.edu.unespar.heranca;

public class Automovel extends Terrestre {

    private int numPortas;
    private String placa;

    public Automovel(int numRodas, int capacidade) {
        super(numRodas, capacidade);
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
