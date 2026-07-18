/* Modifique o algoritmo de busca binária para que ele realize a busca em uma matriz de
elementos. Para isso, você deve considerar que cada linha da matriz está ordenada */

#include <stdio.h>

typedef struct{
    int elementos[100][100];
    int linhas;
    int colunas;
}Matriz;

int buscaBinariaLinha(int linha[], int colunas, int valor){
    int inicio = 0;
    int fim = colunas - 1;

    while(inicio <= fim){
        int meio = (inicio + fim)/2;

        if(linha[meio] == valor)
            return meio;
        else if(valor < linha[meio])
            fim = meio - 1;
        else
            inicio = meio + 1;
    }
    return -1;
}

void buscarMatriz(Matriz m, int valor){
    for(int i = 0; i < m.linhas; i++){
        int pos = buscaBinariaLinha(m.elementos[i], m.colunas, valor);
        if(pos != -1){
            printf("Valor %d encontrado na posicao [%d][%d]\n", valor, i, pos);
            return;
        }
    }
    printf("Valor %d nao encontrado na matriz\n", valor);
}

int main(){
    Matriz m;

    m.linhas = 3;
    m.colunas = 4;

    int dados[3][4] = {
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 60},
    };

    for(int i = 0; i < m.linhas; i++){
        for(int j = 0; j < m.colunas; j++){
            m.elementos[i][j] = dados[i][j];
        }
    }

    int valor;
    printf("Digite o valor a ser buscado:");
    scanf("%d", &valor);

    buscarMatriz(m , valor);

    return 0;
}
