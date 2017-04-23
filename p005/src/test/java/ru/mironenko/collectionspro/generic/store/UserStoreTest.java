package ru.mironenko.collectionspro.generic.store;

import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 29.03.2017.
 */
public class UserStoreTest {

    @Test
    public void whenAddElementInStore() {
        UserStore userStore = new UserStore(5);
        User one = new User("1");
        User two = new User("2");

        userStore.add(one);
        userStore.add(two);
        userStore.delete("2");

        assertThat(userStore.get("1"), is(one));
        assertNull(userStore.getList().get(1));
    }


    @Test
    public void whenUpdateItemReturnNewItem() {
        UserStore userStore = new UserStore(5);
        User one = new User("1");
        User two = new User("2");
        User three = new User("3");

        userStore.add(one);
        userStore.add(two);
        userStore.update("2", three);

        assertThat(userStore.get("3"), is(three));
    }
}