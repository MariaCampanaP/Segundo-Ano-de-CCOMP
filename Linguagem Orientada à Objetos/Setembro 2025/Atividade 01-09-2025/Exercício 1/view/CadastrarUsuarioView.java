package view;

import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Usuario;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class CadastrarUsuarioView extends JDialog {

    private UsuarioRepository usuarioRepository;

    private DefaultListModel<Usuario> modeloListaUsuarios;

    private JLabel labelLogin;

    private JTextField textLogin;

    private JLabel labelSenha;

    private JTextField textSenha;

    private JLabel labelNome;

    private JTextField textNome;

    private JButton botaoCadastrar;

    private JButton botaoCancelar;

    public CadastrarUsuarioView(JDialog telaPai, DefaultListModel modeloListaUsuarios) {
        super(telaPai, "Tela de cadastro", true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        usuarioRepository = UsuarioRepositoryJson.getInstancia();
        this.modeloListaUsuarios = modeloListaUsuarios;

        labelLogin = new JLabel("Login:");
        textLogin = new JTextField();

        labelSenha = new JLabel("Senha:");
        textSenha = new JTextField();

        labelNome = new JLabel("Nome:");
        textNome = new JTextField();

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener((e) -> {
            if (textLogin.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Digite um login!");
            } else if (textSenha.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Digite uma senha!");
            } else if (textNome.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Digite um nome!");
            } else {
                Usuario usuarioNovo = new Usuario(
                        textLogin.getText(),
                        textSenha.getText(),
                        textNome.getText());
                usuarioRepository.salvar(usuarioNovo);
                this.modeloListaUsuarios.clear();
                this.modeloListaUsuarios.addAll(usuarioRepository.listar());
                JOptionPane.showMessageDialog(rootPane, "Usuário cadastrado com sucesso!");
                limparCampos();
                CadastrarUsuarioView.this.setVisible(false);
            }
        });
        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener((e) -> {
            limparCampos();
            CadastrarUsuarioView.this.setVisible(false);
        });

        add(labelLogin);
        add(textLogin);
        add(labelSenha);
        add(textSenha);
        add(labelNome);
        add(textNome);
        add(botaoCadastrar);
        add(botaoCancelar);
        setLocationRelativeTo(telaPai);
    }

    private void limparCampos() {
        textLogin.setText("");
        textSenha.setText("");
        textNome.setText("");
    }
}
