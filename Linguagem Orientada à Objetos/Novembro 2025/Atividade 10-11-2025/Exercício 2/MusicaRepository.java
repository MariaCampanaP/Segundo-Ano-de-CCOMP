import java.util.List;

public interface MusicaRepository {
    void salvar(Musica musica);
    void remover(Musica musica);
    
    List<Musica> listar();
    
}
