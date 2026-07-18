package br.edu.unespar.formasgeometricas;

public class Retangulo extends FormaGeometrica {

    private double lado;

    private double altura;

    public Retangulo(double lado, double altura) {
        this.lado = lado;
        this.altura = altura;
    }
    
    public boolean isQuadrado(){
        return lado == altura;
    }

    @Override
    public double area() {
        return lado * altura;
    }

    @Override
    public double perimetro() {
        return 2 * lado + 2 * altura;
    }

    @Override
    public String toString() {
        return "Retângulo com lado " + lado + " e altura " + altura;
    }

}
