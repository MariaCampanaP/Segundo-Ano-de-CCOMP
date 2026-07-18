/* Implemente uma função chamada imprimirBucket(int indice), que imprime somente os
elementos de um bucket específico. Por exemplo: índice 7 -> imprime apenas a lista
encadeada da posição 7 */

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

void imprimirBucket(No **tabela, int tamanho, int indice){
    if(indice < 0 || indice >= tamanho){
        printf("Indice invalido\n");
        return;
    }

    printf("Bucket [%d] -> ", indice);

    No *aux = tabela[indice];
    while(aux != NULL){
        printf("%d -> ", aux->valor);
        aux = aux -> prox;
    }

    printf("NULL\n");

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
    inserir(tabela, tamanho, 5);
    inserir(tabela, tamanho, 7);
    inserir(tabela, tamanho, 17);

    printf("\nTabela Hash Completa:\n");
    imprimir(tabela, tamanho);

    printf("\nImprimindo bucket 7:\n");
    imprimirBucket(tabela, tamanho, 7);

    return 0;

}
