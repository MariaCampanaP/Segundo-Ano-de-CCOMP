package main;

import factory.TelaBotoes;
import javax.swing.SwingUtilities;
import observer.TelaExObs;
import singleton.TelaDupla;

/**
 *
 * @author nicol
 */
public class PadroesDeProjeto {
    
    public static void main(String[] args) {
        //exFactory();
        exObserver();
        //exSingleton();
    }

    private static void exFactory() {
        SwingUtilities.invokeLater(() -> {
            TelaBotoes tela = new TelaBotoes();
        });
    }

    private static void exObserver() {
        SwingUtilities.invokeLater(() -> {
            TelaExObs tela = new TelaExObs();
        });
    }

    private static void exSingleton() {
        SwingUtilities.invokeLater(() -> {
            TelaDupla tela = new TelaDupla();
            TelaDupla tela2 = new TelaDupla();
        });
    }
}
