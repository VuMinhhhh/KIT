package edu.kit.kastel;
/**
 * This enum contains all commands.
 * @author unweb
 */
public enum Command {
    /**
     * Quit command.
     */
    QUIT("quit"),
    /**
     * Add customer command.
     */
    ADD_CUSTOMER("add-customer"),
    /**
     * Add mailman command.
     */
    ADD_MAILMAN("add-mailman"),
    /**
     * Add agent command.
     */
    ADD_AGENT("add-agent"),
    /**
     * Authenticate command.
     */
    AUTHENTICATE("authenticate"),
    /**
     * Log out command.
     */
    LOGOUT("logout"),
    /**
     * Sending mail command.
     */
    SEND_MAIL("send-mail"),
    /**
     * Getting mails command.
     */
    GET_MAIL("get-mail"),
    /**
     * Listing mails command.
     */
    LIST_MAIL("list-mail"),
    /**
     * Listing prices command.
     */
    LIST_PRICE("list-price"),
    /**
     * Reseting PIN command.
     */
    RESET_PIN("reset-pin");

    /**
     * The command which operates the programm
     */
    private final String command;
    /**
     * Constructor.
     * @param command command
     */
    Command(String command) {
        this.command = command;
    }
    /**
     * Getter of the command.
     * @return command
     */
    public String getCommand() {
        return command;
    }
    /**
     * Converting string to command.
     * @param command command
     * @return command in enum when existed, else return null
     */
    public static Command stringToCommand(String command) {
        for (Command c : Command.values()) {
            if (c.getCommand().equals(command)) {
                return c;
            }
        }
        return null;
    }

    
}
