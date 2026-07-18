import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("generoBean")
@RequestScoped
public class GeneroBean {

    private Genero genero = new Genero();

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void salvar() {
        // aqui vai o código para salvar o gênero
        System.out.println("Gênero salvo: " + genero.getNome());
    }
}
