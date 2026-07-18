
package com.mycompany.rosadosventos;

import javax.swing.JOptionPane;

public class RosaDosVentos {

    public static void main(String[] args) {
        
    try{    
    Posicoes direcao = (Posicoes)JOptionPane.showInputDialog(null, "Caixa de opcoes", "Titulo", JOptionPane.INFORMATION_MESSAGE, null, Posicoes.values(), Posicoes.NORTE);  
    JOptionPane.showMessageDialog(null,"Voce escolheu o ponto: " + direcao.toString());
    
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Voce fechou a janela ou a execucao foi cancelada!");
    }
    }
  }
