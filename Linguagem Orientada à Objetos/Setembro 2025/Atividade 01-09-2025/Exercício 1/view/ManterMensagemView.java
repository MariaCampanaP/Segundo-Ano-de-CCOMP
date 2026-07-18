package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Mensagem;
import repository.MensagemRepository;
import repository.MensagemRepositoryJson;

public class ManterMensagemView extends JDialog {

    private MensagemRepository mensagemRepository;

    private JScrollPane scrollLista;

    private JList<Mensagem> listaParaMensagens;

    private DefaultListModel<Mensagem> modeloListaMensagens;

    private JPanel painelInferior;

    private JButton botaoListar;

    private JButton botaoCadastro;

    private JButton botaoRemover;

    private JDialog telaCadastro;

    public ManterMensagemView(JFrame telaPrincipal) {
        super(telaPrincipal, "Mensagens", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        mensagemRepository = MensagemRepositoryJson.getInstancia();

        modeloListaMensagens = new DefaultListModel<>();
        modeloListaMensagens.addAll(mensagemRepository.listar());
        listaParaMensagens = new JList<>(modeloListaMensagens);
        telaCadastro = new CadastrarMensagemView(this, modeloListaMensagens);
        scrollLista = new JScrollPane(listaParaMensagens);
        add(scrollLista, BorderLayout.CENTER);

        painelInferior = new JPanel(new GridLayout(1, 3));
        botaoListar = new JButton("Listar mensagens");
        botaoListar.addActionListener((e) -> {
            modeloListaMensagens.clear();
            modeloListaMensagens.addAll(mensagemRepository.listar());
        });
        botaoCadastro = new JButton("Cadastrar nova mensagem");
        botaoCadastro.addActionListener((e) -> {
            telaCadastro.setVisible(true);
        });
        botaoRemover = new JButton("Remover seleção");
        botaoRemover.addActionListener((e) -> {
            Mensagem mensagemSelecionada = listaParaMensagens.getSelectedValue();
            if (mensagemSelecionada == null) {
                JOptionPane.showMessageDialog(rootPane, "Selecione uma mensagem!");
            } else {
                mensagemRepository.removerPorCodigo(mensagemSelecionada.getCodigo());
                modeloListaMensagens.clear();
                modeloListaMensagens.addAll(mensagemRepository.listar());
            }
        });
        painelInferior.add(botaoListar);
        painelInferior.add(botaoCadastro);
        painelInferior.add(botaoRemover);
        add(painelInferior, BorderLayout.SOUTH);

        Font fonte = new Font("Arial", Font.PLAIN, 20);
        setFontRecursivo(rootPane, fonte);
        setFontRecursivo(telaCadastro, fonte);
        pack();
        telaCadastro.pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void setFontRecursivo(Component componente, Font font) {
        if (componente != null) {
            if (componente instanceof Container) {
                Container container = (Container) componente;
                for (Component comp : container.getComponents()) {
                    setFontRecursivo(comp, font);
                }
            }
            componente.setFont(font);
        }
    }

}
