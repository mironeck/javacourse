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
        Set<Order> setForBID = new HashSet<>(1000);

        /**
         * Set for ASK
         */
        Set<Order> setForASK = new HashSet<>(1000);

        Set<Order> buyResult = new TreeSet<>();
        Set<Order> sellResult = new TreeSet<>();

        /**
         * SAXBuilder for Orders
         */
        OrdersSAXBuilder ordersSAXBuilder = new OrdersSAXBuilder();
        //Map<String, ArrayList<Order>> mapOrders = ordersSAXBuilder.buildMapOrders(fileName);
        Map<String, Book> mapOrdersBook = ordersSAXBuilder.buildMapOrders(fileName);

        for(String tmp : mapOrdersBook.keySet()) {

            setForBID = mapOrdersBook.get(tmp).getBuy();
            setForASK = mapOrdersBook.get(tmp).getSell() ;

            System.out.println("BUY " +  " PRICE " + "  SELL " + "for book = " + tmp);

            Set<Order> setBID = checkForSamePrice(setForBID);
            Set<Order> setASK = checkForSamePrice(setForASK);

            buyResult = checkForBuySell(setBID, setASK)[0];
            sellResult = checkForBuySell(setBID, setASK)[1];

            Set<Order> sortedSetForBID = sortSetFromHightoLow(buyResult);
            Set<Order> sortedSetForASK = sortSetFromLowHighto(sellResult);

            for(Order temp : sortedSetForBID) {
                System.out.println(temp.getPrice() + " " + temp.getVolume());
            }


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
        Set<Order> result = new HashSet<Order>(1000);
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


    public Set<Order>[] checkForBuySell(Set<Order> buy, Set<Order> sell){

        Set<Order> buyResult = new TreeSet<>();
        Set<Order> sellResult = new TreeSet<>();
        Set<Order>[] result = new Set[2];

        boolean flag = false;

        for(Order buyTemp : buy) {

            for(Order sellTemp : sell) {

                if( !(buyTemp.getPrice() >= sellTemp.getPrice()) ) {
                    sellResult.add(sellTemp);
                    flag = true;
                }
            }
            if(flag) {
                buyResult.add(buyTemp);
                flag = false;
            }
        }

        result[0] = buyResult;
        result[1] = sellResult;
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

        new ControlClass().getBIDandASK("D:\\ordersTest2.xml");
    }
}
