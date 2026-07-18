package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import model.Mensagem;
import model.Usuario;
import repository.MensagemRepository;
import repository.MensagemRepositoryJson;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class ManterMensagemView extends JFrame {

    private MensagemRepository mensagemRepository;
    private UsuarioRepository usuarioRepository;

    private DefaultListModel<Mensagem> modeloListaMensagens;
    private JList<Mensagem> listaMensagens;

    private JButton botaoListar, botaoEnviar, botaoRemover;

    public ManterMensagemView() {
        super("Mensagens");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        mensagemRepository = MensagemRepositoryJson.getInstancia();
        usuarioRepository = UsuarioRepositoryJson.getInstancia();

        modeloListaMensagens = new DefaultListModel<>();
        modeloListaMensagens.addAll(mensagemRepository.listar());
        listaMensagens = new JList<>(modeloListaMensagens);
        add(new JScrollPane(listaMensagens), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new GridLayout(1, 3));
        botaoListar = new JButton("Listar todas");
        botaoListar.addActionListener(e -> {
            modeloListaMensagens.clear();
            modeloListaMensagens.addAll(mensagemRepository.listar());
        });

        botaoEnviar = new JButton("Enviar nova");
        botaoEnviar.addActionListener(e -> {
            new EnviarMensagemView(this, modeloListaMensagens).setVisible(true);
        });

        botaoRemover = new JButton("Remover seleção");
        botaoRemover.addActionListener(e -> {
            Mensagem m = listaMensagens.getSelectedValue();
            if (m != null) {
                mensagemRepository.removerPorCodigo(m.getCodigo());
                modeloListaMensagens.clear();
                modeloListaMensagens.addAll(mensagemRepository.listar());
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma mensagem!");
            }
        });

        painelInferior.add(botaoListar);
        painelInferior.add(botaoEnviar);
        painelInferior.add(botaoRemover);
        add(painelInferior, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}