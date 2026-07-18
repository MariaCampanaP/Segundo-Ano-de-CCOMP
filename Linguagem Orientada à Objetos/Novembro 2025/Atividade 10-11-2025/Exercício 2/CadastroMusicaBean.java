import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CadastroMusicaBean {
    
    private Musica musica = new Musica();
    
    @Inject
    private MusicaRepository musicaRepository;
    
    @Inject
    private GeneroRepository generoRepository;
    
    private String mensagem;
    
    public String salvar(){
        try{
            musicaRepository.salvar(musica);
            mensagem = "Música cadastrada com sucesso!";
            musica = new Musica();
        }catch (Exception e) {
            mensagem = "Erro ao cadastrar música: " + e.getMessage();
        }
        
        return "";
        
    }
    
    public List<Genero> getGeneros(){
        return generoRepository.listar();
    }
    
    public Musica getMusica(){
        return musica;
    }
    
    public void setMusica(Musica musica){
        this.musica = musica;
    }
    
    public String getMensagem(){
        return mensagem;
    }
    
}
