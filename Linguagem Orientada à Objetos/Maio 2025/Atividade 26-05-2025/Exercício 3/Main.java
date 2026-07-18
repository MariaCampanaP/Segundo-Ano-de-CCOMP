package id.usuario.exception;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Digite o login do usuário");
            String login = scanner.next();
            System.out.println("Digite a senha do usuário");
            String senha = scanner.next();
            Usuario usuario = new Usuario(login, senha);
            System.out.println("Digite o novo login do usuário");
            String novoLogin = scanner.next();
            usuario.setLogin(login);
            System.out.println("Digite a senha do usuário");
            String novaSenha = scanner.next();
            usuario.setSenha(senha);
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
