package id.produtor.consumidor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class Consumidor implements Runnable {

    private Fila fila;

    private int soma;

    public Consumidor(Fila fila) {
        this.fila = fila;
        soma = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                soma += fila.poll(5.0, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException e){
                showMessageDialog(null, "Lista vazia, não foi possível remover um elemento", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public int getSoma() {
        return soma;
    }

}
