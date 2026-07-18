package id.usuario.exception;

public class UsuarioInvalidoException extends Exception {

    private Usuario usuarioInvalido;

    private boolean loginInvalido;

    private boolean senhaInvalida;

    public UsuarioInvalidoException(Usuario usuarioInvalido, boolean loginInvalido, boolean senhaInvalida) {
        this.usuarioInvalido = usuarioInvalido;
        this.loginInvalido = loginInvalido;
        this.senhaInvalida = senhaInvalida;
    }

    //Modifique esse método para gerar uma mensagem que explica quais erros ocorreram
    @Override
    public String getMessage() {
        String retorno = new String();
        if(loginInvalido && senhaInvalida==false){
            retorno = "Login Invalido (contem menos de 8 ou mais de 32 caracteres)";
        }else if (senhaInvalida && loginInvalido==false){
            retorno = "Senha Invalida (contem menos de 8 ou mais de 32 caracteres)";
        }else if(loginInvalido && senhaInvalida){
            retorno = "Login e senha Invalidos (contem menos de 8 ou mais de 32 caracteres)";
        }
        
        return retorno;
    }

}
