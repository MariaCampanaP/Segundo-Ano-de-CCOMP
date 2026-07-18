#include <stdio.h>

void heapify(int vetor[], int n, int i) {

    int maior = i;
    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;

    if (esquerda < n && vetor[esquerda] > vetor[maior])
        maior = esquerda;

    if (direita < n && vetor[direita] > vetor[maior])
        maior = direita;

    if (maior != i) {
        int temp = vetor[i];
        vetor[i] = vetor[maior];
        vetor[maior] = temp;

        heapify(vetor, n, maior);

    }
}

void heapSort(int vetor[], int n) {

    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(vetor, n, i);

    for (int i = n - 1; i >= 0; i--) {
        int temp = vetor[0];
        vetor[0] = vetor[i];
        vetor[i] = temp;

        heapify(vetor, i, 0);

    }
}

int main() {

    int vetor[] = {12, 11, 13, 5, 6, 7};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    heapSort(vetor, tamanho);

    printf("Vetor ordenado: ");
    for (int i = 0; i < tamanho; i++)
        printf("%d ", vetor[i]);

    return 0;

}
