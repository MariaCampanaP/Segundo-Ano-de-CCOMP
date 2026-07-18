#include <stdio.h>
#include <stdlib.h>

typedef struct no
{
    int valor;
    struct no *proximo;
    struct no *anterior;
} No;

void inserir_no_inicio(No **lista, int num)
{
    No *novo = malloc(sizeof(No));

    if(novo)
    {
        novo->valor = num;
        novo->proximo = *lista;
        novo->anterior = NULL;

        if(*lista)
            (*lista)->anterior = novo;

        *lista = novo;
    }
    else
    {
        printf("Erro ao alocar memoria!\n");
    }
}

void inserir_no_fim(No **lista, int num)
{
    No *aux, *novo = malloc(sizeof(No));

    if(novo)
    {
        novo->valor = num;
        novo->proximo = NULL;

        if(*lista == NULL)
        {
            novo->anterior = NULL;
            *lista = novo;
        }
        else
        {
            aux = *lista;
            while(aux->proximo)
                aux = aux->proximo;

            aux->proximo = novo;
            novo->anterior = aux;
        }
    }
    else
    {
        printf("Erro ao alocar memoria!\n");
    }
}

void inserir_no_meio(No **lista, int num, int ant)
{
    No *aux = *lista, *novo = malloc(sizeof(No));

    if(novo)
    {
        novo->valor = num;

        while(aux && aux->valor != ant)
            aux = aux->proximo;

        if(aux)
        {
            novo->proximo = aux->proximo;
            novo->anterior = aux;

            if(aux->proximo)
                aux->proximo->anterior = novo;

            aux->proximo = novo;
        }
        else
        {
            printf("Valor de referência não encontrado.\n");
            free(novo);
        }
    }
    else
    {
        printf("Erro ao alocar memoria!\n");
    }
}

void inserir_ordenado(No **lista, int num)
{
    No *aux, *novo = malloc(sizeof(No));

    if(novo)
    {
        novo->valor = num;

        if(*lista == NULL || num < (*lista)->valor)
        {
            novo->proximo = *lista;
            novo->anterior = NULL;
            if(*lista)
                (*lista)->anterior = novo;
            *lista = novo;
        }
        else
        {
            aux = *lista;
            while(aux->proximo && aux->proximo->valor < num)
                aux = aux->proximo;

            novo->proximo = aux->proximo;
            novo->anterior = aux;

            if(aux->proximo)
                aux->proximo->anterior = novo;

            aux->proximo = novo;
        }
    }
    else
    {
        printf("Erro ao alocar memoria!\n");
    }
}

No* remover(No **lista, int num)
{
    No *aux = *lista;

    while(aux && aux->valor != num)
        aux = aux->proximo;

    if(aux)
    {
        if(aux->anterior)
            aux->anterior->proximo = aux->proximo;
        else
            *lista = aux->proximo;

        if(aux->proximo)
            aux->proximo->anterior = aux->anterior;

        return aux;
    }

    return NULL;
}

No* buscar(No *lista, int num)
{
    while(lista && lista->valor != num)
        lista = lista->proximo;

    return lista;
}

void imprimir(No *no)
{
    printf("\nLista: ");
    while(no)
    {
        printf("%d ", no->valor);
        no = no->proximo;
    }
    printf("\n");
}

void imprimirContrario(No *no)
{
    printf("\nLista Invertida: ");
    while(no)
    {
        printf("%d ", no->valor);
        no = no->anterior;
    }
    printf("\n");
}

No* ultimo(No *lista)
{
    if(!lista) return NULL;
    while(lista->proximo)
        lista = lista->proximo;
    return lista;
}

int main()
{
    int opcao, valor, anterior;
    No *removido, *lista = NULL;

    do
    {
        printf("\n0 - Sair\n1 - Inserir Inicio\n2 - Inserir Final\n3 - Inserir Meio\n4 - Inserir Ordenadamente\n5 - Remover\n6 - Imprimir\n7 - Buscar\n8 - Imprimir Contrario\n");
        scanf("%d", &opcao);

        switch(opcao)
        {
        case 1:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserir_no_inicio(&lista, valor);
            break;
        case 2:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserir_no_fim(&lista, valor);
            break;
        case 3:
            printf("Digite um valor e o valor de referência: ");
            scanf("%d %d", &valor, &anterior);
            inserir_no_meio(&lista, valor, anterior);
            break;
        case 4:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserir_ordenado(&lista, valor);
            break;
        case 5:
            printf("Digite um valor a ser removido: ");
            scanf("%d", &valor);
            removido = remover(&lista, valor);
            if(removido)
            {
                printf("Elemento removido: %d\n", removido->valor);
                free(removido);
            }
            else
            {
                printf("Elemento inexistente!\n");
            }
            break;
        case 6:
            imprimir(lista);
            break;
        case 7:
            printf("Digite um valor a ser buscado: ");
            scanf("%d", &valor);
            removido = buscar(lista, valor);
            if(removido)
            {
                printf("Elemento encontrado: %d\n", removido->valor);
            }
            else
            {
                printf("Elemento não encontrado!\n");
            }
            break;
        case 8:
            imprimirContrario(ultimo(lista));
            break;
        default:
            if(opcao != 0)
                printf("Opção inválida!\n");
        }
    }
    while(opcao != 0);

    return 0;
}
