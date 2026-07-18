package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Funcionario;
import model.Pedido;

public class FuncionarioRepositoryJPA extends GenericRepositoryJPA<Funcionario> implements FuncionarioRepository {

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
    public List<Funcionario> buscarPorCargo(String cargo) {
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
    public List<Pedido> listarPedidos(Funcionario funcionario) {
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
    public List<Pedido> listarPedidosCompletos(Funcionario funcionario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p WHERE p.atendente = :funcionario", Pedido.class)
                    .setParameter("funcionario", funcionario)
                    .getResultList();

            for (Pedido pedido : pedidos) {
                pedido.getItens().size(); // força carregar os itens
            }
            return pedidos;
        } finally {
            em.close();
        }
    }
}
