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
            treeRoot = parent;
            result = true;
        }
            parent.getChildren().add(leaf);
            result = true;

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

        List<Leaf<E>> result = new ArrayList<>();
        if(treeRoot.element.equals(e)){
            result.add(treeRoot);
        }
        result = getResult((ArrayList<Leaf<E>>)result, leaf, e);
        return result;
    }

    /**
     * Saves leaves with element e into list
     * @param result
     * @param leaf
     * @param e
     * @return list
     */
    private ArrayList<Leaf<E>> getResult(ArrayList<Leaf<E>> result, Leaf<E> leaf, E e){

        for(Leaf<E> tmp : leaf.getChildren()){
            if(tmp.element.equals(e)) {
                result.add((Leaf<E>) tmp);
            }
            if(!leaf.getChildren().isEmpty()) {
                getResult(result, tmp, e);
            }
        }

        return result;
    }

    /**
     * Checks tree is balanced
     * @return boolean
     */
    public boolean isBalanced(Leaf<E> leaf){

        return checkBalance(leaf);
    }

    /**
     * Checks tree is balanced
     * @param root
     * @return boolean
     */
    private boolean checkBalance(Leaf<E> root) {

        int countOfLeaves = root.getChildren().size();
        if(countOfLeaves == 0) {
            return true; // tree is empty
        } else if (countOfLeaves != 2) {
            return false;
        } else {
            return checkBalance(root.getChildren().get(0)) && checkBalance(root.getChildren().get(1));
        }
    }

    /**
     * Class Leaf to create tree
     * @param <E>
     */
     public static class Leaf<E> implements Comparable<Leaf<E>>{

         E element;

        private final List<Leaf<E>> children = new ArrayList<>();


        public Leaf(E element) {
            this.element = element;
        }


        public List<Leaf<E>> getChildren() {
            return children;
        }


         @Override
         public int compareTo(Leaf<E> o) {
             return hashCode() - o.hashCode();
         }

         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (!(o instanceof Leaf)) return false;

             Leaf<?> leaf = (Leaf<?>) o;

             return element != null ? element.equals(leaf.element) : leaf.element == null;

         }

         @Override
         public int hashCode() {
             return element != null ? element.hashCode() : 0;
         }
     }

}
