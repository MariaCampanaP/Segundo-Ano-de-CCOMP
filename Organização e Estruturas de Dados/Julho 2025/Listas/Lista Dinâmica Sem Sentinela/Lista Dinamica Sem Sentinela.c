#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Essa estrutura representa um nó da lista encadeada
typedef struct celula { //Usa typdef para poder usar diretamente no em vez de struct celula
    int codigo, idade; //código -> identificador do elemento, idade -> idade da pessoa
    char nome[20]; //nome -> nome(até 19 caracteres + \0)
    struct celula *prox; //prox -> ponteiro para o próximo nó
} no;

//Estrutura que representa a lista inteira
typedef struct {
    no *primeiro; //Ponteiro para o primeiro nó
    no *ultimo; //Ponteiro para o último nó
} tipo_lista;

//Inicializa uma lista vazia
void cria_lista(tipo_lista *lst) {
    //Define que não há nenhum nó no início  nem no fim
    lst->primeiro = NULL;
    lst->ultimo = NULL;
}

int lista_vazia(tipo_lista *lst) {
    return (lst->primeiro == NULL); //Retorna 1 se a lista vazia, 0 se não
}

int insere_fim(no cel, tipo_lista *lst){
    no *c;
    c = (no*) malloc(sizeof(no)); //Aloca memória para um novo nó (c)
    *c = cel; //Copia os dados de cel para o nó c usando *c = cel
    c->prox = NULL; //Novo nó vai ser o último, então prox = NULL

    if(lista_vazia(lst)){
        lst->primeiro = c; //Lista vazia -> primeiro nó é c
    }else{
        lst->ultimo->prox = c; //Lista não vazia -> último nó atual aponta para c
    }

    lst->ultimo = c; //Atualiza o ponteiro do último nó
    return 1; //Retornar sucesso
}

void imprime_lista(tipo_lista *lst){
    no *c = lst->primeiro; //Começa do primeiro

    while(c != NULL){ //Enquanto não chegar no final
        printf("Codigo: %d\n", c->codigo); //Em cada nó, imprime código, idade e nome
        printf("Idade: %d\n", c->idade);
        printf("Nome: %s\n\n", c->nome);
        c = c->prox; //Vai para o próximo
    }
}

no* busca_elemento(int cod, tipo_lista *lst){
    no *c = lst->primeiro; //Começa no primeiro

    while(c != NULL && cod != c->codigo){
        c = c->prox; //Avança até achar ou chegar no final
    }

    return c; //Retornar o nó encontrado ou NULL
}

int remover_elemento(int cod, tipo_lista *lst){
    no *c = lst->primeiro, *ant = NULL; //c percorre, ant fica antes

    while(c != NULL && cod != c->codigo){
        ant = c; //Atualiza anterior
        c = c->prox; //Avança
    }

    if(c == NULL) return 0; //Não achou -> retorna 0

    if(c == lst->primeiro){
        lst->primeiro = lst->primeiro->prox; //Remove primeiro
        if(lst->primeiro == NULL)
            lst->ultimo = NULL; //Se a lista vazia
    }else if(c == lst->ultimo){
        lst->ultimo = ant; //Remove último
        ant->prox = NULL;
    }else{
        ant->prox = c->prox; //Remove nó do meio
    }

    free(c); //Libera memória
    return 1; //Retorna sucesso
}

int main(){
    tipo_lista lista;
    cria_lista(&lista); //Inicializa lista vazia

    int opcao;

    do{
        printf("------MENU------\n");
        printf("1 - Inserir Elemento\n");
        printf("2 - Imprimir Lista\n");
        printf("3 - Buscar Elemento\n");
        printf("4 - Remover Elemento\n");
        printf("0 - Sair\n");
        printf("Escolha uma opcao: ");
        scanf("%d", &opcao);

        if(opcao == 1){
            no novo;
            printf("Digite o codigo: ");
            scanf("%d", &novo.codigo);
            printf("Digite a idade: ");
            scanf("%d", &novo.idade);
            printf("Digite o nome: ");
            scanf(" %[^\n]", novo.nome); //Lê string com espaço
            insere_fim(novo, &lista);
            printf("Elemento inserido com sucesso!\n\n");

        }else if(opcao == 2){
            if(lista_vazia(&lista)){
                printf("Lista Vazia!\n\n");
            }else{
                imprime_lista(&lista);
            }

        }else if(opcao == 3){
            int cod;
            printf("Digite o codigo a buscar: ");
            scanf("%d", &cod);
            no *res = busca_elemento(cod, &lista);

            if(res == NULL){
                printf("Elemento nao encontrado!\n\n");
            }else{
                printf("Encontrado!\nCodigo: %d\nIdade: %d\nNome: %s\n\n", res->codigo, res->idade, res->nome);
            }

        }else if(opcao == 4){
            int cod;
            printf("Digite o codigo a remover: ");
            scanf("%d", &cod);

            if(remover_elemento(cod, &lista)){
                printf("Elemento Removido!\n\n");
            }else{
                printf("Elemento nao encontrado!\n\n");
            }

        }else if(opcao == 0){
            printf("Saindo...\n");
        }else{
            printf("Opcao invalida!\n\n");
        }

    }while(opcao != 0);

    return 0;
}
