/* Modifique o algoritmo de busca binária para encontrar um elemento em um array ordenado
que foi girado em um ponto desconhecido, por exemplo, [4, 5, 6, 7, 1, 2, 3] */

typedef struct{
    int elementos[100];
    int tamanho;
}Vetor;

int buscaRotacionada(Vetor v, int alvo){
    int inicio = 0;
    int fim = v.tamanho - 1;

    while(inicio <= fim){
        int meio = (inicio + fim)/2;

        if(v.elementos[meio] == alvo)
            return meio;

        if(v.elementos[inicio] <= v.elementos[meio]){
            if(alvo >= v.elementos[inicio] && alvo < v.elementos[meio])
                fim = meio - 1;
            else
                inicio = meio + 1;
        }
        else{
            if(alvo > v.elementos[meio] && alvo <v.elementos[fim])
                inicio = meio + 1;
            else
                fim = meio - 1;
        }
    }

    return -1;

}

int main(){

    Vetor v = {{4, 5, 6, 7, 1, 2, 3}, 7};
    int alvo;

    printf("Digite o elemento que deseja busca:");
    scanf("%d", &alvo);

    int pos = buscaRotacionada(v, alvo);

    if(pos != -1)
        printf("Elemento %d encontrada na posicao %d\n", alvo, pos);
    else
        printf("Elemento nao encontrada.\n");

    return 0;
}
