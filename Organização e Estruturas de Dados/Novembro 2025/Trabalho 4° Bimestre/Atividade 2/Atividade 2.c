/* Implemente uma função chamada maiorLista, que retorna o tamanho da maior lista
encadeada dentre todos os buckets. Essa função é útil para medir o pior caso em hash aberto.*/

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

int maiorLista(No **tabela, int tamanho){
    int maior = 0;

    for(int i = 0; i < tamanho; i++){
        int count = 0;
        No *aux = tabela[i];

        while(aux != NULL){
            count++;
            aux = aux -> prox;
        }

        if(count > maior)
            maior = count;

    }

    return maior;

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

    printf("\nTabela Hash:\n");
    imprimir(tabela, tamanho);

    printf("\nMaior lista encadeada: %d\n", maiorLista(tabela, tamanho));

    return 0;

}
