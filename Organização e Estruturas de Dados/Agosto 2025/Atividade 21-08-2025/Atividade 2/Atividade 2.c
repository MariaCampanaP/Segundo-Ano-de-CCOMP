/*Implemente uma função que inverta a ordem dos elementos de uma pilha usando.
->Exemplo:
    Entrada(topo -> base):1, 2, 3, 4
    Saída: 4, 3, 2, 1
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 100

//Estrutura de dados Pilha
typedef struct{
    int itens[MAX]; //vetor que armazena os elementos
    int topo; //índice do elemento do topo
}Pilha;

//Função que inicializa a pilha (deixa ela vazia)
void inicializar(Pilha *p){
    p->topo = -1; //quando topo == -1, quer dizer que a pilha está vazia
}

//Retorna 1 se a pilha está vazia, 0 caso contrário
int vazia(Pilha *p){
    return p->topo == -1;
}

//Retorna 1 se a pilha está cheia, 0 caso contrário
int cheia(Pilha *p){
    return p->topo == MAX - 1;
}

//Insere um valor no topo da pilha
void push(Pilha *p, int valor){
    if(cheia(p)){ //Verifica se já está cheia
        printf("Pilha Cheia.\n");
        return;
    }
    p->itens[++p->topo] = valor; //Incrementa o topo e guarda o valor
}

//Remove e retorna o elemento do topo da pilha
int pop(Pilha *p){
    if(vazia(p)){ //Não pode desempilhar se estiver vazia
        return -1; //Retorna -1 como erro
    }
    return p->itens[p->topo--]; //Retorna o valor e decrementa o topo
}

//Retorna o valor do topo (sem remover)
int topo(Pilha *p){
    if(vazia(p)) return -1;
    return p->itens[p->topo];
}

//Função para inverter a pilha
void inverter(Pilha *p){
    if(vazia(p) || p->topo == 0){ //Se estiver vazia ou com 1 elemento
        printf("Pilha ja esta invertida ou vazia.\n");
        return;
    }

    Pilha aux1, aux2; //Cria duas pilhas auxiliares
    inicializar(&aux1);
    inicializar(&aux2);

    //Passa todos os elementos de p -> aux1
    while(!vazia(p)){
        push(&aux1, pop(p));
    }

    //Passa todos os elementos de aux1 -> aux2
    while(!vazia(&aux1)){
        push(&aux2, pop(&aux1));
    }

    //Passa todos os elementos de aux2 -> p (volta invertida)
    while(!vazia(&aux2)){
        push(p, pop(&aux2));
    }

    printf("Pilha invertida com sucesso!\n");
}

//Função para mostrar os elementos da pilha
void mostrar(Pilha *p){
    if(vazia(p)){
        printf("Pilha vazia.\n");
        return;
    }
    printf("Pilha(topo -> base):");
    for(int i = p->topo; i >= 0; i--){ //Imprime do topo até a base
        printf("%d ", p->itens[i]);
    }
    printf("\n");
}

int main(){
    Pilha p; //Cria a pilha
    inicializar(&p); //Inicializa (começa vazia)

    int opcao, valor;

    do{
        printf("\n---MENU---\n");
        printf("1 - Empilhar\n");
        printf("2 - Desempilhar\n");
        printf("3 - Mostrar a pilha\n");
        printf("4 - Inverter pilha\n");
        printf("0 - Sair\n");
        printf("Escolha uma opcao:");
        scanf("%d", &opcao);

        switch(opcao){
        case 1:
            printf("Digite o valor:");
            scanf("%d", &valor);
            push(&p, valor);
            break;

        case 2:
            valor = pop(&p);
            if(valor != -1){
                printf("Desempilhado: %d\n", valor);
            }
            break;

        case 3:
            mostrar(&p);
            break;

        case 4:
            inverter(&p);
            mostrar(&p);
            break;

        case 0:
            printf("Saindo...\n");
            break;

        default:
            printf("Opcao Invalida\n");
        }
    }while(opcao != 0);

    return 0;
}
