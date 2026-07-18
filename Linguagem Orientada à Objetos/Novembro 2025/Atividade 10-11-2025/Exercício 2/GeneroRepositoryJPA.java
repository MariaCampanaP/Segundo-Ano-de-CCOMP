import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GeneroRepositoryJPA implements GeneroRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public void salvar(Genero genero){
        em.persist(genero);
    }
    
    @Override
    @Transactional
    public void remover(Genero genero){
        em.remove(em.merge(genero));
    }
    
    @Override
    public List<Genero> listar(){
        return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
    }
    
}
