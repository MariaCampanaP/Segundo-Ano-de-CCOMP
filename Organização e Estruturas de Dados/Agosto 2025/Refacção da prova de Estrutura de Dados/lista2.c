#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum { false, true } boolean;

typedef struct celula {
    int codigo, idade;
    char nome[20];
    struct celula *prox;
} celula;

typedef struct {
    celula *primeiro;
    celula *ultimo;
} tipo_lista;

void cria_lista(tipo_lista *lst) {
    lst->primeiro = NULL;
    lst->ultimo = NULL;
}

boolean lista_vazia(tipo_lista *lst) {
    return (lst->primeiro == NULL);
}

boolean insere_fim(celula cel, tipo_lista *lst) {
    celula *c = (celula *)malloc(sizeof(celula));
    if (c == NULL) return false;

    c->codigo = cel.codigo;
    c->idade = cel.idade;
    strcpy(c->nome, cel.nome);

    if (lista_vazia(lst)) {
        lst->primeiro = c;
        lst->ultimo = c;
        c->prox = c;  // Aponta para si mesmo
    } else {
        c->prox = lst->primeiro;
        lst->ultimo->prox = c;
        lst->ultimo = c;
    }
    return true;
}

void imprime_lista(tipo_lista *lst) {
    if (lista_vazia(lst)) return;

    celula *c = lst->primeiro;
    do {
        printf("Codigo: %d\n", c->codigo);
        printf("Idade: %d\n", c->idade);
        printf("Nome: %s\n", c->nome);
        c = c->prox;
    } while (c != lst->primeiro);
}

celula *busca_elemento(int cod, tipo_lista *lst) {
    if (lista_vazia(lst)) return NULL;

    celula *c = lst->primeiro;
    do {
        if (c->codigo == cod) return c;
        c = c->prox;
    } while (c != lst->primeiro);

    return NULL;
}

boolean remover_elemento(int cod, tipo_lista *lst) {
    if (lista_vazia(lst)) return false;

    celula *atual = lst->primeiro;
    celula *anterior = lst->ultimo;

    do {
        if (atual->codigo == cod) {
            if (atual == lst->primeiro && atual == lst->ultimo) {
                // Apenas um elemento
                lst->primeiro = NULL;
                lst->ultimo = NULL;
            } else {
                anterior->prox = atual->prox;
                if (atual == lst->primeiro)
                    lst->primeiro = atual->prox;
                if (atual == lst->ultimo)
                    lst->ultimo = anterior;
            }
            free(atual);
            lst->ultimo->prox = lst->primeiro;  // manter circularidade
            return true;
        }
        anterior = atual;
        atual = atual->prox;
    } while (atual != lst->primeiro);

    return false;
}

boolean insere_ordenado(celula cel, tipo_lista *lst) {
    celula *novo = (celula *)malloc(sizeof(celula));
    if (novo == NULL) return false;

    novo->codigo = cel.codigo;
    novo->idade = cel.idade;
    strcpy(novo->nome, cel.nome);

    if (lista_vazia(lst)) {
        novo->prox = novo;
        lst->primeiro = novo;
        lst->ultimo = novo;
        return true;
    }

    celula *atual = lst->primeiro;
    celula *anterior = lst->ultimo;

    do {
        if (cel.codigo < atual->codigo) break;
        anterior = atual;
        atual = atual->prox;
    } while (atual != lst->primeiro);

    novo->prox = atual;
    anterior->prox = novo;

    if (atual == lst->primeiro && cel.codigo < atual->codigo)
        lst->primeiro = novo;

    if (anterior == lst->ultimo)
        lst->ultimo = novo;

    lst->ultimo->prox = lst->primeiro;  // garantir circularidade
    return true;
}

void reverte(tipo_lista *lst) {
    if (lista_vazia(lst) || lst->primeiro->prox == lst->primeiro)
        return;

    celula *anterior = lst->ultimo;
    celula *atual = lst->primeiro;
    celula *proximo;

    do {
        proximo = atual->prox;
        atual->prox = anterior;
        anterior = atual;
        atual = proximo;
    } while (atual != lst->primeiro);

    celula *temp = lst->primeiro;
    lst->primeiro = lst->ultimo;
    lst->ultimo = temp;
}

int circular(tipo_lista *lst) {
    return (!lista_vazia(lst) && lst->ultimo->prox == lst->primeiro);
}

// ------------------------ MAIN -------------------------
int main() {
    tipo_lista lista;
    cria_lista(&lista);
    int opcao;
    celula nova;
    int cod;
    celula *buscado;

    do {
        printf("\n--- MENU ---\n");
        printf("1. Inserir elemento no fim\n");
        printf("2. Imprimir lista\n");
        printf("3. Buscar elemento\n");
        printf("4. Remover elemento\n");
        printf("5. Inserir elemento de forma ordenada\n");
        printf("6. Reverter lista\n");
        printf("7. Verificar se a lista eh circular\n");
        printf("0. Sair\n");
        printf("Escolha uma opcao: ");
        scanf("%d", &opcao);
        getchar();

        switch (opcao) {
            case 1:
                printf("Digite o codigo: ");
                scanf("%d", &nova.codigo);
                printf("Digite a idade: ");
                scanf("%d", &nova.idade);
                getchar();
                printf("Digite o nome: ");
                fgets(nova.nome, sizeof(nova.nome), stdin);
                nova.nome[strcspn(nova.nome, "\n")] = '\0';
                if (insere_fim(nova, &lista)) {
                    printf("Elemento inserido com sucesso!\n");
                } else {
                    printf("Erro ao inserir elemento.\n");
                }
                break;

            case 2:
                if (lista_vazia(&lista)) {
                    printf("Lista vazia.\n");
                } else {
                    imprime_lista(&lista);
                }
                break;

            case 3:
                printf("Digite o codigo do elemento a buscar: ");
                scanf("%d", &cod);
                buscado = busca_elemento(cod, &lista);
                if (buscado != NULL) {
                    printf("Elemento encontrado:\n");
                    printf("Codigo: %d\n", buscado->codigo);
                    printf("Idade: %d\n", buscado->idade);
                    printf("Nome: %s\n", buscado->nome);
                } else {
                    printf("Elemento nao encontrado.\n");
                }
                break;

            case 4:
                printf("Digite o codigo do elemento a remover: ");
                scanf("%d", &cod);
                if (remover_elemento(cod, &lista)) {
                    printf("Elemento removido com sucesso.\n");
                } else {
                    printf("Elemento nao encontrado.\n");
                }
                break;

            case 5:
                printf("Digite o codigo: ");
                scanf("%d", &nova.codigo);
                printf("Digite a idade: ");
                scanf("%d", &nova.idade);
                getchar();
                printf("Digite o nome: ");
                fgets(nova.nome, sizeof(nova.nome), stdin);
                nova.nome[strcspn(nova.nome, "\n")] = '\0';

                if (insere_ordenado(nova, &lista)) {
                    printf("Elemento inserido de forma ordenada com sucesso!\n");
                } else {
                    printf("Erro ao inserir elemento.\n");
                }
                break;

            case 6:
                if (lista_vazia(&lista)) {
                    printf("Lista vazia. Nada para reverter.\n");
                } else {
                    reverte(&lista);
                    printf("Lista revertida com sucesso.\n");
                }
                break;

            case 7:
                if (circular(&lista)) {
                    printf("A lista eh circular.\n");
                } else {
                    printf("A lista NAO eh circular.\n");
                }
                break;

            case 0:
                printf("Encerrando o programa.\n");
                break;

            default:
                printf("Opcao Invalida.\n");
        }
    } while (opcao != 0);

    return 0;
}
