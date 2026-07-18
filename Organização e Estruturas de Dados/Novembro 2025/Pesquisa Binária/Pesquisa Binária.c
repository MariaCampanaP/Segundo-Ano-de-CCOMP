/* Pesquisa Binária */

typedef struct record {
    int chave;
    // Outros campos
}registro;

typedef struct table{
    registro item[1000];
    int tamanho;
}tabela;

int busca_binaria_rec(tabela tab, int valor, int esq, int dir){
    if(esq > dir) // Caso base: não encontrou
        return -1;

    int meio = (esq + dir) / 2;

    if(valor == tab.item[meio].chave) // Achou valor
        return meio;
    else if (valor < tab.item[meio].chave) // Procura na metade esquerda
        return busca_binaria_rec(tab, valor, esq, meio - 1);
    else // Procura na metade direita
        return busca_binaria_rec(tab, valor, meio + 1, dir);
}

int busca_binaria(tabela tab, int valor){
    int i, esq, dir;
    if(tab.tamanho == 0)
        return 0;
    else {
        esq = 0;
        dir = tab.tamanho - 1;
        do{
            i = (esq + dir) / 2;
            if(valor > tab.item[i].chave)
                esq = i + 1;
            else
                dir = i + 1;
        } while(valor != tab.item[i].chave && esq <= dir);
        if(valor == tab.item[i].chave)
            return i;
        else
            return -1;
    }
}

int main(){

    tabela tab;
    tab.tamanho = 0;

    for(int i = 0; i <= 30; i++){
        tab.item[i].chave = i;
        tab.tamanho++;
    }

    int resultado = busca_binaria(tab, 20);

    if(resultado != -1)
        printf("Valor encontrado na posicao %d\n", resultado);
    else
        printf("Valor nao encontrado\n");

    return 0;
}
