package ru.mironenko.collectionspro.controltasks.orderbook;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nikita on 23.04.2017.
 */
public class OrdersSAXBuilder  {

    private Map<String, ArrayList<Order>> orders;
    private OrdersHandler ordersHandler;
    private XMLReader reader;

    public Map<String, ArrayList<Order>> getOrders() {
        return orders;
    }


    public OrdersSAXBuilder() {
        //создание SAX-анализатора
        ordersHandler = new OrdersHandler();
        try{
            //создание объекта обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ordersHandler);
        } catch (SAXException e) {
            System.err.print(e);
        }
    }


    public Map<String, ArrayList<Order>> buildMapOrders(String fileName) {

        try{
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.println("SAXException " + e);
        } catch (IOException e) {
            System.err.println("IOException" + e);
        }

        return ordersHandler.getOrders();
    }


//    public static void main(String[] args) {
//
//        OrdersSAXBuilder ordersSAXBuilder = new OrdersSAXBuilder();
//        ordersSAXBuilder.buildMapOrders("D:\\orders.xml");
//
//    }

}
