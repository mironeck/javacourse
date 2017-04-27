package ru.mironenko.collectionspro.controltasks.orderbook;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import com.sun.org.apache.xpath.internal.operations.Or;
import sun.reflect.generics.tree.DoubleSignature;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by nikita on 26.04.2017.
 */
public class ControlClass {

    public void getBIDandASK(String fileName) {

        Set<Order> setForBID = new TreeSet<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getPrice() < o2.getPrice()){
                    return 1;
                } else if (o1.getPrice() > o2.getPrice()) {
                    return -1;
                }
                return 0;
            }
        });

        Set<Order> setForASK = new TreeSet<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getPrice() < o2.getPrice()){
                    return -1;
                } else if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                }
                return 0;
            }
        });


        OrdersSAXBuilder ordersSAXBuilder = new OrdersSAXBuilder();
        Map<String, ArrayList<Order>> mapOrders = ordersSAXBuilder.buildMapOrders(fileName);

        for(String tmp : mapOrders.keySet()) {

            for(Order order : mapOrders.get(tmp)) {

                if("BUY".equals(order.getOperation())) {
                    setForBID.add(order);
                }

                if("SELL".equals(order.getOperation())) {
                    setForASK.add(order);
                }
            }

            System.out.println("BUY " +  " PRICE " + "  SELL " + "for book = " + tmp);

            for(Order temp : setForBID) {
                System.out.println(temp.getPrice() + " " + temp.getVolume());
            }

            for(Order temp : setForASK) {
                System.out.println("           " + temp.getPrice() + " " + temp.getVolume());
            }

        }



    }

    public static void main(String[] args) {

        new ControlClass().getBIDandASK("D:\\ordersTest.xml");
    }
}
