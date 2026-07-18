package id.produtor.consumidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FilaSincronizada implements Fila {

    ArrayBlockingQueue<Integer> elementos;

    public FilaSincronizada() {
        elementos = new ArrayBlockingQueue<>(10);
    }

    @Override
    public void put(int valor) throws InterruptedException {
        elementos.put(valor);
    }

    @Override
    public int take() throws InterruptedException {
        return elementos.take();
    }

    @Override
    public boolean offer(int valor, double tempo, TimeUnit unidadeTempo) throws InterruptedException {
         return elementos.offer(valor, (long) tempo, unidadeTempo);
    }

    @Override
    public Integer poll(double tempo, TimeUnit unidadeTempo) throws InterruptedException {
        return elementos.poll((long) tempo, unidadeTempo);
    }
}
