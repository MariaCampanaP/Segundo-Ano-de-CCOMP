package id.caixa.dialogo;

import javax.swing.JOptionPane;

public class CaixaDialogo {

    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog(null, "Digite um número inteiro");
        try{
            
        
        int numero = Integer.parseInt(entrada);
        JOptionPane.showMessageDialog(null, "Você digitou o número " + numero);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "PANE NO SISTEMA ");
        }
    }
}
