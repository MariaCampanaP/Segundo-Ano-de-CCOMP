import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MusicasBean implements Serializable {
    
    @Inject
    private MusicaRepository musicaRepository;
    
    private List<Musica> musicas;
    
    @PostConstruct
    public void inicializar(){
        musicas = musicaRepository.listar();
    }
    
    public void remover(Musica musica){
        musicaRepository.remover(musica);
        musicas = musicaRepository.listar();
    }
    
    public List<Musica> getMusicas(){
        return musicas;
    }
}
