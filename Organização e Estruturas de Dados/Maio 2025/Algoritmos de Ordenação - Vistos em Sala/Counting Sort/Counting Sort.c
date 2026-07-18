#include <stdio.h>

void countingSort(int vetor[], int n) {

    int max = vetor[0];
    for (int i = 1; i < n; i++)
        if (vetor[i] > max)
            max = vetor[i];

    int count[max + 1];
    for (int i = 0; i <= max; i++)
        count[i] = 0;

    for (int i = 0; i < n; i++)
        count[vetor[i]]++;

    int index = 0;
    for (int i = 0; i <= max; i++) {
        while (count[i] > 0) {
            vetor[index++] = i;
            count[i]--;

        }
    }
}

int main() {

    int vetor[] = {4, 2, 2, 8, 3, 3, 1};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    countingSort(vetor, tamanho);

    printf("Vetor ordenado: ");
    for (int i = 0; i < tamanho; i++)
        printf("%d ", vetor[i]);

    return 0;

}
