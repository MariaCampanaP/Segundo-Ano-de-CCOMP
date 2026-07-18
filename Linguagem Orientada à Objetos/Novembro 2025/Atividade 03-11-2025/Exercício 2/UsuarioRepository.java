public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Usuario buscarPorLogin(String login);
}
