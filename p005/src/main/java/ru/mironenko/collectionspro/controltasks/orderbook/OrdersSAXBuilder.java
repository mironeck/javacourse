package ru.mironenko.collectionspro.controltasks.orderbook;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by nikita on 23.04.2017.
 */
public class OrdersSAXBuilder  {

    private OrdersHandler ordersHandler;
    private XMLReader reader;

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


    public Map<String, Book> buildMapOrders(String fileName) {

        try{
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.println("SAXException " + e);
        } catch (IOException e) {
            System.err.println("IOException" + e);
        }

        return ordersHandler.getStringBookMap();
    }


}
