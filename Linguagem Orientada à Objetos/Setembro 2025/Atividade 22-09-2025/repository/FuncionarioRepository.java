package repository;

import java.util.List;
import model.Funcionario;
import model.Pedido;

public interface FuncionarioRepository extends GenericRepository<Funcionario> {

    public List<Funcionario> listar();

    public List<Funcionario> pesquisarCargo(String cargo);

    public List<Pedido> listarPedidos(Funcionario atendente);

    public List<Pedido> listarPedidosCompletos(Funcionario atendente);
    
    public List<Funcionario> listarOrdenadorPorNome();

    public List<Funcionario> listarOrdenadosPorNome();
}
