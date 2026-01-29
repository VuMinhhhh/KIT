package edu.kit.kastel;
/**
 * This enum contains all error message.
 * @author unweb
 */
public enum Error {
    /**
     * Incorrect input format error.
     */
    INCORRECT_INPUT_FORMAT("Error, incorrect input format!"),
    /**
     * Unauthorised error.
     */
    UNAUTHORISED("Error, unauthorised to execute this command!"),
    /**
     * User name matches identification number error.
     */
    USERNAME_MATCH_IDNUMBER("Error, your username muss not be the same as your identification number!"),
    /**
     * The user is already existed error.
     */
    EXISTED_USER("Error, this user is already existed!"),
    /**
     * The identification number is already existed error.
     */
    EXISTED_IDNUMBER("Error, this identification number is already existed!"),
    /**
     * Invalid first name error.
     */
    INVALID_FIRSTNAME("Error, invalid first name!"),
    /**
     * Invalid last name error.
     */
    INVALID_LASTNAME("Error, invalid last name!"),
    /**
     * Invalid user name error.
     */
    INVALID_USERNAME("Error, invalid user name!"),
    /**
     * Invalid password error.
     */
    INVALID_PASSWORD("Error, invalid password!"),
    /**
     * Invalid indentification number error.
     */
    INVALID_IDNUMBER("Error, invalid identification number!"),
    /**
     * Invalid personal number.
     */
    INVALID_PERSONALNUMBER("Error, invalid personal number!"),
    /**
     * There is already an user active error.
     */
    AKTIV_USER("Error, there is already an user!"), 
    /**
     * There is no user active error.
     */
    INAKTIV_USER("Error, there is no active user at the moment!"),
    /**
     * The receive does not exist error.
     */
    NO_RECEIVER("Error, the receiver does not exist!"),
    /**
     * The postal service does not exist error.
     */
    NO_POSTAL_SERVICE("Error, there aren't such postal service!"),
    /**
     * The sender does not exist error.
     */
    NO_SENDER("Error, the sender does not exist!"),
    /**
     * The inbox is empty error.
     */
    EMPTY_INBOX("Error, the current inbox is empty!"),
    /**
     * The customer does not exist error.
     */
    NO_CUSTOMER("Error, the customer does not exist!"),
    /**
     * The identification number is incorrect.
     */
    INCORRECT_IDNUMBER("Error, the identification number is wrong!");
    /**
     * Error message.
     */
    private final String message;
    /**
     * Constructor.
     * @param message error message
     */
    Error(String message) {
        this.message = message;
    }
    /**
     * Getter of error message.
     * @return error message
     */
    public String getMessage() {
        return message;
    }
}
