
package com.mycompany.contabancariathreads;

import javax.swing.SwingUtilities;

public class ContaBancariaThreads {

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            TelaContaBancaria tela = new TelaContaBancaria();
        });
    }
}
