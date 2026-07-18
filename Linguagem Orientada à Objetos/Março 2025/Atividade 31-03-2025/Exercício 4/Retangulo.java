/* Formas geométricas. Crie um projeto com as seguintes classes:
    a) Uma classe Retangulo, com os atributos lado e altura do tipo double, um construtor que incializa os dois parâmetros e o métodos perimetro, area e isQuadrado,
    que calculam a área, o perímetro e indicam se o retângulo é um quadrado, respectivamente.
*/

package com.mycompany.exercicio4;


public class Retangulo {
    
    private double lado;
    private double altura;

    public Retangulo(double lado, double altura) {
        this.lado = lado;
        this.altura = altura;
    }
    
    public double perimetro(){
        double resultado;
        resultado = 2 * this.lado + 2 * this.altura;
        return resultado;   
    }
    
    public double area(){
        double resultado;
        resultado = this.lado * this.altura;
        return resultado;
    }
    
    public boolean isQuadrado(){
        if(this.lado == this.altura){
            return true;
        }else{
            return false;
        }
    }
}
