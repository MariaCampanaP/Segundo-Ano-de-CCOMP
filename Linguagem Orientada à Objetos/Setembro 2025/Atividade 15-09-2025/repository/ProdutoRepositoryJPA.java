package repository;

import model.Produto;

public class ProdutoRepositoryJPA extends GenericRepositoryJPA<Produto> implements ProdutoRepository {

    public ProdutoRepositoryJPA() {
        super(Produto.class);
    }

}
