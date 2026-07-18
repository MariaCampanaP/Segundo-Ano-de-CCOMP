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

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass().equals(Usuario.class)) {
                Usuario usuario = (Usuario) obj;
                return this.codigo == usuario.codigo
                        && this.login.equals(usuario.login)
                        && this.senha.equals(usuario.senha)
                        && this.nome.equals(usuario.nome);
            }
        }
        return false;

    }

}
