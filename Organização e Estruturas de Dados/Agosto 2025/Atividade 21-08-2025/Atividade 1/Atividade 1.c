/*Implemente um código usando pilha que simule o avançar e voltar de uma página web. Em
sua struct utilize um campo de string para guardar o nome do site.
-> Use duas pilhas: -> páginas visitadas. -> páginas que podem ser acessadas com "avançar"
O usuário pode:
-> Visitar (url) -> empilha no histórico e limpa a pilha de futuro
-> Voltar() -> desempilha do histórico e empilha no futuro
-> Avançar() -> desempilha do futuro e empilha no histórico*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 100

typedef struct{
    char sites[MAX][100]; //Até 100 páginas de 100 caracteres
    int topo; //Topo da pilha
}Pilha;

//Inicializar a pilha
void inicializar(Pilha *p){
    p->topo = -1;
}

//Verifica se a pilha está vazia (retorna 1 se sim, 0 se não)
int vazia(Pilha *p){
    return p->topo == -1;
}

//Verificar se a pilha está cheia (retorna 1 se sim, 0 se não)
int cheia(Pilha *p){
    return p->topo == MAX - 1;
}

//Empilhar
void push(Pilha *p, char *url){
    if(cheia(p)){ //Se está cheia avisa que está cheia
        printf("Pilha Cheia.\n");
        return;
    }
    strcpy(p->sites[++p->topo], url); //Incrementa o topo e copia a string "url"
}

//Desempilhar
char* pop(Pilha *p){
    if(vazia(p)){ //Se está vazia nao tem nada para desempilhar
        return NULL;
    }
    return p->sites[p->topo--]; //Retornar o site e depois decrementa o topo
}

//Olha o elemento do topo sem remover
char* topo(Pilha *p){
    if(vazia(p)){ //Se estiver vazia nao tem topo
        return NULL;
    }
    return p->sites[p->topo]; //Retorna o site do topo
}

//visitar uma nova página
void visitar(Pilha * historico, Pilha *futuro, char *url){
    push(historico, url); //Empilha o site no histórico
    inicializar(futuro); //Limpa a pilha de futuro
    printf("Visitando: %s\n", topo(historico)); //Mostra a página
}

//Volta para a página anterior
void voltar(Pilha *historico, Pilha *futuro){
    if(historico->topo <= 0){ //Se só tem 1 página (ou nenhuma)
        printf("Nao ha paginas para voltar");
        return;
    }
    char *site = pop(historico); //remove o site atual do histórico
    push(futuro, site); //Coloca no futuro
    printf("Voltando para: %s\n", topo(historico)); //Mostra nova página atual
}

//Avança para a próxima página (se tiver)
void avancar(Pilha * historico, Pilha *futuro){
    if(vazia(futuro)){ //Se não tiver nada no futuro
        printf("Nao ha paginas para avancar.\n");
        return;
    }
    char *site = pop(historico); //Remove do futuro
    push(historico, site); //Coloca no histórico
    printf("Avancando para: %s\n", topo(historico)); //Mostra página atual
}

int main(){
    Pilha historico, futuro; //Cria duas pilhas
    inicializar(&historico); //Inicializa histórico
    inicializar(&futuro); //Inicializa futuro

    int opcao;
    char url[100];

    do{
        printf("\n---Menu---\n");
        printf("1 - Visitar o site\n");
        printf("2 - Voltar\n");
        printf("3 - Avancar\n");
        printf("0 - Sair\n");
        printf("Escolha uma opcao:");
        scanf("%d", &opcao);
        getchar();

        switch(opcao){
        case 1:
            printf("Digite a URL:");
            fgets(url, 100, stdin);
            url[strcspn(url, "\n")] = '\0';
            visitar(&historico, &futuro, url);
            break;

        case 2:
            voltar(&historico, &futuro);
            break;

        case 3:
            avancar(&historico, &futuro);
            break;

        case 0:
            printf("Saindo...\n");
            break;

        default:
            printf("Opcao invalida!\n");

        }
        if(!vazia(&historico)){
            printf("Pagina atual: %s\n", topo(&historico));
        }else{
            printf("Nenhuma pagina aberta.\n");
            }

    }while(opcao != 0);

    return 0;
}
