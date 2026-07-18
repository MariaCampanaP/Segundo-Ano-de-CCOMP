package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Funcionario;
import model.Pedido;

public abstract class FuncionarioRepositoryJPA extends GenericRepositoryJPA<Funcionario> implements FuncionarioRepository {

    public FuncionarioRepositoryJPA() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Funcionario> pesquisarCargo(String cargo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Funcionario f WHERE f.cargo = :cargo", Funcionario.class)
                     .setParameter("cargo", cargo)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidos(Funcionario atendente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.atendente = :atendente", Pedido.class)
                     .setParameter("atendente", atendente)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosCompletos(Funcionario atendente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p WHERE p.atendente = :atendente", Pedido.class)
                                     .setParameter("atendente", atendente)
                                     .getResultList();
            // força carregamento dos itens (caso sejam LAZY)
            for (Pedido pedido : pedidos) {
                pedido.getItens().size();
            }
            return pedidos;
        } finally {
            em.close();
        }
    }

    public List<Funcionario> listarOrdenadosPorNome() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Funcionario f ORDER BY f.nome ASC", Funcionario.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
