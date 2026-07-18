/*Defina um tipo de estrutura para armazenar um horário composto de hora, minutos e segundos. Crie um função para realizar a soma entre horários.
A função deve receber dois horários e retornar um horário que é o resultado da soma. Crie também um procedimento que recebe um horário e o apresenta na tela no formato
HH:MM:SS*/

#include <stdio.h>
#include <stdlib.h>

typedef struct{

    int hora;
    int minuto;
    int segundo;

}Horario;

//Função para somar dois horários
    Horario somarHorarios(Horario h1, Horario h2){
    Horario resultado;


    //Soma os segundos
    resultado.segundo = h1.segundo + h2.segundo;
    resultado.minuto = h1.minuto + h2.minuto + (resultado.segundo / 60);
    resultado.segundo %= 60;

    //Soma os minutos
    resultado.hora = h1.hora + h2.hora + (resultado.minuto / 60);
    resultado.minuto %= 60;

    return resultado;
}

//Horario Formatado
void exibirHorario(Horario h){

    printf("%02d:%02d:%02d\n", h.hora, h.minuto, h.segundo);

}

int main(){

    Horario h1 = {2, 45, 50};
    Horario h2 = {1, 20, 30};

    Horario resultado = somarHorarios(h1, h2);

    printf("Horario 1:");
    exibirHorario(h1);
    printf("Horario 2:");
    exibirHorario(h2);
    printf("Soma dos horarios:");
    exibirHorario(resultado);

    return 0;
}
