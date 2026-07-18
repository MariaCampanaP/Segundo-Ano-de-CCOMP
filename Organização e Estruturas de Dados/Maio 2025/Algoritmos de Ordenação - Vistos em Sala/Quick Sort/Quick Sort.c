#include <stdio.h>

void quickSort(int vetor[], int baixo, int alto){

    if(baixo < alto){
        int pivot = vetor[alto];
        int i = (baixo - 1);
        int temp;

        for(int j = baixo; j <= alto - 1; j++){
            if(vetor[j] < pivot){
                i++;
                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }

        temp = vetor[i + 1];
        vetor[i + 1] = vetor[alto];
        vetor[alto] = temp;

        int pi = i + 1;

        quickSort(vetor, baixo, pi - 1);
        quickSort(vetor, pi + 1, alto);

    }
}

int main(){

    int vetor[] = {10, 7, 8, 9, 1, 5};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    quickSort(vetor, 0, tamanho - 1);

    printf("Vetor ordenado:");
    for(int i = 0; i < tamanho; i++){
        printf("%d ", vetor[i]);
    }

    return 0;

}
