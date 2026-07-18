package beans;

import entity.Produto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutosBean implements Serializable {
    @Inject
    private ProdutoRepository produtoRepository;

    private List<Produto> produtos;

    private String nomeBusca;
    private String categoriaBusca;
    
    @PostConstruct
    public void inicializar() {
        produtos = produtoRepository.listar();
    }
    public String listarProdutos() {
        produtos = produtoRepository.listar();
        return "";
    }
    public String removerProduto(Produto produto) {
        produtoRepository.remover(produto);
        produtos = produtoRepository.listar();
        return null;
    }
    
    public String buscarPorNome(){
        produtos = produtoRepository.buscarPorNome(nomeBusca);
        return "";
    }
    
    public String buscarPorCategoria(){
        produtos = produtoRepository.buscarPorCategoria(categoriaBusca);
        return "";
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public String getNomeBusca(){
        return nomeBusca;
    }
    
    public void setNomeBusca(String nomeBuscar){
        this.nomeBusca = nomeBusca;
    }
    
    public String getCategoriaBusca(){
        return categoriaBusca;
    }
    
    public void setCategoriaBusca(String categoriaBusca){
        this.categoriaBusca = categoriaBusca;
    }

}
