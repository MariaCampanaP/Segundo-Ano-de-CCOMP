/*Crie uma interface gráfica para o jogo. A interface gráfica deve possuir, pelo menor, duas telas.
A primeira delas deve permitir que o usuário ou digite uma palavrs secreta ou então escolha uma de uma lista de opções,
além de indicar o número de tentativas que são permitidas.
*/

package com.mycompany.jogodaforca;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfacesDoJogo extends JFrame{
    
    private JTextField campoPalavra;
    private JTextField campoTentativas;
    private JButton botaoIniciar;
    
    public InterfacesDoJogo(){
        super("Jogo da Forca - Inicio");
       
        //Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        
        //Componentes
        campoPalavra = new JTextField(20);
        campoTentativas = new JTextField(5);
        botaoIniciar = new JButton("Começar Jogo");
        
        //Painel com os campos
        JPanel painel = new JPanel(new GridLayout(4, 1));
        painel.add(new JLabel("Digite a palavra secreta:"));
        painel.add(campoPalavra);
        painel.add(new JLabel("Digite o numero de tentativas:"));
        painel.add(campoTentativas);
        painel.add(botaoIniciar);
        
        add(painel);
    
    
    //Ação do botão
   botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palavra = campoPalavra.getText().trim();
                String tentativasTexto = campoTentativas.getText().trim();

                if (palavra.isEmpty() || tentativasTexto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return;
            }
                
            try{
                int tentativas = Integer.parseInt(tentativasTexto);
                
                ControladoraDoJogo jogo = new ControladoraDoJogo(palavra, tentativas);
                dispose();
                new TelaJogo(jogo);
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Digite um numero valido de tentativas");
            }
        }
    });
    
    setVisible(true);
    
    }
}
