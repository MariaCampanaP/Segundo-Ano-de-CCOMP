/* Implemente uma função chamada quantos elementos totais existem na tabela (somando
todos os buckets).*/

#include <stdio.h>
#include <stdlib.h>

int TAM = 10;

typedef struct No {
    int valor;
    struct No* prox;
} No;

No* tabela[10];

int hash(int chave, int tamanho){
    return chave % tamanho;
}

void inserir(int chave) {
    int h = hash(chave, TAM);
    No* novo = (No*)malloc(sizeof(No));
    novo -> valor = chave;
    novo -> prox = tabela[h];
    tabela[h] = novo;
}

void imprimirTabela() {
    for(int i = 0; i < TAM; i++){
        printf("[%d] -> ", i);
        No *aux = tabela[i];
        while(aux != NULL){
            printf("%d -> ", aux->valor);
            aux = aux -> prox;
        }
        printf("NULL\n");
    }
}

int contarElementosTotais(){
    int total = 0;

    for(int i = 0; i < TAM; i++){
        No* aux = tabela[i];
        while(aux != NULL){
            total++;
            aux = aux -> prox;
        }
    }

    return total;
}

int main() {

    for(int i = 0; i < TAM; i++)
        tabela[i] = NULL;

    inserir(5);
    inserir(15);
    inserir(25);
    inserir(8);
    inserir(9);

    imprimirTabela();

    printf("\nTotal de elementos na tabela: %d\n", contarElementosTotais());

    return 0;
}
