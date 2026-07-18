package repository;

import java.util.List;
import model.Mensagem;

public interface MensagemRepository {

    public Mensagem buscarPorCodigo(int codigo);

    public List<Mensagem> listar();

    public boolean salvar(Mensagem mensagem);

    public boolean removerPorCodigo(int codigo);
}
