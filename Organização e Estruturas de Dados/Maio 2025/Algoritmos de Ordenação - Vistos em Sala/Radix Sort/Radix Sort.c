#include <stdio.h>

int getMax(int vetor[], int n) {

    int max = vetor[0];
    for (int i = 1; i < n; i++)
        if (vetor[i] > max)
            max = vetor[i];

    return max;

}

void countingSortRadix(int vetor[], int n, int exp) {

    int output[n], count[10] = {0};

    for (int i = 0; i < n; i++)
        count[(vetor[i] / exp) % 10]++;

    for (int i = 1; i < 10; i++)
        count[i] += count[i - 1];

    for (int i = n - 1; i >= 0; i--) {
        output[count[(vetor[i] / exp) % 10] - 1] = vetor[i];
        count[(vetor[i] / exp) % 10]--;
    }

    for (int i = 0; i < n; i++)
        vetor[i] = output[i];

}

void radixSort(int vetor[], int n) {

    int max = getMax(vetor, n);
    for (int exp = 1; max / exp > 0; exp *= 10)
        countingSortRadix(vetor, n, exp);

}

int main() {

    int vetor[] = {170, 45, 75, 90, 802, 24, 2, 66};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    radixSort(vetor, tamanho);

    printf("Vetor ordenado: ");
    for (int i = 0; i < tamanho; i++)
        printf("%d ", vetor[i]);

    return 0;

}
