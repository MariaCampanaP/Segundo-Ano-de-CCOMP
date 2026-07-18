/* a) Implemente as classes Losango e TrianguloRetangulo, do exercício 4 da lista 
    anterior, como subclasses de FormaGeometrica.
    b)Sobrescreva o método toString() de cada forma geométrica para que produza uma string 
    que representa a forma geométrica
*/

package br.edu.unespar.formasgeometricas;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Losango extends FormaGeometrica {
    
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

    @Override
    public String toString() {
        return "Losango{" + "diagonalHorizontal=" + diagonalHorizontal + ", diagonalVertical=" + diagonalVertical + '}';
    }
}
