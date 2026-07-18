package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

    @Column(unique = true, nullable = false)
    private String email;

    public Cliente(String email, String nome) {
        super(nome);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
