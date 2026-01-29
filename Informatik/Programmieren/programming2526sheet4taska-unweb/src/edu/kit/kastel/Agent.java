package edu.kit.kastel;

/**
 * This class modelises agent.
 * @author unweb
 */
public class Agent extends User {
    /**
     * The personal login ID of the agent.
     */
    String personalNumber;

    /**
     * Constructor of class agent.
     * @param firstName first name
     * @param lastName last name
     * @param personalNumber personal number
     * @param password password
     */
    public Agent(String firstName, String lastName, String personalNumber, String password) {
        super(firstName, lastName, password);
        this.personalNumber = personalNumber;
    }

    /**
     * A getter of the personal number.
     * @return personal number
     */
    public String getPersonalNumber() {
        return personalNumber;
    }
}
