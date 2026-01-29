package edu.kit.kastel;

import java.util.Scanner;

/**
 * This class operates the programm.
 * @author unweb
 */
final class CommandHandler {
    /**
     * Instance of command handler.
     */
    private static final CommandHandler COMMAND_INSTANCE = new CommandHandler();
    /**
     * Instance of post office.
     */
    private static final PostOffice COMMAND_HANDLER = PostOffice.getInstance();
    /** 
     * A boolean variable to controll the programm flow.
     */
    private boolean quit = false; 

    /**
     * Constructor of the class.
     */
    private CommandHandler() {

    }
    /**
     * The function that operates based on users'input.
     * @throws Validity when the input format is incorrect
     */
    public void operation() {
        Scanner input = new Scanner(System.in);
        while (!quit) {
            try {
                String[] commands = input.nextLine().split(" ");
                String[] parameters = (commands.length > 1) ? commands[1].split(";") : new String[0];
                commandHandler(commands, parameters);
            } catch (Validity e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }

    /**
     * Handles all the command of the user.
     * @param commands is a command along with its parameters
     * @param parameters a set of parameters of the command
     * @throws Validity when the input format is incorrect, or when the requirements of the function is not fulfilled
     */
    void commandHandler(String[] commands, String[] parameters) throws Validity {
        Command command = Command.stringToCommand(commands[0]);
        if (command == null) {
            throw new Validity(Error.INCORRECT_INPUT_FORMAT.getMessage());
        }
        switch (command) {
            case QUIT -> {
                COMMAND_HANDLER.commandValidator(commands, 1);
                quit = true;
            }
            case ADD_CUSTOMER -> {
                COMMAND_HANDLER.commandValidator(parameters, 5);
                COMMAND_HANDLER.addCustomer(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]);
            }
            case ADD_MAILMAN -> {
                COMMAND_HANDLER.commandValidator(parameters, 4);
                COMMAND_HANDLER.addMailman(parameters[0], parameters[1], parameters[2], parameters[3]);
            }
            case ADD_AGENT -> {
                COMMAND_HANDLER.commandValidator(parameters, 4);
                COMMAND_HANDLER.addAgent(parameters[0], parameters[1], parameters[2], parameters[3]);
            } 
            case AUTHENTICATE -> {
                COMMAND_HANDLER.commandValidator(parameters, 2);
                COMMAND_HANDLER.authenticate(parameters[0], parameters[1]);
            }
            case LOGOUT -> {
                COMMAND_HANDLER.commandValidator(commands, 1);
                COMMAND_HANDLER.logout();
            }
            case SEND_MAIL -> {
                if (parameters.length == 2) {
                    COMMAND_HANDLER.sendMail(parameters[0], parameters[1]);
                } else if (parameters.length == 3) {
                    COMMAND_HANDLER.sendMail(parameters[0], parameters[1], parameters[2]);
                } else {
                    System.out.println(Error.INCORRECT_INPUT_FORMAT.getMessage()); 
                }
            }  
            case GET_MAIL -> {
                compactGetMail(commands, parameters);
            }
            case LIST_MAIL -> {
                compactListMail(commands, parameters);
            }
            case LIST_PRICE -> {
                compactListPrice(commands, parameters);
            }
            case RESET_PIN -> {
                COMMAND_HANDLER.commandValidator(parameters, 3);
                COMMAND_HANDLER.resetPin(parameters[0], parameters[1], parameters[2]);
            }
            default -> {
                System.out.println(Error.INCORRECT_INPUT_FORMAT.getMessage());
            }
        }
    }
    /**
     * All functions relating get mail are compacted into one function
     * @param commands commands
     * @param parameters parameters
     * @throws Validity when the given information is incorrect or has the incorrect format
     */
    private void compactGetMail(String[] commands, String[] parameters) throws Validity {
        if (commands.length == 1) {
            COMMAND_HANDLER.getMail();
        } else {
            COMMAND_HANDLER.commandValidator(parameters, 1);
            COMMAND_HANDLER.getMail(parameters[0]);
        }
    }
    /**
     * All functions relating list mail are compacted into one function
     * @param commands commands
     * @param parameters parameters
     * @throws Validity when the given information is incorrect or has the incorrect format
     */
    private void compactListMail(String[] commands, String[] parameters) throws Validity {
        if (commands.length == 1) {
            COMMAND_HANDLER.listMail();
        } else {
            COMMAND_HANDLER.commandValidator(parameters, 1);
            COMMAND_HANDLER.listMail(parameters[0]);
        }
    }
    /**
     * All functions relating list price are compacted into one function
     * @param commands commands
     * @param parameters parameters
     * @throws Validity when the given information is incorrect or has the incorrect format
     */
    private void compactListPrice(String[] commands, String[] parameters) throws Validity {
        if (commands.length == 1) {
            COMMAND_HANDLER.listPrice();
        } else {
            COMMAND_HANDLER.commandValidator(parameters, 1);
            COMMAND_HANDLER.listPrice(parameters[0]);
        }
    }
    /**
     * A getter of instance of command handler.
     * @return the instance of command handler
     */
    public static CommandHandler getInstance() {
        return COMMAND_INSTANCE;
    }
}
