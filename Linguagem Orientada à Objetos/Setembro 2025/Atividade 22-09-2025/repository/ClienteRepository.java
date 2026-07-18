package repository;

import java.util.List;
import model.Cliente;
import model.Pedido;

public interface ClienteRepository extends GenericRepository<Cliente> {

    public List<Cliente> listar();

    public Cliente buscarPorEmail(String email);

    public List<Pedido> listarPedidos(Cliente cliente);
    
    public List<Pedido> listarPedidosCompletos(Cliente cliente);
    
    public long numeroDePedidos(Cliente cliente);

    public long numeroPedidosPorCliente(Cliente cliente);
}
