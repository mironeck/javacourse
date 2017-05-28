package ru.mironenko.jmm;

/**
 * Created by nikita on 27.05.2017.
 */
public class MySharedObject {

    //static variable pointing to instance of MySharedObject

    public static final MySharedObject sharedInstance = new MySharedObject();

    // member variables pointing to object on the heap
    public Integer object2 = new Integer(0);

}
