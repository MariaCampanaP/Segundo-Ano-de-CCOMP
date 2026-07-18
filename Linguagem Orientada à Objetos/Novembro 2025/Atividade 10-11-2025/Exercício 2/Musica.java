import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Musica {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;
    
    @ManyToOne
    private Genero genero;
    
    public Musica(){}
    
    public Musica(String nome, Genero genero){
        this.nome = nome;
        this.genero = genero;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public Genero setGenero(){
        return genero;
    }
    
    public void setGenereo(Genero genero){
        this.genero = genero;
    }
}
