package repository;

import java.util.List;
import model.Mensagem;

public interface MensagemRepository {
    Mensagem buscaPorCodigo(int codigo);

    List<Mensagem> listar();

    boolean salvar(Mensagem mensagem);

    boolean removerPorCodigo(int codigo);
}
