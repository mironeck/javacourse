package ru.mironenko.collectionspro.controltasks.orderbook;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.*;

/**
 * Created by nikita on 19.04.2017.
 */
public class OrdersHandler extends DefaultHandler{

    /**
     * Map to hold books. String key is value of book tag
     */
    private Map<String, Book> stringBookMap;

    /**
     * Current order
     */
    private Order current = null;

    /**
     * ID of order to delete
     */
    int orderIDToDelete;
    /**
     * Book of order to delete
     */
    String bookToDelete;
    /**
     * Order to delete
     */
    Order orderToDelete;

    /**
     * Constructor of OrdersHandler initialises stringBookMap
     */
    public OrdersHandler() {
        stringBookMap = new TreeMap<>();
    }

    /**
     * Getter of stringBookMap
     * @return stringBookMap
     */
    public Map<String, Book> getStringBookMap() {
        return stringBookMap;
    }


    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started");
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if("AddOrder".equals(localName)) {
            //parses line of xml document
            String book = attributes.getValue("book");
            String operation = attributes.getValue("operation");
            double price = Double.parseDouble(attributes.getValue("price"));
            int volume = Integer.parseInt(attributes.getValue("volume"));
            int orderID = Integer.parseInt(attributes.getValue("orderId"));
            //create new Order
            current = new Order(book, operation, price, volume, orderID);
        }

        if("DeleteOrder".equals(localName)) {
            bookToDelete = attributes.getValue("book");
            orderIDToDelete = Integer.parseInt(attributes.getValue("orderId"));
            //create new order that needed to delete
            orderToDelete = new Order(bookToDelete, orderIDToDelete);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       // System.out.print(new String(ch, start, length));
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if("AddOrder".equals(localName)) {

            Book book = stringBookMap.get(current.getBook());
            if (book == null) {
                book = new Book();
                stringBookMap.put(current.getBook(), book);
            }
            if(current.getOperation().equals("BUY")) {

                book.getBuy().add(current);
            }
            if(current.getOperation().equals("SELL")) {
                book.getSell().add(current);
            }
        }

        if("DeleteOrder".equals(localName)) {
            stringBookMap.get(bookToDelete).getBuy().remove(orderToDelete);
            stringBookMap.get(bookToDelete).getSell().remove(orderToDelete);
        }
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("\nParsing ended");
    }

}
