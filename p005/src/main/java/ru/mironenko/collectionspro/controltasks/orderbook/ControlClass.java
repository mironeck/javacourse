package ru.mironenko.collectionspro.controltasks.orderbook;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import com.sun.corba.se.pept.transport.InboundConnectionCache;
import com.sun.org.apache.xpath.internal.operations.Or;
import sun.reflect.generics.tree.DoubleSignature;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by nikita on 26.04.2017.
 */
public class ControlClass {

    /**
     * Creates two sets: for BID and for ASK.
     * @param fileName
     */
    public void getBIDandASK(String fileName) {

        /**
         * Set for BID
         */
        Set<Order> setForBID = new TreeSet<>();

        /**
         * Set for ASK
         */
        Set<Order> setForASK = new TreeSet<>();

        /**
         * SAXBuilder for Orders
         */
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
            setForBID = checkForSamePrice(setForBID);
            //setForBID = sortSetFromHightoLow(setForBID);

            //mapForBID = checkForSamePrice(mapForBID);

            for(Order temp : setForBID) {
                System.out.println(temp.getPrice() + " " + temp.getVolume());
            }

            setForASK = checkForSamePrice(setForASK);
            //setForASK = sortSetFromLowHighto(setForASK);
            //mapForASK = checkForSamePrice(mapForASK);
            for(Order temp : setForASK) {
                System.out.println("           " + temp.getPrice() + " " + temp.getVolume());
            }

            setForBID.clear();
            setForASK.clear();
        }
    }

    private Set<Order> checkForSamePrice(Set<Order> orders) {

        int volume = 0;
        Set<Order> result = new TreeSet<Order>();
//        (new Comparator<Order>() {
//            @Override
//            public int compare(Order o1, Order o2) {
//                if(o1.getPrice() < o2.getPrice()){
//                    return -1;
//                } else if(o1.getPrice() > o2.getPrice()) {
//                    return 1;
//                }
//                return 0;
//            }
//        });
        Order newOrder = null;
        boolean flag = false;

        for(Order temp : orders) {
            volume = temp.getVolume();
            for (Order check : orders) {
                if ((temp.getId() != check.getId()) && (temp.getPrice() == check.getPrice())) {
                    volume += check.getVolume();

                    flag = true;
                }
            }
            if(flag) {
                newOrder = new Order(temp.getBook(), temp.getOperation(), temp.getPrice(), volume, temp.getId()+ temp.getId());
                result.add(newOrder);
                flag = false;
                newOrder = null;
            } else {
                result.add(temp);
            }
        }

        return result;

    }

    public static Set<Order> sortSetFromHightoLow(Set<Order> orders) {

       Set<Order> sortedSet = new TreeSet<Order>(new Comparator<Order>() {
           @Override
           public int compare(Order o1, Order o2) {
               if(o1.getPrice() < o2.getPrice()){
                   return -1;
               } else if(o1.getPrice() > o2.getPrice()) {
                   return 1;
               }
               return 0;
           }
       });

        sortedSet.addAll(orders);
        return sortedSet;
    }

    public static Set<Order> sortSetFromLowHighto(Set<Order> orders) {

        Set<Order> sortedSet = new TreeSet<Order>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if(o1.getPrice() < o2.getPrice()){
                    return 1;
                } else if(o1.getPrice() > o2.getPrice()) {
                    return -1;
                }
                return 0;
            }
        });

        sortedSet.addAll(orders);
        return sortedSet;
    }

    public static void main(String[] args) {

        new ControlClass().getBIDandASK("D:\\ordersTest.xml");
    }
}
