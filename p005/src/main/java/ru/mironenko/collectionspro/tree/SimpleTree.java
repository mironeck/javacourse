package ru.mironenko.collectionspro.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 11.04.2017.
 */
//Создать простую структуру дерева. Методы addChild(Leaf, E), List<E> getChildren()

public class SimpleTree<E> {

    Leaf<E> treeRoot;



    public boolean addChild(Leaf<E> parent, E element){

        boolean result = false;
        Leaf<E> leaf = new Leaf<E>(element);
        if(treeRoot == null) {
            treeRoot = leaf;
            result = true;
        } else {
            parent.children.add(leaf);
            result = true;
        }
        return result;
    }

    public List<E> getChildren(){

        return (List<E>) treeRoot.getChildren();
    }


     static class Leaf<E> {
        E element;
        
        private final List<Leaf<E>> children = new ArrayList<>();


        public Leaf(E element) {
            this.element = element;
        }
         

        public List<Leaf<E>> getChildren() {
            return children;
        }

    }

}
