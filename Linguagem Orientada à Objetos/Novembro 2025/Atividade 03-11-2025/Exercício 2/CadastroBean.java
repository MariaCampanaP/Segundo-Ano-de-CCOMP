import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CadastroBean {
    
    @Inject
    private UsuarioRepository usuarioRepo;
    
    private Usuario usuario = new Usuario();
    
    public String cadastrar(){
        usuarioRepo.salvar(usuario);
        return "login.xhtml?faces-redirect=true";
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
}
