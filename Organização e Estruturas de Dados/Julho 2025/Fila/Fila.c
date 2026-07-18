#include <stdio.h>
#include <stdlib.h>

typedef struct no {
    int valor;
    struct no *proximo;
    struct no *anterior;
} No;

// Inserir no final (enfileirar)
void inserir_no_fim(No **lista, int num) {
    No *aux, *novo = malloc(sizeof(No));

    if(novo) {
        novo->valor = num;
        novo->proximo = NULL;
        novo->anterior = NULL;

        if(*lista == NULL) {
            *lista = novo;
        } else {
            aux = *lista;
            while(aux->proximo)
                aux = aux->proximo;

            aux->proximo = novo;
            novo->anterior = aux;
        }
    } else {
        printf("Erro ao alocar memoria!\n");
    }
}

// Remover do início (desenfileirar)
No* remover_inicio(No **lista) {
    if (*lista == NULL)
        return NULL;

    No *aux = *lista;
    *lista = aux->proximo;

    if (*lista)
        (*lista)->anterior = NULL;

    return aux;
}

// Imprimir a fila
void imprimir(No *no) {
    printf("\nFila: ");
    while(no) {
        printf("%d ", no->valor);
        no = no->proximo;
    }
    printf("\n");
}

int main() {
    int opcao, valor;
    No *removido, *fila = NULL;

    do {
        printf("\n0 - Sair\n1 - Enfileirar (Inserir)\n2 - Desenfileirar (Remover)\n3 - Imprimir\n");
        scanf("%d", &opcao);

        switch(opcao) {
            case 1:
                printf("Digite um valor: ");
                scanf("%d", &valor);
                inserir_no_fim(&fila, valor);
                break;
            case 2:
                removido = remover_inicio(&fila);
                if(removido) {
                    printf("Elemento removido: %d\n", removido->valor);
                    free(removido);
                } else {
                    printf("Fila vazia!\n");
                }
                break;
            case 3:
                imprimir(fila);
                break;
            default:
                if(opcao != 0)
                    printf("Opção inválida!\n");
        }
    } while(opcao != 0);

    return 0;
}
