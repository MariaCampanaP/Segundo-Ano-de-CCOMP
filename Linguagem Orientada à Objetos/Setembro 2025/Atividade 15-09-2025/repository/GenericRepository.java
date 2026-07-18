package repository;

public interface GenericRepository<T> {

    void cadastrar(T entity);

    T buscarPorId(long id);

    void alterar(T entity);

    void remover(T entity);
}
