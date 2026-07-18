#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Função Heap Sort
void heapsort(int a[], int n) {
    int i = n / 2, pai, filho, t;
    while (1) {
        if (i > 0) {
            i--;
            t = a[i];
        } else {
            n--;
            if (n <= 0) return;
            t = a[n];
            a[n] = a[0];
        }
        pai = i;
        filho = i * 2 + 1;
        while (filho < n) {
            if ((filho + 1 < n) && (a[filho + 1] > a[filho]))
                filho++;
            if (a[filho] > t) {
                a[pai] = a[filho];
                pai = filho;
                filho = pai * 2 + 1;
            } else {
                break;
            }
        }
        a[pai] = t;
    }
}

// Função para preencher o vetor com números aleatórios
void preencheVetor(int *vetor, int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        vetor[i] = rand() % 1000000;
    }
}

int main() {
    int tamanhos[] = {50000, 100000, 150000};
    int numTamanhos = 3;

    srand(time(NULL)); // Inicializa o gerador de números aleatórios

    for (int k = 0; k < numTamanhos; k++) {
        int tamanho = tamanhos[k];
        int *vetor = (int*) malloc(tamanho * sizeof(int));
        if (vetor == NULL) {
            printf("Erro ao alocar memória.\n");
            return 1;
        }

        preencheVetor(vetor, tamanho);

        clock_t inicio = clock();
        heapsort(vetor, tamanho);
        clock_t fim = clock();

        double tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;

        printf("Tempo para ordenar %d elementos: %.4f segundos\n\n", tamanho, tempo);

        free(vetor);
    }

    return 0;
}
