/* Faça um algoritmo que mostre os valores pares de 0 a 100
OBSERVAÇÃO: numero par if (i % 2 == 0) é par */

#include <stdio.h>
#include <stdlib.h>

int main(){

    int i;

    printf("Numeros pares de 0 a 100");

    for(i >= 0; i <= 100; i++){
        if(i % 2 == 0){
            printf("%d\n", i);
        }
    }

    return 0;

}
