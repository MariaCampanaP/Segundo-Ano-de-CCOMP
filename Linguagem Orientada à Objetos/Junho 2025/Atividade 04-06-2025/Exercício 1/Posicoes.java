package com.mycompany.rosadosventos;

public enum Posicoes {
    
    //Pontos Cardeais
    NORTE("Norte"), SUL("Sul"), LESTE("Leste"), OESTE("oeste"),
    
    //Pontos Colaterais
    NORDESTE("Nordeste"), SUDESTE("Sudeste"), SUDOESTE("Sudoeste"), NOROESTE("Noroeste");
    
    private String pontos;
    
    Posicoes(String pontos){
        this.pontos = pontos;
    }
    
    @Override
    public String toString(){
        return pontos;
    }
}