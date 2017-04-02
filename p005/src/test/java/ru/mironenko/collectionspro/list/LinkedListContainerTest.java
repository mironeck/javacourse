package ru.mironenko.collectionspro.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 02.04.2017.
 */
public class LinkedListContainerTest {
    @Test
    public void whenGetSizeThenShouldReturnSize() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.getSize(), is(3));
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void whenAddElementByIndexThenShouldReturnThisElement() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.addIndex(1, 5);

        assertThat(list.get(1), is(5));
    }

    @Test
    public void whenRemoveElementByIndexThenShouldReturnThisElement() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Integer resultOne = list.remove(0);
        Integer resultTwo = list.remove(1);

        assertThat(resultOne, is(1));
        assertThat(list.get(0), is(2));
        assertThat(resultTwo, is(3));
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void whenIterateThenReturnElement() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();

        Integer one = it.next();
        Integer two = it.next();
        boolean hasnextOne = it.hasNext();
        it.next();
        boolean hasnextTwo = it.hasNext();
        assertThat(one, is(1));
        assertThat(two, is(2));
        assertThat(hasnextOne, is(true));
        assertThat(hasnextTwo, is(false));

    }
}