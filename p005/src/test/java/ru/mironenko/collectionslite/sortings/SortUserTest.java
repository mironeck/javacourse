package ru.mironenko.collectionslite.sortings;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 20.03.2017.
 */
public class SortUserTest {
    @Test
    public void whenSortByAge() {

        List<User> list = new ArrayList<>();
        list.add(new User("b", 2));
        list.add(new User("c", 3));
        list.add(new User("e", 5));
        list.add(new User("a", 1));
        list.add(new User("d", 4));

        Set<User> result = new SortUser().sort(list);
        User[] array = result.toArray(new User[result.size()]);

        assertThat(array[0].getAge(), is(1));
        assertThat(array[1].getAge(), is(2));
        assertThat(array[2].getAge(), is(3));
        assertThat(array[3].getAge(), is(4));
        assertThat(array[4].getAge(), is(5));

    }

}