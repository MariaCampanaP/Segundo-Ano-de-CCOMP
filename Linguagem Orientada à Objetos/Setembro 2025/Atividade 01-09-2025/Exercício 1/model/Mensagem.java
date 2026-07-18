package model;

public class Mensagem {

    private int codigo;

    private Usuario remetente;

    private Usuario destinatario;

    private String titulo;

    private String corpo;

    public Mensagem(int codigo, Usuario remetente, Usuario destinatario, String titulo, String corpo) {
        this.codigo = codigo;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.titulo = titulo;
        this.corpo = corpo;
    }

    public Mensagem(Usuario remetente, Usuario destinatario, String titulo, String corpo) {
        this.codigo = -1;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.titulo = titulo;
        this.corpo = corpo;
    }

    public int getCodigo() {
        return codigo;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    @Override
    public String toString() {
        return "(" + codigo + ", "
                + remetente + ", "
                + destinatario + ", "
                + titulo + ", "
                + corpo + ")";

    }

}
