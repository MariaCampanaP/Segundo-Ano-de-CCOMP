#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NBUCKET 1000  // Número de buckets
#define INTERVAL 1000  // Intervalo de cada bucket

struct Node {
    int data;
    struct Node *next;
};

void BucketSort(int arr[], int n);
struct Node *InsertionSort(struct Node *list);
int getBucketIndex(int value);
void preencherVetor(int arr[], int n);
void print(int arr[], int n);

void BucketSort(int arr[], int n) {
    int i, j;
    struct Node **buckets;

    // Criar buckets
    buckets = (struct Node **)malloc(sizeof(struct Node *) * NBUCKET);
    for (i = 0; i < NBUCKET; ++i) {
        buckets[i] = NULL;
    }

    // Distribuir os elementos nos buckets
    for (i = 0; i < n; ++i) {
        int pos = getBucketIndex(arr[i]);
        struct Node *current = (struct Node *)malloc(sizeof(struct Node));
        current->data = arr[i];
        current->next = buckets[pos];
        buckets[pos] = current;
    }

    // Ordenar cada bucket
    for (i = 0; i < NBUCKET; ++i) {
        buckets[i] = InsertionSort(buckets[i]);
    }

    // Concatenar os buckets
    for (j = 0, i = 0; i < NBUCKET; ++i) {
        struct Node *node = buckets[i];
        while (node) {
            arr[j++] = node->data;
            node = node->next;
        }
    }

    // Liberar a memória dos buckets
    for (i = 0; i < NBUCKET; ++i) {
        struct Node *node = buckets[i];
        while (node) {
            struct Node *tmp = node;
            node = node->next;
            free(tmp);
        }
    }
    free(buckets);
}

struct Node *InsertionSort(struct Node *list) {
    struct Node *k, *nodeList;
    if (!list || !list->next) return list;

    nodeList = list;
    k = list->next;
    nodeList->next = NULL;

    while (k) {
        struct Node *ptr;
        if (nodeList->data > k->data) {
            struct Node *tmp = k;
            k = k->next;
            tmp->next = nodeList;
            nodeList = tmp;
            continue;
        }

        for (ptr = nodeList; ptr->next; ptr = ptr->next) {
            if (ptr->next->data > k->data) break;
        }

        if (ptr->next) {
            struct Node *tmp = k;
            k = k->next;
            tmp->next = ptr->next;
            ptr->next = tmp;
        } else {
            ptr->next = k;
            k = k->next;
            ptr->next->next = NULL;
        }
    }
    return nodeList;
}

int getBucketIndex(int value) {
    return value / INTERVAL;
}

void preencherVetor(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        arr[i] = rand() % (NBUCKET * INTERVAL);
    }
}

void print(int arr[], int n) {
    for (int i = 0; i < n; ++i) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    srand(time(NULL));
    int tamanhos[] = {50000, 100000, 150000};
    int numTamanhos = sizeof(tamanhos) / sizeof(tamanhos[0]);

    for (int t = 0; t < numTamanhos; t++) {
        int n = tamanhos[t];
        int *array = (int *)malloc(n * sizeof(int));
        preencherVetor(array, n);

        printf("\nOrdenando vetor de tamanho %d...\n", n);

        clock_t inicio = clock();
        BucketSort(array, n);
        clock_t fim = clock();

        double tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;
        printf("Tempo de execucao para %d elementos: %.4f segundos\n", n, tempo);

        free(array);
    }

    return 0;
}
