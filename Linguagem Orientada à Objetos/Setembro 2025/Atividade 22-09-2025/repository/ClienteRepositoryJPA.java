package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Cliente;
import model.Pedido;

public class ClienteRepositoryJPA extends GenericRepositoryJPA<Cliente> implements ClienteRepository {

    public ClienteRepositoryJPA() {
        super(Cliente.class);
    }

    @Override
    public List<Pedido> listarPedidos(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.comprador = :cliente", Pedido.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosCompletos(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Pedido> pedidos = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.comprador = :cliente", Pedido.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
            // Força carregamento dos itens
            for (Pedido pedido : pedidos) {
                pedido.getItens().size();
            }
            return pedidos;
        } finally {
            em.close();
        }
    }

    @Override
    public long numeroPedidosPorCliente(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT COUNT(p) FROM Pedido p WHERE p.comprador = :cliente", Long.class)
                    .setParameter("cliente", cliente)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long numeroDePedidos(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
