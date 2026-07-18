package beans;

import entity.Produto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repository.ProdutoRepository;

@Named
@RequestScoped
public class CadastroBean {

    private Produto produto = new Produto();

    private String mensagem;

    @Inject
    private ProdutoRepository produtoRepository;

    public String cadastrar() {
        try {
            produtoRepository.cadastrar(produto);
            mensagem = "Produto cadastrado com sucesso!";
            produto = new Produto();
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar produto! \n" + e.getMessage();
        }
        return "";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getMensagem() {
        return mensagem;
    }

}
