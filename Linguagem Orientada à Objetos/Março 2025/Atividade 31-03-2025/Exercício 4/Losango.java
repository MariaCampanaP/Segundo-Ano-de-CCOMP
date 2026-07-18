/* Formas geométricas. Crie um projeto com as seguintes classes:
    b) Uma classe Losango, com os atributos diagonalHorizontal e diagonalVertical, do tipo double.
    Também implemente um construto e os métodos perimetro, area e isQuadrado, como no item anterior.
    A área de um losango é dada pelo produto das diagonais dividido por 2. Para calcular o perímetro, calcule um lado do losango
    (Usando Pitágoras) e o multiplique por 4.
 */
package com.mycompany.exercicio4;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Losango {
    
    private double diagonalHorizontal;
    private double diagonalVertical;

    public Losango(double diagonalHorizontal, double diagonalVertical) {
        this.diagonalHorizontal = diagonalHorizontal;
        this.diagonalVertical = diagonalVertical;
    }
    
    public double perimetro(){
        double resultado;
        double lado;
        lado = sqrt(pow(this.diagonalHorizontal / 2,2) + (pow(this.diagonalVertical / 2,2)));
        resultado = lado * 4;
        return resultado;
    }
    
    public double area(){
        double resultado;
        resultado = (this.diagonalHorizontal * diagonalVertical) / 2;
        return resultado;
    }
    
    public boolean isQuadrado(){
        double lado;
        lado = sqrt(pow(this.diagonalHorizontal / 2,2) + (pow(this.diagonalVertical / 2,2)));
        if(area() == lado*lado){
            return true;
        }else{
            return false;
        }
    }
 
}
