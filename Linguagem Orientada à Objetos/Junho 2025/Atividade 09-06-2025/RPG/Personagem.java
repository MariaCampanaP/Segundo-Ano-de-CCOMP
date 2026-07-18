/* Crie uma classe, Personagem para representar os atributos do persongagem. A
classe deve conter, pelo menos, atributos para o nome, a classe (usando a Enum
acima) e o nível do personagem entre  1 e 100
*/

package com.mycompany.rpg;

public class Personagem {
    
    private String nome;
    private int nivel;
    private ClassePersonagens classe;
    
    public Personagem(String nome, int nivel, ClassePersonagens classe){
        this.nome = nome;
        this.nivel = nivel;
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if(nivel >= 1 && nivel <= 100){
            this.nivel = nivel;
        }else{
            throw new IllegalArgumentException("O nível deve estar entre 1 e 100.");
        }
    }

    public ClassePersonagens getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagens classe) {
        this.classe = classe;
    }
}
