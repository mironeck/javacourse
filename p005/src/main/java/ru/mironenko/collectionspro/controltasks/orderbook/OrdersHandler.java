package ru.mironenko.collectionspro.controltasks.orderbook;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nikita on 19.04.2017.
 */
public class OrdersHandler extends DefaultHandler{

    private Map<String, List<Order>> orders;
    private List<Order> bookOneList;
    private List<Order> bookTwoList;
    private List<Order> bookThreeList;

    private Order current = null;

    public OrdersHandler() {
        orders = new TreeMap<>();
        bookOneList = new ArrayList<>();
        bookTwoList = new ArrayList<>();
        bookThreeList = new ArrayList<>();
    }

    public Map<String, List<Order>> getOrders() {
        return orders;
    }


    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if("AddOrder".equals(localName)) {
            current = new Order();
            current.setBook(attributes.getValue("book"));
            current.setOperation(attributes.getValue("operation"));
            current.setPrice(Double.parseDouble(attributes.getValue("price")));
            current.setVolume(Integer.parseInt(attributes.getValue("volume")));
            current.setId(Integer.parseInt(attributes.getValue("orderId")));


        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if("AddOrder".equals(localName)) {
            if(current.getBook().equals("book-1")){
                bookOneList.add(current);
            } else if(current.getBook().equals("book-2")) {
                bookTwoList.add(current);
            } else if(current.getBook().equals("book-3")) {
                bookThreeList.add(current);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {

        orders.put("book-1", bookOneList);
        orders.put("book-2", bookTwoList);
        orders.put("book-3", bookThreeList);
        System.out.println("\nParsing ended");
    }

}
