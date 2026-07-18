package singleton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaDupla extends JFrame {

    private JLabel labelTexto;
    private JButton botaoItalico;
    private JButton botaoNegrito;
    private JButton botaoAtualizar;
    private JPanel painelInferior;
    private ConfiguracaoSistema conf;

    public TelaDupla() {
        super("Exemplo Singleton + Observer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 200);

        conf = ConfiguracaoSistema.getInstancia();
        
        labelTexto = new JLabel("Texto de exemplo!");
        labelTexto.setFont(conf.getFonte());
        add(labelTexto, BorderLayout.CENTER);

        botaoItalico = new JButton("Italico");
        botaoNegrito = new JButton("Negrito");
        
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(botaoItalico)) {
                    conf.textoItalico();
                } else if (e.getSource().equals(botaoNegrito)) {
                    conf.textoNegrito();
                }
            }
        };
        
        botaoItalico.addActionListener(listener);
        botaoNegrito.addActionListener(listener);

        painelInferior = new JPanel();
        painelInferior.add(botaoItalico);
        painelInferior.add(botaoNegrito);
        add(painelInferior, BorderLayout.SOUTH);
        
        conf.adicionarObserver(new ComponentObserver(labelTexto));
        conf.adicionarObserver(new ComponentObserver(botaoItalico));
        conf.adicionarObserver(new ComponentObserver(botaoNegrito));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
