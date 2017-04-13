package ru.mironenko.collectionspro.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import ru.mironenko.collectionspro.tree.SimpleTree.Leaf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 12.04.2017.
 */
public class SimpleTreeTest {

    SimpleTree<Integer> simpleTree = new SimpleTree<>();
    SimpleTree.Leaf<Integer> root = new SimpleTree.Leaf<>(1);

    @Before
    public void init() {

    }


    @Test
    public void whenAddElement() {

        boolean addOne = simpleTree.addChild(root, 1);
        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 3);
        boolean addTwoOne = simpleTree.addChild(root.getChildren().get(0), 4);
        boolean addTwoTwo = simpleTree.addChild(root.getChildren().get(0), 5);

        assertThat(addOne, is (true));
        assertThat(addTwoOne, is (true));
        assertThat(addTwoTwo, is (true));
    }


    @Test
    public void whenGetElementWithCertainValueThenShouldReturnListOfLeaves(){

        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 3);
        simpleTree.addChild(root.getChildren().get(0), 3);
        simpleTree.addChild(root.getChildren().get(0), 2);
        simpleTree.addChild(root.getChildren().get(1), 2);

        List<Integer> result = new ArrayList<>();
        for(Leaf<Integer> tmp : simpleTree.get(root, 2)){
            result.add(tmp.element);
        }

        List<Integer> checked = new ArrayList<>();
        checked.add(2);
        checked.add(2);
        checked.add(2);
        checked.add(2);

        assertThat(result, is(checked));
    }

    @Test
    public void checksIsTreeIsBalancedThenShouldReturnTrue(){

        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 2);
        simpleTree.addChild(root.getChildren().get(0), 3);
        simpleTree.addChild(root.getChildren().get(0), 2);
        simpleTree.addChild(root.getChildren().get(1), 2);
        simpleTree.addChild(root.getChildren().get(1), 2);


        boolean result = simpleTree.isBalanced(root);

        assertThat(result, is (true));

    }

    @Test
    public void checksIsTreeIsBalancedThenShouldReturnFalse(){

        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 2);
        simpleTree.addChild(root, 2);
        simpleTree.addChild(root.getChildren().get(0), 3);
        simpleTree.addChild(root.getChildren().get(0), 2);

        boolean result = simpleTree.isBalanced(root);

        assertThat(result, is (false));

    }
}