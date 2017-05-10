package ru.mironenko.collectionspro.controltasks.orderbook;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.*;

/**
 * Created by nikita on 19.04.2017.
 */
public class OrdersHandler extends DefaultHandler{

    private Map<String, ArrayList<Order>> orders;

    private Order current = null;

    int orderIDToDelete;
    String bookToDelete;
    Order orderToDelete;

    public OrdersHandler() {
        orders = new TreeMap<>();
    }

    public Map<String, ArrayList<Order>> getOrders() {
        return orders;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if("AddOrder".equals(localName)) {

            String book = attributes.getValue("book");
            String operation = attributes.getValue("operation");
            double price = Double.parseDouble(attributes.getValue("price"));
            int volume = Integer.parseInt(attributes.getValue("volume"));
            int orderID = Integer.parseInt(attributes.getValue("orderId"));

            current = new Order(book, operation, price, volume, orderID);
        }

        if("DeleteOrder".equals(localName)) {
            bookToDelete = attributes.getValue("book");
            orderIDToDelete = Integer.parseInt(attributes.getValue("orderId"));

            orderToDelete = new Order(bookToDelete, orderIDToDelete);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if("AddOrder".equals(localName)) {
            ArrayList<Order> list = orders.get(current.getBook());
            if(list == null) {
                list = new ArrayList<>();
                orders.put(current.getBook(), list);
            }
            list.add(current);
        }

        if("DeleteOrder".equals(localName)) {
            orders.get(bookToDelete).remove(orderToDelete);

        }
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("\nParsing ended");
    }

}
