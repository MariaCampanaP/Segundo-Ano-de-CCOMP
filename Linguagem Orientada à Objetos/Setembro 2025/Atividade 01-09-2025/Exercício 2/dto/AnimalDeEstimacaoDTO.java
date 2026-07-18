package dto;

public class AnimalDeEstimacaoDTO {
    private String nome;
    private String sexo;
    private String especie;
    private String donoCpf; // referência pelo CPF

    public AnimalDeEstimacaoDTO(String nome, String sexo, String especie, String donoCpf) {
        this.nome = nome;
        this.sexo = sexo;
        this.especie = especie;
        this.donoCpf = donoCpf;
    }

    public String getNome() { return nome; }
    public String getSexo() { return sexo; }
    public String getEspecie() { return especie; }
    public String getDonoCpf() { return donoCpf; }
}
