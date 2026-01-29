package edu.kit.kastel;

/**
 * This class checks if the given information breaches any format regulations.
 * @author unweb
 */
public class Validity extends Exception {
    /**
     * Constructor of the class.
     * @param message is the error message to be printed out
     */
    public Validity(String message) {
        super(message);
    }
}
