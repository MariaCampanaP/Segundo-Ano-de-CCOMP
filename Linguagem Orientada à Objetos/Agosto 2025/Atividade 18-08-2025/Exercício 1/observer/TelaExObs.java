package observer;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TelaExObs extends JFrame {

    private JTextField textField;

    private JButton botaoUm;

    private JButton botaoDois;

    private ConsoleObserver consoleObserver;

    private TextObserver textObserver;

    public TelaExObs() {
        super("Exemplo Singleton");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        textField = new JTextField();
        botaoUm = new JButton("Botão 1");
        botaoDois = new JButton("Botão 2");

        consoleObserver = new ConsoleObserver();
        textObserver = new TextObserver(textField);

        botaoUm.addActionListener(consoleObserver);
        botaoUm.addActionListener(textObserver);
        botaoDois.addActionListener(consoleObserver);
        botaoDois.addActionListener(textObserver);

        add(textField);
        add(botaoUm);
        add(botaoDois);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
