/*(Desafio) Usando somente pilhas auxiliares, ordene os elementos de uma pilha em ordem crescente (menor no topo)*/

#include <stdio.h>
#define MAX 100

// Estrutura da pilha
typedef struct {
    int itens[MAX];  // Array que armazena os elementos da pilha
    int topo;        // Índice do topo da pilha (-1 se vazia)
} Pilha;

// Inicializa a pilha (vazia)
void inicializar(Pilha *p) {
    p->topo = -1;
}

// Retorna 1 se pilha estiver vazia, 0 caso contrário
int vazia(Pilha *p) {
    return p->topo == -1;
}

// Retorna 1 se pilha estiver cheia, 0 caso contrário
int cheia(Pilha *p) {
    return p->topo == MAX - 1;
}

// Empilha um valor
void push(Pilha *p, int valor) {
    if (cheia(p)) {                  // verifica se cheia
        printf("Pilha cheia!\n");
        return;
    }
    p->itens[++p->topo] = valor;     // incrementa topo e guarda valor
}

// Desempilha e retorna valor do topo
int pop(Pilha *p) {
    if (vazia(p)) {                  // verifica se vazia
        printf("Pilha vazia!\n");
        return -1;
    }
    return p->itens[p->topo--];      // retorna valor e decrementa topo
}

//Retorna o valor do topo sem remover
int topo(Pilha *p){
    return vazia(p) ? -1 : p->itens[p->topo];
}

// Mostra os elementos da pilha (topo -> base)
void mostrar(Pilha *p) {
    if (vazia(p)) {
        printf("Pilha vazia.\n");
        return;
    }
    printf("Pilha (topo -> base): ");
    for (int i = p->topo; i >= 0; i--) {  // do topo até a base
        printf("%d ", p->itens[i]);
    }
    printf("\n");
}

// Função que remove todas as ocorrências de x
void removerX(Pilha *p, int x) {
    Pilha aux;
    inicializar(&aux);                  // pilha auxiliar

    // Desempilha todos os elementos
    while (!vazia(p)) {
        int valor = pop(p);
        if (valor != x) {              // só guarda os diferentes de x
            push(&aux, valor);
        }
    }

    // Devolve os elementos restantes para a pilha original
    while (!vazia(&aux)) {
        push(p, pop(&aux));
    }

    printf("Todos os %d foram removidos.\n", x);
}

//Ordena a pilha em ordem crescente (menor no topo)
void ordenarPilha(Pilha *p){
    Pilha aux;
    inicializar(&aux); //Pilha auxiliar

    while(!vazia(p)){
        int temp = pop(p); //Retira elemento da pilha original
        //Move elementos maiores de aux de volta para o
        while(!vazia(&aux) && topo(&aux) > temp){
            push(p, pop(&aux));
        }
        push(&aux, temp); //Insere temp na posição correta
    }
    //Devolve os elementos ordenados para a pilha original
    while(!vazia(&aux)){
        push(p, pop(&aux));
    }
    printf("Pilha ordenada em ordem crescente!\n");
}

int main() {
    Pilha p;
    inicializar(&p);                     // inicializa pilha vazia

    int opcao, valor;                    // variáveis do menu

    do {
        printf("\n===== MENU =====\n");
        printf("1 - Empilhar (push)\n");
        printf("2 - Desempilhar (pop)\n");
        printf("3 - Mostrar pilha\n");
        printf("4 - Remover um valor x\n");
        printf("5 - Ordenar pilha (crescente)\n");
        printf("0 - Sair\n");
        printf("Escolha uma opcao: ");
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
                printf("Digite o valor a remover: ");
                scanf("%d", &valor);
                removerX(&p, valor);
                break;

            case 5:
                ordenarPilha(&p);
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
