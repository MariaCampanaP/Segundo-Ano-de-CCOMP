#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct no{
    int valor;
    struct no *proximo;
}No;

typedef struct{
    No *inicio; //Primeiro Nó da lista
    No *fim; //Último Nó da lista
    int tam;
}Lista;

void criar_lista(Lista *lista){
    lista->inicio = NULL;
    lista->fim = NULL;
    lista->tam = 0;
}

//procedimento para inserir no início
void inserir_no_inicio(Lista *lista, int num){
    No *novo = malloc(sizeof(No));

    if(novo){
        novo->valor = num;
        novo->proximo = lista->inicio;
        lista->inicio = novo;
        if(lista->fim == NULL){
            lista->fim = novo;
        }
        lista->fim->proximo = lista->inicio;
        lista->tam++;
    }else{
        printf("Erro ao alocar memoria!\n");
    }
}

void inserir_no_fim(Lista *lista, int num){
    No *aux, *novo = malloc(sizeof(No));

    if(novo){
        novo->valor = num;
        if(lista->inicio == NULL){
            lista->inicio = novo;
            lista->fim = novo;
            lista->fim->proximo = lista->inicio;
        }else{
            lista->fim->proximo = novo;
            lista->fim = novo;
            lista->fim->proximo = lista->inicio;
        }
        lista->tam++;
    }else{
        printf("Erro ao alocar memoria!\n");
    }
}

void inserir_ordenado(Lista *lista, int num){
    No *aux, *novo = malloc(sizeof(No));

    if(novo){
        novo->valor = num;

        if(lista->inicio == NULL){
            inserir_no_inicio(lista, num);
        }else if(novo->valor < lista->inicio->valor){
            inserir_no_inicio(lista, num);
        }else{
            aux = lista->inicio;
            while(aux->proximo != lista->inicio && novo->valor > aux->proximo->valor){
                aux = aux->proximo;
            }
            if(aux->proximo == lista->inicio){
                inserir_no_fim(lista, num);
            }else{
                novo->proximo = aux->proximo;
                aux->proximo = novo;
                lista->tam++;
            }
        }
    }else{
        printf("Erro ao alocar memoria!\n");
    }
}

No* remover(Lista *lista, int num){
    No *aux, *remover = NULL;

    if(lista->inicio){
        if(lista->inicio == lista->fim && lista->inicio->valor == num){
            remover = lista->inicio;
            lista->inicio = NULL;
            lista->fim = NULL;
            lista->tam--;
        }else if(lista->inicio->valor == num){
            remover = lista->inicio;
            lista->inicio = remover->proximo;
            lista->fim->proximo = lista->inicio;
            lista->tam--;
        }else{
            aux = lista->inicio;
            while(aux->proximo != lista->inicio && aux->proximo->valor != num){
                aux = aux->proximo;
                }
                if(aux->proximo->valor == num){
                    if(lista->fim == aux->proximo){
                        remover = aux->proximo;
                        aux->proximo = remover->proximo;
                        lista->fim = aux;
                    }else{
                        remover = aux->proximo;
                        aux->proximo = remover->proximo;
                    }
                    lista->tam--;
                }
            }
        }
    return remover;
}

No* buscar(Lista *lista, int num){
    No *aux = lista->inicio;

    if(aux){
        do{
            if(aux->valor == num){
                return aux;
            }
            aux = aux->proximo;
        }while(aux != lista->inicio);
    }
    return NULL;
}

void imprimir(Lista lista){
    No *no = lista.inicio;
    printf("\nLista tam %d:", lista.tam);
    if(no){
        do{
            printf("%d ", no->valor);
            no = no->proximo;
        }while(no != lista.inicio);
        printf("\nInicio:%d\n", no->valor);
    }
    printf("\n\n");
}

int main(){

    int opcao, valor, anterior;

    Lista lista;
    No *removido;

    criar_lista(&lista);

    do{
        printf("\n0 - Sair\n1 - Inserir Inicio\n2 - Inserir Final\n3 - Inserir Ordenadamente\n4 - Remover\n5 - Imprimir\n6 - Buscar\n");
        scanf("%d", &opcao);

        switch(opcao){
        case 1:
            printf("Digite um valor:");
            scanf("%d", &valor);
            inserir_no_inicio(&lista, valor);
            break;
        case 2:
            printf("Digite um valor:");
            scanf("%d", &valor);
            inserir_no_fim(&lista, valor);
            break;
        case 3:
            printf("Digite um valor:");
            scanf("%d", &valor);
            inserir_ordenado(&lista, valor);
            break;
        case 4:
            printf("Digite um valor:");
            scanf("%d", &valor);
            removido = remover(&lista, valor);
            if(removido){
                printf("Elemento Removido:%d\n", removido->valor);
                free(removido);
            }else{
                printf("Elemento Inesistente!\n");
            }
            break;
        case 5:
            imprimir(lista);
            break;
        case 6:
            printf("Digite um valor a ser buscado:");
            scanf("%d", &valor);
            removido = buscar(&lista, valor);
            if(removido){
                printf("Valor encontrado:%d\n", removido->valor);
            }else{
                printf("Valor nao encontrado!\n");
            }
            break;
        default:
            if(opcao != 0){
                printf("Opcao Invalida!\n");
            }
        }
    }while(opcao != 0);

    return 0;
}
