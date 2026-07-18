/* Faça um algoritmo que receba um nome, tal nome deve ser enviado por parâmetro para uma função, que deve retornar quantas vogais esse nome possui.
Ao fim, mostre a mensagem com o número de vogais.*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int checarVogais(char nome[]){

    int vogais = 0;

    for(int i = 0; i < strlen(nome); i++){
        if(tolower(nome[i]) == 'a' || tolower(nome[i]) == 'e' || tolower(nome[i]) == 'i' || tolower(nome[i]) == 'o' || tolower(nome[i]) == 'u'){
            vogais++;
        }
    }

    return vogais;

}

int main(){

    char nome[100];

    printf("Insira um nome\n");
    gets(nome);

    printf("Numero de vogais:%d\n", checarVogais(nome));

    return 0;
}
