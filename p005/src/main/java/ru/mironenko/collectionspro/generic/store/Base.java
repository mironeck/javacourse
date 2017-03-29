package ru.mironenko.collectionspro.generic.store;

/**
 * Created by nikita on 28.03.2017.
 */

/**
 * abstract class for models: user and role
 */
public abstract class Base {

    /**
     * field id
     */
    private String id;

    /**
     * getter for id
     * @return id
     */
    public String getID() {
        return this.id;
    }

    /**
     * getter for id
     * @param id
     */
    public void setID(String id){
        this.id = id;
    }
}
