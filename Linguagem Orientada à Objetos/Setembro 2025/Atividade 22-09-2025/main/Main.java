package main;

import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Item;
import model.Pedido;
import model.Produto;
import repository.ClienteRepository;
import repository.ClienteRepositoryJPA;
import repository.FuncionarioRepository;
import repository.FuncionarioRepositoryJPA;
import repository.ItemRepository;
import repository.ItemRepositoryJPA;
import repository.PedidoRepository;
import repository.PedidoRepositoryJPA;
import repository.ProdutoRepository;
import repository.ProdutoRepositoryJPA;

public class Main {

    private static ClienteRepository clienteRepository = new ClienteRepositoryJPA();
    private static FuncionarioRepository funcionarioRepository = new FuncionarioRepositoryJPA() {
        @Override
        public List<Funcionario> listarOrdenadorPorNome() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };
    private static ProdutoRepository produtoRepository = new ProdutoRepositoryJPA();
    private static PedidoRepository pedidoRepository = new PedidoRepositoryJPA();
    private static ItemRepository itemRepository = new ItemRepositoryJPA();

    public static void main(String[] args) {
        // popularBD(); // descomente se quiser popular o banco

        testeFuncionariosOrdenados();
        testeProdutoMaisBarato();
        testePedidosOrdenadosPorCliente();
        testeNumeroPedidosPorCliente();
    }

    private static void testeFuncionariosOrdenados() {
        System.out.println("\n--- Funcionarios em ordem alfabetica ---");
        List<Funcionario> funcionarios = funcionarioRepository.listarOrdenadosPorNome();
        for (Funcionario f : funcionarios) {
            System.out.println(f.getNome() + " - " + f.getCargo());
        }
    }

    private static void testeProdutoMaisBarato() {
        System.out.println("\n--- Produto mais barato ---");
        double preco = produtoRepository.precoMaisBarato();
        System.out.println("Preco mais barato encontrado: " + preco);
    }

    private static void testePedidosOrdenadosPorCliente() {
        System.out.println("\n--- Pedidos ordenados por cliente ---");
        List<Pedido> pedidos = pedidoRepository.listarOrdenadosPorCliente();
        for (Pedido p : pedidos) {
            System.out.println("Pedido " + p.getId() + " - Cliente: " + p.getComprador().getNome());
        }
    }

    private static void testeNumeroPedidosPorCliente() {
        System.out.println("\n--- Numero de pedidos por cliente ---");
        Cliente cliente = clienteRepository.buscarPorId(1); // supondo que cliente1 está no BD
        long qtd = clienteRepository.numeroPedidosPorCliente(cliente);
        System.out.println("Cliente " + cliente.getNome() + " fez " + qtd + " pedidos.");
    }


    private static void popularBD() {
        Cliente cliente1 = new Cliente("cliente1@email.com", "Alice");
        Cliente cliente2 = new Cliente("cliente2@email.com", "Bob");
        clienteRepository.cadastrar(cliente1);
        clienteRepository.cadastrar(cliente2);

        Funcionario func1 = new Funcionario("Caixa", "Carlos");
        Funcionario func2 = new Funcionario("Atendente", "Diana");
        funcionarioRepository.cadastrar(func1);
        funcionarioRepository.cadastrar(func2);

        Produto p1 = new Produto("Cabo HDMI", 100);
        Produto p2 = new Produto("Cabo VGA", 150);
        Produto p3 = new Produto("Cabo USB", 200);
        Produto p4 = new Produto("Mouse", 50);
        Produto p5 = new Produto("Monitor", 300);
        produtoRepository.cadastrar(p1);
        produtoRepository.cadastrar(p2);
        produtoRepository.cadastrar(p3);
        produtoRepository.cadastrar(p4);
        produtoRepository.cadastrar(p5);

        Item i1 = new Item(p1);
        Item i2 = new Item(p2);
        Item i3 = new Item(p3);
        Item i4 = new Item(p4);
        Item i5 = new Item(p5);
        Item i6 = new Item(p1);
        Item i7 = new Item(p2);
        Item i8 = new Item(p3);

        Pedido pedido1 = new Pedido(cliente1, func1);
        pedido1.adicionarItem(i1);
        pedido1.adicionarItem(i2);
        Pedido pedido2 = new Pedido(cliente1, func2);
        pedido2.adicionarItem(i3);
        pedido2.adicionarItem(i4);
        Pedido pedido3 = new Pedido(cliente2, func1);
        pedido3.adicionarItem(i5);
        pedido3.adicionarItem(i6);
        Pedido pedido4 = new Pedido(cliente2, func2);
        pedido4.adicionarItem(i7);
        pedido4.adicionarItem(i8);
        pedidoRepository.cadastrar(pedido1);
        pedidoRepository.cadastrar(pedido2);
        pedidoRepository.cadastrar(pedido3);
        pedidoRepository.cadastrar(pedido4);
    }
}
