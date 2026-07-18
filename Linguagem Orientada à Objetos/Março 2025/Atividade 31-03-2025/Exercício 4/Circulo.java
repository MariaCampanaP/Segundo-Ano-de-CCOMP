/* Formas geométricas. Crie um projeto com as seguintes classes:
    d) Uma classe Circulo, com o atributo raio do tipo double, e os métodos perimetro e are, como nos itens anteriores.
 */

package com.mycompany.exercicio4;

import static java.lang.Math.pow;

public class Circulo {
    
    private double raio;
    
    public Circulo(double raio) {
        this.raio = raio;
    }
    
    public double perimetro(){
        double resultado;
        resultado = 2 * this.raio * Math.PI;
        return resultado;
    }
    
    public double area(){
        double resultado;
        resultado = Math.PI * pow(this.raio, 2);
        return resultado;
    }
}

    

    
