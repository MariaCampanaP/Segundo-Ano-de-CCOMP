/* Crie uma Enum com as classes de personagens que serão permitidas, como por 
exemplo, MAGO, BARBARO e GUERREIRO
*/

package com.mycompany.rpg;

public enum ClassePersonagens {
    
    //Classes Possíveis
    GUERREIRO("Guerreiro"), LADINO("Ladino"), MAGO("Mago"), CLERIGO("Clérigo"),
    PALADINO("Paladino"), CRUZADOR("Cruzador"), BARBARO("Bárbaro"), NECROMANTE("Necromante"),
    DUELISTA("Duelista"), MONGE("Monge"), ARQUEIRO("Arqueiro"), CURANDEIRO("Curandeiro");
    
    private String nome;
    
    ClassePersonagens(String nome){
        this.nome = nome;
    }
    
    @Override 
    public String toString(){
        return nome;
    }
}
