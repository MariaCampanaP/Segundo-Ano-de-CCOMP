/* FizzBuzz (um problema comum em entrevistas de emprego). Escreva código (para esse problema, basta um método main) para imprimir os 100 primeiros números "FizzBuzz". As regras são as seguintes:
 -> Se um número não é divisível por 3 ou 5, então basta imprimir o número.
 -> Se um número é divisível por 3, esse deve ser subtituído pela String Fizz.
 -> Se um número é divisível por 5, esse deve ser substituído pela string Buzz.
 -> Números que são divisíveis por 3 e 5 devem ser subtituídos pela strings FizzBuzz.
*/

package com.mycompany.exercicio1;

public class Exercicio1 {

    public static void main(String[] args) {
        
        for(int i = 0; i <= 100; i++){
            /*Se o número não é divisível por 3 ou 5*/
            if(i % 3 != 0 && i % 5 != 0){
                System.out.println(i);
            /*Se o número for divisível por 3*/
            }else if(i % 3 == 0 && i % 5 != 0){
                System.out.println("Fizz");
            /*Se o número for divisível por 5*/
            }else if(i % 3 != 0 && i % 5 == 0){
                System.out.println("Buzz");
            /*Se o número for divisível por 3 e 5*/
            }else if(i % 3 == 0 && i % 5 == 0){
                System.out.println("FizzBuzz");
            }
        }
    }
}
