#include <stdio.h>

void merge(int vetor[], int esquerda, int meio, int direita) {

    int n1 = meio - esquerda + 1;
    int n2 = direita - meio;
    int L[n1], R[n2];

    for (int i = 0; i < n1; i++)
        L[i] = vetor[esquerda + i];
    for (int j = 0; j < n2; j++)
        R[j] = vetor[meio + 1 + j];

    int i = 0, j = 0, k = esquerda;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j])
            vetor[k++] = L[i++];
        else
            vetor[k++] = R[j++];
    }

    while (i < n1)
        vetor[k++] = L[i++];

    while (j < n2)
        vetor[k++] = R[j++];

}

void mergeSort(int vetor[], int esquerda, int direita) {

    if (esquerda < direita) {
        int meio = esquerda + (direita - esquerda) / 2;

        mergeSort(vetor, esquerda, meio);
        mergeSort(vetor, meio + 1, direita);

        merge(vetor, esquerda, meio, direita);

    }
}

int main() {

    int vetor[] = {12, 11, 13, 5, 6, 7};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    mergeSort(vetor, 0, tamanho - 1);

    printf("Vetor ordenado: ");
    for (int i = 0; i < tamanho; i++)
        printf("%d ", vetor[i]);

    return 0;

}
