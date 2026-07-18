
package com.mycompany.contabancariathreads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TelaContaBancaria extends JFrame{
    
    private JPanel telaConta;
    private JLabel labelSaldo;
    private JLabel labelPoupanca; 
    private JButton botaoIniciar;
    private ContaBanco conta;
    
    TelaContaBancaria(){
        
        super("Gerson");
        telaConta = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        
        conta = new ContaBanco(0,0);
        
        labelSaldo = new JLabel("Saldo: R$" + conta.getContaSaldo());
        telaConta.add(labelSaldo);
        labelPoupanca = new JLabel("Poupanca: R$" + conta.getContaPoupanca());
        telaConta.add(labelPoupanca);
        
        JButton botaoIniciar = new JButton("Realizar Operações");
        
        ActionListener botaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable r1 = new Runnable() {
                    public void run() {
                        for(int i=0; i<1000; i++){
                            deposito(5);
                            transferenciaSaldoPoupanca(1);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    labelSaldo.setText("Saldo: R$" + conta.getContaSaldo());
                                    labelPoupanca.setText("Poupanca: R$" + conta.getContaPoupanca());
                                }
                            } 
                        );
                        }
                    }
                };
                Runnable r2 = new Runnable() {
                    public void run() {
                        for(int i=0; i<1000; i++){
                            deposito(1);
                            saque(2);
                            transferenciaSaldoPoupanca(2);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    labelSaldo.setText("Saldo: R$" + conta.getContaSaldo());
                                    labelPoupanca.setText("Poupanca: R$" + conta.getContaPoupanca());
                                }
                            } 
                        );
                        }
                    }
                };
                Runnable r3 = new Runnable() {
                    public void run() {
                        for(int i=0; i<1000; i++){
                            saque(1);
                            transferenciaPoupancaSaldo(2);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    labelSaldo.setText("Saldo: R$" + conta.getContaSaldo());
                                    labelPoupanca.setText("Poupanca: R$" + conta.getContaPoupanca());
                                }
                            } 
                        );
                        }
                    }
                };
                Runnable r4 = new Runnable() {
                    public void run() {
                        for(int i=0; i<1000; i++){
                            deposito(2);
                            transferenciaPoupancaSaldo(1);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    labelSaldo.setText("Saldo: R$" + conta.getContaSaldo());
                                    labelPoupanca.setText("Poupanca: R$" + conta.getContaPoupanca());
                                }
                            } 
                        );
                        }
                    }
                };
                
                Thread thread1 = new Thread(r1);
                Thread thread2 = new Thread(r2);
                Thread thread3 = new Thread(r3);
                Thread thread4 = new Thread(r4);
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
            }
        };

        botaoIniciar.addActionListener(botaoListener);
        telaConta.add(botaoIniciar);

        add(telaConta);
        setVisible(true);
        
    }
    
    private synchronized void deposito(float deposito){
        conta.depositarSaldo(deposito);
    }
    
    private synchronized void saque(float saque){
        conta.saqueSaldo(saque);
    }
    
    private synchronized void transferenciaSaldoPoupanca(float transferencia){
        conta.transferenciaSaldoPoupanca(transferencia);
    }
    
    private synchronized void transferenciaPoupancaSaldo(float transferencia){
        conta.transferenciaPoupancaSaldo(transferencia);
    }
}
