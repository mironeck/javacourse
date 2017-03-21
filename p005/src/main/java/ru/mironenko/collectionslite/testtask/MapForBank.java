package ru.mironenko.collectionslite.testtask;

import java.util.*;

/**
 * Created by nikita on 20.03.2017.
 */
//Реализовать коллекцию Map для банка
// Необходимо создать класс User с полями name, passport.
// Необходимо создать класс Account с полями value (кол-во денег), requisites (реквизиты счёта) - это банковский счёт.
// Реализовать коллекцию Map <User, List<Account>>, обозначающую что у каждого пользователя может быть список банковских счетов.
// Необходимо реализовать возможность перечислять деньги, как с одного счёта User на другой счёт того же User, так и на счёт другого User.
// В программе должны быть методы:
//public void addUser(User user) {} - добавление пользователя.
//public void deleteUser(User user) {} - удаление пользователя.
//public void addAccountToUser(User user, Account account) {} - добавить счёт пользователю.
//public void deleteAccountFromUser(User user, Account account) {} - удалить один счёт пользователя.
//public List<Accounts> getUserAccounts (User user) {} - получить список счетов для пользователя.
//public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount) -
// метод для перечисления денег с одного счёта на другой счёт:
//    если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.

public class MapForBank {

    private Map<User, List<Account>> map = new TreeMap<>();

    public Map<User, List<Account>> getMap() {
        return map;
    }

    /**
     * The method add user to map
     * @param user
     */
    public void addUser(User user) {

        this.map.put(user, new ArrayList<Account>());
    }

    /**
     * The method delete user from map
     * @param user
     */
    public void deleteUser(User user) {

        this.map.remove(user);
    }

    /**
     * The method add account to user
     * @param user
     * @param account
     */
    public void addAccountToUser(User user, Account account) {

        this.map.get(user).add(account);

    }

    /**
     * The method delete account from user
     * @param user
     * @param account
     */
    public void deleteAccountFromUser(User user, Account account) {

        this.map.get(user).remove(account);
    }

    /**
     * The method returns list of user's accounts
     * @param user
     * @return list of user's accounts
     */
    public List<Account> getUserAccounts (User user) {
        List<Account> result = new ArrayList<>();

        for (Account account : this.map.get(user)) {
            result.add(account);
        }

        return result;
    }

    /**
     * The method for transfer money from one account to another
     * @param srcUser
     * @param srcAccount
     * @param dstUser
     * @param dstAccount
     * @return boolean value - is operation worked or failed (not enough money or account is not found)
     */
    public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, int value) {

        boolean result;
        if(!this.map.get(srcUser).contains(srcAccount)) {
            result = false;
        } else if (this.map.get(srcUser).get(this.map.get(srcUser).indexOf(srcAccount)).getValue() < value) {
            result = false;
        } else {
            int indexOfSrcAccount = this.map.get(srcUser).indexOf(srcAccount);
            int indexOfDstAccount = this.map.get(dstUser).indexOf(dstAccount);

            int moneyOnSrcAccount = this.map.get(srcUser).get(indexOfSrcAccount).getValue();
            int moneyOnDstAccount = this.map.get(dstUser).get(indexOfDstAccount).getValue();

            this.map.get(srcUser).get(indexOfSrcAccount).setValue(moneyOnSrcAccount - value);
            this.map.get(dstUser).get(indexOfDstAccount).setValue(moneyOnDstAccount += value);
            result = true;
        }

        return result;
    }


    public static void main(String[] args) {

        MapForBank mfb = new MapForBank();

        User userOne = new User("1", "hb001");
        User userTwo = new User("2", "hb002");
        User userThree = new User("3", "hb003");

        mfb.addUser(userOne);
        mfb.addUser(userTwo);
        mfb.addUser(userThree);

        Account accountOne = new Account(1000, 123456);
        Account accountTwo = new Account(100, 1234567);
        Account accountThree = new Account(50, 12345678);
        Account accountFour = new Account(10, 123456789);
        Account accountFive = new Account(1, 123);

        mfb.addAccountToUser(userOne, accountOne);
        mfb.addAccountToUser(userOne, accountFour);
        mfb.addAccountToUser(userTwo, accountTwo);
        mfb.addAccountToUser(userThree, accountThree);

        System.out.println(mfb.transferMoney(userOne, accountOne, userThree, accountThree, 500));

        System.out.println(mfb.getMap().get(userOne).get(mfb.getMap().get(userOne).indexOf(accountOne)).getValue());
        System.out.println(mfb.getMap().get(userThree).get (mfb.getMap().get(userThree).indexOf(accountThree)).getValue());

        for(Account account : mfb.getUserAccounts(userOne)) {
            System.out.println(account.getRequisites() + " " + account.getValue());
        }

    }
}
