package ru.mironenko.collectionspro.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 13.04.2017.
 */
public class SimpleBinarySearchTree<E> {

    Node<E> root;

    /**
     * Constructor
     */
    public SimpleBinarySearchTree(){
        this.root = null;
    }

    /**
     * Inner class to create nodes
     * @param <E>
     */
    public static class Node<E> implements Comparable<Node<E>>{

        E element;
        Node<E> right;
        Node<E> left;
        private final List<Node<E>> children = new ArrayList<>();


        public Node(E e) {
            this.element = e;
            right = null;
            left = null;
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
            if(this == o) return true;
            if(! (o instanceof SimpleBinarySearchTree.Node)) return false;

            Node<?> node = (Node<?>) o;

            return element != null ? element.equals(node.element) : node.element == null;
        }

        @Override
        public int hashCode(){
           return element != null ? element.hashCode() : 0;
        }
    }

    /**
     * Adds nodes to binary tree. If value of the e less then value of the element in root(parent)
     * then add to left, if opposite - to right.
     * @param e
     * @return boolean
     */
    public boolean addChild(E e) {

        boolean result = false;

        Node<E> newNode = new Node<E>(e);
        if(root == null) {
            root = newNode;
            return true;
        }

        Node<E> current = root;
        Node<E> parent = null;
        while(true) {

            parent = current;
            if(e.hashCode() <= current.element.hashCode()) {
                current = current.left;
                if(current == null) {
                    parent.left = newNode;
                    return true;
                }
            } else {
                current = current.right;
                if(current == null) {
                    parent.right = newNode;
                    return true;
                }
            }

        }
    }

    /**
     * Binary searches nodes with e value
     * @param e
     * @return list of nodes
     */
    public List<Node<E>> get(E e){

        List<Node<E>> list = new ArrayList<>();

        Node<E> current = root;

        while(current != null) {

            if(current.element.equals(e)){
                list.add(current);
            }

            if(e.hashCode() <= current.element.hashCode()){
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return list;
    }

}
