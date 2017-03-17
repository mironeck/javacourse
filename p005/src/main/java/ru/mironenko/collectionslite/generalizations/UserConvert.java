package ru.mironenko.collectionslite.generalizations;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nikita on 17.03.2017.
 */
public class UserConvert {

    /**
     * Method convert list of Users to HashMap with key equal id, value equal User
     * @param list
     * @return HashMap
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hashMap = new HashMap<>();

        for(User user : list) {
            hashMap.put(user.getId(), user);
        }

        return hashMap;
    }
}
