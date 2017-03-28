package ru.mironenko.collectionspro.generic.store;

/**
 * Created by nikita on 28.03.2017.
 */
public class Role extends Base {


    @Override
    String getID() {
        return this.id;
    }

    @Override
    void setID(String id) {
        id = this.id;
    }
}
