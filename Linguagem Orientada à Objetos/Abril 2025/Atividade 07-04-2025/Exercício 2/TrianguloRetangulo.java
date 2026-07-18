/* a) Implemente as classes Losango e TrianguloRetangulo, do exercício 4 da lista 
    anterior, como subclasses de FormaGeometrica.
    b)Sobrescreva o método toString() de cada forma geométrica para que produza uma string 
    que representa a forma geométrica
*/

package br.edu.unespar.formasgeometricas;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class TrianguloRetangulo extends FormaGeometrica {
    
    private double catetoHorizontal;
    private double catetoVertical;

    public TrianguloRetangulo(double catetoHorizontal, double catetoVertical) {
        this.catetoHorizontal = catetoHorizontal;
        this.catetoVertical = catetoVertical;
    }
    
    public double perimetro(){
        double resultado;
        double lado;
        lado = sqrt(pow(this.catetoHorizontal, 2) + (pow(this.catetoVertical, 2)));
        resultado = lado + this.catetoHorizontal + this.catetoVertical;
        return resultado;
    }
    
    public double area(){
        double resultado;
        resultado = (this.catetoHorizontal * this.catetoVertical) / 2;
        return resultado;
    }

    @Override
    public String toString() {
        return "TrianguloRetangulo{" + "catetoHorizontal=" + catetoHorizontal + ", catetoVertical=" + catetoVertical + '}';
    }
}
