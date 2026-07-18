/* Dada duas filas, faça uma função que retire o elemento da lista 1 e insira na lista 2*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    int codigo, idade;
    char nome[20];
    struct celula *prox;
}celula;

typedef struct{
    celula *primeiro;
    celula *ultimo;
}tipo_fila;

void cria_fila(tipo_fila *fl){

    fl -> primeiro = NULL;
    fl -> ultimo = NULL;
}

int fila_vazia(tipo_fila *fl){

    if(fl -> primeiro == NULL){
        return 1;
    }else{
        return 0;
    }
}

int insere(celula cel, tipo_fila *fl){

    celula *c;
    c = (celula*)malloc(sizeof(celula));
    *c = cel;
    c -> prox = NULL;
    if(fila_vazia(fl) == 1){
        fl -> primeiro = c;
        fl -> ultimo = c;
    }else{
        fl -> ultimo -> prox = c;
    }
    fl -> ultimo = c;
    return 0;
}

int remove_elemento(int cod, tipo_fila *fl){

    celula *c;
    c = fl -> primeiro;

    if(fila_vazia(fl)){
        printf("Fila Vazia!");
        return 0;
    }
    fl -> primeiro = fl -> primeiro -> prox;
    free(c);
}

void imprime_fila(tipo_fila *fl){

    celula *c = fl -> primeiro;
    while(c != NULL){
        printf("Codigo: %d\n", c -> codigo);
        printf("Idade: %d\n", c -> idade);
        printf("Nome: %s\n", c -> nome);
        c = c -> prox;
    }
}

void transfere_fila(tipo_fila *fila1, tipo_fila *fila2){

    if(fila_vazia(fila1)){
        printf("Fila 1 esta vazia. Nada a tranferir\n");
        return;
    }
    celula *temp = fila1 -> primeiro;
    fila1 -> primeiro = fila1 -> primeiro -> prox;

    if(fila1 -> primeiro == NULL){
        fila1 -> ultimo = NULL;
    }

    temp -> prox = NULL;

    if(fila_vazia(fila2)){
        fila2 -> primeiro = temp;
        fila2 -> ultimo = temp;
    }else{
        fila2 -> ultimo -> prox = temp;
        fila2 -> ultimo = temp;
    }
}

int main() {
    tipo_fila fila1, fila2;
    cria_fila(&fila1);
    cria_fila(&fila2);

    celula a = {1, 21, "Alice", NULL};
    celula b = {2, 25, "Bruno", NULL};
    celula c = {3, 30, "Carlos", NULL};

    insere(a, &fila1);
    insere(b, &fila1);
    insere(c, &fila1);

    printf("Fila 1 antes da transferência:\n");
    imprime_fila(&fila1);

    transfere_fila(&fila1, &fila2);

    printf("\nFila 1 depois da transferência:\n");
    imprime_fila(&fila1);

    printf("\nFila 2 depois de receber o elemento:\n");
    imprime_fila(&fila2);

    return 0;
}


