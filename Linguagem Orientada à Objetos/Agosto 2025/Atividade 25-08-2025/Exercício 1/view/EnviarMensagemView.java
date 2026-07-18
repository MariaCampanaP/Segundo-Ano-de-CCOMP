package view;

import java.awt.GridLayout;
import javax.swing.*;
import model.Mensagem;
import model.Usuario;
import repository.MensagemRepository;
import repository.MensagemRepositoryJson;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class EnviarMensagemView extends JDialog {

    private UsuarioRepository usuarioRepository;
    private MensagemRepository mensagemRepository;

    private JComboBox<Usuario> comboRemetente;
    private JComboBox<Usuario> comboDestinatario;
    private JTextField campoTitulo;
    private JTextArea campoCorpo;

    private JButton botaoEnviar, botaoCancelar;
    private DefaultListModel<Mensagem> modeloListaMensagens;

    public EnviarMensagemView(JFrame telaPai, DefaultListModel<Mensagem> modeloLista) {
        super(telaPai, "Enviar Mensagem", true);
        setLayout(new GridLayout(5, 2));

        usuarioRepository = UsuarioRepositoryJson.getInstancia();
        mensagemRepository = MensagemRepositoryJson.getInstancia();
        this.modeloListaMensagens = modeloLista;

        comboRemetente = new JComboBox<>(usuarioRepository.listar().toArray(new Usuario[0]));
        comboDestinatario = new JComboBox<>(usuarioRepository.listar().toArray(new Usuario[0]));
        campoTitulo = new JTextField();
        campoCorpo = new JTextArea();

        botaoEnviar = new JButton("Enviar");
        botaoEnviar.addActionListener(e -> {
            Usuario remetente = (Usuario) comboRemetente.getSelectedItem();
            Usuario destinatario = (Usuario) comboDestinatario.getSelectedItem();
            String titulo = campoTitulo.getText();
            String corpo = campoCorpo.getText();

            Mensagem m = new Mensagem(titulo, corpo, remetente, destinatario);
            if (mensagemRepository.salvar(m)) {
                JOptionPane.showMessageDialog(this, "Mensagem enviada!");
                modeloListaMensagens.clear();
                modeloListaMensagens.addAll(mensagemRepository.listar());
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao enviar mensagem!");
            }
        });

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> setVisible(false));

        add(new JLabel("Remetente:")); add(comboRemetente);
        add(new JLabel("Destinatário:")); add(comboDestinatario);
        add(new JLabel("Título:")); add(campoTitulo);
        add(new JLabel("Corpo:")); add(new JScrollPane(campoCorpo));
        add(botaoEnviar); add(botaoCancelar);

        pack();
        setLocationRelativeTo(telaPai);
    }
}