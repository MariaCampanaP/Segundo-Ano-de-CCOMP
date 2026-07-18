/* Crie uma classe com uma interface gráfica que permite criar um personagem. Essa 
interface deve possuir campos para cada atributo do personagem e um botão para
criar o personagem. Essa classe deve manter uma lista de personagens, e adicionar
cada personagem criado nesta lista.
*/

package com.mycompany.rpg;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CriacaoPersonagem extends JFrame{
 
    private JTextField nomeField; //Cria uma caixa de texto para receber entrada
    private JTextField nivelField; //Cria uma caixa de texto para receber entrada
    private JComboBox <ClassePersonagens> classeBox; //Cria uma lista de eventos
    private JButton criarBotao; //Cria um botão
    private List <Personagem> personagens; //Lista
    
public CriacaoPersonagem() {
        
        super("Criador de Personagem:");
        
        personagens = new ArrayList<>();
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        
        //Painel de entrada
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2, 10, 10));
        
       painel.add(new JLabel("Nome:"));
       nomeField = new JTextField();
       painel.add(nomeField);
       
       painel.add(new JLabel("Nivel (1 a 100):"));
       nivelField = new JTextField();
       painel.add(nivelField);
       
       painel.add(new JLabel("Classe:"));
       classeBox = new JComboBox<>(ClassePersonagens.values());
       painel.add(classeBox);
       
       criarBotao = new JButton("Criar Personagem");
       painel.add(criarBotao);
      
       //Ação do botão
       criarBotao.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String nome = nomeField.getText().trim();
            int nivel = Integer.parseInt(nivelField.getText().trim());
            ClassePersonagens classe = (ClassePersonagens) classeBox.getSelectedItem();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O nome não pode ser vazio");
                return;
            }

            if (nivel < 1 || nivel > 100) {
                JOptionPane.showMessageDialog(null, "O nível deve estar entre 1 e 100");
                return;
            }

            Personagem p = new Personagem(nome, nivel, classe);
            personagens.add(p);

            JOptionPane.showMessageDialog(null, "Personagem criado com sucesso");

            nomeField.setText("");
            nivelField.setText("");
            classeBox.setSelectedIndex(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor insira um número válido para o nível.");
        }
    }
    });
       
       add(painel);
       setVisible(true);
       
    }

}
