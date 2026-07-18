#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Função Insertion Sort
void insertionSort(int arr[], int n) {
    for (int i = 1; i < n; i++) {
        int tmp = arr[i];
        int j = i;
        while (j > 0 && arr[j - 1] > tmp) {
            arr[j] = arr[j - 1];
            j--;
        }
        arr[j] = tmp;
    }
}

// Função para preencher o vetor com valores aleatórios
void preencherVetor(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        arr[i] = rand() % 1000000; // Valores entre 0 e 999999
    }
}

int main() {
    srand(time(NULL)); // Inicializa gerador de números aleatórios

    int tamanhos[] = {50000, 100000, 150000};

    for (int k = 0; k < 3; k++) {
        int n = tamanhos[k];
        int* arr = (int*)malloc(n * sizeof(int));

        if (arr == NULL) {
            printf("Erro ao alocar memória!\n");
            return 1;
        }

        preencherVetor(arr, n);

        printf("Ordenando vetor de tamanho %d...\n", n);

        clock_t inicio = clock();
        insertionSort(arr, n);
        clock_t fim = clock();

        double tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;

        printf("Tempo para ordenar %d elementos: %.4f segundos\n\n", n, tempo);

        free(arr);
    }

    return 0;
}
