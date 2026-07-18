import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessaoBean implements Serializable {
    private Usuario usuario;
    
    public Usuario getUsuraio(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public boolean isLogado(){
        return usuario != null;
    }
    
    public boolean isAdministrador(){
        return isLogado() && Boolean.TRUE.equals(usuario.getAdministrador());
    }
}
