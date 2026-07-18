package dto;

import java.util.Objects;
import model.Mensagem;

public class MensagemDTO {

    private int codigo;

    private int codRemetente;

    private int codDestinatario;

    private String titulo;

    private String corpo;

    public MensagemDTO(int codigo, int codRemetente, int codDestinatario, String titulo, String corpo) {
        this.codigo = codigo;
        this.codRemetente = codRemetente;
        this.codDestinatario = codDestinatario;
        this.titulo = titulo;
        this.corpo = corpo;
    }

    public MensagemDTO(Mensagem mensagem) {
        this.codigo = mensagem.getCodigo();
        this.codRemetente = mensagem.getRemetente().getCodigo();
        this.codDestinatario = mensagem.getDestinatario().getCodigo();
        this.titulo = mensagem.getTitulo();
        this.corpo = mensagem.getCorpo();
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCodRemetente() {
        return codRemetente;
    }

    public int getCodDestinatario() {
        return codDestinatario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass().equals(MensagemDTO.class)) {
                MensagemDTO mensagemDTO = (MensagemDTO) obj;
                return this.codigo == mensagemDTO.codigo
                        && this.codRemetente == mensagemDTO.codRemetente
                        && this.codDestinatario == mensagemDTO.codDestinatario
                        && this.titulo.equals(mensagemDTO.titulo)
                        && this.corpo.equals(mensagemDTO.corpo);
            }
        }
        return false;
    }

}
