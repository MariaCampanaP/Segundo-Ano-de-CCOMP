package unespar.com.br.trabalho.equipe_6;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceMenuPrincipal extends JFrame{
      
    //Lado esquerdo do menu principal
    private JPanel painelPrincipal;
    private JPanel painelJogo;
    private JMenuBar barraDeMenus;
    private JMenu menuOpcoes;
    private JMenuItem menuItemAbrir;
    private JMenuItem menuItemSalvar;
    private JMenuItem menuItemSair;
    private JMenuItem menuItemEditar;
    private JMenuItem menuItemInfo;
    private JLabel labelJogo = new JLabel("Estado atual do jogo:");
    public JTextArea textAreaJogo = new JTextArea("Abra um arquivo de jogo");

    //Lado direito do menu principal
    private JPanel gridBotoes;
    private JLabel labelIntervalo;
    private JSpinner spinnerIntervalo;
    private JLabel labelIteracoes;
    private JSpinner spinnerNumeroIteracoes;
    private JButton botaoIniciarIteracoes;
    private JButton botaoUmaIteracao;
    private JButton botaoParar;
    private Timer timer;
    private Boolean executarTimer;
    private int contador=0;

    //Fonte para os tabuleiros
    Font monospacedFont = new Font("Monospaced", Font.PLAIN, 12);

    JFileChooser escolhedorDeArquivo;

    Tabuleiro tabuleiroAtual;

    InterfaceMenuPrincipal(){
        super("Jogo da vida de Conway (Adaptado)");
        painelPrincipal = new JPanel(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        //Painel onde será mostrado o jogo (perdeu)
        painelJogo = new JPanel(new GridBagLayout());

        //Label do jogo
        GridBagConstraints consLabelJogo = new GridBagConstraints();
        consLabelJogo.fill = GridBagConstraints.CENTER;
        consLabelJogo.gridx = 0;
        consLabelJogo.gridy = 0;
        consLabelJogo.weightx = 1;
        consLabelJogo.weighty = 0.2;
        painelJogo.add(labelJogo, consLabelJogo);

        //Text Area do Jogo
        GridBagConstraints consTextAreaJogo = new GridBagConstraints();
        consTextAreaJogo.fill = GridBagConstraints.CENTER;
        consTextAreaJogo.gridx = 0;
        consTextAreaJogo.gridy = 1;
        consTextAreaJogo.weightx = 1;
        consTextAreaJogo.weighty = 0.8;
        textAreaJogo.setFocusable(false);
        textAreaJogo.setFont(monospacedFont);
        painelJogo.add(textAreaJogo, consTextAreaJogo);

        //Conjunto Label e Text Area do Jogo
        GridBagConstraints consConjuntoJogo = new GridBagConstraints();
        consConjuntoJogo.fill = GridBagConstraints.HORIZONTAL;
        consConjuntoJogo.gridx = 0;
        consConjuntoJogo.gridy = 0;
        consConjuntoJogo.weightx = 0.6;
        consConjuntoJogo.weighty = 1;
        painelPrincipal.add(painelJogo, consConjuntoJogo);

        //Menus

        barraDeMenus = new JMenuBar();

        menuOpcoes = new JMenu("Opções");
        menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.setActionCommand("Abrir");
        menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalvar.setActionCommand("Salvar");
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setActionCommand("Sair");
        menuItemEditar = new JMenuItem("Editar");
        menuItemEditar.setActionCommand("Editar");
        menuItemInfo = new JMenuItem("Info");
        menuItemInfo.setActionCommand("Info");
        menuOpcoes.add(menuItemAbrir);
        menuOpcoes.add(menuItemSalvar);
        menuOpcoes.add(menuItemSair);
        menuOpcoes.add(menuItemEditar);
        menuOpcoes.add(menuItemInfo);
        barraDeMenus.add(menuOpcoes);

        setJMenuBar(barraDeMenus);

        escolhedorDeArquivo = new JFileChooser();
        //Cria um filtro de arquivos "".txt" para facilitar achar e salvar os arquivos de tabuleiro
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        escolhedorDeArquivo.setFileFilter(textFilter);

        //Listener do menu de opções
        ActionListener listenerMenu = new ActionListener() {
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
                                //Desbloqueio dos botoes
                                botaoUmaIteracao.setEnabled(true);
                                botaoIniciarIteracoes.setEnabled(true);
                                botaoParar.setEnabled(true);
                                menuItemEditar.setEnabled(true);
                                menuItemSalvar.setEnabled(true);
                            } catch (FileNotFoundException naoEncontrado) {
                                JOptionPane.showMessageDialog(rootPane, "Erro de leitura! Arquivo não encontrado","Erro!", JOptionPane.ERROR_MESSAGE);
                            }catch (InputMismatchException erroInput){
                                JOptionPane.showMessageDialog(null, "Erro na leitura do Arquivo!\nVerifique se o arquivo está escrito de acordo com a norma", "ERRO", JOptionPane.ERROR_MESSAGE);
                            }catch (IndexOutOfBoundsException erroIndice){
                                JOptionPane.showMessageDialog(null, "Erro na leitura do Arquivo!\nVerifique o tamanho especificado do tabuleiro!", "ERRO", JOptionPane.ERROR_MESSAGE);
                            }catch (NullPointerException erroNulo){
                                JOptionPane.showMessageDialog(null, "Erro na leitura do Arquivo!\nElemento nulo detectado, verifique se os números das linhas e colunas coincidem com o tamanho das matrizes", "ERRO", JOptionPane.ERROR_MESSAGE);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(null, "Erro desconhecido na leitura do arquivo", "ERRO", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "Salvar":
                        resultado = escolhedorDeArquivo.showSaveDialog(rootPane);
                        if (resultado == JFileChooser.APPROVE_OPTION) {
                            try {
                                PrintWriter writer = new PrintWriter(escolhedorDeArquivo.getSelectedFile());

                                writer.print((tabuleiroAtual.getLinhas()-2) + " " + (tabuleiroAtual.getColunas()-2) + "\n");
                                writer.print(tabuleiroAtual.getTabuleiroCelulasBase() + "\n");
                                writer.print(tabuleiroAtual.getTabuleiroVida() + "\n");
                                writer.close();
                                JOptionPane.showMessageDialog(rootPane, "Arquivo salvo com sucesso!");
                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(rootPane, "Erro ao salvar o arquivo!",
                                        "Erro!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "Sair":
                        dispose();
                        break;
                    case "Editar":
                    abrirMenuEditor();
                        break;
                    case "Info":
                        abrirMenuInfo();
                        break;
                }
            }
        };
        menuItemAbrir.addActionListener(listenerMenu);
        menuItemSalvar.addActionListener(listenerMenu);
        menuItemSair.addActionListener(listenerMenu);
        menuItemEditar.addActionListener(listenerMenu);
        menuItemInfo.addActionListener(listenerMenu);

        //Secao de iterações
        gridBotoes = new JPanel(new GridLayout(5, 1));
        JPanel panelIntervalo = new JPanel(new FlowLayout());
        JPanel panelNumeroIteracoes = new JPanel(new FlowLayout());

        //Configuração do intervalo entre as iterações
        labelIntervalo = new JLabel("Intervalo em ms:");
        spinnerIntervalo = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 100));
        spinnerIntervalo.addChangeListener(new ChangeListener(){//Atualiza o intervalo automaticamente
            @Override
            public void stateChanged(ChangeEvent e) {
                timer.setDelay((int) spinnerIntervalo.getValue());
            }
        
        });
        panelIntervalo.add(labelIntervalo);
        panelIntervalo.add(spinnerIntervalo);

        labelIteracoes = new JLabel("Numero de Iteracoes:");
        spinnerNumeroIteracoes = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panelNumeroIteracoes.add(labelIteracoes);
        panelNumeroIteracoes.add(spinnerNumeroIteracoes);

        //Função do timer
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (executarTimer) {
                    tabuleiroAtual.proximaIteracao();
                    textAreaJogo.setText(tabuleiroAtual.toString());
                    contador++;
                    if(contador == (int) spinnerNumeroIteracoes.getValue()){
                        executarTimer = false;
                    }
                } else {
                    //Liberando os demais botões e bloqueio do botão de parar
                    botaoUmaIteracao.setEnabled(true);
                    botaoIniciarIteracoes.setEnabled(true);
                    menuItemEditar.setEnabled(true);
                    botaoParar.setEnabled(false);
                    contador=0;
                    ((Timer) e.getSource()).stop();
                }
                
            }
        };

        timer = new Timer((int) spinnerIntervalo.getValue(), timerListener);

        //Funções dos botoes
        ActionListener botaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(botaoIniciarIteracoes)) {
                    //Bloqueio dos demais botões durante as iteracoes e desbloqueio do botão de parar
                    botaoUmaIteracao.setEnabled(false);
                    botaoIniciarIteracoes.setEnabled(false);
                    botaoParar.setEnabled(true);
                    menuItemEditar.setEnabled(false);
                    //Execuçãoo do timer
                    timer.setRepeats(true);
                    executarTimer = true;
                    timer.start();
                } else if (e.getSource().equals(botaoUmaIteracao)) {
                    tabuleiroAtual.proximaIteracao();
                    textAreaJogo.setText(tabuleiroAtual.toString());
                }else if (e.getSource().equals(botaoParar)){
                    executarTimer = false;
                }
            }
        };
        botaoUmaIteracao = new JButton("Avançar uma iteração");
        botaoIniciarIteracoes = new JButton("Avançar múltiplas iterações");
        botaoParar = new JButton("Parar");
        botaoUmaIteracao.addActionListener(botaoListener);
        botaoIniciarIteracoes.addActionListener(botaoListener);
        botaoParar.addActionListener(botaoListener);

        //Bloqueia os botoes ate que um arquivo seja aberto
        botaoUmaIteracao.setEnabled(false);
        botaoIniciarIteracoes.setEnabled(false);
        botaoParar.setEnabled(false);
        menuItemEditar.setEnabled(false);
        menuItemSalvar.setEnabled(false);

        //Adiciona os botoes ao painel
        gridBotoes.add(botaoUmaIteracao);
        gridBotoes.add(botaoIniciarIteracoes);
        gridBotoes.add(botaoParar);
        gridBotoes.add(panelIntervalo);
        gridBotoes.add(panelNumeroIteracoes);

        //Painel de Iteracoes
        GridBagConstraints consPainelIteracoes = new GridBagConstraints();
        consLabelJogo.fill = GridBagConstraints.VERTICAL;
        consLabelJogo.gridx = 1;
        consLabelJogo.gridy = 0;
        consLabelJogo.weightx = 0.4;
        consLabelJogo.weighty = 1;
        painelJogo.add(gridBotoes, consPainelIteracoes);

        add(painelPrincipal);
        setVisible(true);


    }

    //Método para abrir o menu de informações
    private void abrirMenuInfo(){

        JDialog menuInfo;
        JTextPane textInfo;
        String info;

        //Definicao do menu
        info = "======================================================================================================================\r\n" + //
                        "\t\tINFORMAÇÕES — JOGO DA VIDA\r\n" + //
                        "======================================================================================================================\r\n" + //
                        "\r\n" + //
                        "O que é o Jogo da Vida?\r\n" + //
                        "O Jogo da Vida (ou “Game of Life”) é um autômato celular criado por John Conway. É uma simulação onde células em um tabuleiro evoluem automaticamente, sem necessidade de interação do usuário durante as iterações.\r\n" + //
                        "\r\n" + //
                        "Regras Originais do Jogo\r\n" + //
                        "Cada célula pode estar VIVA ou Morta, e o próximo estado depende dos vizinhos ao redor:\r\n" + //
                        "\t1.Uma célula viva com menos de 2 vizinhos vivos morre (solidão).\r\n" + //
                        "\t2.Uma célula viva com 2 ou 3 vizinhos vivos continua viva (sobrevivência).\r\n" + //
                        "\t3.Uma célula viva com mais de 3 vizinhos vivos morre (superpopulação).\r\n" + //
                        "\t4.Uma célula morta com exatamente 3 vizinhos se torna viva (reprodução).\r\n" + //
                        "\r\n" + //
                        "Tipos de Células e Comportamentos\r\n" + //
                        "Neste jogo, cada célula tem um tipo específico representado por um caractere especial (por exemplo: ‘+’, ‘@’, ‘&’, ‘#’, ‘.’).\r\n" + //
                        "\t1.Clássica (‘+’) → Segue as regras originais de Conway.\r\n" + //
                        "\t2.Forte (‘@’) → Só morre com menos de 2 vizinhos vivos; revive com mais de 4.\r\n" + //
                        "\t3.Tímida (‘&’) → Morre se tiver qualquer vizinho vivo; revive se todos estiverem \tmortos.\r\n" + //
                        "\t4.Matemática (‘#’) → Vive se o número de vizinhos for ímpar; revive se for par.\r\n" + //
                        "\t5.Borda (‘.’) → Sempre morta; usada apenas para delimitar o tabuleiro.\r\n" + //
                        "\r\n" + //
                        "Funcionalidades do Programa\r\n" + //
                        "\t-Exibir o tabuleiro com as células e suas bordas.\r\n" + //
                        "\t-Avançar uma única iteração ou várias iterações seguidas.\r\n" + //
                        "\t-Escolher quantas iterações avançar (1 ⪯ n ⪯ 100) e intervalo entre elas (0 ⪯ t ⪯ \t2000 ms).\r\n" + //
                        "\t-Editar manualmente o tabuleiro, alterando tipos e estado das células, e também tamanho do tabuleiro.\r\n" + //
                        "\t-Abrir e salvar arquivos com a configuração do jogo.\r\n" + //
                        "\t-Visualizar explicações sobre os tipos de células (esta página).\r\n" + //
                        "\t-Encerrar a aplicação com segurança.\r\n" + //
                        "\r\n" + //
                        "Arquivos de configuração\r\n" + //
                        "\t-Cada arquivo contém:\r\n" + //
                        "\t\t-Dois inteiros m e n (linhas e colunas).\r\n" + //
                        "\t\t-m x n caracteres indicando tipos das células.\r\n" + //
                        "\t\t-m x n bits (0 = morta, 1 = viva)\r\n" + //
                        "\r\n" + //
                        "======================================================================================================================\r\n" + //
                        "\t\tCUIDADOS\r\n" + //
                        "======================================================================================================================\r\n" + //
                        "\t-Durante a edição, CERTIFIQUE-SE QUE CADA TABULEIRO ESTEJA PREENCHIDO NO MESMO PADRÃO QUE ORIGINALMENTE CARREGADOS.\r\n" + //
                        "\t-Toda célula separada por um espaço\r\n" + //
                        "\t-Ao editar o tamanho, certifique-se que o tabuleiro não possui irregularidades (linhas ou colunas maiores que outras), ou espaços e qebras de linha adicionais\r\n" + //
                        "\r\n" + //
                        "Objetivo do jogo\r\n" + //
                        "Observar e explorar a evolução dos padrões das células. Não há pontuação nem condução de vitória: o foco é a experiência visual e a análise do comportamento emergente.\r\n" + //
                        "\r\n" + //
                        "======================================================================================================================\r\n" + //
                        "\t\tFeche esta janela para retornar para retornar\r\n" + //
                        "======================================================================================================================";
        menuInfo = new JDialog(this, "Sobre", true);
        menuInfo.setSize(950, 600);
        menuInfo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Configuração do texto
        textInfo = new JTextPane();
        textInfo.setText(info);
        textInfo.setFocusable(false);
        textInfo.setFont(monospacedFont);
        //Centraliza o texto
        StyledDocument doc = textInfo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        //Scroll do texto
        JScrollPane scrollTexto = new JScrollPane(textInfo);

        menuInfo.add(scrollTexto);

        menuInfo.setVisible(true);
    }

    //Método do menu de edições
    private void abrirMenuEditor(){

        JDialog menuEditor;
        JPanel painelEditor;
        JPanel gridLinhaColuna;
        JPanel flowLinha;
        JLabel labelLinhas;
        JSpinner spinnerLinhas;
        JPanel flowColuna;
        JLabel labelColunas;
        JSpinner spinnerColunas;
        JPanel painelCelulasBase;
        JLabel labelTabuleiroCelulasBase;
        JTextArea textAreaTabuleiroCelulasBase;
        JPanel painelVida;
        JLabel labelTabuleiroVida;
        JTextArea textAreaTabuleiroVida;
        JButton botaoConfirmar;
        JButton botaoAjuda;

        //Janela Pricipal
        menuEditor = new JDialog(this, "Editor de células", true);
        menuEditor.setSize(900, 400);
        menuEditor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        painelEditor = new JPanel(new GridBagLayout());

        //Conjunto Linhas
        labelLinhas = new JLabel("Linhas:");
        spinnerLinhas = new JSpinner(new SpinnerNumberModel(tabuleiroAtual.getLinhas()-2, 0, 20, 1));
        flowLinha = new JPanel(new FlowLayout());
        flowLinha.add(labelLinhas);
        flowLinha.add(spinnerLinhas);

        //Conjunto Colunas
        labelColunas = new JLabel("Colunas:");
        spinnerColunas = new JSpinner(new SpinnerNumberModel(tabuleiroAtual.getColunas()-2, 0, 20, 1));
        flowColuna = new JPanel(new FlowLayout());
        flowColuna.add(labelColunas);
        flowColuna.add(spinnerColunas);

        //Grid Linhas e Colunas
        gridLinhaColuna = new JPanel(new GridLayout(2,1));
        gridLinhaColuna.add(flowLinha);
        gridLinhaColuna.add(flowColuna);

        //Conjunto Tabuleiro Células Base
        painelCelulasBase = new JPanel(new GridBagLayout());

        labelTabuleiroCelulasBase = new JLabel("Células:");
        textAreaTabuleiroCelulasBase = new JTextArea(tabuleiroAtual.getTabuleiroCelulasBase());
        textAreaTabuleiroCelulasBase.setFont(monospacedFont);

        //Cria modelos de Grid Bag Constraints para os tabuleiros de células base e vida
        GridBagConstraints consLabelTabuleiro = new GridBagConstraints();
        consLabelTabuleiro.fill = GridBagConstraints.NORTH;
        consLabelTabuleiro.gridx = 0;
        consLabelTabuleiro.gridy = 0;
        consLabelTabuleiro.weightx = 1;
        consLabelTabuleiro.weighty = 0.2;
        painelCelulasBase.add(labelTabuleiroCelulasBase, consLabelTabuleiro);

        GridBagConstraints consTextAreaTabuleiro = new GridBagConstraints();
        consTextAreaTabuleiro.fill = GridBagConstraints.VERTICAL;
        consTextAreaTabuleiro.gridx = 0;
        consTextAreaTabuleiro.gridy = 1;
        consTextAreaTabuleiro.weightx = 1;
        consTextAreaTabuleiro.weighty = 0.8;
        painelCelulasBase.add(textAreaTabuleiroCelulasBase, consTextAreaTabuleiro);

        //Conjunto Tabuleiro Vida
        painelVida = new JPanel(new GridBagLayout());

        labelTabuleiroVida = new JLabel("Vida:");
        textAreaTabuleiroVida = new JTextArea(tabuleiroAtual.getTabuleiroVida());
        textAreaTabuleiroVida.setFont(monospacedFont);

        painelVida.add(labelTabuleiroVida, consLabelTabuleiro);
        painelVida.add(textAreaTabuleiroVida, consTextAreaTabuleiro);

        //Funcionamento dos botões
        botaoConfirmar = new JButton("Confirmar Mudanças");
        botaoAjuda = new JButton("Informações do jogo");
        ActionListener botaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(botaoConfirmar)){

                try{
                    //Prepara as variáveis para criar um tabuleiro atualizado
                    int m = (int) spinnerLinhas.getValue()+2;
                    int n = (int) spinnerColunas.getValue()+2;
                    String [][] tabuleiroCelulasBase = new String[m][n];
                    int [][] tabuleiroVida = new int[m][n];

                    //Scanners para leitura dos campos
                    Scanner scannerCelulasBase = new Scanner(textAreaTabuleiroCelulasBase.getText());
                    Scanner scannerVida = new Scanner(textAreaTabuleiroVida.getText());

                    for (int i=1; i<m-1; i++){
                        for(int j=1; j<n-1; j++){
                            tabuleiroCelulasBase[i][j] = scannerCelulasBase.next();
                            tabuleiroVida[i][j] = Integer.parseInt(scannerVida.next());

                            //Verificação dos valores
                            if (tabuleiroVida[i][j] != 0 && tabuleiroVida[i][j] != 1){
                                JOptionPane.showMessageDialog(null, "Valor inválido no tabuleiro de vida (Apenas 0 ou 1)", "ERRO", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if (!tabuleiroCelulasBase[i][j].equals("+") && !tabuleiroCelulasBase[i][j].equals("@") &&!tabuleiroCelulasBase[i][j].equals("#") &&!tabuleiroCelulasBase[i][j].equals("&") &&!tabuleiroCelulasBase[i][j].equals(".")){
                                JOptionPane.showMessageDialog(null, "Valor inválido no tabuleiro de células base (Apenas \"+\", \"@\", \"&\", \"#\", ou \".\")", "ERRO", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    scannerCelulasBase.close();
                    scannerVida.close();
                    
                    //Calcula o tamanho total do tabuleiro, considerando quebras de linha e espaços
                    int tamanhoTotal =(int) ((int) spinnerLinhas.getValue()*(int) spinnerColunas.getValue()*2 - 1);

                    //Verificação dos tamanhos
                    if( tamanhoTotal != textAreaTabuleiroVida.getText().length() || tamanhoTotal != textAreaTabuleiroCelulasBase.getText().length()){
                        JOptionPane.showMessageDialog(null, "Tamanho dos tabuleiros incorretos. Lembre-se de ler atentamente as instruções", "ERRO", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    tabuleiroAtual = new Tabuleiro(tabuleiroAtual.CriarTabuleiro(tabuleiroCelulasBase, tabuleiroVida));
                    textAreaJogo.setText(tabuleiroAtual.toString());


                    JOptionPane.showMessageDialog(null, "Tabuleiro Editado com sucesso", "Editado", JOptionPane.INFORMATION_MESSAGE);

                //Outros possiveis erros
                }catch (NullPointerException nulo){
                    JOptionPane.showMessageDialog(null, "Elemento nulo detectado. Preencha os campos adequadamente", "ERRO", JOptionPane.ERROR_MESSAGE);
                }catch (InputMismatchException inputMismatch){
                    JOptionPane.showMessageDialog(null, "Tipo de Input incorreto. Insira apenas números no tabuleiro de vida", "ERRO", JOptionPane.ERROR_MESSAGE);
                }catch (IndexOutOfBoundsException indice){
                    JOptionPane.showMessageDialog(null, "Tamanho dos tabuleiros incorretos. Lembre-se de ler atentamente as instruções", "ERRO", JOptionPane.ERROR_MESSAGE);
                }catch (Exception erroDesconheciado){
                    JOptionPane.showMessageDialog(null, "Erro desconheciado ao tentar editar a tabuleiro", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }else if (e.getSource().equals(botaoAjuda)){
                abrirMenuInfo();
            }
        }
        };
        botaoConfirmar.addActionListener(botaoListener);
        botaoAjuda.addActionListener(botaoListener);

        //Configuracao Painel Principal
        GridBagConstraints consLinhaColuna = new GridBagConstraints();
        consLinhaColuna.fill = GridBagConstraints.CENTER;
        consLinhaColuna.gridx = 0;
        consLinhaColuna.gridy = 0;
        consLinhaColuna.weightx = 0.4;
        consLinhaColuna.weighty = 0.8;
        painelEditor.add(gridLinhaColuna, consLinhaColuna);

        GridBagConstraints consEditorTabuleiroCelulasBase = new GridBagConstraints();
        consEditorTabuleiroCelulasBase.fill = GridBagConstraints.CENTER;
        consEditorTabuleiroCelulasBase.gridx = 1;
        consEditorTabuleiroCelulasBase.gridy = 0;
        consEditorTabuleiroCelulasBase.weightx = 0.3;
        consEditorTabuleiroCelulasBase.weighty = 0.8;
        painelEditor.add(painelCelulasBase, consEditorTabuleiroCelulasBase);

        GridBagConstraints consEditorTabuleiroVida = new GridBagConstraints();
        consEditorTabuleiroVida.fill = GridBagConstraints.CENTER;
        consEditorTabuleiroVida.gridx = 2;
        consEditorTabuleiroVida.gridy = 0;
        consEditorTabuleiroVida.weightx = 0.3;
        consEditorTabuleiroVida.weighty = 0.8;
        painelEditor.add(painelVida, consEditorTabuleiroVida);

        GridBagConstraints consBotaoConfirmar = new GridBagConstraints();
        consBotaoConfirmar.fill = GridBagConstraints.CENTER;
        consBotaoConfirmar.gridx = 1;
        consBotaoConfirmar.gridy = 1;
        consBotaoConfirmar.weightx = 1;
        consBotaoConfirmar.weighty = 0.2;
        painelEditor.add(botaoConfirmar, consBotaoConfirmar);

        GridBagConstraints consBotaoAjuda = new GridBagConstraints();
        consBotaoAjuda.fill = GridBagConstraints.CENTER;
        consBotaoAjuda.gridx = 0;
        consBotaoAjuda.gridy = 1;
        consBotaoAjuda.weightx = 1;
        consBotaoAjuda.weighty = 0.2;
        painelEditor.add(botaoAjuda, consBotaoAjuda);

        menuEditor.add(painelEditor);

        menuEditor.setVisible(true);

    }
    
}


