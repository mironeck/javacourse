package ru.mironenko.collectionspro.controltasks.orderbook;

import java.util.*;

/**
 * Created by nikita on 26.04.2017.
 */
public class ControlClass {

    /**
     * Creates two sets: for BID and for ASK and prints it.
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
            Set<Order> setBID = checkForSamePrice(setForBID);
            Set<Order> sortedSetForBID = sortSetFromHightoLow(setBID);

            for(Order temp : sortedSetForBID) {
                System.out.println(temp.getPrice() + " " + temp.getVolume());
            }

            Set<Order> setASK = checkForSamePrice(setForASK);
            Set<Order> sortedSetForASK = sortSetFromLowHighto(setASK);

            for(Order temp : sortedSetForASK) {
                System.out.println("           " + temp.getPrice() + " " + temp.getVolume());
            }

            setForBID.clear();
            setForASK.clear();
        }
    }

    /**
     * Finds orders with same price, sum volumes, creates new order with this volume and puts in new Set.
     * @param orders
     * @return
     */
    private Set<Order> checkForSamePrice(Set<Order> orders) {

        int volume = 0;
        Set<Order> result = new TreeSet<Order>();
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

    /**
     * Sorts set of orders from higher price to lower price
     * @param orders
     * @return new sorted set
     */
    public static Set<Order> sortSetFromHightoLow(Set<Order> orders) {

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

    /**
     * Sorts set of orders from lower price to higher price
     * @param orders
     * @return new sorted set
     */
    public static Set<Order> sortSetFromLowHighto(Set<Order> orders) {

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

    public static void main(String[] args) {

        new ControlClass().getBIDandASK("D:\\orders.xml");
    }
}
