package ru.mironenko.collectionspro.tree;

import java.util.ArrayList;
import java.util.LinkedList;
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

    /**
     * search leaves with element e
     * @param leaf
     * @param e
     * @return list of elements
     */
    public List<Leaf<E>> get(Leaf<E> leaf, E e){

        List<Leaf<E>> result = new LinkedList<>();
        result = getResult((LinkedList<Leaf<E>>)result, leaf, e);
        return result;
    }

    /**
     * Saves leaves with element e into list
     * @param result
     * @param leaf
     * @param e
     * @return list
     */
    private List<Leaf<E>> getResult(LinkedList<Leaf<E>> result, Leaf<E> leaf, E e){

        for(Leaf<E> tmp : leaf.getChildren()){
            if(tmp.element.equals(e)) {
                result.add(tmp);
            }
            getResult(result, tmp, e);
        }

        return result;
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
