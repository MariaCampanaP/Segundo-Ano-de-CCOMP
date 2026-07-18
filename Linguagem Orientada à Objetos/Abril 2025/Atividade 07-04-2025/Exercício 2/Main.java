/* c) Modifique o método main para que preencha as outras duas posições do vetor
    formas com um objeto da classe Losango e um objeto da classe TrianguloRetangulo,
    e então imprima (usando o método toString()) o objeto com a maior área dentre os quatro
*/

package br.edu.unespar.formasgeometricas;

public class Main {
    
    public static void main(String[] args) {
        int indiceMaiorarea = 0;
        
        FormaGeometrica[] formas = new FormaGeometrica[4];
        formas[0] = new Retangulo(3, 4);
        formas[1] = new Circulo(4);
        formas[2] = new Losango(3, 4);
        formas[3] = new TrianguloRetangulo(3, 4);
        
        for(int i = 0; i < 4; i++){
            if(formas[i].area() > formas[indiceMaiorarea].area()){
                indiceMaiorarea = i;
            }
        }
        System.out.println(formas[indiceMaiorarea]);
    }
} 