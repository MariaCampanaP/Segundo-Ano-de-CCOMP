package repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Item;

public class ItemRepositoryJPA extends GenericRepositoryJPA<Item> implements ItemRepository {

    public ItemRepositoryJPA() {
        super(Item.class);
    }

    @Override
    public List<Item> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
        } finally {
            em.close();
        }
    }

}
