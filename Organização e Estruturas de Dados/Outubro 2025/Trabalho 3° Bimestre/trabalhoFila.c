/*
Universidade Estadual do Paraná - UNESPAR
Curso: Bacharelado em Ciência da Computação - 2° ano
Aluna: Maria Rita Campana Peixoto
Professor: Paulo Roberto de Oliveira
Trabalho de Organização e Estrutura de Dados - 3° Bimestre
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct celula {
    int codigo, idade;
    char nome[20];
    struct celula *prox;
} celula;

typedef struct {
    celula *primeiro;
    celula *ultimo;
} tipo_fila;

void cria_fila(tipo_fila *fl){
    fl->primeiro = NULL;
    fl->ultimo = NULL;
}

bool fila_vazia(tipo_fila *fl){
    if(fl->primeiro == NULL){
        return true;
    } else {
        return false;
    }
}

bool insere(celula cel, tipo_fila *fl){
    celula *c = (celula*)malloc(sizeof(celula));
    if(c == NULL) return false;

    *c = cel;
    c->prox = NULL;

    if(fila_vazia(fl)){
        fl->primeiro = c;
        fl->ultimo = c;
    } else {
        fl->ultimo->prox = c;
        fl->ultimo = c;
    }
    return true;
}

bool remover(tipo_fila *fl, celula *cliente){
    if(fila_vazia(fl)){
        printf("Fila vazia!\n");
        return false;
    }

    celula *c = fl->primeiro;
    *cliente = *c;
    fl->primeiro = fl->primeiro->prox;
    if(fl->primeiro == NULL) fl->ultimo = NULL;
    free(c);
    return true;
}

void imprime_fila(tipo_fila *fl){
    celula *c = fl->primeiro;
    while(c != NULL){
        printf("Codigo: %d | Nome: %s | Idade: %d\n", c->codigo, c->nome, c->idade);
        c = c->prox;
    }
}

int tamanho_fila(tipo_fila *fl){
    int cont = 0;
    celula *c = fl->primeiro;
    while(c != NULL){
        cont++;
        c = c->prox;
    }
    return cont;
}

void pesquisar_cliente(tipo_fila f1, tipo_fila f2, tipo_fila f3, char nome[]){
    celula *c;
    int i, achou = 0;

    tipo_fila filas[3] = {f1, f2, f3};

    for(i = 0; i < 3; i++){
        c = filas[i].primeiro;
        while(c != NULL){
            if(strcmp(c->nome, nome) == 0){
                printf("Cliente %s encontrado na fila do caixa %d (Codigo %d)\n", c->nome, i+1, c->codigo);
                achou = 1;
            }
            c = c->prox;
        }
    }

    if(!achou) printf("Cliente %s nao encontrado.\n", nome);
}

tipo_fila* fila_menor(tipo_fila *f1, tipo_fila *f2, tipo_fila *f3){
    int t1 = tamanho_fila(f1);
    int t2 = tamanho_fila(f2);
    int t3 = tamanho_fila(f3);

    if(t1 <= t2 && t1 <= t3) return f1;
    else if(t2 <= t1 && t2 <= t3) return f2;
    else return f3;
}

int main(){
    tipo_fila caixa1, caixa2, caixa3;
    cria_fila(&caixa1);
    cria_fila(&caixa2);
    cria_fila(&caixa3);

    int opcao, codigo = 1;
    celula cliente;

    do{
        printf("\n---- Banco ----\n");
        printf("1. Inserir cliente na fila\n");
        printf("2. Atender cliente (caixa 1)\n");
        printf("3. Atender cliente (caixa 2)\n");
        printf("4. Atender cliente (caixa 3)\n");
        printf("5. Imprimir numero de clientes em cada fila\n");
        printf("6. Pesquisar cliente por nome\n");
        printf("0. Sair\n");
        printf("Escolha uma opcao: ");
        scanf("%d", &opcao);
        getchar();

        switch(opcao){
            case 1:

                printf("Codigo do cliente:");
                scanf("%d", &cliente.codigo);
                getchar();

                printf("Nome do cliente: ");
                fgets(cliente.nome, 20, stdin);
                cliente.nome[strcspn(cliente.nome, "\n")] = 0;

                printf("Idade do cliente: ");
                scanf("%d", &cliente.idade);
                getchar();

                tipo_fila *fila = fila_menor(&caixa1, &caixa2, &caixa3);
                insere(cliente, fila);

                printf("Cliente %s inserido com sucesso!\n", cliente.nome);
                break;

            case 2:
                if(tamanho_fila(&caixa1) > 0){
                    if(remover(&caixa1, &cliente))
                        printf("Atendido %s no Caixa 1 - Codigo %d\n", cliente.nome, cliente.codigo);
                }else{
                    printf("Nao eh possivel remover: fila do Caixa 1 esta vazia!\n");
                }
                break;

            case 3:
                if(tamanho_fila(&caixa2) > 0){
                    if(remover(&caixa2, &cliente))
                        printf("Atendido %s no Caixa 2 - Codigo %d\n", cliente.nome, cliente.codigo);
                }else{
                    printf("Nao eh possivel remover: fila do Caixa 2 esta vazia!\n");
                }
                break;

            case 4:
                if(tamanho_fila(&caixa3) > 0){
                    if(remover(&caixa3, &cliente))
                        printf("Atendido %s no Caixa 3 - Codigo %d\n", cliente.nome, cliente.codigo);
                }else{
                    printf("Nao eh possivel remover: a fila do Caixa 3 esta vazia!\n");
                }
                break;

            case 5:
                printf("Caixa 1: %d clientes\n", tamanho_fila(&caixa1));
                printf("Caixa 2: %d clientes\n", tamanho_fila(&caixa2));
                printf("Caixa 3: %d clientes\n", tamanho_fila(&caixa3));
                break;

            case 6: {
                printf("Nome do cliente: ");
                char nome[20];
                fgets(nome, 20, stdin);
                nome[strcspn(nome, "\n")] = 0;
                pesquisar_cliente(caixa1, caixa2, caixa3, nome);
                break;
            }

            case 0:
                printf("Saindo...\n");
                break;

            default:
                printf("Opcao invalida!\n");
        }

    } while(opcao != 0);

    return 0;
}
