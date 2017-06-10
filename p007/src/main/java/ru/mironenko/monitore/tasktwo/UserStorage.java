package ru.mironenko.monitore.tasktwo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikita on 04.06.2017.
 */

// Создать класс хранилища пользователей UserStorage.
// В него можно добавлять новых пользователей. Редактировать. Удалять и читать.
// У пользователя есть поле amount. Нуждо добавить метод перечисления денег с одного пользователя на другой.
// Код должен быть ThreadSafe.


public class UserStorage {

    /**
     * Storage for User
     */
    private Map<Integer, User> userMap = new HashMap<>();

    /**
     * getter of user's storage
     * @return user's storage
     */
    public Map<Integer, User> getUserMap() {
        return this.userMap;
    }

    /**
     * Adds User to userMap with string id
     * @param user - new user to put in storage
     */
    public synchronized void addUser(final User user) {

        this.userMap.put(user.getId(), user);
    }


    /**
     * Gets user from userMap and edits the city
     * @param user - user to edit
     * @param newsity - new city of user
     */
    public synchronized void editUsersCity(final User user, final String newsity) {

        this.userMap.get(user.getId()).setCity(newsity);
    }


    /**
     * Removes User from userMap
     * @param userToDelete
     */
    public synchronized void deleteUser(final User userToDelete) {

        this.userMap.remove(userToDelete.getId());
    }


    /**
     * Transfer money from srcUser to dstUser
     * @param srcUser - user from whom transfers money
     * @param dstUser - user to whom transfers money
     * @param value - amount of money to transfer
     */
    public synchronized boolean transferMoney(final User srcUser, final User dstUser, final double value) {

        boolean result = true;
        double moneyOnSrcUser = this.userMap.get(srcUser.getId()).getAmount();
        double moneyOnDstUser = this.userMap.get(srcUser.getId()).getAmount();

        if(moneyOnSrcUser < value) {
            result = false;
        } else {

            this.userMap.get(srcUser.getId()).setAmount(moneyOnSrcUser - value);
            this.userMap.get(dstUser.getId()).setAmount(moneyOnDstUser + value);
            result = true;
        }
        return result;
    }

}
