package ru.mironenko.collectionspro.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 02.04.2017.
 */
public class QueueContainerTest {

    @Test
    public void whenPushElementsShouldAddInTheTail(){

        QueueContainer<Integer> qc = new QueueContainer<>();
        qc.push(1);
        qc.push(2);
        qc.push(3);

        assertThat(qc.get(0), is(1));
    }

    @Test
    public void whenPullElementsShouldRemovedFromTheHead(){

        QueueContainer<Integer> qc = new QueueContainer<>();
        qc.push(1);
        qc.push(2);
        qc.push(3);

        Integer result = qc.pull();

        assertThat(result, is(1));
        assertThat(qc.get(0), is(2));
        assertThat(qc.getSize(), is(2));
    }

}