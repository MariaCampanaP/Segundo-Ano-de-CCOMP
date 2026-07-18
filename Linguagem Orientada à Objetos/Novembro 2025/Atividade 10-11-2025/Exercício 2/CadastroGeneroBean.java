import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CadastroGeneroBean {
    
    private Genero genero = new Genero();
    
    @Inject 
    private GeneroRepository generoRepository;
    
    private String mensagem;
    
    public String salvar(){
        try{
            generoRepository.salvar(genero);
            mensagem = "Gênero cadastrado com sucesso!";
            genero = new Genero();
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar gênero: " + e.getMessage();
        }
        
        return "";
        
    }
    
    public Genero getGenero(){
        return genero;
    }
    
    public void setGenero(Genero geneto){
        this.genero = genero;
    }
    
    public String getMensagem(){
        return mensagem;
    }
    
}
