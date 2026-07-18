package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import model.Cliente;
import model.Funcionario;
import model.Item;
import model.Pedido;

public class PedidoRepositoryJPA extends GenericRepositoryJPA<Pedido> implements PedidoRepository {

    public PedidoRepositoryJPA() {
        super(Pedido.class);
    }

    @Override
    public List<Pedido> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> buscarPorComprador(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.comprador = :cliente", Pedido.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> buscarPorAtendente(Funcionario funcionario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.atendente = :funcionario", Pedido.class)
                    .setParameter("funcionario", funcionario)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Set<Item> listarItens(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Pedido p = em.find(Pedido.class, pedido.getId());
            p.getItens().size(); // inicializa
            return p.getItens();
        } finally {
            em.close();
        }
    }
}
