package id.telas.basicas;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        String nomeBotao = JOptionPane.showInputDialog(null, "Insira o novo nome do botão");
        
        TelaBasica tela = new TelaBasica(nomeBotao);

    }
}
