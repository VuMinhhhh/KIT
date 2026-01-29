package edu.kit.kastel;

/**
 * This class modelises mail.
 * @author unweb
 */
public class Mail {
    /**
     * Sender.
     */
    private final String sender;
    /**
     * The chosen postal service of the mail.
     */
    private final String postalService;
    /** 
     * Receiver.
     */
    private final String receiver;

    /**
     * Constructor of the class.
     * @param sender sender
     * @param postalService postal service
     * @param receiver receiver
     */
    public Mail(String sender, String postalService, String receiver) {
        this.sender = sender;
        this.postalService = postalService;
        this.receiver = receiver;
    }

    /**
     * A getter for the sender.
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * A getter for the postal service.
     * @return postal service
     */
    public String getPostalService() {
        return postalService;
    }

    /** 
     * A getter for the receiver.
     * @return receiver
     */
    public String getReceiver() {
        return receiver;
    }
}
