/*Faça uma função que imprima somente os nós folhas de uma ABP*/

#include <stdio.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h> // malloc e free

typedef enum {false, true} boolean;

typedef struct No{
    int codigo;
    struct No *esquerda;
    struct No *direita;
}No;

typedef struct{
    No* raiz;
}Arvore;

//Cria a árvore
void cria_arvore(Arvore* arv){
    arv->raiz = NULL;
}

//Verifica se eh nulo
boolean eh_nulo(No* no_atual){
    return (no_atual == NULL);
}

//Adicionar um nó
No* insere(int cod, No* no_atual){
    if(eh_nulo(no_atual)){
        no_atual = (No*)malloc(sizeof(No));
        no_atual->codigo = cod;
        no_atual->esquerda = NULL;
        no_atual->direita = NULL;
    }
    else if(cod < no_atual->codigo){
        no_atual->esquerda = insere(cod, no_atual->esquerda);
    }else{
        no_atual->direita = insere(cod, no_atual->direita);
    }
    return no_atual;
}

//Pesquisar um item
boolean pesquisa(int cod, No* no_atual){
    if(no_atual == NULL){
        return false;
    }else if(cod < no_atual->codigo){
        return pesquisa(cod, no_atual->esquerda);
    }else if(cod > no_atual->codigo){
        return pesquisa(cod, no_atual->direita);
    }else{
        return true;
    }
}

//Antecessor
No* antecessor(No *atual, No *ant){
    if(ant->direita != NULL){
        ant->direita = antecessor(atual, ant->direita);
    }else{
        No *aux;
        atual->codigo = ant->codigo;
        aux = ant;
        ant = ant->esquerda;
        free(aux);
    }
    return ant;
}

//Remoção
No* remocao(int cod, No* no_atual){
    No* aux;
    if(no_atual == NULL){
        return NULL;
    }else if(cod < no_atual->codigo){
        no_atual->esquerda = remocao(cod, no_atual->esquerda);
    }else if(cod > no_atual->codigo){
        no_atual->direita = remocao(cod, no_atual->direita);
    }else if(no_atual->direita == NULL){
        aux = no_atual;
        no_atual = no_atual->esquerda;
        free(aux);
    }else if(no_atual->esquerda == NULL){
        aux = no_atual;
        no_atual = no_atual->direita;
        free(aux);
    }else{
        no_atual->esquerda = antecessor(no_atual, no_atual->esquerda);
    }
    return no_atual;
}

//Caminhamento Pré-Ordem (RED)
void pre_ordem(No* no_atual){
    if(no_atual != NULL){
        printf("%d ", no_atual->codigo);
        pre_ordem(no_atual->esquerda);
        pre_ordem(no_atual->direita);
    }
}

//Caminhamento Em-ordem (ERD)
void em_ordem(No* no_atual){
    if(no_atual != NULL){
        em_ordem(no_atual->esquerda);
        printf("%d ", no_atual->codigo);
        em_ordem(no_atual->direita);
    }
}

//Caminhamento Pós-Ordem (EDR)
void pos_ordem(No* no_atual){
    if(no_atual != NULL){
        pos_ordem(no_atual->esquerda);
        pos_ordem(no_atual->direita);
        printf("%d ", no_atual->codigo);
    }
}

void imprime_folhas(No* no_atual){
    if(no_atual != NULL){
        if(no_atual->esquerda == NULL && no_atual->direita == NULL){
            printf("%d ", no_atual->codigo);
        }
        imprime_folhas(no_atual->esquerda);
        imprime_folhas(no_atual->direita);
    }
}

int main(){
    Arvore arv;
    cria_arvore(&arv);

    int opcao, valor;

    do {
        printf("\n---Menu Arvore Binaria---\n");
        printf("1 - Inserir elemento\n");
        printf("2 - Pesquisar elemento\n");
        printf("3 - Remover elemento\n");
        printf("4 - Exibir em ordem (ERD)\n");
        printf("5 - Exibir pre ordem (RED)\n");
        printf("6 - Exibir pos ordem (EDR)\n");
        printf("7 - Exibir folhas da arvore\n");
        printf("0 - Sair\n");
        printf("Escolha: ");
        scanf("%d", &opcao);

        switch(opcao){
            case 1:
                printf("Digite o valor a inserir: ");
                scanf("%d", &valor);
                arv.raiz = insere(valor, arv.raiz);
                printf("Inserido!\n");
                break;

            case 2:
                printf("Digite o valor a pesquisar: ");
                scanf("%d", &valor);
                if(pesquisa(valor, arv.raiz))
                    printf("Valor %d encontrado!\n", valor);
                else
                    printf("Valor %d nao encontrado!\n", valor);
                break;

            case 3:
                printf("Digite o valor a remover: ");
                scanf("%d", &valor);
                arv.raiz = remocao(valor, arv.raiz);
                printf("Removido (se existia).\n");
                break;

            case 4:
                printf("Em ordem: ");
                em_ordem(arv.raiz);
                printf("\n");
                break;

            case 5:
                printf("Pre ordem: ");
                pre_ordem(arv.raiz);
                printf("\n");
                break;

            case 6:
                printf("Pos ordem: ");
                pos_ordem(arv.raiz);
                printf("\n");
                break;

            case 7:
                printf("Folhas da arvore:");
                imprime_folhas(arv.raiz);
                printf("\n");
                break;

            case 0:
                printf("Saindo...\n");
                break;

            default:
                printf("Opcao invalida!\n");
        }

    } while(opcao != 0);

    return 0;
}
