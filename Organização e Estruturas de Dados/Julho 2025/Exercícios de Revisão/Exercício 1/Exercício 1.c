/* Dado o código de lista simplesmente encadeada, faça uma função que conte e retorne
a quantidade de elementos presentes na lista*/

#include <stdio.h>
#include <stdlib.h>

typedef struct celula{
    int codigo, idade;
    char nome[20];
    struct celula *prox;
}celula;

typedef struct{
    celula *primeiro;
    celula *ultimo;
}tipo_lista;

void cria_lista(tipo_lista *lst){
    lst -> primeiro = NULL;
    lst -> ultimo = NULL;
}

int lista_vazia(tipo_lista *lst){

    if(lst -> primeiro == NULL){
        return 1;
    }else{
        return 0;
    }
}

int insere_fim(celula cel, tipo_lista *lst){

    celula *c;
    c = (celula*)malloc(sizeof(celula));
    *c = cel;
    c -> prox = NULL;

    if(lista_vazia(lst) == 1){
        lst -> primeiro = c;
    }else{
        lst -> ultimo -> prox = c;
    }
    lst -> ultimo = c;
    return 1;
}

void imprime_lista(tipo_lista *lst){

    celula *c;
    c = lst -> primeiro;
    while(c != NULL){

        printf("Codigo: %d\n", c -> codigo);
        printf("Idade: %d\n", c -> idade);
        printf("Nome: %d\n", c -> nome);
        c = c -> prox;
    }
}

celula* busca_elemento(int cod, tipo_lista *lst){

    celula *c;
    c = lst -> primeiro;
    while(c != NULL && cod != c -> codigo){
        c = c -> prox;
    }
    return c;
}

int remover_elemento(int cod, tipo_lista *lst){

    celula *c, *ant;

    ant = NULL;
    c = lst -> primeiro;
    while(c != NULL && cod != c -> codigo){
        ant = c;
        c = c -> prox;
    }if (c == lst -> primeiro){
        lst -> primeiro = lst -> primeiro -> prox;
    }else if(c == lst -> ultimo){
        lst -> ultimo = ant;
        ant -> prox = NULL;
    }else{
        ant -> prox = c -> prox;
    }
    free(c);
    return 1;
}

int contar_elementos(tipo_lista *lst){

    int cont = 0;
    celula *c = lst -> primeiro;
    while(c != NULL){
        cont++;
        c = c -> prox;
    }
    return cont;
}

int main(){
    tipo_lista lista;
    cria_lista(&lista);
    int opcao, valor;
    celula temp;

    do{
        printf("\n\t0 - Sair\n\t1 - Inserir Fim\n\t2 - Imprimir Lista\n\t3 - Buscar Elemento\n\t4 - Remover Elemento\n\t5 - Contar Elementos\n");
        scanf("%d", &opcao);

        switch (opcao){
        case 1:
            printf("Digite o codigo: ");
            scanf("%d", &temp.codigo);
            printf("Digite a idade: ");
            scanf("%d", &temp.idade);
            printf("Digite o nome: ");
            scanf("%s", temp.nome);
            if (insere_fim(temp, &lista)){
                printf("Elemento inserido com sucesso!\n");
            }else{
                printf("Erro ao inserir o elemento.\n");
            }
            break;

        case 2:
            imprime_lista(&lista);
            break;

        case 3:
            printf("Digite o codigo do elemento a ser buscado: ");
            scanf("%d", &valor);
            celula *resultado = busca_elemento(valor, &lista);
            if (resultado != NULL){
                printf("Elemento encontrado:\n");
                printf("Codigo: %d\n", resultado->codigo);
                printf("Idade: %d\n", resultado->idade);
                printf("Nome: %s\n", resultado->nome);
            }else{
                printf("Elemento não encontrado.\n");
            }
            break;

        case 4:
            printf("Digite o codigo do elemento a ser removido: ");
            scanf("%d", &valor);
            if (remover_elemento(valor, &lista)){
                printf("Elemento removido com sucesso!\n");
            }else{
                printf("Erro ao remover o elemento.\n");
            }
            break;

        case 5:
            printf("Quantidade de elementos na lista:%d\n", contar_elementos(&lista));
            break;

        case 0:
            printf("Saindo...\n");
            break;

        default:
            printf("Opção invalida! Tente novamente.\n");
        }
    }
    while (opcao != 0);

    return 0;
}
