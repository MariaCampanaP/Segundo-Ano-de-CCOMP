package view;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaPrincipal extends JFrame {

    private JButton botaoUsuarios;

    private JButton botaoMensagens;

    public TelaPrincipal() {
        super("Escolha uma opção");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        botaoUsuarios = new JButton("Usuários");
        botaoUsuarios.addActionListener((e) -> {
            new ManterUsuarioView(this);
        });

        botaoMensagens = new JButton("Mensagens");
        botaoMensagens.addActionListener((e) -> {
            new ManterMensagemView(this);
        });
        add(botaoUsuarios);
        add(botaoMensagens);
        
        Font fonte = new Font("Arial", Font.PLAIN, 20);
        this.setFont(fonte);
        botaoUsuarios.setFont(fonte);
        botaoMensagens.setFont(fonte);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
