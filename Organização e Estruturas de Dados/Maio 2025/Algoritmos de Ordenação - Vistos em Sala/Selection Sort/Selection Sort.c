#include <stdio.h>

void selectionSort(int vetor[], int tamanho){

    int i, j, min_idx, temp;
    for(i = 0; i < tamanho - 1; i++){
        min_idx = i;
        for(j = i + 1; j < tamanho; j++){
            if(vetor[j] < vetor[min_idx]){
                min_idx = j;
            }
        }

        temp = vetor[min_idx];
        vetor[min_idx] = vetor [i];
        vetor[i] = temp;

    }
}

int main(){

    int vetor[] = {64, 25, 12, 22, 11};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    selectionSort(vetor, tamanho);

    printf("Vetor ordenado:");
    for(int i = 0; i < tamanho; i++){
        printf("%d ", vetor[i]);
    }

    return 0;

}
