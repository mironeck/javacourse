package ru.mironenko.collectionspro.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 11.04.2017.
 */
//Создать простую структуру дерева. Методы addChild(Node, E), List<E> getChildren()

public class SimpleTree<E> {

    Node<E> treeRoot;

    public boolean addChild(Node<E> parent, E element){

        boolean result = false;
        Node<E> leaf = new Node<E>(element);
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
    public List<Node<E>> get(Node<E> leaf, E e){

        List<Node<E>> result = new ArrayList<>();
        if(treeRoot.element.equals(e)){
            result.add(treeRoot);
        }
        result = getResult((ArrayList<Node<E>>)result, leaf, e);
        return result;
    }

    /**
     * Saves leaves with element e into list
     * @param result
     * @param leaf
     * @param e
     * @return list
     */
    private ArrayList<Node<E>> getResult(ArrayList<Node<E>> result, Node<E> leaf, E e){

        for(Node<E> tmp : leaf.getChildren()){
            if(tmp.element.equals(e)) {
                result.add((Node<E>) tmp);
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
    public boolean isBalanced(Node<E> leaf){

        return checkBalance(leaf);
    }

    /**
     * Checks tree is balanced
     * @param root
     * @return boolean
     */
    private boolean checkBalance(Node<E> root) {

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
     * Class Node to create tree
     * @param <E>
     */
     public static class Node<E> implements Comparable<Node<E>>{

         E element;

        private final List<Node<E>> children = new ArrayList<>();


        public Node(E element) {
            this.element = element;
        }


        public List<Node<E>> getChildren() {
            return children;
        }


         @Override
         public int compareTo(Node<E> o) {
             return hashCode() - o.hashCode();
         }

         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (!(o instanceof SimpleTree.Node)) return false;

             Node<?> leaf = (Node<?>) o;

             return element != null ? element.equals(leaf.element) : leaf.element == null;

         }

         @Override
         public int hashCode() {
             return element != null ? element.hashCode() : 0;
         }
     }

}
