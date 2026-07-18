package br.edu.unespar.lista.vetor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        ListaVetor lista = new ListaVetor();
        int opcao;
        do {
            imprimirOpcoes();
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    int numInserido = leitor.nextInt();
                    lista.inserir(numInserido);
                    break;
                case 2:
                    int buscar = leitor.nextInt();
                    lista.buscar(buscar);
                    if(lista.buscar(buscar) == -1){
                        System.out.println("Valor ausente");
                    }else{
                        System.out.println("Valor encontrado");
                    }
                    break;
                case 3:
                    int removers = leitor.nextInt();
                    lista.remover(removers);
                    if(lista.remover(removers) == true){
                        System.out.println("Valor removido com sucesso");
                    }else{
                        System.out.println("Valor nao removido");
                    }
                    break;
                case 4:
                    System.out.println(lista.toString());
                    break;
                case 5:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;

            }
        } while (opcao != 5);
    }

    private static void imprimirOpcoes() {
        System.out.println("Digite a operacao desejada:");
        System.out.println("1. Inserir elemento");
        System.out.println("2. Buscar elemento");
        System.out.println("3. Remover elemento");
        System.out.println("4. Imprimir a lista");
        System.out.println("5. Sair");
    }
}
