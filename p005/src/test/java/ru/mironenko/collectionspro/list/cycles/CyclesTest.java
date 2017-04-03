package ru.mironenko.collectionspro.list.cycles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 03.04.2017.
 */
public class CyclesTest {

    @Test
    public void whenHasCyclesShouldReturnTrue() {
        Cycles cycles = new Cycles();
        Node first = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = three;
        three.next = four;
        four.next = first;

       assertThat(cycles.hasCycle(first), is(true));
    }

    @Test
    public void whenHasntCyclesShouldReturnFaulse() {
        Cycles cycles = new Cycles();
        Node first = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = three;
        three.next = four;
        four.next = null;

        assertThat(cycles.hasCycle(first), is(false));
    }

}