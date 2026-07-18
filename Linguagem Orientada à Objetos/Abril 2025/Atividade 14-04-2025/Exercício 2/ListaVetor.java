package br.edu.unespar.lista.vetor;

import java.util.Iterator;

public class ListaVetor implements Lista, Iterable<Integer> {

    private int[] elementos;

    private int indiceAtual;

    public ListaVetor() {
        elementos = new int[10];
        indiceAtual = 0;
    }

    public int[] getElementos() {
        return elementos;
    }

    public int getIndiceAtual() {
        return indiceAtual;
    }

    public void inserir(int elemento) {
        if (indiceAtual >= elementos.length) {
            int[] novosElementos = new int[elementos.length * 2];
            for (int i = 0; i < elementos.length; i++) {
                novosElementos[i] = elementos[i];
            }
            elementos = novosElementos;
        }
        elementos[indiceAtual] = elemento;
        indiceAtual++;
    }

    public int buscar(int elemento) {
        for (int i = 0; i < indiceAtual; i++) {
            if (elementos[i] == elemento) {
                return i;
            }
        }
        return -1;
    }

    public boolean remover(int elemento) {
        int posicao = buscar(elemento);
        if (posicao == -1) {
            return false;
        }
        for (int i = posicao; i < indiceAtual - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        indiceAtual = indiceAtual - 1;
        return true;
    }

    public String toString() {
        if (indiceAtual == 0) {
            return "Lista vazia!";
        }
        String lista = "";
        for (int i = 0; i < indiceAtual; i++) {
            lista = lista + elementos[i] + ", ";
        }
        return lista;

    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteradorListaVetor(this);
    }

}
