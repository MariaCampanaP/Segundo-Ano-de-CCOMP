/*Desenvolva um algoritmo que receba uma expressão qualquer que possua parênteses. O algoritmo deve
verificar se a sequência está correta ou não. Uilize o conceito de pilha implementada com ponteiros
Exemplo: a+(b*c)-(x*x)) -> Expressão incorreta
         (a+(b*c)-(x*x)) -> Expressão correta*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct celula{
    char dado;
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

//Empilhar
void push(tipo_pilha *pl, char valor){
    celula *novo = (celula*) malloc(sizeof(celula));
    if(novo == NULL){
        printf("Erro de alocacao!\n");
        exit(1);
    }
    novo->dado = valor;
    novo->prox = pl->topo;
    pl->topo = novo;
    pl->tamanho++;
}

//Desempilhar
char pop(tipo_pilha *pl){
    if(pl->topo == NULL) return '\0'; // pilha vazia
    celula *remover = pl->topo;
    char valor = remover->dado;
    pl->topo = remover->prox;
    free(remover);
    pl->tamanho--;
    return valor;
}

//Verifica se a pilha está vazia
int pilha_vazia(tipo_pilha *pl){
    return (pl->topo == NULL);
}

//Conta elementos
int numero_de_elementos(tipo_pilha *pl){
    return pl->tamanho;
}

//Verifica expressão com parênteses
int verifica_expressao(char *expr){
    tipo_pilha p;
    cria_pilha(&p);

    for(int i = 0; i < strlen(expr); i++){
        if(expr[i] == '('){
            push(&p, '(');
        }else if(expr[i] == ')'){
            if(pilha_vazia(&p)){
                return 0; // fecha sem abrir
            }
            pop(&p);
        }
    }
    return pilha_vazia(&p); // se sobrou '(' é incorreta
}

int main(){
    char expressao[100];
    printf("Digite a expressao: ");
    fgets(expressao, sizeof(expressao), stdin);
    expressao[strcspn(expressao, "\n")] = '\0';

    if(verifica_expressao(expressao)){
        printf("Expressao Correta.\n");
    }else{
        printf("Expressao Incorreta.\n");
    }

    return 0;
}
