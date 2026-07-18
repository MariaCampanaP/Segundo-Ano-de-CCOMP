/* Faça um algoritmo para verificar se um número é triangular. Um número é triangular quando o resultado
do produto de três números consecutivos.
    Exemplo: 24 = 2 x 3 x 4.
*/

#include <stdio.h>
#include <stdlib.h>

int main(){

    int num;
    int i;
    int j;
    int check = 0;

        printf("Insira um numero:\n");
        scanf("%d", &num);

        if(num != 0){
            if(num > 0){
                for(i = 1; i < num; i++){
                    if((i * (i + 1) * (i+2)) == num){
                        check++;
                    }
                    }}}

            if(check != 0){
                printf("%d e triangular\n", num);
            }else{
                printf("%d nao e triangular\n", num);
            }

    return 0;

    }
