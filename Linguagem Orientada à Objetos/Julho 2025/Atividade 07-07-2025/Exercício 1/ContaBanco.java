
package com.mycompany.contabancariathreads;

public class ContaBanco {
    
    private float contaSaldo;
    private float contaPoupanca;

    public ContaBanco(float contaSaldo, float contaPoupanca) {
        this.contaSaldo = contaSaldo;
        this.contaPoupanca = contaPoupanca;
    }

    public float getContaSaldo() {
        return contaSaldo;
    }

    public void setContaSaldo(float contaSaldo) {
        this.contaSaldo = contaSaldo;
    }

    public float getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(float contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }
    
    public void depositarSaldo(float deposito){
        contaSaldo += deposito;
    }
    
    public void saqueSaldo(float saque){
        contaSaldo -= saque;
    }
    
    public void transferenciaSaldoPoupanca(float transferencia){
        contaSaldo -= transferencia;
        contaPoupanca += transferencia;
    }
    
    public void transferenciaPoupancaSaldo(float transferencia){
        contaSaldo += transferencia;
        contaPoupanca -= transferencia;
    }
}
