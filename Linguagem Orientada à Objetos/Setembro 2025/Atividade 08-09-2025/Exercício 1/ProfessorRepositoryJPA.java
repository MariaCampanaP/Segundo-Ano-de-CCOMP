package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Disciplina;
import model.Professor;

public class ProfessorRepositoryJPA implements ProfessorRepository {

    @Override
    public void cadastrar(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(professor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Professor buscarPorId(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Professor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Professor> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Professor p", Professor.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void adicionarDisciplina(Professor professor, Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Professor prof = em.find(Professor.class, professor.getId());
            
            em.getTransaction().begin();
            prof.adicionarDisciplina(disciplina);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
}
