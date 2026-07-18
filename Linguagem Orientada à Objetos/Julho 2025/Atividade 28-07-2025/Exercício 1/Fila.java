package id.produtor.consumidor;

import java.util.concurrent.TimeUnit;

public interface Fila {

    //insere um valor na Fila
    public void put(int valor) throws InterruptedException;

    //obtem um valor da Fila
    public int take() throws InterruptedException;

    //tenta inserir um valor na fila
    public boolean offer(int valor, double tempo, TimeUnit unidadeTempo) throws InterruptedException;

    //tenta remover um valor na fila
    public Integer poll(double tempo, TimeUnit unidadeTempo) throws InterruptedException;
}

