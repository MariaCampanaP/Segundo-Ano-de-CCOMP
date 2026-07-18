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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Usuario;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class ManterUsuarioView extends JFrame {

    private UsuarioRepository usuarioRepository;

    private JScrollPane scrollLista;

    private JList<Usuario> listaParaUsuarios;

    private DefaultListModel<Usuario> modeloListaUsuarios;

    private JPanel painelBusca;

    private JLabel labelBusca;

    private JTextField textBusca;

    private JButton botaoBusca;

    private JPanel painelInferior;

    private JButton botaoListar;

    private JButton botaoCadastro;

    private JButton botaoRemover;

    private JDialog telaCadastro;

    public ManterUsuarioView() {
        super("Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        usuarioRepository = UsuarioRepositoryJson.getInstancia();

        modeloListaUsuarios = new DefaultListModel<>();
        modeloListaUsuarios.addAll(usuarioRepository.listar());
        listaParaUsuarios = new JList<>(modeloListaUsuarios);
        telaCadastro = new CadastrarUsuarioView(this, modeloListaUsuarios);
        scrollLista = new JScrollPane(listaParaUsuarios);
        add(scrollLista, BorderLayout.CENTER);

        painelBusca = new JPanel(new GridLayout(1, 3));
        labelBusca = new JLabel("Login para buscar:");
        textBusca = new JTextField();
        botaoBusca = new JButton("Buscar");
        botaoBusca.addActionListener((e) -> {
            Usuario usuarioBuscado = usuarioRepository.buscaPorLogin(textBusca.getText());
            if (usuarioBuscado == null) {
                JOptionPane.showMessageDialog(rootPane, "Usuário não encontrado!");
            } else {
                modeloListaUsuarios.clear();
                modeloListaUsuarios.addElement(usuarioBuscado);
            }
        });
        painelBusca.add(labelBusca);
        painelBusca.add(textBusca);
        painelBusca.add(botaoBusca);
        add(painelBusca, BorderLayout.NORTH);

        painelInferior = new JPanel(new GridLayout(1, 3));
        botaoListar = new JButton("Listar todos");
        botaoListar.addActionListener((e) -> {
            modeloListaUsuarios.clear();
            modeloListaUsuarios.addAll(usuarioRepository.listar());
        });
        botaoCadastro = new JButton("Cadastrar novo");
        botaoCadastro.addActionListener((e) -> {
            telaCadastro.setVisible(true);
        });
        botaoRemover = new JButton("Remover seleção");
        botaoRemover.addActionListener((e) -> {
            Usuario usuarioSelecionado = listaParaUsuarios.getSelectedValue();
            if (usuarioSelecionado == null) {
                JOptionPane.showMessageDialog(rootPane, "Selecione um usuário!");
            } else {
                usuarioRepository.removerPorCodigo(usuarioSelecionado.getCodigo());
                modeloListaUsuarios.clear();
                modeloListaUsuarios.addAll(usuarioRepository.listar());
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
                    setFontRecursivo(comp, font); // Recursive call for sub-components
                }
            }
            componente.setFont(font); // Set the font for the current component
        }
    }

}
