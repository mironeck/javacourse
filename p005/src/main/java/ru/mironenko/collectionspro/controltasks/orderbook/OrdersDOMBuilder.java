package ru.mironenko.collectionspro.controltasks.orderbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Created by nikita on 20.04.2017.
 */
public class OrdersDOMBuilder {

    private Map<String, List<Order>> orders;
    private List<Order> bookOneList;
    private List<Order> bookTwoList;
    private List<Order> bookThreeList;

    private DocumentBuilder docBuilder;

    public OrdersDOMBuilder() {
        this.orders = new TreeMap<>();
        this.bookOneList = new ArrayList<>();
        this.bookTwoList = new ArrayList<>();
        this.bookThreeList = new ArrayList<>();

        //создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<Order>> getOrders() {
        return orders;
    }

    public void buildSetOrders(String fileName) {

        Document doc = null;
        try {

            //парсинг XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            //получение списка дочерних элементов <order>
            NodeList orderList = root.getElementsByTagName("AddOrder");
            for(int i = 0; i < orderList.getLength(); i++) {
                Element orderElement = (Element) orderList.item(i);
                Order order = buildOrder(orderElement);
                if(order.getBook().equals("book-1")){
                    bookOneList.add(order);
                } else if(order.getBook().equals("book-2")) {
                    bookTwoList.add(order);
                } else if(order.getBook().equals("book-3")) {
                    bookThreeList.add(order);
                }
            }

            orders.put("book-1", bookOneList);
            orders.put("book-2", bookTwoList);
            orders.put("book-3", bookThreeList);

        } catch (IOException e) {
            System.err.println("IOException " + e);
        } catch (SAXException e) {
            System.err.println("SAXException " + e);
        }


    }

    private Order buildOrder(Element orderElement) {

        String bookName = getElementTextContent(orderElement, "book");
        Integer id = Integer.parseInt(getElementTextContent(orderElement, "orderId"));
        String operation = getElementTextContent(orderElement, "operation");
        Double price = Double.parseDouble(getElementTextContent(orderElement, "price"));
        Integer volume = Integer.parseInt(getElementTextContent(orderElement, "volume"));
        Order order = new Order(bookName, id, operation, price, volume);
        return order;
    }

    private static String getElementTextContent(Element element, String attributeName) {

        String text = element.getAttribute(attributeName);
        return text;
    }

    public static void main(String[] args) {

        OrdersDOMBuilder domBuilder = new OrdersDOMBuilder();
        domBuilder.buildSetOrders("D:\\ordersTest.xml");

        for(Order tmp : domBuilder.getOrders().get("book-1")) {
            System.out.println(tmp.getBook() + " " + tmp.getId() + " " + tmp.getOperation() + " " + tmp.getPrice() + " " + tmp.getVolume());
        }

        for(Order tmp : domBuilder.getOrders().get("book-2")) {
            System.out.println(tmp.getBook() + " " + tmp.getId() + " " + tmp.getOperation() + " " + tmp.getPrice() + " " + tmp.getVolume());
        }

        for(Order tmp : domBuilder.getOrders().get("book-3")) {
            System.out.println(tmp.getBook() + " " + tmp.getId() + " " + tmp.getOperation() + " " + tmp.getPrice() + " " + tmp.getVolume());
        }
    }


}