package repository;

import model.AnimalDeEstimacao;
import java.util.List;

public interface AnimalDeEstimacaoRepository {
    List<AnimalDeEstimacao> listar();
    boolean salvar(AnimalDeEstimacao animal);
}
