package edu.kit.kastel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * This class modelises customer.
 * @author unweb
 */
public class Customer extends User {
    /**
     * Username of the customer.
     */
    private final String username;
    /**
     * Identification number of the customer.
     */
    private final String idNumber;
    /**
     * Mail inbox of the customer.
     */
    private final List<Mail> inbox = new ArrayList<>();
    /**
     * list of sent mails of the customer.
     */
    private final List<Mail> sent = new ArrayList<>();
    /**
     * A map that tracks the total number of orders of the customer.
     */
    private final Map<String, Integer> totalOrders = new HashMap<>();
    /**
     * A map that tracks the total number of received orders of the customer.
     */
    private final Map<String, Integer> receivedOrders = new HashMap<>();

    /**
     * Constructor of the class.
     * @param firstName first name
     * @param lastName last name
     * @param username username
     * @param password password
     * @param idNumber identification number
     */
    public Customer(String firstName, String lastName, String username, String password, String idNumber) {
        super(firstName, lastName, password);
        this.username = username;
        this.idNumber = idNumber;
    }

    /**
     * A setter for password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * A getter for username.
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * A getter for identification number.
     * @return identification number
     */
    public String getIdNumber() {
        return idNumber;
    }
    /**
     * Add a mail to the customer's inbox.
     * @param sender sender
     * @param postalService postal service
     * @param receiver receiver
     */
    public void receiveMail(String sender, String postalService, String receiver) {
        Mail receivedMail = new Mail(sender, postalService, receiver);
        receivedOrders.put(postalService, receivedOrders.getOrDefault(postalService, 0) + 1);
        inbox.add(receivedMail);        
    }

    /**
     * Send a mail.
     * @param sender sender
     * @param postalService postal service
     * @param receiver receiver
     */
    public void sendMail(String sender, String postalService, String receiver) {
        Mail sentMail = new Mail(sender, postalService, receiver);
        totalOrders.put(postalService, totalOrders.getOrDefault(postalService, 0) + 1);
        sent.add(sentMail);   
    }
    /**
     * A getter for customer's inbox.
     * @return inbox
     */
    public List<Mail> getInbox() {
        return inbox;
    }
    /**
     * A getter for customer's sent mails.
     * @return the sent mails
     */
    public List<Mail> getSent() {
        return sent;
    }
    /**
     * A getter for the customer's latest mail.
     */
    public void getMail() {
        inbox.clear();
    }
    /**
     * A getter for the customer's order tracking.
     * @return total orders
     */
    public Map<String, Integer> getTotalOrders() {
        return totalOrders;
    }
    /**
     * A getter for the customer's received orders.
     * @return total orders
     */
    public Map<String, Integer> getReceivedOrders() {
        return receivedOrders;
    }

    
}
