package repository;

import java.util.List;
import model.Disciplina;
import model.Professor;

public interface ProfessorRepository {

    public void cadastrar(Professor professor);

    public Professor buscarPorId(long id);

    public List<Professor> listarTodos();
    
    public void adicionarDisciplina(Professor professor, Disciplina disciplina);
}
