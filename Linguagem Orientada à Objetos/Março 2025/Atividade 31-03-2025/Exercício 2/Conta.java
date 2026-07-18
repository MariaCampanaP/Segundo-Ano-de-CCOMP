/* Crie uma classe Conta com os atributos saldo e poupança do tipo int, para representar uma conta bancária. Os valores devem ser guardados em centavos.
Sua classe deve possuir um método construtor que inicializa ambos os atributos com o valor zero. Além disso, inclua métodos para:
    -> Um método toString que retorna uma string representando o saldo e a poupança.
    -> Depositar um valor qualquer na conta.
    -> Sacar um valor qualquer da conta, dentro do limite de saldo.
    -> Mover um valor do saldo para a poupança.
    -> Mover um valor da poupança para o saldo
Nos três últimos casos, seu método deve retornar um valor do tipo boolean indicando se a operação obteve sucesso
*/
package com.mycompany.exercicio2;


public class Conta {
    int saldo;
    int poupanca;
    
    public Conta(int saldo, int poupanca){
        this.saldo = 0;
        this.poupanca = 0;
    }

    @Override
    public String toString() {
        return "Exercicio2_Conta{" + "Saldo = R$" + (saldo / 100 + saldo % 100) + ", Poupanca = R$ " + (poupanca / 100 + poupanca % 100) + '}';
    }
    
    public void Deposito(int valor){
        this.saldo = this.saldo + valor;
    }
    
    public boolean Sacar (int sacar){
        if(sacar <= this.saldo){
            this.saldo = this.saldo - sacar;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean Saldo(int mover){
        if(mover <= this.saldo){
            this.saldo = this.saldo - mover;
            this.poupanca = this.poupanca + mover;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean Poupanca(int mopa){
        if(mopa <= this.saldo){
            this.saldo = this.saldo + mopa;
            this.poupanca = this.poupanca - mopa;
            return true;
        }else{
            return false;
        }
    }
}