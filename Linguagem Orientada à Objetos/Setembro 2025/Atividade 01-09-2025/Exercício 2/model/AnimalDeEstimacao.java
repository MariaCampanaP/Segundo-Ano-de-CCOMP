package model;

public class AnimalDeEstimacao {
    private String nome;
    private String sexo;
    private String especie;
    private transient Cliente dono; // Add transient to break circular reference

    public AnimalDeEstimacao(String nome, String sexo, String especie, Cliente dono) {
        this.nome = nome;
        this.sexo = sexo;
        this.especie = especie;
        this.dono = dono;
    }

    public String getNome() { return nome; }
    public String getSexo() { return sexo; }
    public String getEspecie() { return especie; }
    public Cliente getDono() { return dono; }

    @Override
    public String toString() {
        return "Animal{" +
                "nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dono=" + (dono != null ? dono.getNome() : "N/A") +
                '}';
    }
}