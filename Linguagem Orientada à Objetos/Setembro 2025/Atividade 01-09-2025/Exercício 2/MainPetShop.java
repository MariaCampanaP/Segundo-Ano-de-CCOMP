import model.Cliente;
import model.AnimalDeEstimacao;
import repository.ClienteRepository;
import repository.ClienteRepositoryJson;
import repository.AnimalDeEstimacaoRepository;
import repository.AnimalDeEstimacaoRepositoryJson;

import java.util.List;
import java.util.Scanner;

public class MainPetShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClienteRepository clienteRepo = new ClienteRepositoryJson("clientes.json");
        AnimalDeEstimacaoRepository animalRepo = new AnimalDeEstimacaoRepositoryJson("animais.json", clienteRepo);

        while (true) {
            System.out.println("\n--- Menu Pet Shop ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Animal");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar Animais");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeCliente = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = new Cliente(nomeCliente, cpf);
                    clienteRepo.salvar(cliente);
                    System.out.println("Cliente cadastrado!");
                    break;

                case 2:
                    List<Cliente> clientes = clienteRepo.listar();
                    if (clientes.isEmpty()) {
                        System.out.println("Cadastre primeiro um cliente.");
                        break;
                    }
                    System.out.println("Escolha o dono:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println(i + " - " + clientes.get(i).getNome());
                    }
                    int indice = Integer.parseInt(sc.nextLine());
                    Cliente dono = clientes.get(indice);

                    System.out.print("Nome do animal: ");
                    String nomeAnimal = sc.nextLine();
                    System.out.print("Especie (cao, gato, reptil, peixe): ");
                    String especie = sc.nextLine();
                    System.out.print("Sexo: ");
                    String sexo = sc.nextLine();

                    AnimalDeEstimacao animal = new AnimalDeEstimacao(nomeAnimal, sexo, especie, dono);
                    dono.adicionarAnimal(animal);
                    animalRepo.salvar(animal);
                    clienteRepo.salvar(dono); // atualiza cliente com o novo animal
                    System.out.println("Animal cadastrado!");
                    break;

                case 3:
                    System.out.println("\n--- Clientes ---");
                    for (Cliente c : clienteRepo.listar()) {
                        System.out.println(c);
                    }
                    break;

                case 4:
                    System.out.println("\n--- Animais ---");
                    for (AnimalDeEstimacao a : animalRepo.listar()) {
                        System.out.println(a);
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
}