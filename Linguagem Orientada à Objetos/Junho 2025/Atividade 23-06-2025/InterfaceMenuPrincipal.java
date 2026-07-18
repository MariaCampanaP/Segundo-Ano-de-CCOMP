package unespar.com.br.trabalho.equipe_6;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class InterfaceMenuPrincipal extends JFrame{
      
    JPanel painelPrincipal;
    JPanel painelJogo;
    JMenuBar barraDeMenus;
    JMenu menuArquivo;
    JMenuItem menuItemAbrir;
    JMenuItem menuItemSalvar;
    JMenuItem menuItemSair;
    JLabel labelJogo = new JLabel("Estado atual do jogo:");
    JTextArea textAreaJogo = new JTextArea();

    JFileChooser escolhedorDeArquivo;

    Tabuleiro tabuleiroAtual;

    InterfaceMenuPrincipal(){
        super("Jogo da vida de Conway (Adaptado)");
        painelPrincipal = new JPanel(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        //Painel onde sera mostrado o jogo
        painelJogo = new JPanel(new GridBagLayout());

        //Label do jogo
        GridBagConstraints consLabelJogo = new GridBagConstraints();
        consLabelJogo.fill = GridBagConstraints.CENTER;
        consLabelJogo.gridx = 0;
        consLabelJogo.gridy = 0;
        consLabelJogo.weightx = 1;
        consLabelJogo.weighty = 0.2;
        painelJogo.add(labelJogo, consLabelJogo);

        //Painel do jogo
        GridBagConstraints consTextAreaJogo = new GridBagConstraints();
        consTextAreaJogo.fill = GridBagConstraints.HORIZONTAL;
        consTextAreaJogo.gridx = 0;
        consTextAreaJogo.gridy = 1;
        consTextAreaJogo.weightx = 1;
        consTextAreaJogo.weighty = 0.8;
        textAreaJogo.setFocusable(false);
        painelJogo.add(textAreaJogo, consTextAreaJogo);

        //Conjunto Label e Text Area do Jogo
        GridBagConstraints consConjuntoJogo = new GridBagConstraints();
        consConjuntoJogo.fill = GridBagConstraints.HORIZONTAL;
        consConjuntoJogo.gridx = 0;
        consConjuntoJogo.gridy = 0;
        consConjuntoJogo.weightx = 1;
        consConjuntoJogo.weighty = 0.8;
        painelPrincipal.add(painelJogo, consConjuntoJogo);

        //Menus

        barraDeMenus = new JMenuBar();

        menuArquivo = new JMenu("Arquivo");
        menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.setActionCommand("Abrir");
        menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalvar.setActionCommand("Salvar");
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setActionCommand("Sair");
        menuArquivo.add(menuItemAbrir);
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemSair);
        barraDeMenus.add(menuArquivo);

        setJMenuBar(barraDeMenus);

        escolhedorDeArquivo = new JFileChooser();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado;
                switch (e.getActionCommand()) {
                    case "Abrir":
                        resultado = escolhedorDeArquivo.showOpenDialog(rootPane);
                        if (resultado == JFileChooser.APPROVE_OPTION) {
                            try {
                                tabuleiroAtual = new Tabuleiro(escolhedorDeArquivo.getSelectedFile());
                                textAreaJogo.setText(tabuleiroAtual.toString());
                            } catch (FileNotFoundException | InputMismatchException ex) {
                                JOptionPane.showMessageDialog(rootPane, "Erro de leitura!",
                                        "Erro!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "Salvar":
                        resultado = escolhedorDeArquivo.showSaveDialog(rootPane);
                        if (resultado == JFileChooser.APPROVE_OPTION) {
                            try {
                                PrintWriter writer = new PrintWriter(escolhedorDeArquivo.getSelectedFile());
                                int linhas = tabuleiroAtual.getLinhas();
                                int colunas = tabuleiroAtual.getColunas();

                                writer.print((linhas-2) + " " + (colunas-2) + "\n");
                                writer.print(tabuleiroAtual.getTabuleiroCelulasBase() + "\n");
                                writer.print(tabuleiroAtual.getTabuleiroVida() + "\n");
                                writer.close();
                                JOptionPane.showMessageDialog(rootPane, "Arquivo salvo com sucesso!");
                                textAreaJogo.setText(null);
                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(rootPane, "Erro ao salvar o arquivo!",
                                        "Erro!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "Sair":
                        dispose();
                        break;
                }
            }
        };
        menuItemAbrir.addActionListener(listener);
        menuItemSalvar.addActionListener(listener);
        menuItemSair.addActionListener(listener);

        add(painelPrincipal);
        setVisible(true);

    }

    
}


