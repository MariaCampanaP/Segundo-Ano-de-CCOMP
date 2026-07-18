package repository;

import java.util.List;
import model.Funcionario;
import model.Pedido;

public interface FuncionarioRepository extends GenericRepository<Funcionario> {
    
    List<Funcionario> listar();
    List<Funcionario> buscarPorCargo(String cargo);
    List<Pedido> listarPedidos(Funcionario funcionario);
    List<Pedido> listarPedidosCompletos(Funcionario funcionario);
}
