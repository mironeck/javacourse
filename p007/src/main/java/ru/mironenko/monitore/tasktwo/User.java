package ru.mironenko.monitore.tasktwo;

import java.util.Random;

/**
 * Created by nikita on 04.06.2017.
 */
public class User {

    /**
     * users's id
     */
    private final int id;
    /**
     * user's name
     */
    private final String name;
    /**
     * user's surname
     */
    private final String surname;
    /**
     * amount of user's money
     */
    private double amount;

    /**
     * User's city
     */
    private String city;

    /**
     * Constructor of User
     * @param name users's id
     * @param surname user's surname
     * @param amount amount of user's money
     */
    public User(String name, String surname, double amount, String city) {
        this.name =  name;
        this.surname = surname;
        this.id = (int)Math.random() * 100;
        this.amount = amount;
        this.city = city;
    }

    /**
     * Getter of user's id
     * @return user's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter of user's name
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter of user's surname
     * @return user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * getter of user's amount of money
     * @return user's amount of money
     */
    public double getAmount() {
        return amount;
    }

    /**
     * getter of user's city
     * @return user's city
     */
    public String getCity() {
        return city;
    }

    /**
     * setter of user's amount of money
     * @param amount set of user's amount of money
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * setter of user's city
     * @param city - new city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        return getSurname() != null ? getSurname().equals(user.getSurname()) : user.getSurname() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }
}
