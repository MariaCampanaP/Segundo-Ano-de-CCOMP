import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MusicaRepositoryJPA implements MusicaRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public void salvar(Musica musica){
        em.persist(musica);
    }
    
    @Override
    @Transactional
    public void remover(Musica musica){
        em.remove(em.merge(musica));
    }
    
    @Override
    public List<Musica> listar(){
        return em.createQuery("SELECT m FROM Musica m", Musica.class).getResultList();
    }
}
