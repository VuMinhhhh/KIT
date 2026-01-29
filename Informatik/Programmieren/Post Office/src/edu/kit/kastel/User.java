package edu.kit.kastel;

/**
 * This class modelises the user in general.
 * @author unweb
 */
abstract class User {
    /** 
     * Password.
     */
    protected String password;
    /**
     * First name.
     */
    private final String firstName;
    /**
     * Last name.
     */
    private final String lastName;
    /**
     * Constructor of the class.
     * @param firstName first name
     * @param lastName last name
     * @param password password
     */
    User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    /** 
     * Checks if the user has given the correct password.
     * @param password password
     * @return true if the given password is correct, otherwise false
     */
    public boolean login(String password) {
        return this.password.equals(password);
    }
    /**
     * A getter for first name.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    /** 
     * A getter for last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * A getter for the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
