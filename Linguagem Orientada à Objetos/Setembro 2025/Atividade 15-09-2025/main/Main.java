package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.Produto;
import model.Item;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/jpa.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Criando clientes
        Cliente alice = new Cliente("alice@email.com", "Alice");
        Cliente bob = new Cliente("bob@email.com", "Bob");
        em.persist(alice);
        em.persist(bob);

        // Criando funcionários
        Funcionario carlos = new Funcionario("Caixa", "Carlos");
        Funcionario diana = new Funcionario("Atendente", "Diana");
        em.persist(carlos);
        em.persist(diana);

        // Criando produtos
        Produto p1 = new Produto("Produto A", 100);
        Produto p2 = new Produto("Produto B", 150);
        Produto p3 = new Produto("Produto C", 200);
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        // Criando pedidos
        Pedido pedido1 = new Pedido(alice, carlos);
        Pedido pedido2 = new Pedido(alice, diana);
        Pedido pedido3 = new Pedido(bob, carlos);

        // Adicionando itens aos pedidos
        pedido1.adicionarItem(new Item(p1));
        pedido1.adicionarItem(new Item(p2));

        pedido2.adicionarItem(new Item(p2));
        pedido2.adicionarItem(new Item(p3));

        pedido3.adicionarItem(new Item(p1));
        pedido3.adicionarItem(new Item(p3));

        // Persistindo pedidos (itens são persistidos automaticamente por cascata)
        em.persist(pedido1);
        em.persist(pedido2);
        em.persist(pedido3);

        em.getTransaction().commit();

        // Consultando pedidos
        System.out.println("=== Todos os pedidos ===");
        for (Pedido p : em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList()) {
            System.out.println("Pedido " + p.getId() + " - Cliente: " + p.getComprador().getNome());
        }

        // Consultando pedidos de Alice
        System.out.println("=== Pedidos do cliente Alice ===");
        for (Pedido p : em.createQuery("SELECT p FROM Pedido p WHERE p.comprador = :cliente", Pedido.class)
                .setParameter("cliente", alice)
                .getResultList()) {
            System.out.println("Pedido " + p.getId());
        }

        em.close();
        emf.close();
    }
}
