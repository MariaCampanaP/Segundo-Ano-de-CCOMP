/*
A classe String possui um método charAt(int index) que recebe um inteiro index e retorna o caractere
da String na posição index. Crie um programa que lê uma string s e um índice i da entrada padrão e imprime o 
i-ésimo caractere de s. Seu programa deve tratar a exceção IndexOutOfBoundsException que pode ocorrer durante a sua 
execução, imprimindo uma mensagem de erro.
*/
package com.mycompany.stringoutofbounds;

import java.util.Scanner;

public class StringOutOfBounds {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int i = scanner.nextInt();

        
        try{
            System.out.println(s.charAt(i));      
        } catch (IndexOutOfBoundsException e){
            System.out.println("Deu ruim, i-ésimo termo nao existe!");
        }
    }
}
