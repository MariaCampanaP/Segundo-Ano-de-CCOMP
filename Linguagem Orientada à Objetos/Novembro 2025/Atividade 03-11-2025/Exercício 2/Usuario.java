import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    private String login;
    private String senha;
    private Boolean administrador;
    
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
    
    public Boolean getAdministrador(){
        return administrador;
    }
    
    public void setAdministrador(Boolean administrador){
        this.administrador = administrador;
    }
}
