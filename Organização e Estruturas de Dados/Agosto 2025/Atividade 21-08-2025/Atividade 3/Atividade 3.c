/*Crie uma função que encontre o maior elemento da pilha, devolvendo a pilha exatamente
igual ao estado inicial*/

#include <stdio.h>
#include <stdlib.h>

#define MAX 100

//Estrutura da pilha
typedef struct {
    int itens[MAX]; //Vetor que armazena os elementos
    int topo; //Índice do elemento do topo
} Pilha;

// Função que inicializa a pilha (deixa vazia)
void inicializar(Pilha *p) { p->topo = -1; }

//Retorna 1 se a pilha estiver vazia, 0 caso contrário
int vazia(Pilha *p) { return p->topo == -1; }

//Retorna 1 se a pilha estiver cheia, 0 caso contrário
int cheia(Pilha *p) { return p->topo == MAX - 1; }

//Empilha um valor no topo da pilha
void push(Pilha *p, int valor) {
    if (cheia(p)) { //Verifica se a pilha está cheia
        printf("Pilha cheia!\n");
        return; //Não empilha se cheia
    }
    p->itens[++p->topo] = valor; //Incrementa topo e guarda o valor
}

//Desempilha o valor do topo da pilha e retorna
int pop(Pilha *p) {
    if (vazia(p)) { //Não pode desempilhar se estiver vazia
        printf("Pilha vazia!\n");
        return -1; //Retorna -1 para indicar erro
    }
    return p->itens[p->topo--]; //Retorna valor e decrementa topo
}

//Retorna o valor do topo da pilha sem remover
int topo(Pilha *p) {
    if (vazia(p)) return -1; //Retorna -1 se vazia
    return p->itens[p->topo]; //Retorna o topo
}

// Encontra o maior elemento sem alterar a pilha
int maiorElemento(Pilha *p) {
    if (vazia(p)) { //Verifica se a pilha está vazia
        printf("Pilha vazia!\n");
        return -1; //Retorna -1 para indicar erro
    }

    Pilha aux; //Pilha auxiliar
    inicializar(&aux); //Inicializa a pilha auxiliar

    int maior = pop(p);       //Pega o primeiro como referência
    push(&aux, maior);        //Guarda na auxiliar

    //Percorre os elementos restantes
    while (!vazia(p)) {
        int valor = pop(p); //Desempilha o elemento atual
        if (valor > maior) { //Verifica se é maior que o maior encontrado
            maior = valor; //Atualiza o maior
        }
        push(&aux, valor); //Guarda o elemento na pilha auxiliar
    }

    //Devolve os elementos para a pilha original
    while (!vazia(&aux)) {
        push(p, pop(&aux)); //Mantém o estado original da pilha
    }

    return maior; //Retorna o maior elemento encontrado
}

// Mostrar pilha
void mostrar(Pilha *p) {
    if (vazia(p)) { //Se a pilha estiver vazia
        printf("Pilha vazia.\n");
        return;
    }
    printf("Pilha (topo -> base): ");
    for (int i = p->topo; i >= 0; i--) { //Percorre do topo até a base
        printf("%d ", p->itens[i]);
    }
    printf("\n");
}

int main() {
    Pilha p; //Cria a pilha
    inicializar(&p); //Inicializa como vazia

    int opcao, valor; //Variáveis para o menu e valores

    do {
        printf("\n===== MENU =====\n");
        printf("1 - Empilhar (push)\n");
        printf("2 - Desempilhar (pop)\n");
        printf("3 - Mostrar pilha\n");
        printf("4 - Encontrar maior elemento\n");
        printf("0 - Sair\n");
        printf("Opcao: ");
        scanf("%d", &opcao);

        switch(opcao) {
            case 1:
                printf("Digite o valor: ");
                scanf("%d", &valor);
                push(&p, valor);
                break;
            case 2:
                valor = pop(&p);
                if (valor != -1)
                    printf("Desempilhado: %d\n", valor);
                break;
            case 3:
                mostrar(&p);
                break;
            case 4:
                valor = maiorElemento(&p);
                if (valor != -1)
                    printf("Maior elemento: %d\n", valor);
                break;
            case 0:
                printf("Saindo...\n");
                break;
            default:
                printf("Opcao invalida!\n");
        }

    } while (opcao != 0);

    return 0;
}
