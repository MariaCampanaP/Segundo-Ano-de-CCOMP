package repository;

import model.Cliente;
import java.util.List;

public interface ClienteRepository {
    List<Cliente> listar();
    boolean salvar(Cliente cliente);
}
