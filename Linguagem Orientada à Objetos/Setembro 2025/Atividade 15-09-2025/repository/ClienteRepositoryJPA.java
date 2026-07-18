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
    public List<Cliente> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } finally {
            em.close();
        }

    }

    @Override
    public Cliente buscarPorEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class).setParameter("email", email).getSingleResult();
        } finally {
            em.close();
        }

    }

    @Override
    public List<Pedido> listarPedidos(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.comprador = :cliente",
                    Pedido.class).setParameter("cliente", cliente).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosCompletos(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p WHERE p.comprador = :cliente",
                    Pedido.class).setParameter("cliente", cliente).getResultList();
            for (Pedido pedido : pedidos) {
                pedido.getItens().size();
            }
            return pedidos;
        } finally {
            em.close();
        }
    }

}
