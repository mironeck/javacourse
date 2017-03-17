package ru.mironenko.collectionslite.generalizations;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 17.03.2017.
 */
public class UserConvertTest {

    @Test
    public void whenGetListOfUsersAndConvertItToHashMap() {

        UserConvert userConvert = new UserConvert();
        User userOne = new User(1, "Kolya", "Minsk");
        User userTwo = new User(2, "Mike", "York");
        HashMap<Integer, User> checked = new HashMap<>();
        checked.put(1, userOne);
        checked.put(2, userTwo);

        List<User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);

        HashMap<Integer, User> result = userConvert.process(list);

        assertThat(checked, is(result));
    }



}