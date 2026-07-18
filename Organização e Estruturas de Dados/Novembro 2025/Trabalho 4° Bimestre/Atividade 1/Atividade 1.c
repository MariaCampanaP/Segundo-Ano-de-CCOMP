/* Implemente uma função chamada contarColisoes, ela deve retornar quantos buckets
possuem mais de 1 elemento (Um bucket é cada posição do vetor de listas da tabela hash).
Essa função é útil para medir a qualidade da função hash.*/

#include <stdio.h>
#include <stdlib.h>

typedef struct no{
    int valor;
    struct no *prox;
}No;

int hash(int chave, int tamanho){
    return chave % tamanho;
}

No **criarTabela(int tamanho){
    No **tabela = (No **)malloc(tamanho * sizeof(No *));
    for(int i = 0; i < tamanho; i++)
        tabela[i] = NULL;
    return tabela;

}

void inserir(No **tabela, int tamanho, int valor){
    int indice = hash(valor, tamanho);

    No *novo = (No *)malloc(sizeof(No));
    novo -> valor = valor;
    novo -> prox = tabela[indice];
    tabela[indice] = novo;

}

int contarColisoes(No *tabela[], int tamanho){
    int colisoes = 0;

    for(int i = 0; i < tamanho; i++){
        int count = 0;
        No *aux = tabela[i];

        while(aux != NULL){
            count++;
            aux = aux -> prox;
        }

        if(count > 1){
            colisoes++;

        }
    }

    return colisoes;

}

void imprimir(No **tabela, int tamanho){
    for(int i = 0; i < tamanho; i++){
        printf("[%d] -> ", i);
        No *aux = tabela[i];
        while(aux != NULL){
            printf("%d -> ", aux->valor);
            aux = aux -> prox;
        }

        printf("NULL\n");

    }
}

int main(){
    int tamanho = 10;
    No **tabela = criarTabela(tamanho);

    inserir(tabela, tamanho, 10);
    inserir(tabela, tamanho, 20);
    inserir(tabela, tamanho, 30);
    inserir(tabela, tamanho, 25);
    inserir(tabela, tamanho, 35);
    inserir(tabela, tamanho, 15);

    printf("\nTabela Hash:\n");
    imprimir(tabela, tamanho);

    int colisoes = contarColisoes(tabela, tamanho);
    printf("\nBuckets com colisoes: %d\n", colisoes);

    return 0;

}
