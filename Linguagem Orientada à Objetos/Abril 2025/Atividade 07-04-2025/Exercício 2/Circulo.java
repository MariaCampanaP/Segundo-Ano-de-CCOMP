/* Sobrescreva o método toString() de cada forma geométrica para que produza uma 
    string que representa a forma geométrica.
*/

package br.edu.unespar.formasgeometricas;

public class Circulo extends FormaGeometrica {

    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double area() {
        return Math.PI * raio * raio;
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * raio;
    }

    @Override
    public String toString() {
        return "Circulo{" + "raio=" + raio + '}';
    }
}
