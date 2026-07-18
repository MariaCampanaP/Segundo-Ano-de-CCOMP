package br.edu.unespar.heranca;

public class Transporte {

    protected int capacidade;

    public Transporte(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String toString() {
        return "Transporte com capacidade " + capacidade;
    }
}
