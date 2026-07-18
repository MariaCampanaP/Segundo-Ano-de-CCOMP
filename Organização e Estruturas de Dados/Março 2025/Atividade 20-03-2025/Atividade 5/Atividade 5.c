/* Faça um algoritmo que receba dois valores a e b, e mostre a soma de todos os valores entre eles, por exemplo: a = 5, b = 10, resultado: 45
Observação: quando pedir a soma de todos os valores utilizar um for e um if*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int a;
    int b;
    int soma;

    printf("Digite um valor de a:");
    scanf("%d", &a);

    printf("Digite um valor de b:");
    scanf("%d", &b);

    for(int i = a; i <= b; i++){
        soma = soma + i;
    }

    printf("A soma dos valores de a ate b e:%d", soma);

    return 0;
}
