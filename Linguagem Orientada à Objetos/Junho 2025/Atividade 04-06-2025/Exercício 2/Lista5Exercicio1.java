package id.exercicio.um;

import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class Lista5Exercicio1 {

    public static void main(String[] args) {
        
    String s = new String();
    int i=0;
        
        try{
            String entradaString = (JOptionPane.showInputDialog(null, "Insira uma string"));
            s = entradaString;
            
            int entradaIndice = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o índice a ser encontrado"));
            i = entradaIndice;
            
            JOptionPane.showMessageDialog(null, "O caractere corresponde da string é: "
            + s.charAt(i));
            
        } catch(InputMismatchException e){
            JOptionPane.showMessageDialog(null, "Entrada inválida", "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch(IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "O índice deve ser válido!", "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "O programa foi fechado durante sua execução","Erro!", JOptionPane.ERROR_MESSAGE);
    }
}
    
}
