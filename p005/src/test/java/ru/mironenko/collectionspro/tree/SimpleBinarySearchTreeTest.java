package ru.mironenko.collectionspro.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 14.04.2017.
 */
public class SimpleBinarySearchTreeTest {

    @Test
    public void addElements(){

        SimpleBinarySearchTree<Integer> sbstree = new SimpleBinarySearchTree<>();
        boolean addOne = sbstree.addChild(1);
        sbstree.addChild(2);
        sbstree.addChild(2);
        sbstree.addChild(3);
        boolean addTwoOne = sbstree.addChild(4);
        boolean addTwoTwo = sbstree.addChild(5);

        assertThat(addOne, is (true));
        assertThat(addTwoOne, is (true));
        assertThat(addTwoTwo, is (true));
    }

    @Test
    public void whenGetNodesWithCertainElementThenShouldReturnListOfIt(){

        SimpleBinarySearchTree<String> sbstree = new SimpleBinarySearchTree<>();

        for(int i = 0; i < 1000; i++) {
            sbstree.addChild("One");
            sbstree.addChild("Two");
            sbstree.addChild("Three");
            sbstree.addChild("One");
            sbstree.addChild("Five");
            sbstree.addChild("One");
            sbstree.addChild("Seven");
            sbstree.addChild("Eight");
            sbstree.addChild("One");
            sbstree.addChild("Ten");
        }

        long start = System.currentTimeMillis();
        List<SimpleBinarySearchTree.Node<String>> result = sbstree.get("One");
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);


        //assertThat(result, is (checked));
    }

}