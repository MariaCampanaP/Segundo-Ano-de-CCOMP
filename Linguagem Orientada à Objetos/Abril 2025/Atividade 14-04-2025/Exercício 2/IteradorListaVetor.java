/* Implemente o método hasNext() na classe IteradorListaVetor. Esse método deve
   retornar true se há mais algum elemento na lista que ainda não foi retornado 
   pelo método next(), e false caso contrário.

   Implemente o método next() na classe IteradorListaVetor. Na primeira vez que
   é chamado, esse método deve retornar o primeiro elemento da lista. Na segunda
   vez, deve retornar o segundo elemento, e assim por diante. Perceba que, se o 
   método next() retornar o último elemento da lista, então todas as próximas 
   chamadas ao método hasNext() devem retornar false.
*/

package br.edu.unespar.lista.vetor;

import java.util.Iterator;

public class IteradorListaVetor implements Iterator<Integer> {

    private ListaVetor lista;
    private int indiceAtual  = 0;

    public IteradorListaVetor(ListaVetor lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return this.lista.getElementos() != null;
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new java.util.NoSuchElementException("Nao ha mais elementos.");
        }
        
        int proximo[] = this.lista.getElementos();
        
        Integer valor = proximo[this.indiceAtual];
        this.indiceAtual++;
        
        return valor;
    }
}