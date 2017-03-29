package ru.mironenko.collectionspro.generic.store;

import ru.mironenko.collectionspro.generic.SimpleList;

/**
 * Created by nikita on 29.03.2017.
 */

/**
 * abstract class for userstore and rolestore
 */
public abstract class BaseStore implements Store{

    /**
     * array for storage items Users of Roles
     */
    private SimpleList<Base> list;
    /**
     * size of list
     */
    private int size;

    /**
     * constructor
     * @param size
     */
    public BaseStore(int size) {
        this.size = size;
        this.list = new SimpleList<>(size);
    }

    /**
     * getter for list
     * @return list
     */
    public SimpleList<Base> getList() {
        return this.list;
    }

    /**
     * setter for list
     * @param list
     */
    public void setList(SimpleList<Base> list) {
        this.list = list;
    }

    /**
     * add item to storage
     * @param item
     */
    public void add(Base item){
        this.list.add(item);
    }

    /**
     * update item in storage
     * @param id - id of item to update
     * @param newItem - new item
     */
    public void update(String id, Base newItem) {

        Base item;
        for(int i = 0; i < this.size; i++) {
            item = this.list.get(i);
            if((item != null) && (id.equals(item.getID()))){
                this.list.update(i, newItem);
            }
        }
    }

    /**
     * delete item by id
     * @param id - id of item to delete
     */
    public void delete(String id) {

        Base item;
        for(int i = 0; i < this.size; i++) {
            item = this.list.get(i);
            if((item != null) && (id.equals(item.getID()))){
                this.list.delete(i);
                }
        }
    }

    /**
     * find item by id and return it
     * @param id - id of item to find
     * @return item
     */
    public Base get(String id) {
        Base result = null;
        Base item;
        for(int i = 0; i < this.size; i++) {
            item = this.list.get(i);
            if((item != null) && (id.equals(item.getID()))){
                result = item;
            }
        }
        return result;
    }
}
