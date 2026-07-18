package model;

public class Usuario {

    private int codigo;

    private String login;

    private String senha;

    private String nome;

    public Usuario(String login, String senha, String nome) {
        this.codigo = -1;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario(int codigo, String login, String senha, String nome) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "(" + codigo + ", " + login + ", " + senha + ", " + nome + ")";
    }

}
