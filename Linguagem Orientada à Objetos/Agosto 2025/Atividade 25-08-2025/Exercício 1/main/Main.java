package main;

import javax.swing.SwingUtilities;
import view.ManterUsuarioView;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ManterUsuarioView tela = new ManterUsuarioView();
            }
        });
    }
}
