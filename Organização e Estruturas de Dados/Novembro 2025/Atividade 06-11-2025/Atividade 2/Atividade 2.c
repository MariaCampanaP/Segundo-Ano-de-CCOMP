/* Modifique o algoritmo de busca binária para retornar o menor elemento maior que o que está
sendo buscado. Por exemplo, dado o vetor [1, 2, 3, 4, 4, 4, 5, 6, 7], o elemento a ser buscado é o
4. Então a função deve retornar o elemento 5. Faça os testes considerando que pode haver
repetição de números no vetor */

typedef struct record {
    int chave;
    // Outros campos
}registro;

typedef struct table{
    registro item[1000];
    int tamanho;
}tabela;

int buscaSucessor(tabela tab, int valor){
    int esq = 0;
    int dir = tab.tamanho - 1;
    int meio;
    int resposta = -1;

    while(esq <= dir){
        meio = (esq + dir)/2;
        if(tab.item[meio].chave > valor){
            resposta = tab.item[meio].chave;
            dir = meio - 1;
        }else{
            esq = meio + 1;
        }
    }
    return resposta;
}

int main(){

    tabela tab;
    tab.tamanho = 0;

    int dados[] = {1, 2, 3, 4, 4, 4, 5, 6, 7};
    int n = sizeof(dados)/sizeof(dados[0]);

    for(int i = 0; i < n; i++){
        tab.item[i].chave = dados[i];
        tab.tamanho++;
    }

    int valor = 4;
    int sucessor = buscaSucessor(tab, valor);

    if(sucessor != -1)
        printf("O menor elemento maior que %d eh %d\n", valor, sucessor);
    else
        printf("Nao existe elemento maior que %d\n", valor);

    return 0;
}
