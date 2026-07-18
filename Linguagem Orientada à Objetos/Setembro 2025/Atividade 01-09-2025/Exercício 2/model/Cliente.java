package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private transient List<AnimalDeEstimacao> animais; // Add transient

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.animais = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public List<AnimalDeEstimacao> getAnimais() { return animais; }

    public void adicionarAnimal(AnimalDeEstimacao animal) {
        this.animais.add(animal);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", animais=" + (animais != null ? animais.size() : 0) +
                '}';
    }
}