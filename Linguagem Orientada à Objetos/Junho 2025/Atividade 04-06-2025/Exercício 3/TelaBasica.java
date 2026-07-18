package id.telas.basicas;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaBasica extends JFrame {

    private JLabel label;
    private JTextField caixaTexto;
    private JButton botao;

    private String nomeLabel = "Texto do label 1";
    private String nomeCaixaTexto = "Um texto inicial";
    private String nomeBotao = "Um botão";
    
    public TelaBasica(String nomeBotao) {
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 150);

        label = new JLabel(nomeLabel);
        add(label);
        
        caixaTexto = new JTextField(nomeCaixaTexto, 15);
        add(caixaTexto);
        
        botao = new JButton(nomeBotao);
        add(botao);
        
        setVisible(true);
    }
    
    public void setLabel(String s){
        nomeLabel = s;
        label.setText(s);
    }
    
    public void setCaixaTexto(String s){
        nomeCaixaTexto = s;
        caixaTexto.setText(s);
    }
    
    public void setBotao(String s){
        nomeBotao = s;
        botao.setText(s);
    }


}
