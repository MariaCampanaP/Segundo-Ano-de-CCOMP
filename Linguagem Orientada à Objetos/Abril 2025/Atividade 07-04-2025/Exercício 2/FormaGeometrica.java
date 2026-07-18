package br.edu.unespar.formasgeometricas;

public abstract class FormaGeometrica {

    public abstract double area();

    public abstract double perimetro();

    public boolean isMaior(FormaGeometrica outraForma) {
        return this.area() > outraForma.area();
    }

}
