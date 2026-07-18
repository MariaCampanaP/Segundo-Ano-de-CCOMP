package com.mycompany.lfa;

public class ExpressaoRegular {

    public String linguagem01 = "(a|b)*(aa|bb)";
    public String linguagem02 = "a(a|b)*a";
    public String linguagem03 = "(.)*(\\.png|\\.jpe?g|\\.bmp)";
    
    public String letra = "[A-Z,-z]";
    public String letras = letra + "*";
    
    public String branco = "[\n|\t|\b|\r|\s]"; //quebra de linha, tab, backspace, return, space.
    public String brancos = branco + "*";
    
    public String nome = "(" + letras + "|" + brancos + ")*";
    public String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    public static void confere(String exp, String sentenca) {
        if (sentenca.matches(exp)) {
            System.out.println("Sentenca: " + sentenca + " - Aceita");
        } else {
            System.out.println("Sentenca: " + sentenca + " - Rejeita");
        }
    }
}