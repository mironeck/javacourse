package ru.mironenko.collectionspro.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 28.03.2017.
 */
public class ArrayListContainerTest {

    @Test
    public void whenAddElementAndGetItThenReturnIt() {

        ArrayListContainer<Integer> arr = new ArrayListContainer<>();
        arr.add(1);

        assertThat(arr.get(0), is (1));
    }

    @Test
    public void whenAddMoreTenElementThenCapacityDoubled() {

        ArrayListContainer<Integer> arr = new ArrayListContainer<>();
        for (int i = 0; i <= 10; i++) {
            arr.add(i);
        }

        assertThat(arr.size(), is (20));
    }

    @Test
    public void whenIterateThenReturnElement() {

        ArrayListContainer<Integer> arr = new ArrayListContainer<>();
        Iterator it = arr.iterator();

        arr.add(1);
        arr.add(2);

        assertThat(it.next(), is (1));
        assertThat(it.next(), is (2));
        assertThat(it.hasNext(), is (false));

    }

}