package view;

import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Mensagem;
import model.Usuario;
import repository.MensagemRepository;
import repository.MensagemRepositoryJson;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class CadastrarMensagemView extends JDialog {

    private MensagemRepository mensagemRepository;

    private UsuarioRepository usuarioRepository;

    private DefaultListModel<Mensagem> modeloListaMensagens;

    private JLabel labelRemetente;

    private JComboBox<Usuario> comboBoxRemetente;

    private DefaultComboBoxModel<Usuario> remetenteModel;

    private JLabel labelDestinatario;

    private JComboBox<Usuario> comboBoxDestinatario;

    private DefaultComboBoxModel<Usuario> destinatarioModel;

    private JLabel labelTitulo;

    private JTextField textTitulo;

    private JLabel labelCorpo;

    private JTextArea textAreaCorpo;

    private JButton botaoCadastrar;

    private JButton botaoCancelar;

    public CadastrarMensagemView(JDialog telaPai, DefaultListModel modeloListaUsuarios) {
        super(telaPai, "Tela de cadastro", true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        mensagemRepository = MensagemRepositoryJson.getInstancia();
        usuarioRepository = UsuarioRepositoryJson.getInstancia();
        this.modeloListaMensagens = modeloListaUsuarios;

        labelRemetente = new JLabel("Remetente:");
        remetenteModel = new DefaultComboBoxModel<>();
        remetenteModel.addAll(usuarioRepository.listar());
        comboBoxRemetente = new JComboBox<>();
        comboBoxRemetente.setModel(remetenteModel);

        labelDestinatario = new JLabel("Destinatário:");
        destinatarioModel = new DefaultComboBoxModel<>();
        destinatarioModel.addAll(usuarioRepository.listar());
        comboBoxDestinatario = new JComboBox<>();
        comboBoxDestinatario.setModel(destinatarioModel);

        labelTitulo = new JLabel("Título:");
        textTitulo = new JTextField();

        labelCorpo = new JLabel("Corpo:");
        textAreaCorpo = new JTextArea();

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener((e) -> {
            if (textTitulo.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Digite um título!");
            } else if (textAreaCorpo.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Digite um corpo!");

            } else {
                Mensagem mensagemNova = new Mensagem(
                        (Usuario) comboBoxRemetente.getSelectedItem(),
                        (Usuario) comboBoxDestinatario.getSelectedItem(),
                        textTitulo.getText(),
                        textAreaCorpo.getText());
                if (mensagemRepository.salvar(mensagemNova)) {
                    this.modeloListaMensagens.clear();
                    this.modeloListaMensagens.addAll(mensagemRepository.listar());
                    JOptionPane.showMessageDialog(rootPane, "Mensagem cadastrada com sucesso!");
                    limparCampos();
                    CadastrarMensagemView.this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Falha ao cadastrar mensagem!");
                }

            }
        });
        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener((e) -> {
            limparCampos();
            CadastrarMensagemView.this.setVisible(false);
        });

        add(labelRemetente);
        add(comboBoxRemetente);
        add(labelDestinatario);
        add(comboBoxDestinatario);
        add(labelTitulo);
        add(textTitulo);
        add(labelCorpo);
        add(textAreaCorpo);
        add(botaoCadastrar);
        add(botaoCancelar);
        setLocationRelativeTo(telaPai);
        limparCampos();
    }

    private void limparCampos() {
        comboBoxRemetente.setSelectedIndex(0);
        comboBoxDestinatario.setSelectedIndex(0);
        textTitulo.setText("");
        textAreaCorpo.setText("");

    }
}
