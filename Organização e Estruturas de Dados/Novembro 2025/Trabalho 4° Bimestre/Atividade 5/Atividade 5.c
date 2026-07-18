/* Implemente uma função chamada percentualOcupacao(), que retorna um valor entre 0% e
100% indicando a porcentagem de buckets não vazios. (Uma forma de medir o quão uniforme
é a dispersão da tabela hash).*/

#include <stdio.h>
#include <stdlib.h>

int TAM = 10;

typedef struct No {
    int valor;
    struct No* prox;
} No;

No* tabela[10];

int hash(int chave){
    return chave % TAM;
}

void inserir(int chave) {
    int h = hash(chave);
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

float percentualOcupacao(){
    int ocupados = 0;

    for(int i = 0; i < TAM; i++){
        if(tabela[i] != NULL)
            ocupados++;
    }

    return (ocupados / (float)TAM) * 100.0;

}

int main() {

    for(int i = 0; i < TAM; i++)
        tabela[i] = NULL;

    inserir(5);
    inserir(15);
    inserir(25);
    inserir(8);
    inserir(9);

    printf("\nTabela Hash:\n");
    imprimirTabela();

    printf("\nPercentual de ocupacao: %.2f%%\n", percentualOcupacao());

    return 0;

}
