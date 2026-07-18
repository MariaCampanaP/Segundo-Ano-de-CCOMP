package repository;

import java.util.List;
import java.util.Set;
import model.Cliente;
import model.Funcionario;
import model.Item;
import model.Pedido;

public interface PedidoRepository extends GenericRepository<Pedido>{
    
    List<Pedido> listar();
    List<Pedido> buscarPorComprador(Cliente cliente);
    List<Pedido> buscarPorAtendente(Funcionario funcionario);
    Set<Item> listarItens(Pedido pedido);
    
}
