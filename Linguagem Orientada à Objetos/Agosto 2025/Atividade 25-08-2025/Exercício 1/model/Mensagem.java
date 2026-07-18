package model;

public class Mensagem {
    private int codigo;
    private String titulo;
    private String corpo;
    private Usuario remetente;
    private Usuario destinatario;
    
    public Mensagem(int codigo, String titulo, String corpo, Usuario remetente, Usuario destinatario){
        this.codigo = codigo;
        this.titulo = titulo;
        this.corpo = corpo;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    
    public Mensagem(String titulo, String corpo, Usuario remetente, Usuario destinatario){
        this.codigo = -1;
        this.titulo = titulo;
        this.corpo = corpo;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getCorpo(){
        return corpo;
    }
    
    public Usuario getRemetente(){
        return remetente;
    }
    
    public Usuario getDestinatario(){
        return destinatario;
    }
    
    @Override
    public String toString(){
        return "[" + codigo + "] " + titulo + " (de: " + remetente.getNome() + " para: " + destinatario.getNome() + ")";
    }
}
