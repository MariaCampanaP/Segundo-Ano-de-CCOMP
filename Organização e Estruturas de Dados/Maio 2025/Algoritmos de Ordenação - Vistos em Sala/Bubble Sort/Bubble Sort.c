#include <stdio.h>

void bubbleSort(int vetor[], int tamanho){

    int i, j, temp;
    for(i = 0; i < tamanho - 1; i++){
        for(j = 0; j < tamanho - i - 1; j++){
            if(vetor[j] > vetor[j + 1]){
                //Troca os elementos
                temp = vetor[j];
                vetor[j] = vetor[j + 1];
                vetor[j + 1] = temp;
            }
        }
    }
}

int main(){

    int vetor[] = {5, 2, 9, 1, 5, 6};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);
    int i;

    printf("Vetor original:");
    for(i = 0; i < tamanho; i++){
        printf("%d ", vetor[i]);
    }

    printf("\n");

    bubbleSort(vetor, tamanho);

    printf("Vetor ordenado:");
    for(i = 0; i < tamanho; i++){
        printf("%d ", vetor[i]);
    }

    printf("\n");

    return 0;

}
