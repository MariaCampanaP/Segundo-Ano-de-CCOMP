package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Item;
import model.Pedido;
import model.Produto;

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
    public List<Pedido> pesquisarPorComprador(Cliente comprador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.comprador = :comprador",
                    Pedido.class).setParameter("comprador", comprador).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> pesquisarPorAtendente(Funcionario atendente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.atendente = :atendente",
                    Pedido.class).setParameter("atendente", atendente).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Item> listarItens(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p.itens FROM Pedido p WHERE p = :pedido",
                    Item.class).setParameter("pedido", pedido).getResultList();
        } finally {
            em.close();
        }
    }
    
    public long numeroDeItens(Pedido pedido){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(i) FROM Item i WHERE i.pedido = :pedido",
                    Long.class).setParameter("pedido", pedido).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public int valorProdutoMaisCaro(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT MAX(i.produto.preco) FROM Item i WHERE i.pedido = :pedido",
                    Integer.class).setParameter("pedido", pedido).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public double precoMedio(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT AVG(i.produto.preco) FROM Item i WHERE i.pedido = :pedido",
                    Double.class).setParameter("pedido", pedido).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Pedido> listarOrdenadosPorCliente(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p ORDER BY p.comprador.id ASC", Pedido.class).getResultList();
        } finally {
            em.close();
        }
    }

}
