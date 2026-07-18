package repository;

import entity.Produto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoRepositoryJPA implements ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produto> listar() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void cadastrar(Produto produto) {
        em.persist(produto);
    }

    @Override
    @Transactional
    public void remover(Produto produto) {
        em.remove(em.merge(produto));
    }
    
    @Override 
    public List<Produto> buscarPorNome(String nome){
        return em.createQuery("SELECT p FROM Produto p WHERE p.nome LIKE :nome", Produto.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
    
    @Override
    public List<Produto> buscarPorCategoria(String categoria){
        return em.createQuery("SELECT p FROM Produto p WHERE p.categoria LIKE :categoria", Produto.class)
                .setParameter("categoria", "%" + categoria + "%")
                .getResultList();
    }

}
