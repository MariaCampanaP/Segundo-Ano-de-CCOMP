/* Faça um algoritmo que receba valores enquanto forem diferentes de 0. Ao final, deve-se mostrar a quantidade de números positivos
e negativos, o maior, o menor, e a média entre os positivos
*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int positivo;
    int negativo;
    int i;
    float num;
    float maior = -999999;
    float menor = 999999;
    float media;
    float soma;

    do{
        printf("Digite um numero:");
        scanf("%f", &num);

        if(num < 0){
            negativo++;
        }

        if(num > 0){
            positivo++;
            soma = soma + num;
            i++;
            media = soma / i;
        }

        if(num != 0){
            if(num > maior){
                maior = num;
            }

            if(num < menor){
                menor = num;
            }

        }
    }while (num != 0);

    printf("A quantidade de positivos e:%d\n", positivo);
    printf("A quantidade de negativos e:%d\n", negativo);
    printf("O maior e:%.2f:\n", maior);
    printf("O menor e:%.2f\n", menor);
    printf("A media entre os positivos e:%.2f\n", media);

    return 0;

}
