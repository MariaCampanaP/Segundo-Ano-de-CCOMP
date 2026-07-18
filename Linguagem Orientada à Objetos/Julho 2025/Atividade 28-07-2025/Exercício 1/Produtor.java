package id.produtor.consumidor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class Produtor implements Runnable {

    private Fila fila;

    public Produtor(Fila fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                if(!fila.offer(10, 5.0, TimeUnit.SECONDS)){
                    showMessageDialog(null, "Não foi possível adicionar um elemento", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
