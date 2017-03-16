package ru.mironenko.collectionslite.collectionsframework;

import java.util.*;

/**
 * Created by nikita on 16.03.2017.
 */

//Написать программу, которая замеряет время вставки в коллекцию большого количества случайных строк и удаления в коллекции первых n элементов для:
//        LinkedList
//        ArrayList
//        TreeSet
//
//        В классе должно быть 2 метода:
//public long add(Collection<String> collection, String line, int amount) {}
//public long delete(Collection<String> collection, String line, int amount) {}
//        По результатам тестов расставьте коллекции по местам и объясните результат.


public class TimeOfCollectionsWork {

    /**
     * Method adds to collection random strings
     * @param collection
     * @param amount
     * @return
     */
    public long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < amount; i++) {
            collection.add(generateString("0123456789", 10));
        }

        long spentTime = System.currentTimeMillis() - startTime;
        return spentTime;
    }

    /**
     * Method remove strings from collection by iterator
     * @param collection
     * @param amount
     * @return
     */
    public long remove(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();

        int n = 0;
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext() && n < amount) {
            iterator.next();
            iterator.remove();
            n++;
        }
        long spentTime = System.currentTimeMillis() - startTime;
        return spentTime;
    }

    /**
     * Method create random string
     * @param characters
     * @param length
     * @return string
     */
    public static String generateString(String characters, int length) {
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }


    public static void main(String[] args) {

        Collection<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Collection<String> treeSet = new TreeSet<>();

        TimeOfCollectionsWork time = new TimeOfCollectionsWork();

        long addTimeLinkedList = time.add(linkedList, 100000);
        long removeTimeLinkedList = time.remove(linkedList, 100000);

        System.out.println("LinkedList " + addTimeLinkedList + " " + removeTimeLinkedList);

        long addTimeArrayList = time.add(arrayList, 100000);
        for (int i = 0; i < 10; i++) {
            System.out.println(arrayList.get(i));
        }
        long removeTimeArrayList = time.remove(arrayList, 100000);

        System.out.println("ArrayList " + addTimeArrayList + " " + removeTimeArrayList);

        long addTimeTreeSet = time.add(treeSet, 100000);
        long removeTimeTreeSet = time.remove(treeSet, 100000);

        System.out.println("TreeSet " + addTimeTreeSet + " " + removeTimeTreeSet);

        checkAddWinner(addTimeArrayList, addTimeLinkedList, addTimeTreeSet);
        checkRemoveWinner(removeTimeArrayList, removeTimeLinkedList, removeTimeTreeSet);

    }

    private static void checkRemoveWinner(long removeTimeArrayList, long removeTimeLinkedList, long removeTimeTreeSet) {
        System.out.println("Удаление ");
        if ( (removeTimeLinkedList < removeTimeArrayList) && (removeTimeLinkedList < removeTimeTreeSet)) {
            System.out.println("1. LinkedList");
            if ( removeTimeArrayList < removeTimeTreeSet ) {
                System.out.println("2. ArrayList");
                System.out.println("3. TreeSet");
            } else {
                System.out.println("2. TreeSet");
                System.out.println("3. ArrayList");
            }
        } else if ( (removeTimeArrayList < removeTimeLinkedList) && (removeTimeArrayList < removeTimeTreeSet )) {
            System.out.println("1. ArrayList");
            if (removeTimeLinkedList < removeTimeTreeSet) {
                System.out.println("2. LinkedList");
                System.out.println("3. TreeSet");
            } else {
                System.out.println("2. TreeSet");
                System.out.println("3. LinkedList");
            }
        } else if ((removeTimeTreeSet < removeTimeArrayList) && (removeTimeTreeSet < removeTimeLinkedList)) {
            System.out.println("1. TreeSet");
            if (removeTimeArrayList < removeTimeLinkedList) {
                System.out.println("2. ArrayList");
                System.out.println("3. LinkedList");
            } else {
                System.out.println("2. LinkedList");
                System.out.println("3. ArrayList");
            }
        }
    }

    private static void checkAddWinner(long addTimeArrayList, long addTimeLinkedList, long addTimeTreeSet) {
        System.out.println("Добавление ");
        if ( (addTimeLinkedList < addTimeArrayList) && (addTimeLinkedList < addTimeTreeSet)) {
            System.out.println("1. LinkedList");
            if ( addTimeArrayList < addTimeTreeSet ) {
                System.out.println("2. ArrayList");
                System.out.println("3. TreeSet");
            } else {
                System.out.println("2. TreeSet");
                System.out.println("3. ArrayList");
            }
        } else if ( (addTimeArrayList < addTimeLinkedList) && (addTimeArrayList < addTimeTreeSet )) {
            System.out.println("1. ArrayList");
            if (addTimeLinkedList < addTimeTreeSet) {
                System.out.println("2. LinkedList");
                System.out.println("3. TreeSet");
            } else {
                System.out.println("2. TreeSet");
                System.out.println("3. LinkedList");
            }
        } else if ((addTimeTreeSet < addTimeArrayList) && (addTimeTreeSet < addTimeLinkedList)) {
            System.out.println("1. TreeSet");
            if (addTimeArrayList < addTimeLinkedList) {
                System.out.println("2. ArrayList");
                System.out.println("3. LinkedList");
            } else {
                System.out.println("2. LinkedList");
                System.out.println("3. ArrayList");
            }
        }
    }
}
