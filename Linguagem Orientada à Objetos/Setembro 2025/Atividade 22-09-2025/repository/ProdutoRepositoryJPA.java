package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Produto;

public class ProdutoRepositoryJPA extends GenericRepositoryJPA<Produto> implements ProdutoRepository {

    public ProdutoRepositoryJPA() {
        super(Produto.class);
    }

    @Override
    public List<Produto> produtosComItens() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT i.produto FROM Item i", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> produtosDistintosComItens() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT DISTINCT i.produto FROM Item i", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> listarMaisCaroParaMaisBarato() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p ORDER BY p.preco DESC", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public int precoMaisBarato(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("SELECT MIN(p.preco) FROM Produto p", Integer.class).getSingleResult();
        } finally {
            em.close();
        }
    }

}
