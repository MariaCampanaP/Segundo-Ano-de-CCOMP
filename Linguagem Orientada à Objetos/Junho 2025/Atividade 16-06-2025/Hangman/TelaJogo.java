/*Crie uma interface gráfica para o jogo. A interface gráfica deve possuir, pelo menos, duas telas.
A segunda deve permitid ao usuário jogar o jogo. Essa tela deve mostrar quais letras da palavra secreta
já foram adivinhadas corretamente, além de quantas tentativas restam e as tentativas que já ocorreram. Por exemplo,
digamos que a palavra secreta seja "receio" e que o usuário já tenha usado três de cinco tentativas para as letras
'a' e 'e', e tentando a palavra 'releio'. Deve-se mostrar a string _e_e__, indicar que restam duas tentativas, e indicar
que o usuário já tentou as letras 'a' e 'e' e a palavra 'releio'.
*/

package com.mycompany.jogodaforca;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaJogo extends JFrame{
    
    private ControladoraDoJogo jogo;
    private JLabel palavraParcialLabel;
    private JLabel tentativasRestantesLabel;
    private JList<String> listaTentativas;
    private DefaultListModel<String> modeloLista;
    private JTextField campoEntrada;
    private JButton botaoTentar;
    
    public TelaJogo(ControladoraDoJogo jogo){
        super("Jogo da Forca");
        this.jogo = jogo;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        //Componentes principais
        palavraParcialLabel = new JLabel(jogo.getPalavraParcial(), SwingConstants.CENTER);
        tentativasRestantesLabel = new JLabel("Tentativas restantes:" + jogo.getNumTentativas());
        
        modeloLista  = new DefaultListModel<>();
        listaTentativas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaTentativas);
        
        campoEntrada = new JTextField(10);
        botaoTentar = new JButton("Tentar");
        
        JPanel painelTopo = new JPanel(new GridLayout(2, 1));
        painelTopo.add(palavraParcialLabel);
        painelTopo.add(tentativasRestantesLabel);
        
        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Digite uma letra ou palavra:"));
        painelEntrada.add(campoEntrada);
        painelEntrada.add(botaoTentar);
        
        add(painelTopo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelEntrada, BorderLayout.SOUTH);
        
        botaoTentar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String tentativa = campoEntrada.getText().trim().toLowerCase();
                campoEntrada.setText("");
                
                if(tentativa.isEmpty())return;
                
                if(tentativa.length() == 1){
                    jogo.tentarLetra(tentativa.charAt(0));
                }else{
                    jogo.tentarPalavra(tentativa);
                }
                
                atualizarTela();
                
                if(jogo.isJogoEncerrado()){
                    String msg = jogo.isVitoria() ? "Parabens! Voce venceu!" : "Fim de jogo. A palavra era " + jogo.getPalavraSecreta();
                    JOptionPane.showMessageDialog(null, msg);
                    dispose();
                }
        }
    });
    
    setVisible(true);
}
    
    private void atualizarTela(){
        palavraParcialLabel.setText(jogo.getPalavraParcial());
        tentativasRestantesLabel.setText("Tentativas restantes: " + jogo.getNumTentativas());
        modeloLista.clear();
        for(String tentativa : jogo.getTentativas()){
            modeloLista.addElement(tentativa);
        }
    }
}
