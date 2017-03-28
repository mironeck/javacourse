package ru.mironenko.collectionspro.generic.store;

import ru.mironenko.collectionspro.generic.SimpleList;

/**
 * Created by nikita on 28.03.2017.
 */
public class RoleStore implements Store {

    SimpleList<Base> list;

    public RoleStore(int size) {
        this.list = new SimpleList<>(size);
    }

    public void add(Base item){
        list.add(item);
    }

    public void update(int position, Base newItem) {
        list.update(position, newItem);
    }

    public void delete(int position) {
        list.delete(position);
    }
}
