package dto;

import java.util.List;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private List<String> animaisNomes; // apenas nomes dos animais para simplificar

    public ClienteDTO(String nome, String cpf, List<String> animaisNomes) {
        this.nome = nome;
        this.cpf = cpf;
        this.animaisNomes = animaisNomes;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public List<String> getAnimaisNomes() { return animaisNomes; }
}
