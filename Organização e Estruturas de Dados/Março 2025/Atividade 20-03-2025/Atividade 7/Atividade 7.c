/* Faça um algoritmo que receba valores enquanto forem diferentes de 0 e mostre se eles são primos ou não*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int num;
    int i;
    int primo;

    do{
        printf("Insira um numero:");
        scanf("%d", &num);
        primo = 0;

        if(num != 0){
            if(num > 0){
                for(i = 1; i <= num; i++){
                    if(num % i == 0){
                        primo++;
                    }}}

            if(primo == 2){
                printf("%d e primo\n", num);
            }else{
                printf("%d nao e primo\n", num);
            }

        }

    }while (num != 0);

    return 0;

    }
