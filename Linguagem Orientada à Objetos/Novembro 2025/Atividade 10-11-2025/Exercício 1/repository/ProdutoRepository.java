package repository;

import entity.Produto;
import java.util.List;

public interface ProdutoRepository {

    public List<Produto> listar();

    public void cadastrar(Produto produto);

    public void remover(Produto produto);
    
    public List<Produto> buscarPorNome(String nome);
    
    public List<Produto> buscarPorCategoria (String categoria);

}
