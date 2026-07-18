#include <stdio.h>
#define N_BUCKETS 10

void insertionSort(float arr[], int n) {

    for (int i = 1; i < n; i++) {
        float chave = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > chave) {
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = chave;

    }
}

void bucketSort(float arr[], int n) {

    float buckets[N_BUCKETS][n];
    int count[N_BUCKETS] = {0};

    for (int i = 0; i < n; i++) {
        int bi = arr[i] * N_BUCKETS;
        buckets[bi][count[bi]++] = arr[i];
    }

    for (int i = 0; i < N_BUCKETS; i++) {
        if (count[i] > 0)
            insertionSort(buckets[i], count[i]);
    }

    int index = 0;
    for (int i = 0; i < N_BUCKETS; i++) {
        for (int j = 0; j < count[i]; j++) {
            arr[index++] = buckets[i][j];

        }
    }
}

int main() {

    float vetor[] = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    bucketSort(vetor, tamanho);

    printf("Vetor ordenado: ");
    for (int i = 0; i < tamanho; i++)
        printf("%.4f ", vetor[i]);

    return 0;

}
