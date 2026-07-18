package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsoleObserver implements ActionListener {

    private int contagem;

    public ConsoleObserver() {
        contagem = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Algum botao foi apertado " + (++contagem) + " vezes.");
    }

}
