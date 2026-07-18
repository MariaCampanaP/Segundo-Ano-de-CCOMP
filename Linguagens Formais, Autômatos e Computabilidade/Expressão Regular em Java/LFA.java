package com.mycompany.lfa;
 
//Nome da Aluna: Maria Rita Campana Peixoto
//2° Ano de Ciência da Computação - UNESPAR

public class LFA {

    public static void main(String[] args) {
        ExpressaoRegular ER = new ExpressaoRegular();
        ER.confere(ER.linguagem01, "bb");
        ER.confere(ER.linguagem02, "aa");
        ER.confere(ER.linguagem03, "imagem_02.jpg");
        
        ER.confere(ER.letra, "f");
        ER.confere(ER.letras, "fulano");
        ER.confere(ER.letras, "Fulano");
        ER.confere(ER.nome, "Fulano de Town");
        ER.confere(ER.EMAIL, "fulanodeTown@gmail.com");
    }
}
