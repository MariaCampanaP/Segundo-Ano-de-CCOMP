/*Fomas Geométricas. Crie um projeto com as seguintes classes:
    c) Uma classe TrianguloRetangulo, com os atributos catetoHorizontal e catetoVertical,
    do tipo double. Também implemente um construtor e os métodos perimetro e area, como nos itens anteriores.
*/
package com.mycompany.exercicio4;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class TrianguloRetangulo {
    
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
}

    

