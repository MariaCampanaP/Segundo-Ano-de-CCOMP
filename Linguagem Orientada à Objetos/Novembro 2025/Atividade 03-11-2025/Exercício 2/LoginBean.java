import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class LoginBean {
    
    private String login;
    private String senha;
    
    @Inject
    private UsuarioRepository usuarioRepo;
    
    public String autenticar(){
        Usuario u = usuarioRepo.buscarPorLogin(login);
        if(u != null && u.getSenha().equals(senha)){
            HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sessao.setAttribute("usuario", u);
            return "resultadoLogin.xhtml?faces-redirect=true";
        }
        return "login.xhtml?erro=true";
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
}
