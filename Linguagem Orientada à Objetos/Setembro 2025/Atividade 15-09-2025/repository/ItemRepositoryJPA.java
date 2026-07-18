package repository;

import model.Item;

public class ItemRepositoryJPA extends GenericRepositoryJPA<Item> implements ItemRepository {

    public ItemRepositoryJPA() {
        super(Item.class);
    }

}
