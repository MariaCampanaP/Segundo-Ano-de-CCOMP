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
} tipo_fila;

void cria_fila(tipo_fila *fl) {
    fl->primeiro = NULL;
    fl->ultimo = NULL;
}

boolean fila_vazia(tipo_fila *fl) {
    return (fl->primeiro == NULL);
}

boolean insere(celula cel, tipo_fila *fl) {
    celula *c = (celula *)malloc(sizeof(celula));
    if (c == NULL) return false;

    *c = cel;
    c->prox = NULL;

    if (fila_vazia(fl)) {
        fl->primeiro = c;
    } else {
        fl->ultimo->prox = c;
    }

    fl->ultimo = c;
    return true;
}

void imprime_fila(tipo_fila *fl) {
    celula *c = fl->primeiro;
    while (c != NULL) {
        printf("Codigo: %d\n", c->codigo);
        printf("Idade: %d\n", c->idade);
        printf("Nome: %s\n\n", c->nome);
        c = c->prox;
    }
}

boolean remover_elemento(tipo_fila *fl) {
    if (fila_vazia(fl)) return false;

    celula *c = fl->primeiro;
    fl->primeiro = fl->primeiro->prox;

    if (fl->primeiro == NULL)
        fl->ultimo = NULL;

    free(c);
    return true;
}

// Função para comparar se duas filas são exatamente iguais
int iguais(tipo_fila *f1, tipo_fila *f2) {
    celula *c1 = f1->primeiro;
    celula *c2 = f2->primeiro;

    while (c1 != NULL && c2 != NULL) {
        if (c1->codigo != c2->codigo || c1->idade != c2->idade || strcmp(c1->nome, c2->nome) != 0)
            return 0;
        c1 = c1->prox;
        c2 = c2->prox;
    }

    // Se ambos chegaram ao final, são iguais
    return (c1 == NULL && c2 == NULL) ? 1 : 0;
}

// ---------------------------- MAIN ----------------------------

int main() {
    tipo_fila fila1, fila2;
    cria_fila(&fila1);
    cria_fila(&fila2);

    celula nova;
    int opcao, qual_fila;

    do {
        printf("\n--- MENU ---\n");
        printf("1. Inserir elemento\n");
        printf("2. Imprimir fila\n");
        printf("3. Remover elemento\n");
        printf("4. Comparar filas\n");
        printf("0. Sair\n");
        printf("Escolha uma opcao: ");
        scanf("%d", &opcao);
        getchar();

        switch (opcao) {
            case 1:
                printf("Inserir em qual fila? (1 ou 2): ");
                scanf("%d", &qual_fila);
                printf("Digite o codigo: ");
                scanf("%d", &nova.codigo);
                printf("Digite a idade: ");
                scanf("%d", &nova.idade);
                getchar();
                printf("Digite o nome: ");
                fgets(nova.nome, sizeof(nova.nome), stdin);
                nova.nome[strcspn(nova.nome, "\n")] = '\0';

                if (qual_fila == 1) {
                    insere(nova, &fila1);
                } else if (qual_fila == 2) {
                    insere(nova, &fila2);
                } else {
                    printf("Fila invalida.\n");
                }
                break;

            case 2:
                printf("Imprimir qual fila? (1 ou 2): ");
                scanf("%d", &qual_fila);
                if (qual_fila == 1) {
                    imprime_fila(&fila1);
                } else if (qual_fila == 2) {
                    imprime_fila(&fila2);
                } else {
                    printf("Fila invalida.\n");
                }
                break;

            case 3:
                printf("Remover de qual fila? (1 ou 2): ");
                scanf("%d", &qual_fila);
                if (qual_fila == 1) {
                    if (remover_elemento(&fila1)) printf("Removido com sucesso!\n");
                    else printf("Fila 1 vazia.\n");
                } else if (qual_fila == 2) {
                    if (remover_elemento(&fila2)) printf("Removido com sucesso!\n");
                    else printf("Fila 2 vazia.\n");
                } else {
                    printf("Fila invalida.\n");
                }
                break;

            case 4:
                if (iguais(&fila1, &fila2)) {
                    printf("As filas sao IGUAIS.\n");
                } else {
                    printf("As filas sao DIFERENTES.\n");
                }
                break;

            case 0:
                printf("Encerrando...\n");
                break;

            default:
                printf("Opcao invalida!\n");
        }

    } while (opcao != 0);

    return 0;
}
