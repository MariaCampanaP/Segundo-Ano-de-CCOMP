package main;

import javax.swing.SwingUtilities;
import view.ManterMensagemView;
import view.ManterUsuarioView;
import view.TelaPrincipal;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ManterUsuarioView();
                //new ManterMensagemView();
                new TelaPrincipal();
            }
        });
    }
}
