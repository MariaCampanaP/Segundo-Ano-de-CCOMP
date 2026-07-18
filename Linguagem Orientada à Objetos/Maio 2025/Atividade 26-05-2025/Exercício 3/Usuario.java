package id.usuario.exception;

public class Usuario {

    private String login;

    private String senha;
    
    private boolean erroLogin = false;
    private boolean erroSenha = false;

    //Modifique esse método para lançar a exceção correta caso ocorra um erro
    public Usuario(String login, String senha) throws UsuarioInvalidoException {
        
        
        if(login.length() >= 8 && login.length() <= 32){
        this.login = login;
        }else{
            erroLogin = true;
        }
        
        if(senha.length() >= 8 && senha.length() <= 32){
        this.senha = senha;
        }else{
            erroSenha = true;
        }

        throw new UsuarioInvalidoException(this, erroLogin, erroSenha);
    }

    public String getLogin() {
        return login;
    }
    //Modifique esse método para lançar a exceção correta caso ocorra um erro
    public void setLogin(String login) throws UsuarioInvalidoException {

        
        if(login.length() >= 8 && login.length() <= 32){
        this.login = login;
        }else{
            erroLogin = true;
        }
        throw new UsuarioInvalidoException(this, erroLogin, erroSenha);
    }

    public String getSenha() {
        return senha;
    }
    //Modifique esse método para lançar a exceção correta caso ocorra um erro
    public void setSenha(String senha) throws UsuarioInvalidoException {

        
        if(senha.length() >= 8 && senha.length() <= 32){
        this.senha = senha;
        }else{
            erroSenha = true;
        }
        throw new UsuarioInvalidoException(this, erroLogin, erroSenha);
    }
    
}
