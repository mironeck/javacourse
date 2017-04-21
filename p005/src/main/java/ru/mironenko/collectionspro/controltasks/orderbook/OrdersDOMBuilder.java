package ru.mironenko.collectionspro.controltasks.orderbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.mironenko.collectionspro.tree.SimpleBinarySearchTree;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by nikita on 20.04.2017.
 */
public class OrdersDOMBuilder {

    private Set<Order> orders;
    private DocumentBuilder docBuilder;

    public OrdersDOMBuilder() {
        this.orders = new TreeSet<Order>();
        //создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Set<Order> getOrders() {
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
            for(int i = 0; i < 20; i++) {
                Element orderElement = (Element) orderList.item(i);
                Order order = buildOrder(orderElement);
                orders.add(order);
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        } catch (SAXException e) {
            System.err.println("SAXException " + e);
        }


    }

    private Order buildOrder(Element orderElement) {

        Integer id = Integer.parseInt(getElementTextContent(orderElement, "id"));
        String operation = getElementTextContent(orderElement, "operation");
        Double price = Double.parseDouble(getElementTextContent(orderElement, "price"));
        Integer volume = Integer.parseInt(getElementTextContent(orderElement, "volume"));
        Order order = new Order(id, operation, price, volume);
        return order;
    }

    private static String getElementTextContent(Element element, String elementName) {

        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    public static void main(String[] args) {

        OrdersDOMBuilder domBuilder = new OrdersDOMBuilder();
        domBuilder.buildSetOrders("D:\\orders.xml");
        System.out.println(domBuilder.getOrders());
    }

}
