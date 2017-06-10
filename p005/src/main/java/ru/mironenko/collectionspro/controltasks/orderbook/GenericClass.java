package ru.mironenko.collectionspro.controltasks.orderbook;

/**
 * Created by nikita on 04.05.2017.
 */
public class GenericClass<T> {

    private final Class<T> type;

    public GenericClass(Class<T> type) {
        this.type = type;
    }

    public Class<T> getMyType() {
        return this.type;
    }

    public static void main(String[] args) {

        GenericClass<?> test = new GenericClass<String>(String.class);
        System.out.println(test.getMyType());
    }
}