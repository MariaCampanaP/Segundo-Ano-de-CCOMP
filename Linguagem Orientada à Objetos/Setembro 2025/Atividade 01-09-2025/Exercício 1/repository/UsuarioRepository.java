package repository;

import java.util.List;
import model.Usuario;

public interface UsuarioRepository {

    public Usuario buscaPorCodigo(int codigo);
    
    public Usuario buscaPorLogin(String login);

    public List<Usuario> listar();

    public boolean salvar(Usuario user);

    public boolean removerPorCodigo(int codigo);
    
    public boolean removerPorCodigoEmCascata(int codigo);

}
