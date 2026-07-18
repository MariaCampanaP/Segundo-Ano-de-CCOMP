package repository;

import java.util.List;
import model.Item;

public interface ItemRepository extends GenericRepository<Item>{
    
    public List<Item> listar();
}
