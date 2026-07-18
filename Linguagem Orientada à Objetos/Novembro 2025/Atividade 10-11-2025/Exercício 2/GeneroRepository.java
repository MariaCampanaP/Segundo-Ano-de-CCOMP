import java.util.List;

public interface GeneroRepository {
    void salvar(Genero genero);
    void remover(Genero genero);
    
    List<Genero> listar();
    
}
