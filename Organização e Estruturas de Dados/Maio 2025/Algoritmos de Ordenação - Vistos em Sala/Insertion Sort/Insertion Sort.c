#include <stdio.h>

void insertionSort(int vetor[], int tamanho){

    int i, chave, j;
    for(i = 1; i < tamanho; i++){
        chave = vetor[i];
        j = i - 1;

        //Move os elementos maiores que a chave para a frente
        while(j >= 0 && vetor[j] > chave){
            vetor[j + 1] = vetor[j];
            j = j - 1;
        }

        vetor[j + 1] = chave;

    }
}

int main(){

    int vetor[] = {9, 5, 1, 4, 3};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);
    int i;

    printf("Vetor original:");
    for(i = 0; i < tamanho; i++){
        printf("%d ", vetor[i]);
    }

    printf("\n");

    insertionSort(vetor, tamanho);

    printf("Vetor ordenado:");
    for(i = 0; i < tamanho; i++){
        printf("%d ", vetor[i]);
    }

    printf("\n");

    return 0;

}
