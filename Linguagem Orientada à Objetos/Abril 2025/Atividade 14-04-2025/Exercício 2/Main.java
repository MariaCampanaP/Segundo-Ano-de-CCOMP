package br.edu.unespar.lista.vetor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ListaVetor lista = new ListaVetor();
        for (int i = 1; i <= 10; i++) {
            lista.inserir(i);
        }
        /*Para testar se seu código funciona. Por enquanto, não imprime nada.
        Seu código deve imprimir os números de 1 a 10 após implementar a classe
        IteradorListaVetor corretamente
         */
        for (Integer elemento : lista) {
            System.out.println(elemento);
        }
    }

}
