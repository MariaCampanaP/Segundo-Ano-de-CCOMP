/* Desenvolva um algoritmo em C que receba o salário de uma pessoa e calcule o valor de IR a ser pago conforme a tabela:
   - Até 3000: Isento
   - 3000 <= 5000: 5%
   - 5000 <= 8000: 10%
   - 8000 <= 12000: 15%
   - 1200: 20%
*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    float salario;
    float impostoRenda;

    printf("Digite o salario:R$");
    scanf("%f", &salario);

    if(salario <= 3000){
        printf("Isento");
        return 0;
    }else if(salario <= 5000){
        impostoRenda = (salario * 0.05);
    }else if(salario <= 8000){
        impostoRenda = (salario * 0.10);
    }else if(salario <= 1200){
        impostoRenda = (salario * 0.15);
    }else{
        impostoRenda = (salario * 0.20);
    }

    printf("O imposto de renda e:R$%.2f", impostoRenda);

    return 0;

}
