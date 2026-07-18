/* Faça um algoritmo que receba dois vetores de 5 posições e retorne um terceiro vetor, com todos os elementos dos dois vetores intercalados*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int vetor1[5];
    int vetor2[5];
    int vetor3[5];
    int i = 0;

    //Vetor 1
    for(i = 0; i < 5; i++){
        scanf("%d", &vetor1[i]);
    }

     //Vetor 2
     for(i = 0; i < 5; i++){
        scanf("%d", &vetor2[i]);
     }

     printf("Vetores intercalados:\n");

     //Intercalação dos vetores
     for(i = 0; i < 5; i++){
        if(i % 2 == 0){
            vetor3[i] = vetor1[i];
        }else{
            vetor3[i] = vetor2[i];
        }

        printf("%d\n", vetor3[i]);

     }

     return 0;
}
