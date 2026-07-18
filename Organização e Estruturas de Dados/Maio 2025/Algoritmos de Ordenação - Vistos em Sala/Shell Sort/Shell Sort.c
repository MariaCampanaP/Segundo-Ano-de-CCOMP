#include <stdio.h>

void shellSort(int vetor[], int tamanho) {

    for (int intervalo = tamanho / 2; intervalo > 0; intervalo /= 2) {
        for (int i = intervalo; i < tamanho; i++) {
            int temp = vetor[i];
            int j;
            for (j = i; j >= intervalo && vetor[j - intervalo] > temp; j -= intervalo)
                vetor[j] = vetor[j - intervalo];
            vetor[j] = temp;

        }
    }
}

int main() {

    int vetor[] = {12, 34, 54, 2, 3};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);

    shellSort(vetor, tamanho);

    printf("Vetor ordenado: ");
    for (int i = 0; i < tamanho; i++)
        printf("%d ", vetor[i]);

    return 0;

}
