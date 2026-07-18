package id.produtor.consumidor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FilaNaosincronizada implements Fila {

    private List<Integer> elementos;

    public FilaNaosincronizada() {
        elementos = new ArrayList<>();
    }

    @Override
    public void put(int valor) throws InterruptedException {
        elementos.add(valor);
    }

    @Override
    public int take() throws InterruptedException {
        return elementos.removeFirst();
    }

    @Override
    public boolean offer(int valor, double tempo, TimeUnit unidadeTempo) throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer poll(double tempo, TimeUnit unidadeTempo) throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
