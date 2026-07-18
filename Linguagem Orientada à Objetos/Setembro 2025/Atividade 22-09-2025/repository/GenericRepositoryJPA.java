package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class GenericRepositoryJPA<T> implements GenericRepository<T> {

    private Class<T> classe;

    public GenericRepositoryJPA(Class<T> classe) {
        this.classe = classe;
    }

    @Override
    public void cadastrar(T objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Erro ao salvar um objeto do tipo " + classe);
        } finally {
            em.close();
        }

    }

    @Override
    public T buscarPorId(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(classe, id);
        } catch (PersistenceException e) {
            System.out.println("Erro ao buscar um objeto do tipo " + classe);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void alterar(T objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Erro ao alterar um objeto do tipo " + classe);
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(T objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.merge(objeto));
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Erro ao remover um objeto do tipo " + classe);
        } finally {
            em.close();
        }
    }

}
