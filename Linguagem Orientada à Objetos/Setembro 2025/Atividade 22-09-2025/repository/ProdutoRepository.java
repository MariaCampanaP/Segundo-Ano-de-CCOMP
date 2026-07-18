package repository;

import java.util.List;
import model.Produto;

public interface ProdutoRepository extends GenericRepository<Produto> {
    
    public List<Produto> listarMaisCaroParaMaisBarato();
    
    public List<Produto> produtosComItens();
    
    public List<Produto> produtosDistintosComItens();
    
    public int precoMaisBarato();
}
