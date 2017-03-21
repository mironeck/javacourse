package ru.mironenko.collectionslite.testtask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 21.03.2017.
 */
public class MapForBankTest {

    @Test
    public void whenAddUser() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");

        mfb.addUser(userOne);

        assertThat(mfb.getMap().containsKey(userOne), is(true));
    }

    @Test
    public void whenDeleteUser() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");

        mfb.addUser(userOne);
        mfb.deleteUser(userOne);
        assertThat(mfb.getMap().containsKey(userOne), is(false));
    }

    @Test
    public void whenAddAccountToUserAccountAdds() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");
        Account accountOne = new Account(1000, 123456);

        mfb.addUser(userOne);
        mfb.addAccountToUser(userOne, accountOne);

        assertThat(mfb.getMap().get(userOne).size(), is (1));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");
        mfb.addUser(userOne);
        Account accountOne = new Account(1000, 123456);
        mfb.addAccountToUser(userOne, accountOne);
        mfb.deleteAccountFromUser(userOne, accountOne);

        assertThat(mfb.getMap().get(userOne).size(), is (0));
    }

    @Test
    public void whenGetUserAccounts() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");
        mfb.addUser(userOne);
        Account accountOne = new Account(1000, 123456);
        Account accountTwo = new Account(100, 1234567);
        mfb.addAccountToUser(userOne, accountOne);
        mfb.addAccountToUser(userOne, accountTwo);

        List<Account> result = mfb.getUserAccounts(userOne);

        List<Account> checked = new ArrayList<>();
        checked.add(accountOne);
        checked.add(accountTwo);

        assertThat(result, is (checked));

    }

    @Test
    public void whenTransferMoneySucceed() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");
        Account accountOne = new Account(1000, 123456);
        Account accountTwo = new Account(100, 1234567);

        mfb.addUser(userOne);
        mfb.addAccountToUser(userOne, accountOne);
        mfb.addAccountToUser(userOne, accountTwo);

        boolean result = mfb.transferMoney(userOne, accountOne, userOne, accountTwo, 500);

        int moneyOnAccountOne = mfb.getMap().get(userOne).get(mfb.getMap().get(userOne).indexOf(accountOne)).getValue();
        int moneyOnAccountTwo = mfb.getMap().get(userOne).get(mfb.getMap().get(userOne).indexOf(accountTwo)).getValue();
        assertThat(result, is (true));
        assertThat(moneyOnAccountOne, is (500));
        assertThat(moneyOnAccountTwo, is (600));
    }


    @Test
    public void whenTransferMoneyFailedBecauseCantFindAccount() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");
        Account accountOne = new Account(1000, 123456);
        Account accountTwo = new Account(100, 1234567);

        mfb.addUser(userOne);
        mfb.addAccountToUser(userOne, accountOne);
        boolean result = mfb.transferMoney(userOne, accountTwo, userOne, accountOne, 500);

        assertThat(result, is (false));
    }

    @Test
    public void whenTransferMoneyFailedBecauseNotEnoughMoney() {
        MapForBank mfb = new MapForBank();
        User userOne = new User("1", "hb001");
        Account accountOne = new Account(100, 123456);
        Account accountTwo = new Account(100, 1234567);

        mfb.addUser(userOne);
        mfb.addAccountToUser(userOne, accountOne);
        mfb.addAccountToUser(userOne, accountTwo);

        boolean result = mfb.transferMoney(userOne, accountOne, userOne, accountTwo, 500);

        assertThat(result, is (false));
    }
}