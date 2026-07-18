package id.swing.timer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TelaComTimer extends JFrame {
    
    private JLabel labelNomeTimer;
    
    private JLabel labelTimer;
    
    private JButton botaoIniciar;
    
    private JButton botaoParar;
    
    private JButton botaoReset;
    
    private JLabel labelIntervalo;
    
    private JSpinner spinnerIntervalo;
    
    private Timer timer;
    
    private int contador;
    
    private boolean executarTimer;
    
    public TelaComTimer() {
        super("Tela com timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        
        contador = 0;
        labelNomeTimer = new JLabel("Timer:");
        labelTimer = new JLabel(String.valueOf(contador));
        add(labelNomeTimer);
        add(labelTimer);
        
        botaoIniciar = new JButton("Iniciar/Continuar");
        botaoParar = new JButton("Parar");
        botaoReset = new JButton("Reiniciar");
        
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (executarTimer) {
                    labelTimer.setText(String.valueOf(contador++));
                } else {
                    ((Timer) e.getSource()).stop();
                }
                
            }
        };
       
        
        ActionListener botaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(botaoIniciar)) {
                    executarTimer = true;
                    timer.setRepeats(true);
                    timer.start();
                    botaoIniciar.setEnabled(false);
                } else if (e.getSource().equals(botaoParar)) {
                    executarTimer = false;
                    botaoIniciar.setEnabled(true);
                }
            }
        };
        
        ActionListener botaoResetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(botaoReset)) {
                    contador = 0;
                    labelTimer.setText(String.valueOf(contador));
                }
            }
        };
        
        labelIntervalo = new JLabel("Intervalo em ms:");
        spinnerIntervalo = new JSpinner(new SpinnerNumberModel(100, 100, 2000, 100));
        
        timer = new Timer((int) spinnerIntervalo.getValue(), timerListener);
        
        botaoIniciar.addActionListener(botaoListener);
        botaoParar.addActionListener(botaoListener);
        add(botaoIniciar);
        add(botaoParar);
        
    
        spinnerIntervalo.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                
                timer.setDelay((int) spinnerIntervalo.getValue());
            }
        
        });
        
        add(labelIntervalo);
        add(spinnerIntervalo);
        
        botaoReset.addActionListener(botaoResetListener);
        add(botaoReset);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
