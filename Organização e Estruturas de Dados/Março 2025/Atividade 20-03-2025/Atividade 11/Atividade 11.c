/* Faça um algoritmo que receba um valor n e preencha uma matriz nxn. Posterioremente, apresente a diagonal principal da matriz*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int i;
    int j;
    int n;

    printf("Digite um tamanho para a matriz:");
    scanf("%d", &n);

    int matriz[n][n];

    //Para escanear o tamanho da matriz
    for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            scanf("%d", &matriz[i][j]);

        }

    }

    printf("A diagonal principal e:\n");

    //Para printar a diagonal principal
     for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            if(i == j){
                printf("%d\n", matriz[i][j]);
            }
        }
     }


    return 0;

}
