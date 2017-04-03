package ru.mironenko.collectionspro.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 03.04.2017.
 */
public class LinkedListSetTest {

    @Test
    public void whenAddSameElementThenIncludeUniqueElement() {

        LinkedListSet<Integer> set = new LinkedListSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        Iterator<Integer> it = set.iterator();

        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }
}