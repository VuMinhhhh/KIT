package edu.kit.kastel;

import java.util.HashMap;
import java.util.Map;

/**
 * This class covers all functions needed for this programm.
 * @author unweb
 */

public final class PostOffice {
    /**
     * A programm instance to operate the programm.
     */
    private static final PostOffice POST_OFFICE = new PostOffice();
    /**
     * A map to store all customers.
     */
    private final Map<String, Customer> customers = new HashMap<>();
    /**
     * A map to store all mailmans.
     */
    private final Map<String, Mailman> mailmans = new HashMap<>();
    /** 
     * A map to store all agents.
     */
    private final Map<String, Agent> agents = new HashMap<>();
    /**
     * A map to store all users in general.
     */
    private final Map<String, User> users = new HashMap<>();
    /**
     * A variable to give out the current authenticated user.
     */
    private User currentUser;
    /**
     * A private constructor.
     */
    private PostOffice() {

    }
    /**
     * A getter for PostOffice instance.
     * @return the PostOffice instance
     */ 
    public static PostOffice getInstance() {
        return POST_OFFICE;
    }
    /**
     * Check if the command and the parameter format are correct.
     * @param commands is the command along with its parameters
     * @param numberOfCommands is the number of commands or parameters
     * @throws Validity when the number of parameters doesn't match with the number of parameters needed
     */
    void commandValidator(String[] commands, int numberOfCommands) throws Validity {
        if (commands.length != numberOfCommands) {
            throw new Validity(Error.INCORRECT_INPUT_FORMAT.getMessage()); 
        }
    }
    /**
     * Add a customer to the system.
     * @param firstName first name
     * @param lastName last name
     * @param username username
     * @param password password
     * @param idNumber identification number
     * @throws Validity when an user has already logged in, or when the informations format is incorrect
     */
    void addCustomer(String firstName, String lastName, String username, String password, String idNumber) throws Validity {
        if (currentUser != null) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        validator(firstName, "[^;\\n\\r]+", Error.INVALID_FIRSTNAME.getMessage());
        validator(lastName, "[^;\\n\\r]+", Error.INVALID_LASTNAME.getMessage());
        validator(username, "[a-zA-Z0-9]{4,9}", Error.INVALID_USERNAME.getMessage());
        validator(password, "[^;\\n\\r]{4,9}", Error.INVALID_PASSWORD.getMessage());
        validator(idNumber, "[^,\\n\\r]{9}", Error.INVALID_IDNUMBER.getMessage());
        if (username.equals(idNumber)) {
            throw new Validity(Error.USERNAME_MATCH_IDNUMBER.getMessage());
        }
        Customer newCustomer = new Customer(firstName, lastName, username, password, idNumber);
        if (users.containsKey(username)) {
            throw new Validity(Error.EXISTED_USER.getMessage());
        }
        for (Customer cs : customers.values()) {
            if (cs.getIdNumber().equals(idNumber)) {
                throw new Validity(Error.EXISTED_IDNUMBER.getMessage());
            }
        } 
        customers.put(username, newCustomer);
        users.put(username, newCustomer);
        System.out.println("OK");
    }
    /**
     * Add a mailman to the system.
     * @param firstName first name
     * @param lastName last name
     * @param personalNumber personal number
     * @param password password
     * @throws Validity when an user has already logged in, or when the informations format is incorrect
     */
    void addMailman(String firstName, String lastName, String personalNumber, String password) throws Validity {
        if (currentUser != null) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        validator(firstName, "[^;\\n\\r]+", Error.INVALID_FIRSTNAME.getMessage());
        validator(lastName, "[^;\\n\\r]+", Error.INVALID_LASTNAME.getMessage());
        validator(personalNumber, "[1-9][0-9]*", Error.INVALID_PERSONALNUMBER.getMessage());
        validator(password, "[^;\\n\\r]{4,9}", Error.INVALID_PASSWORD.getMessage());
        Mailman newMailman = new Mailman(firstName, lastName, personalNumber, password);
        if (users.containsKey(personalNumber)) {
            throw new Validity(Error.EXISTED_USER.getMessage());
        }
        mailmans.put(personalNumber, newMailman);
        users.put(personalNumber, newMailman);
        System.out.println("OK");
    }
    /**
     * Add an agent to the system.
     * @param firstName first name
     * @param lastName last name
     * @param personalNumber personal number
     * @param password password 
     * @throws Validity when an user has already logged in, or when the informations format is incorrect
     */
    void addAgent(String firstName, String lastName, String personalNumber, String password) throws Validity {
        if (currentUser != null) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        validator(firstName, "[^;\\n\\r]+", Error.INVALID_FIRSTNAME.getMessage());
        validator(lastName, "[^;\\n\\r]+", Error.INVALID_LASTNAME.getMessage());
        validator(personalNumber, "[1-9][0-9]*", Error.INVALID_PERSONALNUMBER.getMessage());
        validator(password, "[^;\\n\\r]{4,9}", Error.INVALID_PASSWORD.getMessage());
        Agent newAgent = new Agent(firstName, lastName, personalNumber, password);
        if (users.containsKey(personalNumber)) {
            throw new Validity(Error.EXISTED_USER.getMessage());
        }
        agents.put(personalNumber, newAgent);
        users.put(personalNumber, newAgent);
        System.out.println("OK");
    }
    /**
     * Checks whether the informations given has the correct format.
     * @param object is the information that needed to be checked
     * @param criteria is the criteria of this checking
     * @param message the given out message when an error occurs
     * @throws Validity when information has the incorrect format
     */
    void validator(String object, String criteria, String message) throws Validity {
        if (!object.matches(criteria)) {
            throw new Validity(message);
        }
    }
    /**
     * An user uses this function to log in.
     * @param username username
     * @param password password
     * @throws Validity if there is already an user in the system or the given information is incorrect
     */
    void authenticate(String username, String password) throws Validity {
        if (currentUser != null) {
            throw new Validity(Error.AKTIV_USER.getMessage());
        } 
        if (!users.containsKey(username)) {
            throw new Validity("Error, " + username + " and " + password + " is not correct!");
        } else {
            User user = users.get(username);
            if (!user.getPassword().equals(password)) {
                throw new Validity("Error, " + username + " and " + password + " is not correct!");
            } else {
                System.out.println("OK");
                currentUser = user;
            }
        }
    }
    /**
     * Log out.
     * @throws Validity when the user hasn't logged in yet 
     */
    void logout() throws Validity {
        if (currentUser == null) {
            throw new Validity(Error.INAKTIV_USER.getMessage()); 
        }
        currentUser = null;
        System.out.println("OK");
    }
    /**
     * A customer sends an email to another customer.
     * @param postalService the chosen postal service
     * @param receiver receiver
     * @throws Validity when the user is not a customer, or the information of the postal service of the receiver is incorrect
     */
    void sendMail(String postalService, String receiver) throws Validity {
        if (!(currentUser instanceof Customer)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        } 
        Customer currentCustomer = (Customer) currentUser;
        if (!customers.containsKey(receiver)) {
            throw new Validity(Error.NO_RECEIVER.getMessage());
        }
        for (PostalService ps : PostalService.values()) {
            if (ps.getPostalService().equals(postalService)) {
                String sender = currentCustomer.getUsername();
                currentCustomer.sendMail(sender, postalService, receiver);
                Customer receivingCustomer = customers.get(receiver);
                receivingCustomer.receiveMail(sender, postalService, receiver);
                System.out.println("OK");
                return;
            }
        }
        throw new Validity(Error.NO_POSTAL_SERVICE.getMessage());
    }
    /**
     * A mailman sends an email from a customer to another.
     * @param sender sender
     * @param postalService the chosen postal service
     * @param receiver receiver
     * @throws Validity when the current user is not a mailman, or when the given information is incorrect
     */
    void sendMail(String postalService, String receiver, String sender) throws Validity {
        if (!(currentUser instanceof Mailman)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        } 
        if (!customers.containsKey(sender)) {
            throw new Validity(Error.NO_SENDER.getMessage());
        }
        if (!customers.containsKey(receiver)) {
            throw new Validity(Error.NO_RECEIVER.getMessage());
        }
        for (PostalService ps : PostalService.values()) {
            if (ps.getPostalService().equals(postalService)) {
                Customer sendingCustomer = customers.get(sender);
                sendingCustomer.sendMail(sender, postalService, receiver);
                Customer receivingCustomer = customers.get(receiver);
                receivingCustomer.receiveMail(sender, postalService, receiver);
                System.out.println("OK");
                return;
            }
        }
        throw new Validity(Error.NO_POSTAL_SERVICE.getMessage());
    }
    /**
     * A customer gets a mail.
     * @throws Validity when the current user is not a customer. or his inbox is empty
     */
    void getMail() throws Validity {
        if (!(currentUser instanceof Customer)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        } 
        Customer currentCustomer = (Customer) currentUser;
        if (currentCustomer.getInbox().isEmpty()) {
            throw new Validity(Error.EMPTY_INBOX.getMessage());
        } else {
            currentCustomer.getMail();
            System.out.println("OK");
        } 
    }
    /**
     * A mailman gets mail for his customer.
     * @param username customer
     * @throws Validity when the current user is not a mailman, or when the inbox of the customer is empty
     */
    void getMail(String username) throws Validity {
        if (!(currentUser instanceof Mailman)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        } 
        if (!customers.containsKey(username)) {
            throw new Validity(Error.NO_CUSTOMER.getMessage());
        }
        Customer receivingCustomer = customers.get(username);
        if (receivingCustomer.getInbox().isEmpty()) {
            throw new Validity(Error.EMPTY_INBOX.getMessage());
        } else {
            receivingCustomer.getMail();
            System.out.println("OK");
        } 
    }
    /**
     * Gives out a list of cost of postal services of the customer.
     * @throws Validity when the current user is not a customer
     */
    void listMail() throws Validity {
        if (!(currentUser instanceof Customer)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        Customer currentCustomer = (Customer) currentUser;
        if (currentCustomer.getTotalOrders().isEmpty()) {
            System.out.println("OK");
            return;
        }
        for (PostalService ps : PostalService.values()) {
            Map<String, Integer> receivedOrders = currentCustomer.getReceivedOrders();
            String postalService = ps.getPostalService();
            if (receivedOrders.containsKey(postalService)) {
                int numberOfOrders = receivedOrders.get(postalService);
                System.out.println(postalService + ";" + numberOfOrders);
            }
        }
    }
    /**
     * A mailman or an agent gives out a list of cost of postal service of a customer.
     * @param username customer
     * @throws Validity when the current user is neither a mailman nor an agent
     */
    void listMail(String username) throws Validity {
        if (!(currentUser instanceof Mailman || currentUser instanceof Agent)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        if (!customers.containsKey(username)) {
            throw new Validity(Error.NO_CUSTOMER.getMessage());
        }
        Customer currentCustomer = customers.get(username);
        if (currentCustomer.getTotalOrders().isEmpty()) {
            System.out.println("OK");
            return;
        }
        for (PostalService ps : PostalService.values()) {
            Map<String, Integer>  receivedOrders = currentCustomer.getReceivedOrders();
            String postalService = ps.getPostalService();
            if (receivedOrders.containsKey(postalService)) {
                int numberOfOrders = receivedOrders.get(postalService);
                System.out.println(postalService + ";" + numberOfOrders);
            }
        }
    }
    /**
     * Gives out a list of cost of postal services of the customer.
     * @throws Validity when the current user is not a customer
     */
    void listPrice() throws Validity {
        if (!(currentUser instanceof Customer)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        Customer currentCustomer = (Customer) currentUser;
        if (currentCustomer.getTotalOrders().isEmpty()) {
            System.out.println("OK");
            return;
        }
        for (PostalService ps : PostalService.values()) {
            Map<String, Integer> totalOrders = currentCustomer.getTotalOrders();
            String postalService = ps.getPostalService();
            if (totalOrders.containsKey(postalService)) {
                int numberOfOrders = totalOrders.get(postalService);
                double totalPrice = numberOfOrders * ps.getPrice();
                System.out.printf(java.util.Locale.US, "%s;%d;%.2f%n", postalService, numberOfOrders, totalPrice);
            }
        }
    }
    /**
     * A mailman or an agent gives out a list of cost of postal service of a customer.
     * @param username customer
     * @throws Validity when the current user is neither a mailman nor an agent
     */
    void listPrice(String username) throws Validity {
        if (!(currentUser instanceof Mailman || currentUser instanceof Agent)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        if (!customers.containsKey(username)) {
            throw new Validity(Error.NO_CUSTOMER.getMessage());
        }
        Customer currentCustomer = customers.get(username);
        if (currentCustomer.getTotalOrders().isEmpty()) {
            System.out.println("OK");
            return;
        }
        for (PostalService ps : PostalService.values()) {
            Map<String, Integer>  totalOrders = currentCustomer.getTotalOrders();
            String postalService = ps.getPostalService();
            if (totalOrders.containsKey(postalService)) {
                int numberOfOrders = totalOrders.get(postalService);
                double totalPrice = numberOfOrders * ps.getPrice();
                System.out.printf(java.util.Locale.US, "%s;%d;%.2f%n", postalService, numberOfOrders, totalPrice);
            }
        }
    }
    /**
     * Reset a customer's password.
     * @param username customer
     * @param idNumber identification number
     * @param password new password
     * @throws Validity when the current user is not an agent or the given information ist incorrect or has the incorrect format
     */
    void resetPin(String username, String idNumber, String password) throws Validity {
        if (!(currentUser instanceof Agent)) {
            throw new Validity(Error.UNAUTHORISED.getMessage());
        }
        if (!customers.containsKey(username)) {
            throw new Validity(Error.NO_CUSTOMER.getMessage());
        } 
        Customer currentCustomer = customers.get(username);
        if (!currentCustomer.getIdNumber().equals(idNumber)) {
            throw new Validity(Error.INCORRECT_IDNUMBER.getMessage());
        }
        validator(password, "[^;\\n\\r]{4,9}", Error.INVALID_PASSWORD.getMessage());
        currentCustomer.setPassword(password);
        System.out.println("OK");
    }
}