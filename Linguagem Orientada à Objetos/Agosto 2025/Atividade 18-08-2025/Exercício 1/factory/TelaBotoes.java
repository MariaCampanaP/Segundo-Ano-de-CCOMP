package factory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaBotoes extends JFrame {

    private JComboBox<TipoBotao> comboBoxBotoes;

    private JButton botaoAdicionar;

    private JPanel painelBotoes;

    public TelaBotoes() {
        super("Exemplo Factory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 200);

        comboBoxBotoes = new JComboBox<>(TipoBotao.values());

        botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelBotoes.add(BotaoFactory.criarBotao(
                        (TipoBotao) comboBoxBotoes.getSelectedItem()));
                revalidate();
            }
        });

        painelBotoes = new JPanel();

        add(comboBoxBotoes, BorderLayout.NORTH);
        add(botaoAdicionar, BorderLayout.SOUTH);
        add(painelBotoes, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

    }
}
