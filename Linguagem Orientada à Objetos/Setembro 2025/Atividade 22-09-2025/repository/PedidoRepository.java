package repository;

import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Item;
import model.Pedido;

public interface PedidoRepository extends GenericRepository<Pedido> {

    public List<Pedido> listar();

    public List<Pedido> pesquisarPorComprador(Cliente comprador);

    public List<Pedido> pesquisarPorAtendente(Funcionario atendente);

    public List<Item> listarItens(Pedido pedido);

    public long numeroDeItens(Pedido pedido);

    public int valorProdutoMaisCaro(Pedido pedido);

    public double precoMedio(Pedido pedido);
    
    public List<Pedido> listarOrdenadosPorCliente();
}
