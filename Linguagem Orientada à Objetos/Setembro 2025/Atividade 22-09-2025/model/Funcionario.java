package model;

import jakarta.persistence.Entity;

@Entity
public class Funcionario extends Pessoa {

    private String cargo;

    public Funcionario(String cargo, String nome) {
        super(nome);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
