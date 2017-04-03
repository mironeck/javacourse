package ru.mironenko.collectionspro.list.cycles;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import ru.mironenko.collectionspro.list.cycles.Node;

/**
 * Created by nikita on 02.04.2017.
 */

/**
 * Class checks is list has cycles
 */
public class Cycles {
    /**
     * checks is list has cycles
     * @param first first node
     * @return boolean
     */
    public boolean hasCycle(Node first) {

        boolean result = false;

        Node firstNode = first;
        Node node = first.next;
        while (node != null || node != firstNode) {
            node = node.next;
            if(node == firstNode) {
                result = true;
                break;
            }
            if (node == null) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Node first = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = three;
        three.next = four;
        four.next = null;

        Cycles cycles = new Cycles();
        System.out.println(cycles.hasCycle(first));

    }
}
