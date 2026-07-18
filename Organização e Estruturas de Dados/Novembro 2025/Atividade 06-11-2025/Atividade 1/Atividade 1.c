/* Modifique o algoritmo de busca binária para retornar a quantidade de elementos
iguais ao que está sendo buscado presentes no vetor */

typedef struct record {
    int chave;
    // Outros campos
}registro;

typedef struct table{
    registro item[1000];
    int tamanho;
}tabela;

int buscaBinariaConta(tabela tab, int valor){
    int esq = 0;
    int dir = tab.tamanho;
    int meio;
    int cont = 0;

    while(esq <= dir){
        meio = (esq + dir)/2;
    if(valor == tab.item[meio].chave){
        cont = 1;

        int i = meio - 1;
        while(i >= 0 && tab.item[i].chave == valor){
            cont++;
            i--;
        }

        i = meio + 1;
        while(i < tab.tamanho && tab.item[i].chave == valor){
            cont++;
            i++;
        }

        return cont;
    }
    else if(valor < tab.item[meio].chave){
        dir = meio - 1;

    }
    else{
        esq = meio + 1;
        }
    }

    return 0;
}

int main(){

    tabela tab;
    tab.tamanho = 0;

    int dados[] = {1, 2, 2, 2, 3, 4, 5};
    int n = sizeof(dados)/sizeof(dados[0]);

    for(int i = 0; i <= n; i++){
        tab.item[i].chave = dados[i];
        tab.tamanho++;
    }

    int valor = 2;
    int qtd = buscaBinariaConta(tab, valor);

    if(qtd > 0)
        printf("O valor %d aparece %d vezes no vetor.\n", valor, qtd);
    else
        printf("Valor nao encontrado\n");

    return 0;
}
