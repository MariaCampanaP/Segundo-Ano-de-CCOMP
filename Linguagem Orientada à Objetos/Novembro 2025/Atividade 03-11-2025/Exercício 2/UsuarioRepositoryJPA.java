import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UsuarioRepositoryJPA implements UsuarioRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void salvar(Usuario usuario){
        em.persist(usuario);
    }
    
    @Override
    public Usuario buscarPorLogin(String login){
        try{
            return em.find(Usuario.class, login);
        }catch (Exception e){
            return null;
        }
    }
    
}
