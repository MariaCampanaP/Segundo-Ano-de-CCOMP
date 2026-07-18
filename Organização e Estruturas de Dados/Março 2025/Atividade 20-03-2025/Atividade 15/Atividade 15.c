/*Faça um algoritmo que receba nome, e 4 notas (Outro vetor de structs) de 6 alunos, armazene esses dados em um vetor de structs.
Posteriormente, o vetor deve ser percorrido, e deverá ser apresentado o nome do aluno com maior média entre as 4 notas.*/

#include <stdio.h>
#include <stdlib.h>

struct aluno{

    char nome[50];
    float notas[4];
    float media;

};

typedef struct aluno Aluno;

int main(){

    Aluno estudante[6];
    int i;
    float nota;
    float soma = 0;
    float maiorMedia = 0;
    int indiceMaior = 0;

    //Alunos
    for(i = 0; i < 6; i++){
        printf("Nome do estudante:");
        gets(estudante[i].nome);

    //Reinicia a soma para cada aluno
    soma = 0;
    //Notas dos Alunos
    for(int j = 0; j < 4; j++){
        printf("Nota %d:", j + 1);
        scanf("%f", &estudante[i].notas[j]);
        soma += estudante[i].notas[j];
        getchar();
    }

    //Calculo da média do aluno
    estudante[i].media = soma / 4;

    if(estudante[i].media > maiorMedia){ //Se estudante[i].media for maior que maior media -> maior media vai para estudante[i].media. Atualizamos o indice maior
        maiorMedia = estudante[i].media;
        indiceMaior = i;
        }
    }

    printf("\nAluno com maior media:%s", estudante[indiceMaior].nome);
    printf("\nMedia:%.2f\n", maiorMedia);

    return 0;

}

