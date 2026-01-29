package edu.kit.kastel;

/**
 * This class modelises the mailman.
 * @author unweb
 */
public class Mailman extends User {
    /**
     * Personal identification number of the mailman.
     */
    String personalNumber;

    /**
     * Constructor of the class.
     * @param firstName first name
     * @param lastName last name
     * @param personalNumber personal id number
     * @param password password
     */
    public Mailman(String firstName, String lastName, String personalNumber, String password) {
        super(firstName, lastName, password);
        this.personalNumber = personalNumber;
    }

    /**
     * A getter for the personal number of the mailman.
     * @return personal number
     */
    public String getPersonalNumber() {
        return personalNumber;
    }
}
