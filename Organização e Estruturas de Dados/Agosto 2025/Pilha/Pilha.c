//Estrutura da pilha

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    int codigo, idade;
    char nome[20];
    struct celula *prox;
}celula;

typedef struct{
    celula *topo;
    int tamanho;
}tipo_pilha;

//Cria pilha vazia
void cria_pilha(tipo_pilha *pl){
    pl->topo = NULL;
    pl->tamanho = 0;
}

//Cria pilha vazia
int pilha_vazia(tipo_pilha *pl){
    return(pl->topo == NULL && pl->tamanho == 0);
}

//Inserir elemento
void inserir_elemento(tipo_pilha *pl, int codigo, int idade, char nome[]){
    celula *novo = (celula*) malloc(sizeof(celula));
    if(novo ==  NULL){
        printf("Erro ao alocar.\n");
        return;
    }
    novo->codigo = codigo;
    novo->idade = idade;
    strcpy(novo->nome, nome);

    novo->prox = pl->topo;
    pl->topo = novo;
    pl->tamanho++;
}

//Tamanho da pilha
int numero_de_elementos(tipo_pilha *pl){
    return pl->tamanho;
}

//Desempilhar
void desempilhar_pilha(tipo_pilha *pl){
    if(pl->topo == NULL){
        printf("Pilha vazia, nao ha o que remover.\n");
        return;
    }

    celula *removido = pl->topo;
    pl->topo = removido->prox;
    pl->tamanho--;

    printf("Elemento removido:\n");
    printf("Codigo:%d| Idade:%d | Nome:%s\n", removido->codigo, removido->idade, removido->nome);

    free(removido);
}

int main(){

    tipo_pilha minha_pilha;
    cria_pilha(&minha_pilha);
    int opcao;
    int codigo, idade;
    char nome[20];

    do{
        printf("\n---Menu---\n");
        printf("1. Verificacao se a pilha esta vazia ou nao.\n");
        printf("2. Inserir elemento.\n");
        printf("3. Desempilhar.\n");
        printf("0. Sair\n");
        printf("Escolha uma opcao:");
        scanf("%d", &opcao);
        getchar();

        switch(opcao){
        case 1:
            if(pilha_vazia(&minha_pilha)){
            printf("Pilha vazia!\n");
        }else{
            printf("A pilha nao esta vazia!\n");
        }
        break;

        case 2:
            printf("Digite o codigo:");
            scanf("%d", &codigo);
            printf("Digite a idade:");
            scanf("%d", &idade);
            getchar();
            printf("Digite o nome:");
            fgets(nome, sizeof(nome),stdin);
            nome[strcspn(nome, "\n")] = '\0';

            inserir_elemento(&minha_pilha, codigo, idade, nome);
            printf("Elemento empilhado.\n");
            break;

        case 3:
            desempilhar_pilha(&minha_pilha);
            break;

        case 0:
            printf("Encerrando o programa...\n");
            break;

        default:
            printf("Opcao invalida.\n");

        }
    }while(opcao != 0);

    return 0;
}
