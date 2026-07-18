/* Desenvolva um algoritmo em C que receba um dia de 1 a 7 e mostre o nome do dia da semana, por exemplo: 3: Terça-feira, 7: Sábado. (Utilize switch-case)*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int diaSemana;

    printf("Dias da semana\n");
    printf("1 - Domingo\n");
    printf("2 - Segunda-feira\n");
    printf("3 - Terca-feira\n");
    printf("4 - Quarta-feira\n");
    printf("5 - Quinta-feira\n");
    printf("6 - Sexta-feira\n");
    printf("7 - Sabado\n\n");

    scanf("%d", &diaSemana);

    switch(diaSemana){

    case 1:
        printf("Domingo\n");
        break;

    case 2:
        printf("Segunda-feira\n");
        break;

    case 3:
        printf("Terca-feira\n");
        break;

    case 4:
        printf("Quarta-feira\n");
        break;

    case 5:
        printf("Quinta-feira\n");
        break;

    case 6:
        printf("Sexta-feira\n");
        break;

    case 7:
        printf("Sabado\n");
        break;

    default:
        printf("Nenhum dia invalido!\n");
        break;

    }

    return 0;

}
